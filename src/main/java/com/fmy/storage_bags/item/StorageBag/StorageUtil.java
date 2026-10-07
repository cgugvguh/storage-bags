package com.fmy.storage_bags.item.StorageBag;

import com.fmy.storage_bags.item.ModItem.ModTiers;
import com.fmy.storage_bags.menu.StorageBagMenu;
import com.fmy.storage_bags.tag.ModItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author 宛
 * @version 1.0
 */
public class StorageUtil {//负责数据储存和处理, 注意: 所有跟储物袋有关的方法储物袋本身都是先传入
    private static final Map<UUID, Storage> BAG_STORAGE = new HashMap<>();//根据物品袋 uuid 储存的信息
    private static final String BAG_ID_KEY = "bagId";
    private static final String NBT_DATA = "StorageData";
    private static final String NBT_KIND = "StorageKind";
    private static final int DEFAULT_MAX_STORAGE = 64;

    /**
     * 从 itemStack 获取 种类信息
     * @param bagStack 储物袋本身物品堆
     * @return 储物袋种类名称
     */
    public static String getKindName(ItemStack bagStack) {//根据物品堆获取种类名
        StorageBag storageBag = (StorageBag) bagStack.getItem();
        return storageBag.getKindName();
    }
    /**
     * 判断一个物品堆是否为储物袋
     * @param itemStack 物品堆
     * @return 是否为储物袋
     */
    public static boolean isStorageBag(ItemStack itemStack){
        return itemStack.getItem() instanceof StorageBag;
    }

    /**
     * 从 ItemStack 的 NBT 读取 bagId，没有就生成一个写回去
     * @param bagStack 储物袋本身物品堆
     * @return 储物袋 UUID
     */
    public static UUID getOrCreateBagId(ItemStack bagStack) {//通过物品堆获取包Id
        CompoundTag tag = bagStack.getOrCreateTag();//根据物品获取或创建标签
        if (!tag.hasUUID(BAG_ID_KEY)) {//如果标签里面没有 UUID
            tag.putUUID(BAG_ID_KEY, UUID.randomUUID());//创建一个
        }
        return tag.getUUID(BAG_ID_KEY);//返回 UUID
    }

    /**
     * 从 itemStack 获取 Storage
     * @param bagStack 储物袋本身物品堆
     * @return 根据物品堆来读取的 storage
     */
    public static Storage getStorage(ItemStack bagStack) {
        UUID id = StorageUtil.getOrCreateBagId(bagStack);
        Storage storage = BAG_STORAGE.get(id);
        if (storage != null) return storage;//返回保存好的 storage
        //如果还没有根据 UUID 创建好的 storage
        storage = loadFromNbt(bagStack);//从 NBT 加载, 没有会创建新的
        BAG_STORAGE.put(id, storage);//保存 storage
        return storage;//返回加载好或创建好的 storage
    }

    /**
     * 根据这个物品的 bagId 拿到它专属的 存储上限
     * @param bagStack 储物袋本身物品堆
     * @return 根据 UUID 返回最大存储数量
     */
    public static int getMaxStorage(ItemStack bagStack) {
        CompoundTag tag = bagStack.getTag();
        return (tag != null && tag.contains("MaxStorage"))//从NBT中根据 MaxStorage 词条加载，没有就返回默认值 64
                ? tag.getInt("MaxStorage")
                : DEFAULT_MAX_STORAGE;
    }

    /**
     * 直接给储物袋设定一个最大储物上限
     * @param bagStack 储物袋本身物品堆
     * @param maxStorage 扩容乘数
     * @return 是否扩容成功
     */
    public static boolean setMaxStorage(ItemStack bagStack, int maxStorage) {
        if (check(bagStack, maxStorage)) {//检查扩容是否合适
            bagStack.getOrCreateTag().putInt("MaxStorage", maxStorage);//如果合适, 将新的最大存储上限写入储物袋 NBT
            return true;
        }
        return false;
    }

    /**
     * 重设最大存储上限，用于扩容储物袋
     * @param bagStack 储物袋本身物品堆
     * @param multiplier 扩容乘数
     * @return 是否扩容成功
     */
    public static boolean setMaxStorageByMultiplier(ItemStack bagStack, int multiplier) {
        int newMax = (multiplier == -1)//如果乘数为 -1, 扩容为最大值, 不为 -1 则根据乘数得到一个合适最大存储量
                ? Integer.MAX_VALUE
                : (int) (DEFAULT_MAX_STORAGE * Math.pow(2, multiplier));
        return setMaxStorage(bagStack, newMax);
    }

    /**
     * 直接对储物袋进行填充
     * @param  bagStack 储物袋
     * @param multiplier 添加物品乘数
     */
    public static boolean fill(ItemStack bagStack, int multiplier){
        Storage storage = getStorage(bagStack);//获取储物信息
        Map<Item, Integer> storageInfo = storage.getStorageInfo();
        Set<Item> items = storageInfo.keySet();
        int maxNumber = Math.min(getMaxStorage(bagStack), 1728);//获取最大可储存数字, 当填充过多时, 加以限制为 1728 (3 * 9 * 64)
        if(multiplier != -1){//如果乘数不是 -1
            for(Item item : items){//遍历储物袋物品信息
                if(new ItemStack(item).is(ModItemTags.FILL_BLACKLIST)){
                    continue;
                }
                int newNumber = storageInfo.get(item) + (int)((multiplier/32.0) * maxNumber);//根据乘数增加数量
                storageInfo.put(item, Math.min(newNumber, maxNumber));//进行添加操作
            }
        } else {//直接设为上限
            for (Item item : items) {
                storageInfo.put(item, getMaxStorage(bagStack));
            }
        }
        if (!bagStack.isEmpty()) {
            saveToNbt(bagStack, storage);//保存 storage 数据到 stack 的 NBT
        }
        return true;
    }

    /**
     * 将物品尝试存入储物袋
     * @param bagStack 储物袋物品堆
     * @param itemStack 要存入的物品
     */
    public static int tryStoreToStorage(ItemStack bagStack, ItemStack itemStack) {
        Item item = itemStack.getItem();//待储存物品
        Storage storage = getStorage(bagStack);
        Map<Item, Integer> storageInfo = storage.getStorageInfo();
        if (itemStack.isEmpty() || !storageInfo.containsKey(item)) return 0;//如果物品堆为空或者储物袋没有这个物品
        int current = storageInfo.get(item);
        int max = StorageUtil.getMaxStorage(bagStack);
        if (current >= max) return 0;//储物袋已满
        int canStore = max - current;//储物袋剩余容量
        int toStore = Math.min(canStore, itemStack.getCount());//从剩余容量和待存储数量里面取一个最小值
        storageInfo.put(item, current + toStore);//放入储物袋
        // 立即写回 NBT
        StorageUtil.saveToNbt(bagStack, storage);
        return toStore;
    }

    public static boolean storeAllFromInventory(ItemStack bagStack, ServerPlayer pPlayer) {
        boolean changed = false;
        Inventory inv = pPlayer.getInventory();

        // 遍历玩家背包全部 41 格（快捷栏、主背包、盔甲、副手）
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);
            if (stack.isEmpty()) continue;

            int stored = StorageUtil.tryStoreToStorage(bagStack, stack);
            if (stored <= 0) continue;

            stack.shrink(stored);
            if (stack.isEmpty()) {
                inv.setItem(i, ItemStack.EMPTY);
            } else {
                inv.setChanged();
            }
            changed = true;
        }
        return changed;
    }

    /**
     * 从 itemStack 获取 种类信息
     * @param bagStack 储物袋本身物品堆
     * @return 从 NBT 加载好或者创建好的 Storage
     */
    public static Storage loadFromNbt(ItemStack bagStack) {
        UUID id = StorageUtil.getOrCreateBagId(bagStack);//获取 UUID
        Storage storage = new Storage(getKindName(bagStack));//创建新的 storage 用于存储

        CompoundTag tag = bagStack.getTag();
        if (tag != null && tag.contains(NBT_KIND) && tag.contains(NBT_DATA)) {//如果 NBT 保存了(数据在服务端而客户端没有)
            // NBT 里记录了种类，用它覆盖
            String kindName = tag.getString(NBT_KIND);
            storage = new Storage(kindName);

            CompoundTag data = tag.getCompound(NBT_DATA);
            for (String key : data.getAllKeys()) {
                Item item = BuiltInRegistries.ITEM.get(new ResourceLocation(key));
                if (item != Items.AIR) {
                    storage.getStorageInfo().put(item, data.getInt(key));
                }
            }
        }
        return storage;
    }
    /** 把 Storage 序列化进 ItemStack 的 NBT */
    public static void saveToNbt(ItemStack stack, Storage storage) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString(NBT_KIND, storage.getKindName());

        CompoundTag data = new CompoundTag();
        for (Map.Entry<Item, Integer> e : storage.getStorageInfo().entrySet()) {
            if (e.getValue() > 0) {   // 只存有数量的，减小 NBT
                data.putInt(BuiltInRegistries.ITEM.getKey(e.getKey()).toString(), e.getValue());
            }
        }
        tag.put(NBT_DATA, data);
    }

    /**
     * 通过材质返回扩容乘数，传入扩容插件获取乘数
     * @param tier 传入的材质
     * @return 返回扩容乘数
     */
    public static int getMultiplier(Tier tier){
        int multiplier = 0;
        if (tier == Tiers.IRON) multiplier = 2;
        else if (tier == Tiers.GOLD) multiplier = 4;
        else if (tier == Tiers.DIAMOND) multiplier = 6;
        else if (tier == Tiers.NETHERITE) multiplier = 8;
        else if (tier == ModTiers.INFINITE) multiplier = -1;
        return multiplier;
    }



    /**
     * 判定扩容行为是否合适
     * @param bagStack 背包物品堆
     * @param newMax 将设最大值
     * @return 扩容是否合理
     */
    private static boolean check(ItemStack bagStack, int newMax) {
        if (newMax <= getMaxStorage(bagStack)) return false;//如果扩容的量没有原版的大, 扩容不合理
        Storage storage = getStorage(bagStack);
        int max = storage.getMaxCount();//通过 storage 获取已存储的最大数量
        return max <= newMax;//如果改变容量后新值不可容纳所有物品, 扩容不合理
    }
}
