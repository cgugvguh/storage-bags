package com.fmy.storage_bags.item.StorageBag;

import net.minecraft.world.item.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 宛
 * @version 1.0
 */
public enum Kinds {//包含了种类信息
    CUTTING_TREE("cutting_tree"),
    MINING_ORE("mining_ore"),
    PLANT("plant"),
    INDUSTRY("industry"),
    FISHING("fishing"),
    BUTTLE("buttle"),
    BUILDING("building"),
    HUSBANDRY("husbandry"),
    CUSTOM("custom");
    private final String kindName;//种类名
    private final List<Item> storageInfo = new ArrayList<>();//可存储物品信息
    Kinds(String kindName) {
        this.kindName = kindName;//设置种类信息
        setStorage();//设置存储信息
    }
    public List<Item> getStorageInfo(){
        return storageInfo;
    }
    public static Kinds getKindFromName(String name){
        return switch (name) {
            case "cutting_tree" -> Kinds.CUTTING_TREE;
            case "mining_ore" -> Kinds.MINING_ORE;
            case "plant" -> Kinds.PLANT;
            case "industry" -> Kinds.INDUSTRY;
            case "fishing" -> Kinds.FISHING;
            case "buttle" -> Kinds.BUTTLE;
            case "building" -> Kinds.BUILDING;
            default -> Kinds.CUSTOM;
        };
    }
    public String getKindName(){
        return kindName;
    }

    private void setStorage() {
        switch (this.kindName) {
            case "cutting_tree" -> {
                // ---- 橡木 (Oak) ----
                storageInfo.add(Items.OAK_LOG);
                storageInfo.add(Items.OAK_WOOD );
                storageInfo.add(Items.STRIPPED_OAK_LOG );
                storageInfo.add(Items.STRIPPED_OAK_WOOD);
                storageInfo.add(Items.OAK_PLANKS);
                storageInfo.add(Items.OAK_SAPLING);
                storageInfo.add(Items.OAK_LEAVES);

                // ---- 云杉木 (Spruce) ----
                storageInfo.add(Items.SPRUCE_LOG);
                storageInfo.add(Items.SPRUCE_WOOD);
                storageInfo.add(Items.STRIPPED_SPRUCE_LOG);
                storageInfo.add(Items.STRIPPED_SPRUCE_WOOD);
                storageInfo.add(Items.SPRUCE_PLANKS);
                storageInfo.add(Items.SPRUCE_SAPLING);
                storageInfo.add(Items.SPRUCE_LEAVES);

                // ---- 白桦木 (Birch) ----
                storageInfo.add(Items.BIRCH_LOG);
                storageInfo.add(Items.BIRCH_WOOD);
                storageInfo.add(Items.STRIPPED_BIRCH_LOG);
                storageInfo.add(Items.STRIPPED_BIRCH_WOOD);
                storageInfo.add(Items.BIRCH_PLANKS);
                storageInfo.add(Items.BIRCH_SAPLING);
                storageInfo.add(Items.BIRCH_LEAVES);

                // ---- 丛林木 (Jungle) ----
                storageInfo.add(Items.JUNGLE_LOG);
                storageInfo.add(Items.JUNGLE_WOOD);
                storageInfo.add(Items.STRIPPED_JUNGLE_LOG);
                storageInfo.add(Items.STRIPPED_JUNGLE_WOOD);
                storageInfo.add(Items.JUNGLE_PLANKS);
                storageInfo.add(Items.JUNGLE_SAPLING);
                storageInfo.add(Items.JUNGLE_LEAVES);

                // ---- 金合欢木 (Acacia) ----
                storageInfo.add(Items.ACACIA_LOG);
                storageInfo.add(Items.ACACIA_WOOD);
                storageInfo.add(Items.STRIPPED_ACACIA_LOG);
                storageInfo.add(Items.STRIPPED_ACACIA_WOOD);
                storageInfo.add(Items.ACACIA_PLANKS);
                storageInfo.add(Items.ACACIA_SAPLING);
                storageInfo.add(Items.ACACIA_LEAVES);

                // ---- 深色橡木 (Dark Oak) ----
                storageInfo.add(Items.DARK_OAK_LOG);
                storageInfo.add(Items.DARK_OAK_WOOD);
                storageInfo.add(Items.STRIPPED_DARK_OAK_LOG);
                storageInfo.add(Items.STRIPPED_DARK_OAK_WOOD);
                storageInfo.add(Items.DARK_OAK_PLANKS);
                storageInfo.add(Items.DARK_OAK_SAPLING);
                storageInfo.add(Items.DARK_OAK_LEAVES);

                // ---- 红树木 (Mangrove) ----
                storageInfo.add(Items.MANGROVE_LOG);
                storageInfo.add(Items.MANGROVE_WOOD);
                storageInfo.add(Items.STRIPPED_MANGROVE_LOG);
                storageInfo.add(Items.STRIPPED_MANGROVE_WOOD);
                storageInfo.add(Items.MANGROVE_PLANKS);
                storageInfo.add(Items.MANGROVE_PROPAGULE);
                storageInfo.add(Items.MANGROVE_LEAVES);

                // ---- 樱花木 (Cherry) ----
                storageInfo.add(Items.CHERRY_LOG);
                storageInfo.add(Items.CHERRY_WOOD);
                storageInfo.add(Items.STRIPPED_CHERRY_LOG);
                storageInfo.add(Items.STRIPPED_CHERRY_WOOD);
                storageInfo.add(Items.CHERRY_PLANKS);
                storageInfo.add(Items.CHERRY_SAPLING);
                storageInfo.add(Items.CHERRY_LEAVES);

                // ---- 竹块 (Bamboo) ----
                storageInfo.add(Items.BAMBOO_BLOCK);
                storageInfo.add(Items.STRIPPED_BAMBOO_BLOCK);
                storageInfo.add(Items.BAMBOO_PLANKS);
                storageInfo.add(Items.BAMBOO_MOSAIC);
                storageInfo.add(Items.BAMBOO);

                // ---- 绯红菌 (Crimson) ----
                storageInfo.add(Items.CRIMSON_STEM);
                storageInfo.add(Items.CRIMSON_HYPHAE);
                storageInfo.add(Items.STRIPPED_CRIMSON_STEM);
                storageInfo.add(Items.STRIPPED_CRIMSON_HYPHAE);
                storageInfo.add(Items.CRIMSON_PLANKS);
                storageInfo.add(Items.CRIMSON_FUNGUS);

                // ---- 诡异菌 (Warped) ----
                storageInfo.add(Items.WARPED_STEM);
                storageInfo.add(Items.WARPED_HYPHAE);
                storageInfo.add(Items.STRIPPED_WARPED_STEM);
                storageInfo.add(Items.STRIPPED_WARPED_HYPHAE);
                storageInfo.add(Items.WARPED_PLANKS);
                storageInfo.add(Items.WARPED_FUNGUS);
            }
            case "mining_ore" -> {
                // ---- 煤炭 (Coal) ----
                storageInfo.add(Items.COAL);
                storageInfo.add(Items.COAL_ORE);
                storageInfo.add(Items.DEEPSLATE_COAL_ORE);
                storageInfo.add(Items.COAL_BLOCK);

                // ---- 铁 (Iron) ----
                storageInfo.add(Items.RAW_IRON);
                storageInfo.add(Items.IRON_INGOT);
                storageInfo.add(Items.IRON_ORE);
                storageInfo.add(Items.DEEPSLATE_IRON_ORE);
                storageInfo.add(Items.RAW_IRON_BLOCK);
                storageInfo.add(Items.IRON_BLOCK);
                storageInfo.add(Items.IRON_NUGGET);

                // ---- 铜 (Copper) ----
                storageInfo.add(Items.RAW_COPPER);
                storageInfo.add(Items.COPPER_INGOT);
                storageInfo.add(Items.COPPER_ORE);
                storageInfo.add(Items.DEEPSLATE_COPPER_ORE);
                storageInfo.add(Items.RAW_COPPER_BLOCK);
                storageInfo.add(Items.COPPER_BLOCK);

                // ---- 金 (Gold) ----
                storageInfo.add(Items.RAW_GOLD);
                storageInfo.add(Items.GOLD_INGOT);
                storageInfo.add(Items.GOLD_ORE);
                storageInfo.add(Items.DEEPSLATE_GOLD_ORE);
                storageInfo.add(Items.NETHER_GOLD_ORE);
                storageInfo.add(Items.RAW_GOLD_BLOCK);
                storageInfo.add(Items.GOLD_BLOCK);
                storageInfo.add(Items.GOLD_NUGGET);

                // ---- 红石 (Redstone) ----
                storageInfo.add(Items.REDSTONE);
                storageInfo.add(Items.REDSTONE_ORE);
                storageInfo.add(Items.DEEPSLATE_REDSTONE_ORE);
                storageInfo.add(Items.REDSTONE_BLOCK);

                // ---- 青金石 (Lapis Lazuli) ----
                storageInfo.add(Items.LAPIS_LAZULI);
                storageInfo.add(Items.LAPIS_ORE);
                storageInfo.add(Items.DEEPSLATE_LAPIS_ORE);
                storageInfo.add(Items.LAPIS_BLOCK);

                // ---- 钻石 (Diamond) ----
                storageInfo.add(Items.DIAMOND);
                storageInfo.add(Items.DIAMOND_ORE);
                storageInfo.add(Items.DEEPSLATE_DIAMOND_ORE);
                storageInfo.add(Items.DIAMOND_BLOCK);

                // ---- 绿宝石 (Emerald) ----
                storageInfo.add(Items.EMERALD);
                storageInfo.add(Items.EMERALD_ORE);
                storageInfo.add(Items.DEEPSLATE_EMERALD_ORE);
                storageInfo.add(Items.EMERALD_BLOCK);

                // ---- 下界石英 (Nether Quartz) ----
                // 注意：石英块 (QUARTZ_BLOCK) 为不可逆合成，已排除
                storageInfo.add(Items.QUARTZ);
                storageInfo.add(Items.NETHER_QUARTZ_ORE);

                // ---- 下界合金 (Netherite) ----
                storageInfo.add(Items.NETHERITE_SCRAP);
                storageInfo.add(Items.NETHERITE_INGOT);
                storageInfo.add(Items.ANCIENT_DEBRIS);
                storageInfo.add(Items.NETHERITE_BLOCK);
            }
            case "plant" -> {
                // ---- 小麦 (Wheat) ----
                storageInfo.add(Items.WHEAT_SEEDS);
                storageInfo.add(Items.WHEAT);

                // ---- 胡萝卜 (Carrot) ----
                // 胡萝卜没有独立种子，种植时直接用胡萝卜本身
                storageInfo.add(Items.CARROT);

                // ---- 马铃薯 (Potato) ----
                storageInfo.add(Items.POTATO);
                storageInfo.add(Items.POISONOUS_POTATO);

                // ---- 甜菜 (Beetroot) ----
                storageInfo.add(Items.BEETROOT_SEEDS);
                storageInfo.add(Items.BEETROOT);

                // ---- 西瓜 (Melon) ----
                storageInfo.add(Items.MELON_SEEDS);
                storageInfo.add(Items.MELON_SLICE);
                storageInfo.add(Items.MELON);

                // ---- 南瓜 (Pumpkin) ----
                storageInfo.add(Items.PUMPKIN_SEEDS);
                storageInfo.add(Items.PUMPKIN);

                // ---- 火把花 (Torch flower) ----
                storageInfo.add(Items.TORCHFLOWER_SEEDS);
                storageInfo.add(Items.TORCHFLOWER);

                // ---- 甘蔗 (Sugar Cane) ----
                // 种在水边的沙子/泥土/草方块上，不属于耕地，但按你要求保留
                storageInfo.add(Items.SUGAR_CANE);
            }
            case "industry" -> {
                // ---- 基础红石元件 ----
                storageInfo.add(Items.REDSTONE);              // 红石粉

                storageInfo.add(Items.REDSTONE_BLOCK);        // 红石块

                storageInfo.add(Items.REDSTONE_TORCH);        // 红石火把

                storageInfo.add(Items.REPEATER);              // 红石中继器

                storageInfo.add(Items.COMPARATOR);            // 红石比较器

                storageInfo.add(Items.REDSTONE_LAMP);         // 红石灯


                // ---- 电源/触发类 ----
                storageInfo.add(Items.LEVER);                 // 拉杆

                storageInfo.add(Items.DAYLIGHT_DETECTOR);     // 阳光探测器

                storageInfo.add(Items.SCULK_SENSOR);          // 幽匿感测体

                storageInfo.add(Items.CALIBRATED_SCULK_SENSOR); // 校准幽匿感测体

                storageInfo.add(Items.LIGHTNING_ROD);         // 避雷针

                storageInfo.add(Items.TARGET);                // 标靶

                storageInfo.add(Items.TRIPWIRE_HOOK);         // 绊线钩

                storageInfo.add(Items.TRAPPED_CHEST);         // 陷阱箱

                storageInfo.add(Items.DETECTOR_RAIL);         // 探测铁轨


                // ---- 按钮 ----
                storageInfo.add(Items.OAK_BUTTON);
                storageInfo.add(Items.SPRUCE_BUTTON);
                storageInfo.add(Items.BIRCH_BUTTON);
                storageInfo.add(Items.JUNGLE_BUTTON);
                storageInfo.add(Items.ACACIA_BUTTON);
                storageInfo.add(Items.DARK_OAK_BUTTON);
                storageInfo.add(Items.MANGROVE_BUTTON);
                storageInfo.add(Items.CHERRY_BUTTON);
                storageInfo.add(Items.BAMBOO_BUTTON);
                storageInfo.add(Items.CRIMSON_BUTTON);
                storageInfo.add(Items.WARPED_BUTTON);
                storageInfo.add(Items.STONE_BUTTON);
                storageInfo.add(Items.POLISHED_BLACKSTONE_BUTTON);

                // ---- 压力板 ----
                storageInfo.add(Items.OAK_PRESSURE_PLATE);
                storageInfo.add(Items.SPRUCE_PRESSURE_PLATE);
                storageInfo.add(Items.BIRCH_PRESSURE_PLATE);
                storageInfo.add(Items.JUNGLE_PRESSURE_PLATE);
                storageInfo.add(Items.ACACIA_PRESSURE_PLATE);
                storageInfo.add(Items.DARK_OAK_PRESSURE_PLATE);
                storageInfo.add(Items.MANGROVE_PRESSURE_PLATE);
                storageInfo.add(Items.CHERRY_PRESSURE_PLATE);
                storageInfo.add(Items.BAMBOO_PRESSURE_PLATE);
                storageInfo.add(Items.CRIMSON_PRESSURE_PLATE);
                storageInfo.add(Items.WARPED_PRESSURE_PLATE);
                storageInfo.add(Items.STONE_PRESSURE_PLATE);
                storageInfo.add(Items.POLISHED_BLACKSTONE_PRESSURE_PLATE);
                storageInfo.add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
                storageInfo.add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE);

                // ---- 传输/机械元件 ----
                storageInfo.add(Items.PISTON);
                storageInfo.add(Items.STICKY_PISTON);
                storageInfo.add(Items.DISPENSER);
                storageInfo.add(Items.DROPPER);
                storageInfo.add(Items.HOPPER);
                storageInfo.add(Items.NOTE_BLOCK);
                storageInfo.add(Items.BELL);
                storageInfo.add(Items.TNT);
                storageInfo.add(Items.OBSERVER);
                storageInfo.add(Items.LECTERN);
                storageInfo.add(Items.JUKEBOX);

                // ---- 铁轨 ----
                storageInfo.add(Items.RAIL);
                storageInfo.add(Items.POWERED_RAIL);
                storageInfo.add(Items.ACTIVATOR_RAIL);

            }
            case "fishing" -> {
                //鱼类
                storageInfo.add(Items.COD);               // 生鳕鱼（守卫者、远古守卫者、北极熊掉落）

                storageInfo.add(Items.SALMON);            // 生鲑鱼（守卫者、远古守卫者掉落）

                storageInfo.add(Items.PUFFERFISH);        // 河豚（守卫者、远古守卫者掉落）

                storageInfo.add(Items.TROPICAL_FISH);     // 热带鱼（守卫者、远古守卫者掉落）
                // ---- 钓鱼可获得杂物 (Junk) ----
                storageInfo.add(Items.LILY_PAD);              // 睡莲

                storageInfo.add(Items.BONE);                  // 骨头

                storageInfo.add(Items.BOWL);                  // 碗

                storageInfo.add(Items.LEATHER);               // 皮革

                storageInfo.add(Items.LEATHER_BOOTS);         // 皮革靴子（通常已损坏）

                storageInfo.add(Items.ROTTEN_FLESH);          // 腐肉

                storageInfo.add(Items.TRIPWIRE_HOOK);         // 绊线钩

                storageInfo.add(Items.STICK);                 // 木棍

                storageInfo.add(Items.STRING);                // 线

                storageInfo.add(Items.INK_SAC);               // 墨囊


                // ---- 仅丛林生物群系可钓到的杂物 ----
                storageInfo.add(Items.BAMBOO);                // 竹子

                storageInfo.add(Items.COCOA_BEANS);           // 可可豆


                // ---- 钓鱼宝藏 (Treasure) 中的可堆叠物品 ----
                // 已排除附魔书、附魔弓、附魔钓鱼竿等不可堆叠物品
                storageInfo.add(Items.SADDLE);                // 马鞍

                storageInfo.add(Items.NAME_TAG);              // 命名牌

                storageInfo.add(Items.NAUTILUS_SHELL);        // 鹦鹉螺壳
            }
            case "buttle" -> {
                // ---- 怪物掉落物 ----
                storageInfo.add(Items.ROTTEN_FLESH);      // 腐肉

                storageInfo.add(Items.BONE);              // 骨头

                storageInfo.add(Items.ARROW);             // 箭

                storageInfo.add(Items.STRING);            // 线

                storageInfo.add(Items.SPIDER_EYE);        // 蜘蛛眼

                storageInfo.add(Items.GUNPOWDER);         // 火药

                storageInfo.add(Items.ENDER_PEARL);       // 末影珍珠

                storageInfo.add(Items.BLAZE_ROD);         // 烈焰棒

                storageInfo.add(Items.GHAST_TEAR);        // 恶魂之泪

                storageInfo.add(Items.PHANTOM_MEMBRANE);  // 幻翼膜

                storageInfo.add(Items.PRISMARINE_SHARD);  // 海晶碎片

                storageInfo.add(Items.PRISMARINE_CRYSTALS); // 海晶砂粒

                storageInfo.add(Items.WET_SPONGE);        // 湿海绵

                storageInfo.add(Items.SHULKER_SHELL);     // 潜影壳

                storageInfo.add(Items.NETHER_STAR);       // 下界之星

                storageInfo.add(Items.DRAGON_EGG);        // 龙蛋

                storageInfo.add(Items.SCULK_CATALYST);    // 幽匿催发体

                storageInfo.add(Items.SLIME_BALL);        // 黏液球

                storageInfo.add(Items.MAGMA_CREAM);       // 岩浆膏

                storageInfo.add(Items.COAL);              // 煤炭（凋灵骷髅掉落）

                storageInfo.add(Items.WITHER_SKELETON_SKULL); // 凋灵骷髅头

                storageInfo.add(Items.EMERALD);           // 绿宝石（掠夺者、卫道士掉落）

                storageInfo.add(Items.TOTEM_OF_UNDYING);  // 不死图腾

                storageInfo.add(Items.LEATHER);           // 皮革（疣猪兽掉落）

                storageInfo.add(Items.PORKCHOP);          // 生猪排（疣猪兽掉落）

                storageInfo.add(Items.GOLD_INGOT);        // 金锭（僵尸猪灵、猪灵、溺尸掉落）

                storageInfo.add(Items.GOLD_NUGGET);       // 金粒（僵尸猪灵、猪灵掉落）

                storageInfo.add(Items.IRON_INGOT);        // 铁锭（僵尸、尸壳掉落）

                storageInfo.add(Items.CARROT);            // 胡萝卜（僵尸、尸壳掉落）

                storageInfo.add(Items.POTATO);            // 马铃薯（僵尸、尸壳掉落）

                storageInfo.add(Items.BAMBOO);            // 竹子（熊猫掉落，但熊猫是动物，此处已排除；若需保留请自行添加）

                storageInfo.add(Items.NAUTILUS_SHELL);    // 鹦鹉螺壳（溺尸掉落）

                storageInfo.add(Items.GLASS_BOTTLE);      // 玻璃瓶（女巫掉落）

                storageInfo.add(Items.REDSTONE);          // 红石粉（女巫掉落）

                storageInfo.add(Items.GLOWSTONE_DUST);    // 萤石粉（女巫掉落）

                storageInfo.add(Items.STICK);             // 木棍（女巫掉落）

                storageInfo.add(Items.SUGAR);             // 糖（女巫掉落）
            }
            case "husbandry" -> {
                // ---- 牛 (Cow) ----
                storageInfo.add(Items.LEATHER);           // 皮革

                storageInfo.add(Items.BEEF);              // 生牛肉


                // ---- 猪 (Pig) ----
                storageInfo.add(Items.PORKCHOP);          // 生猪排


                // ---- 鸡 (Chicken) ----
                storageInfo.add(Items.FEATHER);           // 羽毛

                storageInfo.add(Items.CHICKEN);           // 生鸡肉


                // ---- 羊 (Sheep) ----
                // ---- 羊毛 (Wool) ----
                storageInfo.add(Items.WHITE_WOOL);        // 白色羊毛

                storageInfo.add(Items.ORANGE_WOOL);       // 橙色羊毛

                storageInfo.add(Items.MAGENTA_WOOL);      // 品红色羊毛

                storageInfo.add(Items.LIGHT_BLUE_WOOL);   // 淡蓝色羊毛

                storageInfo.add(Items.YELLOW_WOOL);       // 黄色羊毛

                storageInfo.add(Items.LIME_WOOL);         // 黄绿色羊毛

                storageInfo.add(Items.PINK_WOOL);         // 粉红色羊毛

                storageInfo.add(Items.GRAY_WOOL);         // 灰色羊毛

                storageInfo.add(Items.LIGHT_GRAY_WOOL);   // 淡灰色羊毛

                storageInfo.add(Items.CYAN_WOOL);         // 青色羊毛

                storageInfo.add(Items.PURPLE_WOOL);       // 紫色羊毛

                storageInfo.add(Items.BLUE_WOOL);         // 蓝色羊毛

                storageInfo.add(Items.BROWN_WOOL);        // 棕色羊毛

                storageInfo.add(Items.GREEN_WOOL);        // 绿色羊毛

                storageInfo.add(Items.RED_WOOL);          // 红色羊毛

                storageInfo.add(Items.BLACK_WOOL);        // 黑色羊毛

                storageInfo.add(Items.MUTTON);            // 生羊肉


                // ---- 兔子 (Rabbit) ----
                storageInfo.add(Items.RABBIT_HIDE);       // 兔子皮

                storageInfo.add(Items.RABBIT);            // 生兔肉

                storageInfo.add(Items.RABBIT_FOOT);       // 兔子脚（稀有掉落）


                // ---- 熊猫 (Panda) ----
                storageInfo.add(Items.BAMBOO);            // 竹子


                // ---- 猫 (Cat) ----
                storageInfo.add(Items.STRING);            // 线


                // ---- 海龟 (Turtle) ----
                storageInfo.add(Items.SEAGRASS);          // 海草（成年海龟掉落）

                storageInfo.add(Items.SCUTE);             // 鳞甲（幼年海龟成长为成年时掉落）


                // ---- 雪傀儡 (Snow Golem) ----
                storageInfo.add(Items.SNOWBALL);          // 雪球


                // ---- 铁傀儡 (Iron Golem) ----
                storageInfo.add(Items.IRON_INGOT);        // 铁锭

                storageInfo.add(Items.POPPY);             // 虞美人
            }
            case "building" -> {

                // ---- 混凝土 (Concrete) ----
                storageInfo.add(Items.WHITE_CONCRETE);
                storageInfo.add(Items.ORANGE_CONCRETE);
                storageInfo.add(Items.MAGENTA_CONCRETE);
                storageInfo.add(Items.LIGHT_BLUE_CONCRETE);
                storageInfo.add(Items.YELLOW_CONCRETE);
                storageInfo.add(Items.LIME_CONCRETE);
                storageInfo.add(Items.PINK_CONCRETE);
                storageInfo.add(Items.GRAY_CONCRETE);
                storageInfo.add(Items.LIGHT_GRAY_CONCRETE);
                storageInfo.add(Items.CYAN_CONCRETE);
                storageInfo.add(Items.PURPLE_CONCRETE);
                storageInfo.add(Items.BLUE_CONCRETE);
                storageInfo.add(Items.BROWN_CONCRETE);
                storageInfo.add(Items.GREEN_CONCRETE);
                storageInfo.add(Items.RED_CONCRETE);
                storageInfo.add(Items.BLACK_CONCRETE);

                // ---- 石英系列 ----
                storageInfo.add(Items.QUARTZ_BLOCK);
                storageInfo.add(Items.QUARTZ_PILLAR);
                storageInfo.add(Items.CHISELED_QUARTZ_BLOCK);
                storageInfo.add(Items.SMOOTH_QUARTZ);
                storageInfo.add(Items.QUARTZ_BRICKS);

                // ---- 木质台阶 ----
                storageInfo.add(Items.OAK_SLAB);
                storageInfo.add(Items.SPRUCE_SLAB);
                storageInfo.add(Items.BIRCH_SLAB);
                storageInfo.add(Items.JUNGLE_SLAB);
                storageInfo.add(Items.ACACIA_SLAB);
                storageInfo.add(Items.DARK_OAK_SLAB);
                storageInfo.add(Items.MANGROVE_SLAB);
                storageInfo.add(Items.CHERRY_SLAB);
                storageInfo.add(Items.BAMBOO_SLAB);
                storageInfo.add(Items.CRIMSON_SLAB);
                storageInfo.add(Items.WARPED_SLAB);

                // ---- 石质台阶 ----
                storageInfo.add(Items.STONE_SLAB);
                storageInfo.add(Items.SMOOTH_STONE_SLAB);
                storageInfo.add(Items.COBBLESTONE_SLAB);
                storageInfo.add(Items.MOSSY_COBBLESTONE_SLAB);
                storageInfo.add(Items.STONE_BRICK_SLAB);
                storageInfo.add(Items.MOSSY_STONE_BRICK_SLAB);
                storageInfo.add(Items.GRANITE_SLAB);
                storageInfo.add(Items.POLISHED_GRANITE_SLAB);
                storageInfo.add(Items.DIORITE_SLAB);
                storageInfo.add(Items.POLISHED_DIORITE_SLAB);
                storageInfo.add(Items.ANDESITE_SLAB);
                storageInfo.add(Items.POLISHED_ANDESITE_SLAB);
                storageInfo.add(Items.COBBLED_DEEPSLATE_SLAB);
                storageInfo.add(Items.POLISHED_DEEPSLATE_SLAB);
                storageInfo.add(Items.DEEPSLATE_BRICK_SLAB);
                storageInfo.add(Items.DEEPSLATE_TILE_SLAB);

                // ---- 其他石质台阶 ----
                storageInfo.add(Items.SANDSTONE_SLAB);
                storageInfo.add(Items.SMOOTH_SANDSTONE_SLAB);
                storageInfo.add(Items.CUT_STANDSTONE_SLAB);
                storageInfo.add(Items.RED_SANDSTONE_SLAB);
                storageInfo.add(Items.SMOOTH_RED_SANDSTONE_SLAB);
                storageInfo.add(Items.CUT_RED_SANDSTONE_SLAB);
                storageInfo.add(Items.BRICK_SLAB);
                storageInfo.add(Items.MUD_BRICK_SLAB);
                storageInfo.add(Items.NETHER_BRICK_SLAB);
                storageInfo.add(Items.RED_NETHER_BRICK_SLAB);
                storageInfo.add(Items.BLACKSTONE_SLAB);
                storageInfo.add(Items.POLISHED_BLACKSTONE_SLAB);
                storageInfo.add(Items.POLISHED_BLACKSTONE_BRICK_SLAB);
                storageInfo.add(Items.END_STONE_BRICK_SLAB);
                storageInfo.add(Items.PURPUR_SLAB);
                storageInfo.add(Items.PRISMARINE_SLAB);
                storageInfo.add(Items.PRISMARINE_BRICK_SLAB);
                storageInfo.add(Items.DARK_PRISMARINE_SLAB);

                // ---- 石英台阶 ----
                storageInfo.add(Items.QUARTZ_SLAB);
                storageInfo.add(Items.SMOOTH_QUARTZ_SLAB);

                // ---- 木质楼梯 ----
                storageInfo.add(Items.OAK_STAIRS);
                storageInfo.add(Items.SPRUCE_STAIRS);
                storageInfo.add(Items.BIRCH_STAIRS);
                storageInfo.add(Items.JUNGLE_STAIRS);
                storageInfo.add(Items.ACACIA_STAIRS);
                storageInfo.add(Items.DARK_OAK_STAIRS);
                storageInfo.add(Items.MANGROVE_STAIRS);
                storageInfo.add(Items.CHERRY_STAIRS);
                storageInfo.add(Items.BAMBOO_STAIRS);
                storageInfo.add(Items.CRIMSON_STAIRS);
                storageInfo.add(Items.WARPED_STAIRS);

                // ---- 石质楼梯 ----
                storageInfo.add(Items.STONE_STAIRS);
                storageInfo.add(Items.COBBLESTONE_STAIRS);
                storageInfo.add(Items.MOSSY_COBBLESTONE_STAIRS);
                storageInfo.add(Items.STONE_BRICK_STAIRS);
                storageInfo.add(Items.MOSSY_STONE_BRICK_STAIRS);
                storageInfo.add(Items.GRANITE_STAIRS);
                storageInfo.add(Items.POLISHED_GRANITE_STAIRS);
                storageInfo.add(Items.DIORITE_STAIRS);
                storageInfo.add(Items.POLISHED_DIORITE_STAIRS);
                storageInfo.add(Items.ANDESITE_STAIRS);
                storageInfo.add(Items.POLISHED_ANDESITE_STAIRS);
                storageInfo.add(Items.COBBLED_DEEPSLATE_STAIRS);
                storageInfo.add(Items.POLISHED_DEEPSLATE_STAIRS);
                storageInfo.add(Items.DEEPSLATE_BRICK_STAIRS);
                storageInfo.add(Items.DEEPSLATE_TILE_STAIRS);

                // ---- 其他石质楼梯 ----
                storageInfo.add(Items.SANDSTONE_STAIRS);
                storageInfo.add(Items.SMOOTH_SANDSTONE_STAIRS);
                storageInfo.add(Items.RED_SANDSTONE_STAIRS);
                storageInfo.add(Items.SMOOTH_RED_SANDSTONE_STAIRS);
                storageInfo.add(Items.BRICK_STAIRS);
                storageInfo.add(Items.MUD_BRICK_STAIRS);
                storageInfo.add(Items.NETHER_BRICK_STAIRS);
                storageInfo.add(Items.RED_NETHER_BRICK_STAIRS);
                storageInfo.add(Items.BLACKSTONE_STAIRS);
                storageInfo.add(Items.POLISHED_BLACKSTONE_STAIRS);
                storageInfo.add(Items.POLISHED_BLACKSTONE_BRICK_STAIRS);
                storageInfo.add(Items.END_STONE_BRICK_STAIRS);
                storageInfo.add(Items.PURPUR_STAIRS);
                storageInfo.add(Items.PRISMARINE_STAIRS);
                storageInfo.add(Items.PRISMARINE_BRICK_STAIRS);
                storageInfo.add(Items.DARK_PRISMARINE_STAIRS);

                // ---- 石英楼梯 ----
                storageInfo.add(Items.QUARTZ_STAIRS);
                storageInfo.add(Items.SMOOTH_QUARTZ_STAIRS);

                // ---- 栅栏 ----
                storageInfo.add(Items.OAK_FENCE);
                storageInfo.add(Items.SPRUCE_FENCE);
                storageInfo.add(Items.BIRCH_FENCE);
                storageInfo.add(Items.JUNGLE_FENCE);
                storageInfo.add(Items.ACACIA_FENCE);
                storageInfo.add(Items.DARK_OAK_FENCE);
                storageInfo.add(Items.MANGROVE_FENCE);
                storageInfo.add(Items.CHERRY_FENCE);
                storageInfo.add(Items.BAMBOO_FENCE);
                storageInfo.add(Items.CRIMSON_FENCE);
                storageInfo.add(Items.WARPED_FENCE);
                storageInfo.add(Items.NETHER_BRICK_FENCE);
                // ---- 墙 (Wall) ----
                storageInfo.add(Items.COBBLESTONE_WALL);
                storageInfo.add(Items.MOSSY_COBBLESTONE_WALL);
                storageInfo.add(Items.STONE_BRICK_WALL);
                storageInfo.add(Items.MOSSY_STONE_BRICK_WALL);
                storageInfo.add(Items.GRANITE_WALL);
                storageInfo.add(Items.DIORITE_WALL);
                storageInfo.add(Items.ANDESITE_WALL);
                storageInfo.add(Items.SANDSTONE_WALL);
                storageInfo.add(Items.RED_SANDSTONE_WALL);
                storageInfo.add(Items.BRICK_WALL);
                storageInfo.add(Items.MUD_BRICK_WALL);
                storageInfo.add(Items.NETHER_BRICK_WALL);
                storageInfo.add(Items.RED_NETHER_BRICK_WALL);
                storageInfo.add(Items.END_STONE_BRICK_WALL);
                storageInfo.add(Items.PRISMARINE_WALL);
                storageInfo.add(Items.BLACKSTONE_WALL);
                storageInfo.add(Items.POLISHED_BLACKSTONE_WALL);
                storageInfo.add(Items.POLISHED_BLACKSTONE_BRICK_WALL);
                storageInfo.add(Items.COBBLED_DEEPSLATE_WALL);
                storageInfo.add(Items.POLISHED_DEEPSLATE_WALL);
                storageInfo.add(Items.DEEPSLATE_BRICK_WALL);
                storageInfo.add(Items.DEEPSLATE_TILE_WALL);

                // ---- 门 (Door) ----
                storageInfo.add(Items.OAK_DOOR);
                storageInfo.add(Items.SPRUCE_DOOR);
                storageInfo.add(Items.BIRCH_DOOR);
                storageInfo.add(Items.JUNGLE_DOOR);
                storageInfo.add(Items.ACACIA_DOOR);
                storageInfo.add(Items.DARK_OAK_DOOR);
                storageInfo.add(Items.MANGROVE_DOOR);
                storageInfo.add(Items.CHERRY_DOOR);
                storageInfo.add(Items.BAMBOO_DOOR);
                storageInfo.add(Items.CRIMSON_DOOR);
                storageInfo.add(Items.WARPED_DOOR);
                storageInfo.add(Items.IRON_DOOR);

                // ---- 活板门 (Trapdoor) ----
                storageInfo.add(Items.OAK_TRAPDOOR);
                storageInfo.add(Items.SPRUCE_TRAPDOOR);
                storageInfo.add(Items.BIRCH_TRAPDOOR);
                storageInfo.add(Items.JUNGLE_TRAPDOOR);
                storageInfo.add(Items.ACACIA_TRAPDOOR);
                storageInfo.add(Items.DARK_OAK_TRAPDOOR);
                storageInfo.add(Items.MANGROVE_TRAPDOOR);
                storageInfo.add(Items.CHERRY_TRAPDOOR);
                storageInfo.add(Items.BAMBOO_TRAPDOOR);
                storageInfo.add(Items.CRIMSON_TRAPDOOR);
                storageInfo.add(Items.WARPED_TRAPDOOR);
                storageInfo.add(Items.IRON_TRAPDOOR);

                // ---- 栅栏门 (Fence Gate) ----
                storageInfo.add(Items.OAK_FENCE_GATE);
                storageInfo.add(Items.SPRUCE_FENCE_GATE);
                storageInfo.add(Items.BIRCH_FENCE_GATE);
                storageInfo.add(Items.JUNGLE_FENCE_GATE);
                storageInfo.add(Items.ACACIA_FENCE_GATE);
                storageInfo.add(Items.DARK_OAK_FENCE_GATE);
                storageInfo.add(Items.MANGROVE_FENCE_GATE);
                storageInfo.add(Items.CHERRY_FENCE_GATE);
                storageInfo.add(Items.BAMBOO_FENCE_GATE);
                storageInfo.add(Items.CRIMSON_FENCE_GATE);
                storageInfo.add(Items.WARPED_FENCE_GATE);

                // ---- 石头 ----
                storageInfo.add(Items.STONE);
                storageInfo.add(Items.SMOOTH_STONE);
                storageInfo.add(Items.COBBLESTONE);
                storageInfo.add(Items.MOSSY_COBBLESTONE);
                storageInfo.add(Items.STONE_BRICKS);
                storageInfo.add(Items.MOSSY_STONE_BRICKS);
                storageInfo.add(Items.CRACKED_STONE_BRICKS);
                storageInfo.add(Items.CHISELED_STONE_BRICKS);
                storageInfo.add(Items.GRANITE);
                storageInfo.add(Items.POLISHED_GRANITE);
                storageInfo.add(Items.DIORITE);
                storageInfo.add(Items.POLISHED_DIORITE);
                storageInfo.add(Items.ANDESITE);
                storageInfo.add(Items.POLISHED_ANDESITE);
                storageInfo.add(Items.DEEPSLATE);
                storageInfo.add(Items.COBBLED_DEEPSLATE);
                storageInfo.add(Items.POLISHED_DEEPSLATE);
                storageInfo.add(Items.DEEPSLATE_BRICKS);
                storageInfo.add(Items.DEEPSLATE_TILES);
                storageInfo.add(Items.CHISELED_DEEPSLATE);
                storageInfo.add(Items.CRACKED_DEEPSLATE_BRICKS);
                storageInfo.add(Items.CRACKED_DEEPSLATE_TILES);
                storageInfo.add(Items.TUFF);
                storageInfo.add(Items.CALCITE);
                storageInfo.add(Items.DRIPSTONE_BLOCK);
                storageInfo.add(Items.BLACKSTONE);
                storageInfo.add(Items.POLISHED_BLACKSTONE);
                storageInfo.add(Items.POLISHED_BLACKSTONE_BRICKS);
                storageInfo.add(Items.CHISELED_POLISHED_BLACKSTONE);
                storageInfo.add(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
                storageInfo.add(Items.BASALT);
                storageInfo.add(Items.POLISHED_BASALT);
                storageInfo.add(Items.SMOOTH_BASALT);
            }
        }
    }
}
