package com.fmy.storage_bags.menu;

import com.fmy.storage_bags.Internet.ModNetwork;
import com.fmy.storage_bags.Internet.StorageActionPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 宛
 * @version 1.0
 */
@OnlyIn(Dist.CLIENT)
public class StorageBagScreen extends AbstractContainerScreen<StorageBagMenu> {
    private static final ResourceLocation BG_LOCATION =
            new ResourceLocation("storage_bags","textures/gui/container/storage_bag.png");
    private static final int SCROLLER_FULL_HEIGHT = 54;
    private static final int RECIPES_COLUMNS = 6;
    private static final int RECIPES_ROWS = 3;
    private static final int RECIPES_X = 7;
    private static final int RECIPES_Y = 14;
    public static final int SCROLLER_X = 106;
    public static final int SCROLLER_Y = 14;
    private static final int VISIBLE_COUNT = RECIPES_COLUMNS * RECIPES_ROWS;

    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private EditBox countInput;
    private Button confirmButton;
    public StorageBagScreen(StorageBagMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        menu.registerUpdateListener(this::containerChanged);
        --this.titleLabelY;
    }

    /** 每次渲染时从菜单取最新的物品条目列表，避免数据不同步 */
    private List<Map.Entry<Item, Integer>> getEntries() {
        return new ArrayList<>(this.menu.getStorage().getStorage().entrySet());
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(g);
        super.render(g, mouseX, mouseY, partialTick);
        this.renderTooltip(g, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
        int x = this.leftPos;
        int y = this.topPos;

        g.blit(BG_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);

        // 滚动条
        int k = (int) (41.0F * this.scrollOffs);
        g.blit(BG_LOCATION, x + SCROLLER_X, y + SCROLLER_Y + k,
                176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15, 256, 256);

        // 按钮 + 物品
        int px = x + RECIPES_X;
        int py = y + RECIPES_Y;
        int end = this.startIndex + VISIBLE_COUNT;
        this.renderButtons(g, mouseX, mouseY, px, py, end);
        this.renderItems(g, px, py, end);
    }

    @Override
    protected void renderTooltip(GuiGraphics g, int mouseX, int mouseY) {
        super.renderTooltip(g, mouseX, mouseY);

        List<Map.Entry<Item, Integer>> entries = getEntries();
        int end = this.startIndex + VISIBLE_COUNT;

        for (int i = this.startIndex; i < end && i < entries.size(); i++) {
            int idx = i - this.startIndex;
            int x = this.leftPos + RECIPES_X + idx % RECIPES_COLUMNS * 16;
            int y = this.topPos + RECIPES_Y + idx / RECIPES_COLUMNS * 18 + 2;
            if (mouseX >= x && mouseX < x + 16 && mouseY >= y && mouseY < y + 18) {
                g.renderTooltip(this.font, entries.get(i).getKey().getDescription(), mouseX, mouseY);
                break;
            }
        }
    }

    private void renderButtons(GuiGraphics g, int mouseX, int mouseY, int px, int py, int end) {
        List<Map.Entry<Item, Integer>> entries = getEntries();
        for (int i = this.startIndex; i < end && i < entries.size(); i++) {
            int idx = i - this.startIndex;
            int x = px + idx % RECIPES_COLUMNS * 16;
            int row = idx / RECIPES_COLUMNS;
            int y = py + row * 18 + 2;
            int v = this.imageHeight;
            if (i == this.menu.getSelectedItemIndex()) {
                v += 18;
            } else if (mouseX >= x && mouseY >= y && mouseX < x + 16 && mouseY < y + 18) {
                v += 36;
            }
            g.blit(BG_LOCATION, x, y - 1, 0, v, 16, 18);
        }
    }

    private void renderItems(GuiGraphics g, int px, int py, int end) {
        List<Map.Entry<Item, Integer>> entries = getEntries();
        for (int i = this.startIndex; i < end && i < entries.size(); i++) {
            int idx = i - this.startIndex;
            int x = px + idx % RECIPES_COLUMNS * 16;
            int row = idx / RECIPES_COLUMNS;
            int y = py + row * 18 + 2;

            Map.Entry<Item, Integer> entry = entries.get(i);
            g.renderItem(new ItemStack(entry.getKey()), x, y);

            // 数量：右下角右对齐
            String num = String.valueOf(entry.getValue());
            int numX = x + 16 - this.font.width(num);
            int numY = y + 9;

            //在物品之上绘制数量文本
            g.pose().pushPose();
            g.pose().translate(0,0,200);
            g.drawString(this.font, num, numX, numY, 0xFFFFFF, true);
            g.pose().popPose();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        List<Map.Entry<Item, Integer>> entries = getEntries();
        int end = this.startIndex + VISIBLE_COUNT;

        for (int l = this.startIndex; l < end && l < entries.size(); l++) {
            int idx = l - this.startIndex;
            double x = this.leftPos + RECIPES_X + idx % RECIPES_COLUMNS * 16;
            double y = this.topPos + RECIPES_Y + idx / RECIPES_COLUMNS * 18;
            if (mouseX >= x && mouseY >= y && mouseX < x + 16 && mouseY < y + 18
                    && this.menu.clickMenuButton(this.minecraft.player, l)) {
                Minecraft.getInstance().getSoundManager()
                        .play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                if (this.minecraft.gameMode != null) {
                    this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, l);
                }
                return true;
            }
        }

        double sx = this.leftPos + SCROLLER_X;
        double sy = this.topPos + SCROLLER_Y;
        if (mouseX >= sx && mouseX < sx + 12 && mouseY >= sy && mouseY < sy + SCROLLER_FULL_HEIGHT) {
            this.scrolling = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.scrolling && this.isScrollBarActive()) {
            int top = this.topPos + RECIPES_Y;
            int bottom = top + SCROLLER_FULL_HEIGHT;
            this.scrollOffs = ((float) mouseY - (float) top - 7.5F) / ((float) (bottom - top) - 15.0F);
            this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + 0.5D)
                    * RECIPES_COLUMNS;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (this.isScrollBarActive()) {
            int rows = this.getOffscreenRows();
            float f = (float) delta / (float) rows;
            this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
            this.startIndex = (int) ((double) (this.scrollOffs * (float) rows) + 0.5D) * RECIPES_COLUMNS;
        }
        return true;
    }

    private boolean isScrollBarActive() {
        return this.menu.getStorageNum() > VISIBLE_COUNT;
    }

    protected int getOffscreenRows() {
        int total = this.menu.getStorageNum();
        int totalRows = (total + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS;
        return Math.max(0, totalRows - RECIPES_ROWS);
    }

    private void containerChanged() {
        if (!isScrollBarActive()) {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }
    @Override
    protected void init() {
        super.init();
        int x = this.leftPos;
        int y = this.topPos;

        // 输入框：setFilter 限制只能输入数字
        this.countInput = new EditBox(this.font, x + 120, y + 20, 48, 18,
                Component.literal("count"));
        this.countInput.setMaxLength(6);
        this.countInput.setFilter(s -> s.matches("\\d*"));   // 只允许数字，空串也允许
        this.addRenderableWidget(this.countInput);

        // 确定按钮
        this.confirmButton = Button.builder(
                Component.literal("OK"),
                btn -> onConfirm()
        ).bounds(x + 120, y + 43, 48, 18).build();
        this.addRenderableWidget(this.confirmButton);
    }

    private void onConfirm() {
        String text = this.countInput.getValue();
        int amount = 0;

        // 解析：空串或非数字都当作 0
        if (!text.isEmpty()) {
            try {
                amount = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                amount = 0;
            }
        }

        // 范围校验，防止作弊
        if (amount < 0) amount = 0;
        if (amount > 1_000_000) amount = 1_000_000;
        // 发给服务端
        ModNetwork.CHANNEL.sendToServer(new StorageActionPacket(amount));

        this.countInput.setValue("");
    }

}