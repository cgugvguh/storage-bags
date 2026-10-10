package com.fmy.storage_bags.item.Plugin;

import com.fmy.storage_bags.item.ModItem.ModTiers;
import com.fmy.storage_bags.item.StorageBag.StorageBag;
import com.fmy.storage_bags.item.StorageBag.StorageUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author 宛
 * @version 1.0
 */
public class FillPlugin extends Item {
    private int multiplier = 0;

    public FillPlugin(Tier tier, Properties pProperties) {
        super(pProperties);
        if (tier == Tiers.IRON) multiplier = 2;
        else if (tier == Tiers.GOLD) multiplier = 4;
        else if (tier == Tiers.DIAMOND) multiplier = 6;
        else if (tier == Tiers.NETHERITE) multiplier = 8;
        else if (tier == ModTiers.INFINITE) multiplier = -1;
    }

    public int getMultiplier() {
        return multiplier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack plugin = player.getItemInHand(hand);
        ItemStack bagStack = player.getOffhandItem();
        if (!(bagStack.getItem() instanceof StorageBag)) {
            return super.use(level, player, hand);
        }
        if (level.isClientSide) {
            if (StorageUtil.canFill(bagStack)) {
                return InteractionResultHolder.sidedSuccess(plugin, true);//客户端提前返回
            }
            return InteractionResultHolder.fail(plugin);
        }

        if (StorageUtil.fillByMultiplier(bagStack, multiplier)) {//修改背包最大数量, 如果成功
            if (!player.getAbilities().instabuild) {
                plugin.shrink(1);//数量 -1
            }
            return InteractionResultHolder.sidedSuccess(plugin, false);//返回交互成功
        }
        return InteractionResultHolder.fail(plugin);


    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        Component.translatable("storage_bags.plugin.description");
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
