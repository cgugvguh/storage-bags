package com.fmy.storage_bags.item.Plugin;

import com.fmy.storage_bags.item.ModItem.ModTiers;
import com.fmy.storage_bags.item.StorageBag.StorageBag;
import com.fmy.storage_bags.item.StorageBag.StorageUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author 宛
 * @version 1.0
 */
public class ExpandPlugin extends Item {
    private int multiplier = 0;
    public ExpandPlugin(Tier pTier, Properties pProperties) {
        super(pProperties);
        if (pTier == Tiers.IRON) multiplier = 2;
        else if (pTier == Tiers.GOLD) multiplier = 4;
        else if (pTier == Tiers.DIAMOND) multiplier = 6;
        else if (pTier == Tiers.NETHERITE) multiplier = 8;
        else if (pTier == ModTiers.INFINITE) multiplier = -1;
    }

    public int getMultiplier() {
        return multiplier;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        return super.useOn(pContext);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack plugin = player.getItemInHand(hand);
        ItemStack bag = player.getOffhandItem();
        if(!(bag.getItem() instanceof StorageBag)){
            return super.use(level, player, hand);
        }
        if (level.isClientSide()){
            if(StorageUtil.canSetMaxStorageByMultiplier(bag, multiplier)){
                return InteractionResultHolder.sidedSuccess(plugin, true);
            }else {
                return InteractionResultHolder.fail(plugin);
            }
        }
        if (StorageUtil.setMaxStorageByMultiplier(bag, this.multiplier)) {//修改背包最大数量, 如果成功
            if (!player.getAbilities().instabuild) {
                plugin.shrink(1);//数量 -1
            }
            return InteractionResultHolder.sidedSuccess(plugin, false);//返回交互成功
        }

        return InteractionResultHolder.fail(plugin);
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("storage_bags.plugin.description"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
