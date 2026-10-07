package com.fmy.storage_bags.item.StorageBag;

import com.fmy.storage_bags.item.Plugin.ExpandPlugin;
import com.fmy.storage_bags.item.Plugin.FillPlugin;
import com.fmy.storage_bags.menu.StorageBagMenu;
import com.fmy.storage_bags.stats.ModStats;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * @author 宛
 * @version 1.0
 */
public class StorageBag extends Item {//负责物品交互逻辑, 只保留储物袋的各类性质和交互逻辑
    private final Kinds kind;
    public StorageBag(Properties pProperties,String kind) {
        super(pProperties);
        this.kind = Kinds.getKindFromName(kind);//储物袋种类
    }

    /**
     * 返回储物袋的种类名
     * @return 储物袋种类名
     */
    public String getKindName(){
        return kind.getKindName();
    }

    /**
     * 返回储物袋的种类
     * @return 储物袋种类
     */
    public Kinds getKind() {
        return this.kind;
    }
    /*@Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {

        ItemStack bagStack = pPlayer.getItemInHand(pHand);//获取该储物袋
        Storage storage = StorageUtil.getStorage(bagStack);

        if (!pLevel.isClientSide && pPlayer instanceof ServerPlayer serverPlayer) {
            // 只允许主手打开
            if (pHand != InteractionHand.MAIN_HAND) {//如果是在副手
                ItemStack itemStack = pPlayer.getMainHandItem();//获取主手物品

                if(storage.isAllowed(itemStack.getItem())){//如果包含了主手物品
                    int stored = StorageUtil.tryStoreToStorage(bagStack,itemStack);
                    if (stored <= 0) itemStack = ItemStack.EMPTY;
                    itemStack.shrink(stored);
                    return InteractionResultHolder.success(bagStack);
                }
                if(storage.getKindName().equals("custom")){
                    if(itemStack.isEmpty()){
                        storage.getStorageInfo().entrySet().removeIf((entry) -> entry.getValue() <= 0);
                        return InteractionResultHolder.success(bagStack);
                    }
                    if(itemStack.getItem() instanceof ExpandPlugin expandPlugin){//如果是储物袋
                        if (StorageUtil.setMaxStorage(bagStack, expandPlugin.getMultiplier())) {//修改背包最大数量, 如果成功
                            if (!pPlayer.getAbilities().instabuild) {
                                itemStack.shrink(1);//数量 -1
                                return InteractionResultHolder.sidedSuccess(itemStack, false);//返回交互成功
                            }
                        }
                    }else if(itemStack.getItem() instanceof FillPlugin fillPlugin){
                        if (StorageUtil.fill(bagStack, fillPlugin.getMultiplier())) {//修改背包最大数量, 如果成功
                            if (!pPlayer.getAbilities().instabuild) {
                                itemStack.shrink(1);//数量 -1
                                return InteractionResultHolder.sidedSuccess(itemStack, false);//返回交互成功
                            }
                        }
                    }
                    if(itemStack.getMaxStackSize() == 1){//只允许存入最大堆叠数量为 1 的物品
                        return InteractionResultHolder.fail(bagStack);
                    }
                    int maxNum = Math.max((StorageUtil.getMaxStorage(bagStack) / 64) * 27, 27);//增加种类最小值 27
                    maxNum = Math.min(maxNum, 100);// 增加种类最大值 100
                    int size = StorageUtil.getStorage(bagStack).getStorageInfo().size();//当前尺寸
                    if(size >= maxNum){//如果大于最大尺寸
                        return InteractionResultHolder.fail(bagStack);
                    }else {
                        storage.addStorageKind(itemStack.getItem());//增加此类物品
                        int stored = StorageUtil.tryStoreToStorage(bagStack,itemStack);//放入
                        if (stored <= 0) itemStack = ItemStack.EMPTY;
                        itemStack.shrink(stored);
                        StorageUtil.saveToNbt(bagStack, storage);
                        return InteractionResultHolder.success(bagStack);
                    }

                }
                return InteractionResultHolder.pass(pPlayer.getItemInHand(pHand));
            }

            String menuName = "container." + storage.getKindName() + "_storage_bag";

            NetworkHooks.openScreen(
                    serverPlayer,
                    new SimpleMenuProvider(
                            (id, inv, player) -> new StorageBagMenu(
                                    id, inv, ContainerLevelAccess.NULL, storage, bagStack
                            ),
                            bagStack.hasCustomHoverName()
                                    ? bagStack.getHoverName()
                                    : Component.translatable(menuName)
                    ),
                    (FriendlyByteBuf buf) -> {
                        buf.writeUtf(storage.getKindName());
                        buf.writeVarInt(storage.getStorageInfo().size());
                        for (Map.Entry<Item, Integer> e : storage.getStorageInfo().entrySet()) {
                            buf.writeUtf(BuiltInRegistries.ITEM.getKey(e.getKey()).toString());
                            buf.writeVarInt(e.getValue());
                        }
                    }
            );

            pPlayer.awardStat(ModStats.USE_STORAGE_BAG.get());
        }

        return InteractionResultHolder.sidedSuccess(bagStack, pLevel.isClientSide);
    }*/

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack bagStack = pPlayer.getItemInHand(pHand);

        // 类型检查
        if (!StorageUtil.isStorageBag(bagStack)) {
            return InteractionResultHolder.fail(bagStack);
        }

        // 客户端只返回成功，不改数据
        if (!pLevel.isClientSide) {
            if (!(pPlayer instanceof ServerPlayer serverPlayer)) {
                return InteractionResultHolder.fail(bagStack);
            }

            Storage storage = StorageUtil.getStorage(bagStack);

            // ==================== 副手：处理主手物品 ====================
            if (pHand != InteractionHand.MAIN_HAND) {
                ItemStack itemStack = pPlayer.getMainHandItem();

                if (storage.isAllowed(itemStack.getItem())) {
                    int stored = StorageUtil.tryStoreToStorage(bagStack, itemStack);
                    if (stored <= 0) return InteractionResultHolder.fail(bagStack);   // 没存入返回交互失败
                    itemStack.shrink(stored);//扣除物品
                    return InteractionResultHolder.success(bagStack);   // 返回 bagStack
                }

                if (storage.getKindName().equals("custom")) {//自定义储物袋
                    if (itemStack.isEmpty()) {//空手
                        storage.getStorageInfo().entrySet().removeIf(entry -> entry.getValue() <= 0);//移去无物品的种类信息
                        return InteractionResultHolder.success(bagStack);
                    }

                    if (itemStack.getItem() instanceof ExpandPlugin expandPlugin) {//如果是插件走插件逻辑
                        if (StorageUtil.setMaxStorage(bagStack, expandPlugin.getMultiplier())) {
                            if (!pPlayer.getAbilities().instabuild) {
                                itemStack.shrink(1);
                                return InteractionResultHolder.sidedSuccess(itemStack, false);   // 插件使用成功
                            }
                        }
                        // 失败或创造模式 → 继续往下
                    } else if (itemStack.getItem() instanceof FillPlugin fillPlugin) {
                        if (StorageUtil.fill(bagStack, fillPlugin.getMultiplier())) {
                            if (!pPlayer.getAbilities().instabuild) {
                                itemStack.shrink(1);
                                return InteractionResultHolder.sidedSuccess(itemStack, false);
                            }
                        }
                        // 失败或创造模式 → 继续往下
                    }

                    if (itemStack.getMaxStackSize() == 1) {
                        return InteractionResultHolder.fail(bagStack);
                    }
                    //设置自定义储物袋上下限
                    int maxNum = Math.max((StorageUtil.getMaxStorage(bagStack) / 64) * 27, 27);
                    maxNum = Math.min(maxNum, 100);
                    int size = StorageUtil.getStorage(bagStack).getStorageInfo().size();
                    if (size >= maxNum) {//超上限返回
                        return InteractionResultHolder.fail(bagStack);
                    }

                    storage.addStorageKind(itemStack.getItem());//不超添加种类
                    if(pPlayer.isCrouching()){//如果蹲着直接存入
                        int stored = StorageUtil.tryStoreToStorage(bagStack, itemStack);
                        if (stored <= 0) return InteractionResultHolder.fail(bagStack);
                        itemStack.shrink(stored);
                    }
                    StorageUtil.saveToNbt(bagStack, storage);
                    return InteractionResultHolder.success(bagStack);
                }

                return InteractionResultHolder.pass(pPlayer.getItemInHand(pHand));
            }

            // ==================== 主手：打开菜单 ====================
            String menuName = "container." + storage.getKindName() + "_storage_bag";//菜单名

            NetworkHooks.openScreen(
                    serverPlayer,
                    new SimpleMenuProvider(
                            (id, inv, player) -> new StorageBagMenu(
                                    id, inv, ContainerLevelAccess.NULL, storage, bagStack
                            ),
                            bagStack.hasCustomHoverName()//储物袋名, 如果有铁砧修改后的名字, 打开菜单就是这个了
                                    ? bagStack.getHoverName()
                                    : Component.translatable(menuName)
                    ),
                    (FriendlyByteBuf buf) -> {
                        buf.writeUtf(storage.getKindName());
                        buf.writeVarInt(storage.getStorageInfo().size());
                        for (Map.Entry<Item, Integer> e : storage.getStorageInfo().entrySet()) {
                            buf.writeUtf(BuiltInRegistries.ITEM.getKey(e.getKey()).toString());
                            buf.writeVarInt(e.getValue());
                        }
                    }
            );

            pPlayer.awardStat(ModStats.USE_STORAGE_BAG.get());
        }
        return InteractionResultHolder.sidedSuccess(bagStack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(StorageUtil.isStorageBag(pStack)){
            if(Screen.hasShiftDown()){
                String storageKind = StorageUtil.getStorage(pStack).getKindName();
                switch (storageKind) {
                    case "custom" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.custom").withStyle(ChatFormatting.BLUE));
                    }
                    case "cutting_tree" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.cutting_tree").withStyle(ChatFormatting.BLUE));
                    }
                    case "mining_ore" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.mining_ore").withStyle(ChatFormatting.BLUE));
                    }
                    case "plant" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.plant").withStyle(ChatFormatting.BLUE));
                    }
                    case "industry" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.industry").withStyle(ChatFormatting.BLUE));
                    }
                    case "fishing" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.fishing").withStyle(ChatFormatting.BLUE));
                    }
                    case "battle" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.battle").withStyle(ChatFormatting.BLUE));
                    }
                    case "building" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.building").withStyle(ChatFormatting.BLUE));
                    }
                    case "husbandry" -> {
                        pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description.husbandry").withStyle(ChatFormatting.BLUE));
                    }
                }
            }else{
                pTooltipComponents.add(Component.translatable("tooltip.storage_bags.description").withStyle(ChatFormatting.BLUE));
            }
        }
    }
}
