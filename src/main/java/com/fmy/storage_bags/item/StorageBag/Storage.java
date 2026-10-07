package com.fmy.storage_bags.item.StorageBag;

import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 宛
 * @version 1.0
 */
public class Storage {
    private final String kindName;
    private final Map<Item, Integer> storageInfo = new LinkedHashMap<>();//存储数量和物品种类信息
    public Storage(String kindName) {
        Kinds kind = getKind(kindName);//获取种类信息
        this.kindName = kindName;//保存种类名
        // 根据 Kind 的可存储列表初始化，数量全部为 0
        for (Item item : kind.getItemKind()) {//通过种类信息来初始化
            storageInfo.put(item, 0);
        }
    }
    public static Kinds getKind(String kind){//根据种类名获取种类, 用于初始化
        return Kinds.getKindFromName(kind);
    }

    public String getKindName() {//获取种类名
        return kindName;
    }

    public Map<Item, Integer> getStorageInfo() {//获取存储的物品信息
        return storageInfo;
    }

    public void addStorageKind(Item item) {//增加物品种类
        storageInfo.put(item, 0);
    }

    /** 某种物品是否允许存入 */
    public boolean isAllowed(Item item) {
        return storageInfo.containsKey(item);
    }

    /** 查询数量 */
    public int getCount(Item item) {
        return storageInfo.getOrDefault(item, 0);
    }

    /** 设置数量 */
    public void setCount(Item item, int count) {
        if (storageInfo.containsKey(item)) {
            storageInfo.put(item, count);
        }
    }

    /** 增加数量，返回实际增加的量 */
    public int addCount(Item item, int amount) {
        if (!storageInfo.containsKey(item)) return 0;//如果没有此类物品, 返回 0
        int old = storageInfo.get(item);
        storageInfo.put(item, old + amount);//添加
        return amount;//返回增量
    }

    /** 减少数量，返回实际减少的量 */
    public int removeCount(Item item, int amount) {
        if (!storageInfo.containsKey(item)) return 0;
        int old = storageInfo.get(item);
        int removed = Math.min(old, amount);
        storageInfo.put(item, old - removed);
        return removed;
    }

    /** 当前存储物品中数量最大的值，用于 check 上限 */
    public int getMaxCount() {
        int max = 0;
        for (int num : storageInfo.values()) {
            if (num > max) max = num;
        }
        return max;
    }

}
