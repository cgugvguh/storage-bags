package com.fmy.storage_bags.datagen;

import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.item.ModItem.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author 宛
 * @version 1.0
 */
//生成物品模型文件，告诉游戏找那些物品的贴图或模型
//位于 resources/assets/my_first_mod/models/item
public class ModItemModeProvider extends ItemModelProvider {
    public ModItemModeProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, StorageBags.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //basicItem(ModItems.POWDERED_ROSE_INGOT.get());

        basicItem(ModItems.CUTTING_TREE_STORAGE_BAG);
        basicItem(ModItems.MINING_ORE_STORAGE_BAG);
        basicItem(ModItems.PLANT_STORAGE_BAG);
        basicItem(ModItems.INDUSTRY_STORAGE_BAG);
        basicItem(ModItems.FISHING_STORAGE_BAG);
        basicItem(ModItems.BUTTLE_STORAGE_BAG);
        basicItem(ModItems.BUILDING_STORAGE_BAG);
        basicItem(ModItems.HUSBANDRY_STORAGE_BAG);
        basicItem(ModItems.CUSTOM_STORAGE_BAG);

        basicItem(ModItems.IRON_EXPAND_PLUGIN);
        basicItem(ModItems.GORDEN_EXPAND_PLUGIN);
        basicItem(ModItems.DIAMOND_EXPAND_PLUGIN);
        basicItem(ModItems.NETHERITE_EXPAND_PLUGIN);
        basicItem(ModItems.INFINITE_EXPAND_PLUGIN);
        basicItem(ModItems.IRON_FILL_PLUGIN);
        basicItem(ModItems.GORDEN_FILL_PLUGIN);
        basicItem(ModItems.DIAMOND_FILL_PLUGIN);
        basicItem(ModItems.NETHERITE_FILL_PLUGIN);
        basicItem(ModItems.INFINITE_FILL_PLUGIN);
    }
    public void basicItem(RegistryObject<Item> item)
    {
        String path = item.getId().getPath();
        withExistingParent("item/" + path, mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + path));
    }
}
