package com.fmy.storage_bags.item.StorageBag;

import com.fmy.storage_bags.item.ModItem.ModTiers;
import com.fmy.storage_bags.item.Plugin.ExpandPlugin;
import com.fmy.storage_bags.item.Plugin.FillPlugin;
import com.fmy.storage_bags.menu.StorageBagMenu;
import com.fmy.storage_bags.stats.ModStats;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author 宛
 * @version 1.0
 */
public class StorageBag extends Item {
    // 每个背包一份数据，按 bagId 索引
    public static final Map<UUID, Storage> BAG_STORAGE = new HashMap<>();//根据物品袋 uuid 储存的信息
    private static final Map<UUID, Integer> BAG_MAX_STORAGE = new HashMap<>();//根据物品袋 uuid 储存的上限信息

    private static final int DEFAULT_MAX_STORAGE = 64;//默认堆叠数量
    private static final String BAG_ID_KEY = "bagId";
    private static final String NBT_DATA = "StorageData";
    private static final String NBT_KIND = "StorageKind";
    private final Kinds defaultKind;
    public StorageBag(Properties pProperties,String kind) {
        super(pProperties);
        this.defaultKind = Kinds.getKindFromName(kind);
    }

    /** 从 ItemStack 的 NBT 读取 bagId，没有就生成一个写回去 */
    public static UUID getOrCreateBagId(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.hasUUID(BAG_ID_KEY)) {
            tag.putUUID(BAG_ID_KEY, UUID.randomUUID());
        }
        return tag.getUUID(BAG_ID_KEY);
    }

    /** 根据这个物品的 bagId 拿到它专属的 Storage */
//    public Storage getStorage(ItemStack stack) {
//        UUID id = getOrCreateBagId(stack);
//        return BAG_STORAGE.computeIfAbsent(id, k -> new Storage(kind));
//    }
    public static Storage getStorage(ItemStack stack) {
        if (stack.isEmpty() || !(stack.getItem() instanceof StorageBag)) {
            return new Storage("cutting_tree");   // 兜底，防 NPE
        }
        UUID id = getOrCreateBagId(stack);
        Storage cached = BAG_STORAGE.get(id);
        if (cached != null) return cached;

        Storage loaded = loadFromNbt(stack);
        BAG_STORAGE.put(id, loaded);
        return loaded;
    }
    /** 根据这个物品的 bagId 拿到它专属的 存储上限 */
    public static int getMaxStorage(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        return (tag != null && tag.contains("MaxStorage"))
                ? tag.getInt("MaxStorage")
                : DEFAULT_MAX_STORAGE;
    }

    /** 设定乘数并重设最大存储上限 */
    public static boolean setMaxStorage(ItemStack stack, Tier tier) {
        return setMaxStorage(stack, getMultiplier(tier));
    }

    /** 重设最大存储上限 */
    public static boolean setMaxStorage(ItemStack stack, int multiplier) {
        int newMax = (multiplier == -1)
                ? Integer.MAX_VALUE
                : (int) (DEFAULT_MAX_STORAGE * Math.pow(2, multiplier));
        if (check(stack, newMax)) {
            stack.getOrCreateTag().putInt("MaxStorage", newMax);
            return true;
        }
        return false;
    }
    /**
     * @param  stack 储物袋
     * @param multiplier 添加物品乘数
     * */
    public static boolean fill(ItemStack stack, int multiplier){
        if(getStorage(stack).getKind().getKindName().equals("mining_ore")){
            return false; //不填充矿物袋
        }
        Storage storage = getStorage(stack);//获取储物信息
        Map<Item, Integer> storageInfo = storage.getStorage();
        Set<Item> items = storageInfo.keySet();
        int maxNumber = Math.min(getMaxStorage(stack), 1728);//获取最大可储存数字, 当填充过多时, 加以限制为 1728 (3 * 9 * 64)
        if(multiplier != -1){//如果乘数不是 -1
            for(Item item : items){//遍历储物袋物品信息
                int newNumber = storageInfo.get(item) + (int)((multiplier/32.0) * maxNumber);//根据乘数增加数量
                storageInfo.put(item, Math.min(newNumber, maxNumber));//进行添加操作
            }
        } else {//直接设为上限
            for (Item item : items) {
                storageInfo.put(item, getMaxStorage(stack));
            }
        }
        if (!stack.isEmpty()) {
            saveToNbt(stack, storage);//保存 storage 数据到 stack 的 NBT
        }
        return true;
    }

    /** 检查新上限是否合理 */
    public static boolean check(ItemStack stack, int newMax) {
        if (newMax <= getMaxStorage(stack)) return false;//如果扩容的量没有原版的大, 扩容不合理
        int max = 0;
        for (Integer num : getStorage(stack).getStorage().values()) {
            if (num > max) max = num;//获取存储物品的最大数量
        }
        return max <= newMax;//如果改变容量后新值不可容纳所有物品, 扩容不合理
    }
    //直接填充储物袋的方法
    public static boolean fill(ItemStack itemStack, Tier tier) {
        return fill(itemStack, getMultiplier(tier));
    }
    //获取倍率
    public static int getMultiplier(Tier tier){
        int multiplier = 0;
        if (tier == Tiers.IRON) multiplier = 2;
        else if (tier == Tiers.GOLD) multiplier = 4;
        else if (tier == Tiers.DIAMOND) multiplier = 6;
        else if (tier == Tiers.NETHERITE) multiplier = 8;
        else if (tier == ModTiers.INFINITE) multiplier = -1;
        return multiplier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {

        ItemStack bagStack = pPlayer.getItemInHand(pHand);//获取该储物袋
        Storage storage = getStorage(bagStack);

        if (!pLevel.isClientSide && pPlayer instanceof ServerPlayer serverPlayer) {
            // 只允许主手打开
            if (pHand != InteractionHand.MAIN_HAND) {//如果是在副手
                ItemStack itemStack = pPlayer.getMainHandItem();//获取主手物品

                if(storage.isAllowed(itemStack.getItem())){//如果包含了主手物品
                    int stored = storage.tryStoreToStorage(itemStack,bagStack);
                    if (stored <= 0) itemStack = ItemStack.EMPTY;
                    itemStack.shrink(stored);
                    return InteractionResultHolder.success(bagStack);
                }
                if(storage.getKind().getKindName().equals("custom")){
                    if(itemStack.isEmpty()){
                        storage.getStorage().entrySet().removeIf((entry) -> entry.getValue() <= 0);
                        return InteractionResultHolder.success(bagStack);
                    }
                    if(itemStack.getItem() instanceof ExpandPlugin expandPlugin){//如果是储物袋
                        if (StorageBag.setMaxStorage(bagStack, expandPlugin.getTier())) {//修改背包最大数量, 如果成功
                            if (!pPlayer.getAbilities().instabuild) {
                                itemStack.shrink(1);//数量 -1
                                return InteractionResultHolder.sidedSuccess(itemStack, false);//返回交互成功
                            }
                        }
                    }else if(itemStack.getItem() instanceof FillPlugin fillPlugin){
                        if (StorageBag.fill(bagStack, fillPlugin.getTier())) {//修改背包最大数量, 如果成功
                            if (!pPlayer.getAbilities().instabuild) {
                                itemStack.shrink(1);//数量 -1
                                return InteractionResultHolder.sidedSuccess(itemStack, false);//返回交互成功
                            }
                        }
                    }
                    if(itemStack.getMaxStackSize() == 1){//只允许存入最大堆叠数量为 1 的物品
                        return InteractionResultHolder.fail(bagStack);
                    }
                    int maxNum = Math.max((getMaxStorage(bagStack) / 64) * 27, 27);//增加种类最小值 27
                    maxNum = Math.min(maxNum, 100);// 增加种类最大值 100
                    int size = storage.getStorage().size();//当前尺寸
                    if(size >= maxNum){//如果大于最大尺寸
                        return InteractionResultHolder.fail(bagStack);
                    }else {
                        storage.addStorageKind(itemStack.getItem());//增加此类物品
                        int stored = storage.tryStoreToStorage(itemStack,bagStack);//放入
                        if (stored <= 0) itemStack = ItemStack.EMPTY;
                        itemStack.shrink(stored);
                        saveToNbt(bagStack, storage);
                        return InteractionResultHolder.success(bagStack);
                    }

                }
                return InteractionResultHolder.pass(pPlayer.getItemInHand(pHand));
            }

            String menuName = "container." + storage.getKindName() + "_storage_bag";

            NetworkHooks.openScreen(
                    serverPlayer,
                    new SimpleMenuProvider(
                            (id, inv, player) -> new StorageBagMenu(
                                    id, inv, ContainerLevelAccess.NULL, storage, bagStack
                            ),
                            bagStack.hasCustomHoverName()
                                    ? bagStack.getHoverName()
                                    : Component.translatable(menuName)
                    ),
                    (FriendlyByteBuf buf) -> {
                        buf.writeUtf(storage.getKindName());
                        buf.writeVarInt(storage.getStorage().size());
                        for (Map.Entry<Item, Integer> e : storage.getStorage().entrySet()) {
                            buf.writeUtf(BuiltInRegistries.ITEM.getKey(e.getKey()).toString());
                            buf.writeVarInt(e.getValue());
                        }
                    }
            );

            pPlayer.awardStat(ModStats.USE_STORAGE_BAG.get());
        }

        return InteractionResultHolder.sidedSuccess(bagStack, pLevel.isClientSide);
    }
    /** 从 ItemStack 的 NBT 恢复 Storage */
    public static Storage loadFromNbt(ItemStack stack) {
        UUID id = getOrCreateBagId(stack);
        Kinds kind = getKind(stack);   // 兜底用物品自身的 Kind
        Storage s = new Storage(kind.getKindName());

        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains(NBT_KIND) && tag.contains(NBT_DATA)) {
            // NBT 里记录了种类，用它覆盖
            String kindName = tag.getString(NBT_KIND);
            s = new Storage(kindName);

            CompoundTag data = tag.getCompound(NBT_DATA);
            for (String key : data.getAllKeys()) {
                Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(key));
                if (item != Items.AIR) {
                    s.getStorage().put(item, data.getInt(key));
                }
            }
        }
        return s;
    }
    public static Kinds getKind(ItemStack stack) {
        if (stack.getItem() instanceof StorageBag bag) {
            return bag.getDefaultKind();
        }
        return Kinds.CUTTING_TREE;
    }


    public Kinds getDefaultKind() {
        return this.defaultKind;
    }

    /** 把 Storage 序列化进 ItemStack 的 NBT */
    public static void saveToNbt(ItemStack stack, Storage storage) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString(NBT_KIND, storage.getKindName());

        CompoundTag data = new CompoundTag();
        for (Map.Entry<Item, Integer> e : storage.getStorage().entrySet()) {
            if (e.getValue() > 0) {   // 只存有数量的，减小 NBT
                data.putInt(BuiltInRegistries.ITEM.getKey(e.getKey()).toString(), e.getValue());
            }
        }
        tag.put(NBT_DATA, data);
    }

}
