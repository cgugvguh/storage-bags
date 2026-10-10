package com.fmy.storage_bags.datagen;

import com.fmy.storage_bags.StorageBags;
import com.fmy.storage_bags.item.ModItem.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author 宛
 * @version 1.0
 */
//生成配方文件和配方进度文件
//配方文件位于 resources/data/my_first_mod/recipes
//进度文件位于 resources/data/my_first_mod/advancements/recipes
public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipesProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        //储物袋
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CUTTING_TREE_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.IRON_AXE)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MINING_ORE_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.IRON_PICKAXE)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PLANT_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.IRON_HOE)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INDUSTRY_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.REDSTONE_BLOCK)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FISHING_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.FISHING_ROD)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BATTLE_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.IRON_SWORD)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BUILDING_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.CHERRY_SAPLING)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HUSBANDRY_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.WHEAT)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CUSTOM_STORAGE_BAG.get())
                .pattern(" # ")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.LEATHER)
                .define('f', Items.DIAMOND)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);

        //扩容插件, 通过低等级升级
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GORDEN_EXPAND_PLUGIN.get())
                .pattern("DDD")
                .pattern("#f#")
                .pattern("DDD")
                .define('#', Items.GOLD_BLOCK)
                .define('f', ModItems.IRON_EXPAND_PLUGIN.get())
                .define('D', Items.GOLD_INGOT)
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .group(StorageBags.MOD_ID + ":" + "gorden_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "gorden_expand_plugin01");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_EXPAND_PLUGIN.get())
                .pattern("DDD")
                .pattern("#f#")
                .pattern("DDD")
                .define('#', Items.DIAMOND_BLOCK)
                .define('f', ModItems.GORDEN_EXPAND_PLUGIN.get())
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .group(StorageBags.MOD_ID + ":" + "diamond_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "diamond_expand_plugin01");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_EXPAND_PLUGIN.get())
                .pattern("###")
                .pattern("D#f")
                .pattern("###")
                .define('#', Items.CHEST)
                .define('f', Items.NETHERITE_INGOT)
                .define('D', ModItems.DIAMOND_EXPAND_PLUGIN.get())
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "netherite_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "netherite_expand_plugin01");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_EXPAND_PLUGIN.get())
                .pattern("###")
                .pattern("f#D")
                .pattern("###")
                .define('#', Items.CHEST)
                .define('f', Items.NETHERITE_INGOT)
                .define('D', ModItems.DIAMOND_EXPAND_PLUGIN.get())
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "netherite_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "netherite_expand_plugin02");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INFINITE_EXPAND_PLUGIN.get())
                .pattern("###")
                .pattern("DfD")
                .pattern("###")
                .define('#', Items.NETHERITE_BLOCK)
                .define('f', ModItems.NETHERITE_EXPAND_PLUGIN.get())
                .define('D', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "infinite_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "infinite_expand_plugin01");
        //填充插件, 通过低等级升级
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GORDEN_FILL_PLUGIN.get())
                .pattern("DDD")
                .pattern("#f#")
                .pattern("DDD")
                .define('#', Items.GOLD_BLOCK)
                .define('D', Items.GOLD_INGOT)
                .define('f', ModItems.IRON_FILL_PLUGIN.get())
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .group(StorageBags.MOD_ID + ":" + "gorden_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "gorden_fill_plugin01");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('f', ModItems.GORDEN_EXPAND_PLUGIN.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .group(StorageBags.MOD_ID + ":" + "diamond_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "diamond_fill_plugin01");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("D#f")
                .pattern("###")
                .define('#', Items.EMERALD)
                .define('f', Items.NETHERITE_INGOT)
                .define('D', ModItems.DIAMOND_FILL_PLUGIN.get())
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "netherite_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "netherite_fill_plugin01");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("f#D")
                .pattern("###")
                .define('#', Items.EMERALD)
                .define('f', Items.NETHERITE_INGOT)
                .define('D', ModItems.DIAMOND_FILL_PLUGIN.get())
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "netherite_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "netherite_fill_plugin02");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INFINITE_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.NETHERITE_BLOCK)
                .define('f', ModItems.INFINITE_EXPAND_PLUGIN.get())
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "infinite_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "infinite_fill_plugin01");
        //直接合成扩容插件
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_EXPAND_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.IRON_BLOCK)
                .define('f', Items.CHEST)
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .group(StorageBags.MOD_ID + ":" + "iron_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "iron_expand_plugin02");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GORDEN_EXPAND_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.GOLD_BLOCK)
                .define('f', Items.CHEST)
                .unlockedBy(getHasName(Items.GOLD_BLOCK), has(Items.GOLD_BLOCK))
                .group(StorageBags.MOD_ID + ":" + "gorden_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "gorden_expand_plugin02");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_EXPAND_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.DIAMOND_BLOCK)
                .define('f', Items.CHEST)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .group(StorageBags.MOD_ID + ":" + "diamond_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "diamond_expand_plugin02");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_EXPAND_PLUGIN.get())
                .pattern("###")
                .pattern("f#f")
                .pattern("###")
                .define('#', Items.CHEST)
                .define('f', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "netherite_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "netherite_expand_plugin03");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INFINITE_EXPAND_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.NETHERITE_BLOCK)
                .define('f', Items.CHEST)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "infinite_expand_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "infinite_expand_plugin02");
        //直接合成填充插件
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IRON_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.IRON_BLOCK)
                .define('f', Items.EMERALD_BLOCK)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .group(StorageBags.MOD_ID + ":" + "iron_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "iron_fill_plugin02");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GORDEN_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.GOLD_BLOCK)
                .define('f', Items.EMERALD_BLOCK)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .group(StorageBags.MOD_ID + ":" + "gorden_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "gorden_fill_plugin02");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DIAMOND_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("DfD")
                .pattern("###")
                .define('#', Items.DIAMOND)
                .define('D', Items.DIAMOND_BLOCK)
                .define('f', Items.EMERALD_BLOCK)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .group(StorageBags.MOD_ID + ":" + "diamond_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "diamond_fill_plugin02");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.NETHERITE_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("f#f")
                .pattern("###")
                .define('#', Items.EMERALD_BLOCK)
                .define('f', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "netherite_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "netherite_fill_plugin03");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.INFINITE_FILL_PLUGIN.get())
                .pattern("###")
                .pattern("#f#")
                .pattern("###")
                .define('#', Items.NETHERITE_BLOCK)
                .define('f', Items.DRAGON_EGG)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group(StorageBags.MOD_ID + ":" + "infinite_fill_plugin")
                .save(pWriter, StorageBags.MOD_ID + ":" + "infinite_fill_plugin02");
        /*//熔炉配方
        oreSmelting(pWriter,ICE_ETHER,RecipeCategory.MISC,ModItems.ICE_ETHER.get(),
                0.25f,200,"ice_ether");
        //高炉配方
        oreBlasting(pWriter,ICE_ETHER,RecipeCategory.MISC,ModItems.ICE_ETHER.get(),
                0.25f,100,"ice_ether");
        //有序合成
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ModBlocks.ICE_ETHER_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ICE_ETHER.get())
                .unlockedBy(getHasName(ModItems.ICE_ETHER.get()),has(ModItems.ICE_ETHER.get()))
                .save(pWriter);
        //无序合成
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.ICE_ETHER.get(),9)
                .requires(ModBlocks.ICE_ETHER_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ICE_ETHER_BLOCK.get()),has(ModBlocks.ICE_ETHER_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.SUGAR,3)
                .pattern("###")
                .define('#', ModItemTags.SUGAR_TAG)
                .unlockedBy(getHasName(Items.BEETROOT),has(Items.BEETROOT))
                .save(pWriter,MyFirstMod.MOD_ID + ":" + "sugar_from_beetroot");*/

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                            pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, StorageBags.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
