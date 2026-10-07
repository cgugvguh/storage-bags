package com.fmy.storage_bags.Block;

import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.item.ModItem.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author 宛
 * @version 1.0
 */
public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, StorageBags.MOD_ID);
    //获取方块注册器
                      //放入方块得到物品 pBlock 为 apply() 的参数, 返回的 Item 作为加工后的产物, 参与 registerBlock()
    /*public static final RegistryObject<Block> ICE_ETHER_BLOCK =
            registerBlock("ice_ether_block", () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1.0F).explosionResistance(3.0F)));
    public static final RegistryObject<Block> RAW_ICE_ETHER_BLOCK =
            registerBlock("raw_ice_ether_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));*/
    //两种方块构建方式, 自己写属性或者直接复制别的方块属性
    private static <T extends Block> void registerBlockItems(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties()));
    }
    //默认物品方块注册器
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blocks = BLOCKS.register(name, block);
        registerBlockItems(name, blocks);
        return blocks;
    }
    //绑定自定义物品的方块注册器
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Function<T, Item> itemFactory) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);//注册方块
        ModItems.ITEMS.register(name, () -> itemFactory.apply(registeredBlock.get()));
        //itemFactory.apply(registeredBlock.get()) 放入方块 registeredBlock.get() 返回 Item, Item 作为参数参与 register
        //注册同名方块物品
        return registeredBlock;
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
