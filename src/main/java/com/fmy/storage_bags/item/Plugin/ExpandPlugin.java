package com.fmy.storage_bags.item.Plugin;

import com.fmy.storage_bags.item.StorageBag.StorageBag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/**
 * @author 宛
 * @version 1.0
 */
public class ExpandPlugin extends Item {
    private Tier tier;
    public ExpandPlugin(Tier pTier, Properties pProperties) {
        super(pProperties);
        this.tier = pTier;

    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return super.useOn(pContext);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack plugin = player.getItemInHand(hand);
        ItemStack bag = player.getOffhandItem();

        if (!level.isClientSide() && bag.getItem() instanceof StorageBag) {
            if (StorageBag.setMaxStorage(bag, this.tier)) {//修改背包最大数量, 如果成功
                if (!player.getAbilities().instabuild) {
                    plugin.shrink(1);//数量 -1
                }
                return InteractionResultHolder.sidedSuccess(plugin, false);//返回交互成功
            }
        }

        return super.use(level, player, hand);
    }

    public Tier getTier() {
        return tier;
    }
}
