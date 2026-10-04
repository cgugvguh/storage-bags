package com.fmy.storage_bags.item.StorageBag;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 宛
 * @version 1.0
 */
public class Storage {
    private final Kinds kind;//种类信息
    private final Map<Item, Integer> storage = new LinkedHashMap<>();//存储数量和物品种类信息
    public Storage(String kindName) {
        this.kind = getKind(kindName);//根据种类名获取种类
        // 根据 Kind 的可存储列表初始化，数量全部为 0
        for (Item item : this.kind.getStorageInfo()) {
            storage.put(item, 0);
        }
    }

    public Kinds getKind() {
        return kind;
    }

    public String getKindName() {
        return kind.getKindName();
    }

    public Map<Item, Integer> getStorage() {
        return storage;
    }
    //增加物品种类
    public void addStorageKind(Item item) {
        storage.put(item, 0);
    }

    /** 某种物品是否允许存入 */
    public boolean isAllowed(Item item) {
        return storage.containsKey(item);
    }

    /** 查询数量 */
    public int getCount(Item item) {
        return storage.getOrDefault(item, 0);
    }

    /** 设置数量，不允许超过上限（上限由外部菜单判断） */
    public void setCount(Item item, int count) {
        if (storage.containsKey(item)) {
            storage.put(item, count);
        }
    }

    /** 增加数量，返回实际增加的量 */
    public int addCount(Item item, int amount) {
        if (!storage.containsKey(item)) return 0;//如果没有此类物品, 返回 0
        int old = storage.get(item);
        storage.put(item, old + amount);//添加
        return amount;//返回增量
    }

    /** 减少数量，返回实际减少的量 */
    public int removeCount(Item item, int amount) {
        if (!storage.containsKey(item)) return 0;
        int old = storage.get(item);
        int removed = Math.min(old, amount);
        storage.put(item, old - removed);
        return removed;
    }

    /** 当前存储物品中数量最大的值，用于 check 上限 */
    public int getMaxCount() {
        int max = 0;
        for (int num : storage.values()) {
            if (num > max) max = num;
        }
        return max;
    }
    public static Kinds getKind(String kind){//根据种类名获取种类
        return Kinds.getKindFromName(kind);
    }
    public int tryStoreToStorage(ItemStack itemStack, ItemStack bagStack) {//放入带储存物品和储物袋
        Item item = itemStack.getItem();//待储存物品
        if (itemStack.isEmpty() || !storage.containsKey(item)) return 0;//如果物品堆为空或者储物袋没有这个物品
        int current = storage.get(item);
        int max = StorageBag.getMaxStorage(bagStack);
        if (current >= max) return 0;
        int canStore = max - current;
        int toStore = Math.min(canStore, itemStack.getCount());
        storage.put(item, current + toStore);
        // 立即写回 NBT
        StorageBag.saveToNbt(bagStack, this);
        return toStore;
    }
}
