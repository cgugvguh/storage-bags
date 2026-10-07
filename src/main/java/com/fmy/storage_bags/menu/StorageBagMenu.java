package com.fmy.storage_bags.menu;
import com.fmy.storage_bags.Internet.ModNetwork;
import com.fmy.storage_bags.Internet.StorageActionPacket;
import com.fmy.storage_bags.Internet.StorageSyncPacket;
import com.fmy.storage_bags.item.StorageBag.Storage;
import com.fmy.storage_bags.item.StorageBag.StorageBag;
import com.fmy.storage_bags.item.StorageBag.StorageUtil;
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

    /**
     *
     * @param id 菜单 id，自动传入
     * @param inv 玩家物品栏
     * @param access 自动传入
     * @param storage 储物袋存储的信息
     * @param bagStack 储物袋物品堆
     */
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
            this.storage.getStorageInfo().put(item, count);
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

    public ItemStack getBagStack(){
        return this.bagStack;
    }

    public int getStorageNum() {
        return this.storage.getStorageInfo().size();
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
        if(!StorageUtil.isStorageBag(bagStack)){//如果不是储物袋
            return ItemStack.EMPTY;
        }
        Slot slot = this.slots.get(index);//通过索引获取被点击的槽位
        if (!slot.hasItem()) return ItemStack.EMPTY;//如果是空的直接返回空物品堆

        ItemStack itemStack = slot.getItem();//获取槽位修改前的物品
        ItemStack result = itemStack.copy();//并复制一份
        int stored = StorageUtil.tryStoreToStorage(bagStack, itemStack);//尝试存入

        if (stored <= 0) return ItemStack.EMPTY;//如果没存进去, 返回空物品堆表示没成功

        itemStack.shrink(stored);//存进去了就扣除物品堆里相应的数量
        if (itemStack.isEmpty()) {//如果物品堆是空的
            slot.setByPlayer(ItemStack.EMPTY);//标记物品堆被玩家置空
        } else {//标记槽位发生改变
            slot.setChanged();
        }
        this.broadcastChanges();
        if (slotUpdateListener != null) slotUpdateListener.run();
        if(player instanceof ServerPlayer serverPlayer) {
            syncToClient(serverPlayer);//同步到客户端
        }
        return result;
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
            StorageUtil.saveToNbt(this.bagStack, this.storage);
        }
    }

    public void handleInputAmount(Player player, int amount) {//取出物品
        // 从 storage 取出 amount 个当前选中的物品
        int selected = getSelectedItemIndex();//获取选中物品索引
        if (selected < 0 || selected >= getStorageNum()) return;//如果超出范围, 不处理

        List<Item> items = new ArrayList<>(storage.getStorageInfo().keySet());
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
    public int tryTakeFromStorage(Item item, int amount, Player pPlayer) {
        if (item == null || amount <= 0) return 0;

        Map<Item, Integer> map = storage.getStorageInfo();//获取存储信息
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
            StorageUtil.saveToNbt(this.bagStack, this.storage);
        }

        // 同步给客户端
        this.broadcastChanges();
        if (slotUpdateListener != null) slotUpdateListener.run();

        return toTake;
    }
    // StorageBagMenu
    public void updateStorageFromServer(String kindName, Map<Item, Integer> data) {
        // 保留原有物品列表结构，只更新数量
        Map<Item, Integer> map = this.storage.getStorageInfo();
        for (Map.Entry<Item, Integer> e : data.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        if (slotUpdateListener != null) slotUpdateListener.run();
    }
    private void syncToClient(ServerPlayer player) {
        ModNetwork.CHANNEL.sendTo(
                new StorageSyncPacket(storage.getKindName(), storage.getStorageInfo()),
                player.connection.connection,
                NetworkDirection.PLAY_TO_CLIENT
        );
    }

    public void updateStorage(Map<Item, Integer> newData) {
        this.storage.getStorageInfo().clear();
        this.storage.getStorageInfo().putAll(newData);
        if (slotUpdateListener != null) slotUpdateListener.run();
    }

    public void storeAllFromInventory(ServerPlayer pPlayer) {
        ItemStack bagStack = this.bagStack;
        if (bagStack == null || bagStack.isEmpty()) return;
        if (StorageUtil.storeAllFromInventory(bagStack, pPlayer)) {

            // 同步给客户端
            syncToClient(pPlayer);

            this.broadcastChanges();
            if (slotUpdateListener != null) slotUpdateListener.run();
        }
    }

}
