package com.fmy.storage_bags.menu;
import com.fmy.storage_bags.Internet.ModNetwork;
import com.fmy.storage_bags.Internet.StorageActionPacket;
import com.fmy.storage_bags.Internet.StorageSyncPacket;
import com.fmy.storage_bags.item.StorageBag.Storage;
import com.fmy.storage_bags.item.StorageBag.StorageBag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author 宛
 * @version 1.0
 */
public class StorageBagMenu extends AbstractContainerMenu {

    // 玩家背包槽位范围（储物袋没有自己的槽位）
    private static final int INV_SLOT_START = 0;
    private static final int INV_SLOT_END = 27;
    private static final int USE_ROW_SLOT_START = 27;
    private static final int USE_ROW_SLOT_END = 36;
    private final ItemStack bagStack;

    private final ContainerLevelAccess access;
    private final Level level;
    private final Storage storage;

    /** 当前选中的按钮索引，同步到客户端 */
    private final DataSlot selectedItemIndex = DataSlot.standalone();

    /** 主手打开的储物袋对应的快捷栏槽位，防止物品被拿走 */
    private int lockedSlot = -1;

    /** 数据变化回调，Screen 用来刷新 */
    Runnable slotUpdateListener = () -> {};

    // ================= 服务端构造 =================
    public StorageBagMenu(int id, Inventory inv, ContainerLevelAccess access,
                          Storage storage, ItemStack bagStack) {
        super(ModMenuTypes.STORAGE_BAG_MENU.get(), id);
        this.access = access;
        this.level = inv.player.level();
        this.storage = storage;
        this.bagStack = bagStack;

        this.lockedSlot = USE_ROW_SLOT_START + inv.selected;

        // 玩家主背包 3×9
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // 快捷栏 1×9
        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedItemIndex);
    }

    // ================= 客户端构造 =================
    public StorageBagMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        super(ModMenuTypes.STORAGE_BAG_MENU.get(), id);
        bagStack = ItemStack.EMPTY;
        this.access = ContainerLevelAccess.NULL;
        this.level = inv.player.level();

        // 从包读 Kind 和数据
        String kindName = buf.readUtf();
        this.storage = new Storage(kindName);

        int size = buf.readVarInt();
        for (int i = 0; i < size; i++) {
            String keyStr = buf.readUtf();
            int count = buf.readVarInt();
            Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(keyStr));
            this.storage.getStorage().put(item, count);
        }

        this.lockedSlot = USE_ROW_SLOT_START + inv.selected;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedItemIndex);
    }

    // ================= 数据访问 =================
    public Storage getStorage() {
        return this.storage;
    }

    public int getStorageNum() {
        return this.storage.getStorage().size();
    }

    public int getSelectedItemIndex() {
        return this.selectedItemIndex.get();
    }

    public void registerUpdateListener(Runnable listener) {
        this.slotUpdateListener = listener;
    }

    // ================= 按钮点击 =================
    @Override
    public boolean clickMenuButton(Player player, int index) {
        if (index >= 0 && index < getStorageNum()) {
            this.selectedItemIndex.set(index);
            this.slotUpdateListener.run();   // 通知 Screen 刷新右侧信息
            return true;
        }
        return false;
    }

    // ================= 快速移动：存入 storage =================
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack result = stack.copy();

        int stored = tryStoreToStorage(stack);
        if (stored <= 0) return ItemStack.EMPTY;

        stack.shrink(stored);
        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        this.broadcastChanges();
        if (slotUpdateListener != null) slotUpdateListener.run();
        if(player instanceof ServerPlayer serverPlayer) {
            syncToClient(serverPlayer);
        }
        return result;
    }

    /** 尝试把物品存入 storage，返回实际存入数量 */
    private int tryStoreToStorage(ItemStack stack) {
        if (storage == null || stack.isEmpty()) return 0;
        Item item = stack.getItem();
        Map<Item, Integer> map = storage.getStorage();
        if (!map.containsKey(item)) return 0;

        int max = StorageBag.getMaxStorage(this.bagStack);   // 或从 bagStack 取
        int current = map.get(item);
        if (current >= max) return 0;
        int canStore = max - current;
        int toStore = Math.min(canStore, stack.getCount());
        map.put(item, current + toStore);
        // 立即写回 NBT
        if (this.bagStack != null && !this.bagStack.isEmpty()) {
            StorageBag.saveToNbt(this.bagStack, this.storage);
        }

        this.broadcastChanges();
        if (slotUpdateListener != null) slotUpdateListener.run();
        return toStore;
    }


    // ================= 槽位锁定 =================
    @Override
    public void clicked(int slotId, int button, ClickType clickType, Player player) {
        if (slotId == this.lockedSlot) return;   // 锁住打开菜单的那个储物袋
        super.clicked(slotId, button, clickType, player);
    }

    @Override
    public boolean stillValid(Player player){
    return player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof StorageBag;
    }

    @Override
    public MenuType<?> getType() {
        return ModMenuTypes.STORAGE_BAG_MENU.get();
    }
    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!player.level().isClientSide && this.bagStack != null) {
            StorageBag.saveToNbt(this.bagStack, this.storage);
        }
    }
    public static void handle(StorageActionPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            int amount = msg.amount;
            if (amount < 0) amount = 0;
            if (amount > 1_000_000) amount = 1_000_000;

            if (player.containerMenu instanceof StorageBagMenu menu) {
                menu.handleInputAmount(player, amount);
            }
        });
        context.setPacketHandled(true);
    }
    public void handleInputAmount(Player player, int amount) {
        // 这里写你的业务逻辑
        // 比如：从 storage 取出 amount 个当前选中的物品
        int selected = getSelectedItemIndex();//获取选中物品索引
        if (selected < 0 || selected >= getStorageNum()) return;//如果超出范围, 不处理

        List<Item> items = new ArrayList<>(storage.getStorage().keySet());
        Item item = items.get(selected);//获取被选中物品的数量

        // 示例：取出 amount 个
        int took = tryTakeFromStorage(item, amount, player);//尝试取出
        if(player instanceof ServerPlayer serverPlayer) {
            syncToClient(serverPlayer);//刷新菜单
        }
    }
    /**
     * 从 storage 取出指定数量的物品，返回实际取出的数量。
     * @param item 要取出的物品种类
     * @param amount 玩家想取出的数量
     * @return 实际取出并给到玩家的数量
     */
    public int tryTakeFromStorage(Item item, int amount,Player pPlayer) {
        if (item == null || amount <= 0) return 0;

        Map<Item, Integer> map = storage.getStorage();//获取存储信息
        if (!map.containsKey(item)) return 0;//检查是否有要取的物品

        int current = map.get(item);//获取物品数量
        // 不能超过当前存储量
        int toTake = Math.min(current, amount);//取得要取的数量
        if (toTake <= 0) return 0;//防止输入量小于 0

        // 先扣 storage
        map.put(item, current - toTake);//重置剩余物品信息

        // 给玩家
        ItemStack give = new ItemStack(item, toTake);
        boolean added = pPlayer.getInventory().add(give);

        // 如果背包塞不下，把没塞进去的部分还回 storage
        if (!added || !give.isEmpty()) {
            int leftover = give.getCount();
            if (leftover > 0) {
                map.put(item, map.get(item) + leftover);
            }
            toTake -= leftover;
        }
        // 立即写回 NBT
        if (this.bagStack != null && !this.bagStack.isEmpty()) {
            StorageBag.saveToNbt(this.bagStack, this.storage);
        }

        // 同步给客户端
        this.broadcastChanges();
        if (slotUpdateListener != null) slotUpdateListener.run();

        return toTake;
    }
    // StorageBagMenu
    public void updateStorageFromServer(String kindName, Map<Item, Integer> data) {
        // 保留原有物品列表结构，只更新数量
        Map<Item, Integer> map = this.storage.getStorage();
        for (Map.Entry<Item, Integer> e : data.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        if (slotUpdateListener != null) slotUpdateListener.run();
    }
    private void syncToClient(ServerPlayer player) {
        ModNetwork.CHANNEL.sendTo(
                new StorageSyncPacket(storage.getKindName(), storage.getStorage()),
                player.connection.connection,
                NetworkDirection.PLAY_TO_CLIENT
        );
    }

    public void updateStorage(Map<Item, Integer> newData) {
        this.storage.getStorage().clear();
        this.storage.getStorage().putAll(newData);
        if (slotUpdateListener != null) slotUpdateListener.run();
    }
}
