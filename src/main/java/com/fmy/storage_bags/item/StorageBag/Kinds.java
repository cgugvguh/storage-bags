package com.fmy.storage_bags.item.StorageBag;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

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
    BATTLE("battle"),
    BUILDING("building"),
    HUSBANDRY("husbandry"),
    CUSTOM("custom");
    private final String kindName;//种类名
    private final List<Item> itemKind = new ArrayList<>();//物品种类信息
    Kinds(String kindName) {
        this.kindName = kindName;//设置种类信息
        setStorage();//设置存储信息
    }
    public List<Item> getItemKind(){
        return itemKind;
    }
    public static Kinds getKindFromName(String name){
        return switch (name) {
            case "cutting_tree" -> Kinds.CUTTING_TREE;
            case "mining_ore" -> Kinds.MINING_ORE;
            case "plant" -> Kinds.PLANT;
            case "industry" -> Kinds.INDUSTRY;
            case "fishing" -> Kinds.FISHING;
            case "battle" -> Kinds.BATTLE;
            case "building" -> Kinds.BUILDING;
            case "husbandry" -> Kinds.HUSBANDRY;
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
                itemKind.add(Items.OAK_LOG);
                itemKind.add(Items.OAK_WOOD );
                itemKind.add(Items.STRIPPED_OAK_LOG );
                itemKind.add(Items.STRIPPED_OAK_WOOD);
                itemKind.add(Items.OAK_PLANKS);
                itemKind.add(Items.OAK_SAPLING);
                itemKind.add(Items.OAK_LEAVES);

                // ---- 云杉木 (Spruce) ----
                itemKind.add(Items.SPRUCE_LOG);
                itemKind.add(Items.SPRUCE_WOOD);
                itemKind.add(Items.STRIPPED_SPRUCE_LOG);
                itemKind.add(Items.STRIPPED_SPRUCE_WOOD);
                itemKind.add(Items.SPRUCE_PLANKS);
                itemKind.add(Items.SPRUCE_SAPLING);
                itemKind.add(Items.SPRUCE_LEAVES);

                // ---- 白桦木 (Birch) ----
                itemKind.add(Items.BIRCH_LOG);
                itemKind.add(Items.BIRCH_WOOD);
                itemKind.add(Items.STRIPPED_BIRCH_LOG);
                itemKind.add(Items.STRIPPED_BIRCH_WOOD);
                itemKind.add(Items.BIRCH_PLANKS);
                itemKind.add(Items.BIRCH_SAPLING);
                itemKind.add(Items.BIRCH_LEAVES);

                // ---- 丛林木 (Jungle) ----
                itemKind.add(Items.JUNGLE_LOG);
                itemKind.add(Items.JUNGLE_WOOD);
                itemKind.add(Items.STRIPPED_JUNGLE_LOG);
                itemKind.add(Items.STRIPPED_JUNGLE_WOOD);
                itemKind.add(Items.JUNGLE_PLANKS);
                itemKind.add(Items.JUNGLE_SAPLING);
                itemKind.add(Items.JUNGLE_LEAVES);

                // ---- 金合欢木 (Acacia) ----
                itemKind.add(Items.ACACIA_LOG);
                itemKind.add(Items.ACACIA_WOOD);
                itemKind.add(Items.STRIPPED_ACACIA_LOG);
                itemKind.add(Items.STRIPPED_ACACIA_WOOD);
                itemKind.add(Items.ACACIA_PLANKS);
                itemKind.add(Items.ACACIA_SAPLING);
                itemKind.add(Items.ACACIA_LEAVES);

                // ---- 深色橡木 (Dark Oak) ----
                itemKind.add(Items.DARK_OAK_LOG);
                itemKind.add(Items.DARK_OAK_WOOD);
                itemKind.add(Items.STRIPPED_DARK_OAK_LOG);
                itemKind.add(Items.STRIPPED_DARK_OAK_WOOD);
                itemKind.add(Items.DARK_OAK_PLANKS);
                itemKind.add(Items.DARK_OAK_SAPLING);
                itemKind.add(Items.DARK_OAK_LEAVES);

                // ---- 红树木 (Mangrove) ----
                itemKind.add(Items.MANGROVE_LOG);
                itemKind.add(Items.MANGROVE_WOOD);
                itemKind.add(Items.STRIPPED_MANGROVE_LOG);
                itemKind.add(Items.STRIPPED_MANGROVE_WOOD);
                itemKind.add(Items.MANGROVE_PLANKS);
                itemKind.add(Items.MANGROVE_PROPAGULE);
                itemKind.add(Items.MANGROVE_LEAVES);

                // ---- 樱花木 (Cherry) ----
                itemKind.add(Items.CHERRY_LOG);
                itemKind.add(Items.CHERRY_WOOD);
                itemKind.add(Items.STRIPPED_CHERRY_LOG);
                itemKind.add(Items.STRIPPED_CHERRY_WOOD);
                itemKind.add(Items.CHERRY_PLANKS);
                itemKind.add(Items.CHERRY_SAPLING);
                itemKind.add(Items.CHERRY_LEAVES);

                // ---- 竹块 (Bamboo) ----
                itemKind.add(Items.BAMBOO_BLOCK);
                itemKind.add(Items.STRIPPED_BAMBOO_BLOCK);
                itemKind.add(Items.BAMBOO_PLANKS);
                itemKind.add(Items.BAMBOO_MOSAIC);
                itemKind.add(Items.BAMBOO);

                // ---- 绯红菌 (Crimson) ----
                itemKind.add(Items.CRIMSON_STEM);
                itemKind.add(Items.CRIMSON_HYPHAE);
                itemKind.add(Items.STRIPPED_CRIMSON_STEM);
                itemKind.add(Items.STRIPPED_CRIMSON_HYPHAE);
                itemKind.add(Items.CRIMSON_PLANKS);
                itemKind.add(Items.CRIMSON_FUNGUS);

                // ---- 诡异菌 (Warped) ----
                itemKind.add(Items.WARPED_STEM);
                itemKind.add(Items.WARPED_HYPHAE);
                itemKind.add(Items.STRIPPED_WARPED_STEM);
                itemKind.add(Items.STRIPPED_WARPED_HYPHAE);
                itemKind.add(Items.WARPED_PLANKS);
                itemKind.add(Items.WARPED_FUNGUS);
            }
            case "mining_ore" -> {
                // ---- 煤炭 (Coal) ----
                itemKind.add(Items.COAL);
                itemKind.add(Items.COAL_ORE);
                itemKind.add(Items.DEEPSLATE_COAL_ORE);
                itemKind.add(Items.COAL_BLOCK);

                // ---- 铁 (Iron) ----
                itemKind.add(Items.RAW_IRON);
                itemKind.add(Items.IRON_INGOT);
                itemKind.add(Items.IRON_ORE);
                itemKind.add(Items.DEEPSLATE_IRON_ORE);
                itemKind.add(Items.RAW_IRON_BLOCK);
                itemKind.add(Items.IRON_BLOCK);
                itemKind.add(Items.IRON_NUGGET);

                // ---- 铜 (Copper) ----
                itemKind.add(Items.RAW_COPPER);
                itemKind.add(Items.COPPER_INGOT);
                itemKind.add(Items.COPPER_ORE);
                itemKind.add(Items.DEEPSLATE_COPPER_ORE);
                itemKind.add(Items.RAW_COPPER_BLOCK);
                itemKind.add(Items.COPPER_BLOCK);

                // ---- 金 (Gold) ----
                itemKind.add(Items.RAW_GOLD);
                itemKind.add(Items.GOLD_INGOT);
                itemKind.add(Items.GOLD_ORE);
                itemKind.add(Items.DEEPSLATE_GOLD_ORE);
                itemKind.add(Items.NETHER_GOLD_ORE);
                itemKind.add(Items.RAW_GOLD_BLOCK);
                itemKind.add(Items.GOLD_BLOCK);
                itemKind.add(Items.GOLD_NUGGET);

                // ---- 红石 (Redstone) ----
                itemKind.add(Items.REDSTONE);
                itemKind.add(Items.REDSTONE_ORE);
                itemKind.add(Items.DEEPSLATE_REDSTONE_ORE);
                itemKind.add(Items.REDSTONE_BLOCK);

                // ---- 青金石 (Lapis Lazuli) ----
                itemKind.add(Items.LAPIS_LAZULI);
                itemKind.add(Items.LAPIS_ORE);
                itemKind.add(Items.DEEPSLATE_LAPIS_ORE);
                itemKind.add(Items.LAPIS_BLOCK);

                // ---- 钻石 (Diamond) ----
                itemKind.add(Items.DIAMOND);
                itemKind.add(Items.DIAMOND_ORE);
                itemKind.add(Items.DEEPSLATE_DIAMOND_ORE);
                itemKind.add(Items.DIAMOND_BLOCK);

                // ---- 绿宝石 (Emerald) ----
                itemKind.add(Items.EMERALD);
                itemKind.add(Items.EMERALD_ORE);
                itemKind.add(Items.DEEPSLATE_EMERALD_ORE);
                itemKind.add(Items.EMERALD_BLOCK);

                // ---- 下界石英 (Nether Quartz) ----
                // 注意：石英块 (QUARTZ_BLOCK) 为不可逆合成，已排除
                itemKind.add(Items.QUARTZ);
                itemKind.add(Items.NETHER_QUARTZ_ORE);

                // ---- 下界合金 (Netherite) ----
                itemKind.add(Items.NETHERITE_SCRAP);
                itemKind.add(Items.NETHERITE_INGOT);
                itemKind.add(Items.ANCIENT_DEBRIS);
                itemKind.add(Items.NETHERITE_BLOCK);
            }
            case "plant" -> {
                // ---- 小麦 (Wheat) ----
                itemKind.add(Items.WHEAT_SEEDS);
                itemKind.add(Items.WHEAT);

                // ---- 胡萝卜 (Carrot) ----
                // 胡萝卜没有独立种子，种植时直接用胡萝卜本身
                itemKind.add(Items.CARROT);

                // ---- 马铃薯 (Potato) ----
                itemKind.add(Items.POTATO);
                itemKind.add(Items.POISONOUS_POTATO);

                // ---- 甜菜 (Beetroot) ----
                itemKind.add(Items.BEETROOT_SEEDS);
                itemKind.add(Items.BEETROOT);

                // ---- 西瓜 (Melon) ----
                itemKind.add(Items.MELON_SEEDS);
                itemKind.add(Items.MELON_SLICE);
                itemKind.add(Items.MELON);

                // ---- 南瓜 (Pumpkin) ----
                itemKind.add(Items.PUMPKIN_SEEDS);
                itemKind.add(Items.PUMPKIN);

                // ---- 火把花 (Torch flower) ----
                itemKind.add(Items.TORCHFLOWER_SEEDS);
                itemKind.add(Items.TORCHFLOWER);

                // ---- 甘蔗 (Sugar Cane) ----
                // 种在水边的沙子/泥土/草方块上，不属于耕地，但按你要求保留
                itemKind.add(Items.SUGAR_CANE);
            }
            case "industry" -> {
                // ---- 基础红石元件 ----
                itemKind.add(Items.REDSTONE);              // 红石粉

                itemKind.add(Items.REDSTONE_BLOCK);        // 红石块

                itemKind.add(Items.REDSTONE_TORCH);        // 红石火把

                itemKind.add(Items.REPEATER);              // 红石中继器

                itemKind.add(Items.COMPARATOR);            // 红石比较器

                itemKind.add(Items.REDSTONE_LAMP);         // 红石灯


                // ---- 电源/触发类 ----
                itemKind.add(Items.LEVER);                 // 拉杆

                itemKind.add(Items.DAYLIGHT_DETECTOR);     // 阳光探测器

                itemKind.add(Items.SCULK_SENSOR);          // 幽匿感测体

                itemKind.add(Items.CALIBRATED_SCULK_SENSOR); // 校准幽匿感测体

                itemKind.add(Items.LIGHTNING_ROD);         // 避雷针

                itemKind.add(Items.TARGET);                // 标靶

                itemKind.add(Items.TRIPWIRE_HOOK);         // 绊线钩

                itemKind.add(Items.TRAPPED_CHEST);         // 陷阱箱

                itemKind.add(Items.DETECTOR_RAIL);         // 探测铁轨


                // ---- 按钮 ----
                itemKind.add(Items.OAK_BUTTON);
                itemKind.add(Items.SPRUCE_BUTTON);
                itemKind.add(Items.BIRCH_BUTTON);
                itemKind.add(Items.JUNGLE_BUTTON);
                itemKind.add(Items.ACACIA_BUTTON);
                itemKind.add(Items.DARK_OAK_BUTTON);
                itemKind.add(Items.MANGROVE_BUTTON);
                itemKind.add(Items.CHERRY_BUTTON);
                itemKind.add(Items.BAMBOO_BUTTON);
                itemKind.add(Items.CRIMSON_BUTTON);
                itemKind.add(Items.WARPED_BUTTON);
                itemKind.add(Items.STONE_BUTTON);
                itemKind.add(Items.POLISHED_BLACKSTONE_BUTTON);

                // ---- 压力板 ----
                itemKind.add(Items.OAK_PRESSURE_PLATE);
                itemKind.add(Items.SPRUCE_PRESSURE_PLATE);
                itemKind.add(Items.BIRCH_PRESSURE_PLATE);
                itemKind.add(Items.JUNGLE_PRESSURE_PLATE);
                itemKind.add(Items.ACACIA_PRESSURE_PLATE);
                itemKind.add(Items.DARK_OAK_PRESSURE_PLATE);
                itemKind.add(Items.MANGROVE_PRESSURE_PLATE);
                itemKind.add(Items.CHERRY_PRESSURE_PLATE);
                itemKind.add(Items.BAMBOO_PRESSURE_PLATE);
                itemKind.add(Items.CRIMSON_PRESSURE_PLATE);
                itemKind.add(Items.WARPED_PRESSURE_PLATE);
                itemKind.add(Items.STONE_PRESSURE_PLATE);
                itemKind.add(Items.POLISHED_BLACKSTONE_PRESSURE_PLATE);
                itemKind.add(Items.LIGHT_WEIGHTED_PRESSURE_PLATE);
                itemKind.add(Items.HEAVY_WEIGHTED_PRESSURE_PLATE);

                // ---- 传输/机械元件 ----
                itemKind.add(Items.PISTON);
                itemKind.add(Items.STICKY_PISTON);
                itemKind.add(Items.DISPENSER);
                itemKind.add(Items.DROPPER);
                itemKind.add(Items.HOPPER);
                itemKind.add(Items.NOTE_BLOCK);
                itemKind.add(Items.BELL);
                itemKind.add(Items.TNT);
                itemKind.add(Items.OBSERVER);
                itemKind.add(Items.LECTERN);
                itemKind.add(Items.JUKEBOX);

                // ---- 铁轨 ----
                itemKind.add(Items.RAIL);
                itemKind.add(Items.POWERED_RAIL);
                itemKind.add(Items.ACTIVATOR_RAIL);

            }
            case "fishing" -> {
                //鱼类
                itemKind.add(Items.COD);               // 生鳕鱼（守卫者、远古守卫者、北极熊掉落）

                itemKind.add(Items.SALMON);            // 生鲑鱼（守卫者、远古守卫者掉落）

                itemKind.add(Items.PUFFERFISH);        // 河豚（守卫者、远古守卫者掉落）

                itemKind.add(Items.TROPICAL_FISH);     // 热带鱼（守卫者、远古守卫者掉落）
                // ---- 钓鱼可获得杂物 (Junk) ----
                itemKind.add(Items.LILY_PAD);              // 睡莲

                itemKind.add(Items.BONE);                  // 骨头

                itemKind.add(Items.BOWL);                  // 碗

                itemKind.add(Items.LEATHER);               // 皮革

                itemKind.add(Items.LEATHER_BOOTS);         // 皮革靴子（通常已损坏）

                itemKind.add(Items.ROTTEN_FLESH);          // 腐肉

                itemKind.add(Items.TRIPWIRE_HOOK);         // 绊线钩

                itemKind.add(Items.STICK);                 // 木棍

                itemKind.add(Items.STRING);                // 线

                itemKind.add(Items.INK_SAC);               // 墨囊


                // ---- 仅丛林生物群系可钓到的杂物 ----
                itemKind.add(Items.BAMBOO);                // 竹子

                itemKind.add(Items.COCOA_BEANS);           // 可可豆


                // ---- 钓鱼宝藏 (Treasure) 中的可堆叠物品 ----
                // 已排除附魔书、附魔弓、附魔钓鱼竿等不可堆叠物品
                itemKind.add(Items.SADDLE);                // 马鞍

                itemKind.add(Items.NAME_TAG);              // 命名牌

                itemKind.add(Items.NAUTILUS_SHELL);        // 鹦鹉螺壳
            }
            case "battle" -> {
                // ---- 怪物掉落物 ----
                itemKind.add(Items.ROTTEN_FLESH);      // 腐肉

                itemKind.add(Items.BONE);              // 骨头

                itemKind.add(Items.ARROW);             // 箭

                itemKind.add(Items.STRING);            // 线

                itemKind.add(Items.SPIDER_EYE);        // 蜘蛛眼

                itemKind.add(Items.GUNPOWDER);         // 火药

                itemKind.add(Items.ENDER_PEARL);       // 末影珍珠

                itemKind.add(Items.BLAZE_ROD);         // 烈焰棒

                itemKind.add(Items.GHAST_TEAR);        // 恶魂之泪

                itemKind.add(Items.PHANTOM_MEMBRANE);  // 幻翼膜

                itemKind.add(Items.PRISMARINE_SHARD);  // 海晶碎片

                itemKind.add(Items.PRISMARINE_CRYSTALS); // 海晶砂粒

                itemKind.add(Items.WET_SPONGE);        // 湿海绵

                itemKind.add(Items.SHULKER_SHELL);     // 潜影壳

                itemKind.add(Items.NETHER_STAR);       // 下界之星

                itemKind.add(Items.DRAGON_EGG);        // 龙蛋

                itemKind.add(Items.SCULK_CATALYST);    // 幽匿催发体

                itemKind.add(Items.SLIME_BALL);        // 黏液球

                itemKind.add(Items.MAGMA_CREAM);       // 岩浆膏

                itemKind.add(Items.COAL);              // 煤炭（凋灵骷髅掉落）

                itemKind.add(Items.WITHER_SKELETON_SKULL); // 凋灵骷髅头

                itemKind.add(Items.EMERALD);           // 绿宝石（掠夺者、卫道士掉落）

                itemKind.add(Items.TOTEM_OF_UNDYING);  // 不死图腾

                itemKind.add(Items.LEATHER);           // 皮革（疣猪兽掉落）

                itemKind.add(Items.PORKCHOP);          // 生猪排（疣猪兽掉落）

                itemKind.add(Items.GOLD_INGOT);        // 金锭（僵尸猪灵、猪灵、溺尸掉落）

                itemKind.add(Items.GOLD_NUGGET);       // 金粒（僵尸猪灵、猪灵掉落）

                itemKind.add(Items.IRON_INGOT);        // 铁锭（僵尸、尸壳掉落）

                itemKind.add(Items.CARROT);            // 胡萝卜（僵尸、尸壳掉落）

                itemKind.add(Items.POTATO);            // 马铃薯（僵尸、尸壳掉落）

                itemKind.add(Items.BAMBOO);            // 竹子（熊猫掉落，但熊猫是动物，此处已排除；若需保留请自行添加）

                itemKind.add(Items.NAUTILUS_SHELL);    // 鹦鹉螺壳（溺尸掉落）

                itemKind.add(Items.GLASS_BOTTLE);      // 玻璃瓶（女巫掉落）

                itemKind.add(Items.REDSTONE);          // 红石粉（女巫掉落）

                itemKind.add(Items.GLOWSTONE_DUST);    // 萤石粉（女巫掉落）

                itemKind.add(Items.STICK);             // 木棍（女巫掉落）

                itemKind.add(Items.SUGAR);             // 糖（女巫掉落）
            }
            case "husbandry" -> {
                // ---- 牛 (Cow) ----
                itemKind.add(Items.LEATHER);           // 皮革

                itemKind.add(Items.BEEF);              // 生牛肉

                // ---- 猪 (Pig) ----
                itemKind.add(Items.PORKCHOP);          // 生猪排

                // ---- 鸡 (Chicken) ----
                itemKind.add(Items.FEATHER);           // 羽毛

                itemKind.add(Items.CHICKEN);           // 生鸡肉

                // ---- 兔子 (Rabbit) ----
                itemKind.add(Items.RABBIT_HIDE);       // 兔子皮

                itemKind.add(Items.RABBIT);            // 生兔肉

                itemKind.add(Items.RABBIT_FOOT);       // 兔子脚（稀有掉落）

                // ---- 熊猫 (Panda) ----
                itemKind.add(Items.BAMBOO);            // 竹子

                // ---- 猫 (Cat) ----
                itemKind.add(Items.STRING);            // 线

                // ---- 海龟 (Turtle) ----
                itemKind.add(Items.SEAGRASS);          // 海草（成年海龟掉落）

                itemKind.add(Items.SCUTE);             // 鳞甲（幼年海龟成长为成年时掉落）

                // ---- 羊 (Sheep) ----
                // ---- 羊毛 (Wool) ----
                itemKind.add(Items.MUTTON);            // 生羊肉

                itemKind.add(Items.WHITE_WOOL);        // 白色羊毛

                itemKind.add(Items.ORANGE_WOOL);       // 橙色羊毛

                itemKind.add(Items.MAGENTA_WOOL);      // 品红色羊毛

                itemKind.add(Items.LIGHT_BLUE_WOOL);   // 淡蓝色羊毛

                itemKind.add(Items.YELLOW_WOOL);       // 黄色羊毛

                itemKind.add(Items.LIME_WOOL);         // 黄绿色羊毛

                itemKind.add(Items.PINK_WOOL);         // 粉红色羊毛

                itemKind.add(Items.GRAY_WOOL);         // 灰色羊毛

                itemKind.add(Items.LIGHT_GRAY_WOOL);   // 淡灰色羊毛

                itemKind.add(Items.CYAN_WOOL);         // 青色羊毛

                itemKind.add(Items.PURPLE_WOOL);       // 紫色羊毛

                itemKind.add(Items.BLUE_WOOL);         // 蓝色羊毛

                itemKind.add(Items.BROWN_WOOL);        // 棕色羊毛

                itemKind.add(Items.GREEN_WOOL);        // 绿色羊毛

                itemKind.add(Items.RED_WOOL);          // 红色羊毛

                itemKind.add(Items.BLACK_WOOL);        // 黑色羊毛

            }
            case "building" -> {

                // ---- 混凝土 (Concrete) ----
                itemKind.add(Items.WHITE_CONCRETE);
                itemKind.add(Items.ORANGE_CONCRETE);
                itemKind.add(Items.MAGENTA_CONCRETE);
                itemKind.add(Items.LIGHT_BLUE_CONCRETE);
                itemKind.add(Items.YELLOW_CONCRETE);
                itemKind.add(Items.LIME_CONCRETE);
                itemKind.add(Items.PINK_CONCRETE);
                itemKind.add(Items.GRAY_CONCRETE);
                itemKind.add(Items.LIGHT_GRAY_CONCRETE);
                itemKind.add(Items.CYAN_CONCRETE);
                itemKind.add(Items.PURPLE_CONCRETE);
                itemKind.add(Items.BLUE_CONCRETE);
                itemKind.add(Items.BROWN_CONCRETE);
                itemKind.add(Items.GREEN_CONCRETE);
                itemKind.add(Items.RED_CONCRETE);
                itemKind.add(Items.BLACK_CONCRETE);

                // ---- 石英系列 ----
                itemKind.add(Items.QUARTZ_BLOCK);
                itemKind.add(Items.QUARTZ_PILLAR);
                itemKind.add(Items.CHISELED_QUARTZ_BLOCK);
                itemKind.add(Items.SMOOTH_QUARTZ);
                itemKind.add(Items.QUARTZ_BRICKS);

                // ---- 木质台阶 ----
                itemKind.add(Items.OAK_SLAB);
                itemKind.add(Items.SPRUCE_SLAB);
                itemKind.add(Items.BIRCH_SLAB);
                itemKind.add(Items.JUNGLE_SLAB);
                itemKind.add(Items.ACACIA_SLAB);
                itemKind.add(Items.DARK_OAK_SLAB);
                itemKind.add(Items.MANGROVE_SLAB);
                itemKind.add(Items.CHERRY_SLAB);
                itemKind.add(Items.BAMBOO_SLAB);
                itemKind.add(Items.CRIMSON_SLAB);
                itemKind.add(Items.WARPED_SLAB);

                // ---- 石质台阶 ----
                itemKind.add(Items.STONE_SLAB);
                itemKind.add(Items.SMOOTH_STONE_SLAB);
                itemKind.add(Items.COBBLESTONE_SLAB);
                itemKind.add(Items.MOSSY_COBBLESTONE_SLAB);
                itemKind.add(Items.STONE_BRICK_SLAB);
                itemKind.add(Items.MOSSY_STONE_BRICK_SLAB);
                itemKind.add(Items.GRANITE_SLAB);
                itemKind.add(Items.POLISHED_GRANITE_SLAB);
                itemKind.add(Items.DIORITE_SLAB);
                itemKind.add(Items.POLISHED_DIORITE_SLAB);
                itemKind.add(Items.ANDESITE_SLAB);
                itemKind.add(Items.POLISHED_ANDESITE_SLAB);
                itemKind.add(Items.COBBLED_DEEPSLATE_SLAB);
                itemKind.add(Items.POLISHED_DEEPSLATE_SLAB);
                itemKind.add(Items.DEEPSLATE_BRICK_SLAB);
                itemKind.add(Items.DEEPSLATE_TILE_SLAB);

                // ---- 其他石质台阶 ----
                itemKind.add(Items.SANDSTONE_SLAB);
                itemKind.add(Items.SMOOTH_SANDSTONE_SLAB);
                itemKind.add(Items.CUT_STANDSTONE_SLAB);
                itemKind.add(Items.RED_SANDSTONE_SLAB);
                itemKind.add(Items.SMOOTH_RED_SANDSTONE_SLAB);
                itemKind.add(Items.CUT_RED_SANDSTONE_SLAB);
                itemKind.add(Items.BRICK_SLAB);
                itemKind.add(Items.MUD_BRICK_SLAB);
                itemKind.add(Items.NETHER_BRICK_SLAB);
                itemKind.add(Items.RED_NETHER_BRICK_SLAB);
                itemKind.add(Items.BLACKSTONE_SLAB);
                itemKind.add(Items.POLISHED_BLACKSTONE_SLAB);
                itemKind.add(Items.POLISHED_BLACKSTONE_BRICK_SLAB);
                itemKind.add(Items.END_STONE_BRICK_SLAB);
                itemKind.add(Items.PURPUR_SLAB);
                itemKind.add(Items.PRISMARINE_SLAB);
                itemKind.add(Items.PRISMARINE_BRICK_SLAB);
                itemKind.add(Items.DARK_PRISMARINE_SLAB);

                // ---- 石英台阶 ----
                itemKind.add(Items.QUARTZ_SLAB);
                itemKind.add(Items.SMOOTH_QUARTZ_SLAB);

                // ---- 木质楼梯 ----
                itemKind.add(Items.OAK_STAIRS);
                itemKind.add(Items.SPRUCE_STAIRS);
                itemKind.add(Items.BIRCH_STAIRS);
                itemKind.add(Items.JUNGLE_STAIRS);
                itemKind.add(Items.ACACIA_STAIRS);
                itemKind.add(Items.DARK_OAK_STAIRS);
                itemKind.add(Items.MANGROVE_STAIRS);
                itemKind.add(Items.CHERRY_STAIRS);
                itemKind.add(Items.BAMBOO_STAIRS);
                itemKind.add(Items.CRIMSON_STAIRS);
                itemKind.add(Items.WARPED_STAIRS);

                // ---- 石质楼梯 ----
                itemKind.add(Items.STONE_STAIRS);
                itemKind.add(Items.COBBLESTONE_STAIRS);
                itemKind.add(Items.MOSSY_COBBLESTONE_STAIRS);
                itemKind.add(Items.STONE_BRICK_STAIRS);
                itemKind.add(Items.MOSSY_STONE_BRICK_STAIRS);
                itemKind.add(Items.GRANITE_STAIRS);
                itemKind.add(Items.POLISHED_GRANITE_STAIRS);
                itemKind.add(Items.DIORITE_STAIRS);
                itemKind.add(Items.POLISHED_DIORITE_STAIRS);
                itemKind.add(Items.ANDESITE_STAIRS);
                itemKind.add(Items.POLISHED_ANDESITE_STAIRS);
                itemKind.add(Items.COBBLED_DEEPSLATE_STAIRS);
                itemKind.add(Items.POLISHED_DEEPSLATE_STAIRS);
                itemKind.add(Items.DEEPSLATE_BRICK_STAIRS);
                itemKind.add(Items.DEEPSLATE_TILE_STAIRS);

                // ---- 其他石质楼梯 ----
                itemKind.add(Items.SANDSTONE_STAIRS);
                itemKind.add(Items.SMOOTH_SANDSTONE_STAIRS);
                itemKind.add(Items.RED_SANDSTONE_STAIRS);
                itemKind.add(Items.SMOOTH_RED_SANDSTONE_STAIRS);
                itemKind.add(Items.BRICK_STAIRS);
                itemKind.add(Items.MUD_BRICK_STAIRS);
                itemKind.add(Items.NETHER_BRICK_STAIRS);
                itemKind.add(Items.RED_NETHER_BRICK_STAIRS);
                itemKind.add(Items.BLACKSTONE_STAIRS);
                itemKind.add(Items.POLISHED_BLACKSTONE_STAIRS);
                itemKind.add(Items.POLISHED_BLACKSTONE_BRICK_STAIRS);
                itemKind.add(Items.END_STONE_BRICK_STAIRS);
                itemKind.add(Items.PURPUR_STAIRS);
                itemKind.add(Items.PRISMARINE_STAIRS);
                itemKind.add(Items.PRISMARINE_BRICK_STAIRS);
                itemKind.add(Items.DARK_PRISMARINE_STAIRS);

                // ---- 石英楼梯 ----
                itemKind.add(Items.QUARTZ_STAIRS);
                itemKind.add(Items.SMOOTH_QUARTZ_STAIRS);

                // ---- 栅栏 ----
                itemKind.add(Items.OAK_FENCE);
                itemKind.add(Items.SPRUCE_FENCE);
                itemKind.add(Items.BIRCH_FENCE);
                itemKind.add(Items.JUNGLE_FENCE);
                itemKind.add(Items.ACACIA_FENCE);
                itemKind.add(Items.DARK_OAK_FENCE);
                itemKind.add(Items.MANGROVE_FENCE);
                itemKind.add(Items.CHERRY_FENCE);
                itemKind.add(Items.BAMBOO_FENCE);
                itemKind.add(Items.CRIMSON_FENCE);
                itemKind.add(Items.WARPED_FENCE);
                itemKind.add(Items.NETHER_BRICK_FENCE);
                // ---- 墙 (Wall) ----
                itemKind.add(Items.COBBLESTONE_WALL);
                itemKind.add(Items.MOSSY_COBBLESTONE_WALL);
                itemKind.add(Items.STONE_BRICK_WALL);
                itemKind.add(Items.MOSSY_STONE_BRICK_WALL);
                itemKind.add(Items.GRANITE_WALL);
                itemKind.add(Items.DIORITE_WALL);
                itemKind.add(Items.ANDESITE_WALL);
                itemKind.add(Items.SANDSTONE_WALL);
                itemKind.add(Items.RED_SANDSTONE_WALL);
                itemKind.add(Items.BRICK_WALL);
                itemKind.add(Items.MUD_BRICK_WALL);
                itemKind.add(Items.NETHER_BRICK_WALL);
                itemKind.add(Items.RED_NETHER_BRICK_WALL);
                itemKind.add(Items.END_STONE_BRICK_WALL);
                itemKind.add(Items.PRISMARINE_WALL);
                itemKind.add(Items.BLACKSTONE_WALL);
                itemKind.add(Items.POLISHED_BLACKSTONE_WALL);
                itemKind.add(Items.POLISHED_BLACKSTONE_BRICK_WALL);
                itemKind.add(Items.COBBLED_DEEPSLATE_WALL);
                itemKind.add(Items.POLISHED_DEEPSLATE_WALL);
                itemKind.add(Items.DEEPSLATE_BRICK_WALL);
                itemKind.add(Items.DEEPSLATE_TILE_WALL);

                // ---- 门 (Door) ----
                itemKind.add(Items.OAK_DOOR);
                itemKind.add(Items.SPRUCE_DOOR);
                itemKind.add(Items.BIRCH_DOOR);
                itemKind.add(Items.JUNGLE_DOOR);
                itemKind.add(Items.ACACIA_DOOR);
                itemKind.add(Items.DARK_OAK_DOOR);
                itemKind.add(Items.MANGROVE_DOOR);
                itemKind.add(Items.CHERRY_DOOR);
                itemKind.add(Items.BAMBOO_DOOR);
                itemKind.add(Items.CRIMSON_DOOR);
                itemKind.add(Items.WARPED_DOOR);
                itemKind.add(Items.IRON_DOOR);

                // ---- 活板门 (Trapdoor) ----
                itemKind.add(Items.OAK_TRAPDOOR);
                itemKind.add(Items.SPRUCE_TRAPDOOR);
                itemKind.add(Items.BIRCH_TRAPDOOR);
                itemKind.add(Items.JUNGLE_TRAPDOOR);
                itemKind.add(Items.ACACIA_TRAPDOOR);
                itemKind.add(Items.DARK_OAK_TRAPDOOR);
                itemKind.add(Items.MANGROVE_TRAPDOOR);
                itemKind.add(Items.CHERRY_TRAPDOOR);
                itemKind.add(Items.BAMBOO_TRAPDOOR);
                itemKind.add(Items.CRIMSON_TRAPDOOR);
                itemKind.add(Items.WARPED_TRAPDOOR);
                itemKind.add(Items.IRON_TRAPDOOR);

                // ---- 栅栏门 (Fence Gate) ----
                itemKind.add(Items.OAK_FENCE_GATE);
                itemKind.add(Items.SPRUCE_FENCE_GATE);
                itemKind.add(Items.BIRCH_FENCE_GATE);
                itemKind.add(Items.JUNGLE_FENCE_GATE);
                itemKind.add(Items.ACACIA_FENCE_GATE);
                itemKind.add(Items.DARK_OAK_FENCE_GATE);
                itemKind.add(Items.MANGROVE_FENCE_GATE);
                itemKind.add(Items.CHERRY_FENCE_GATE);
                itemKind.add(Items.BAMBOO_FENCE_GATE);
                itemKind.add(Items.CRIMSON_FENCE_GATE);
                itemKind.add(Items.WARPED_FENCE_GATE);

                // ---- 石头 ----
                itemKind.add(Items.STONE);
                itemKind.add(Items.SMOOTH_STONE);
                itemKind.add(Items.COBBLESTONE);
                itemKind.add(Items.MOSSY_COBBLESTONE);
                itemKind.add(Items.STONE_BRICKS);
                itemKind.add(Items.MOSSY_STONE_BRICKS);
                itemKind.add(Items.CRACKED_STONE_BRICKS);
                itemKind.add(Items.CHISELED_STONE_BRICKS);
                itemKind.add(Items.GRANITE);
                itemKind.add(Items.POLISHED_GRANITE);
                itemKind.add(Items.DIORITE);
                itemKind.add(Items.POLISHED_DIORITE);
                itemKind.add(Items.ANDESITE);
                itemKind.add(Items.POLISHED_ANDESITE);
                itemKind.add(Items.DEEPSLATE);
                itemKind.add(Items.COBBLED_DEEPSLATE);
                itemKind.add(Items.POLISHED_DEEPSLATE);
                itemKind.add(Items.DEEPSLATE_BRICKS);
                itemKind.add(Items.DEEPSLATE_TILES);
                itemKind.add(Items.CHISELED_DEEPSLATE);
                itemKind.add(Items.CRACKED_DEEPSLATE_BRICKS);
                itemKind.add(Items.CRACKED_DEEPSLATE_TILES);
                itemKind.add(Items.TUFF);
                itemKind.add(Items.CALCITE);
                itemKind.add(Items.DRIPSTONE_BLOCK);
                itemKind.add(Items.BLACKSTONE);
                itemKind.add(Items.POLISHED_BLACKSTONE);
                itemKind.add(Items.POLISHED_BLACKSTONE_BRICKS);
                itemKind.add(Items.CHISELED_POLISHED_BLACKSTONE);
                itemKind.add(Items.CRACKED_POLISHED_BLACKSTONE_BRICKS);
                itemKind.add(Items.BASALT);
                itemKind.add(Items.POLISHED_BASALT);
                itemKind.add(Items.SMOOTH_BASALT);
            }
        }
    }
}
