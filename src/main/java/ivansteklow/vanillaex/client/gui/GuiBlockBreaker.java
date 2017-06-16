package ivansteklow.vanillaex.client.gui;

import java.util.ArrayList;
import java.util.List;

import ivansteklow.vanillaex.client.gui.ProgressBar.ProgressBarDirection;
import ivansteklow.vanillaex.container.ContainerBlockBreaker;
import ivansteklow.vanillaex.init.Refs;
import ivansteklow.vanillaex.network.PacketGetWorker;
import ivansteklow.vanillaex.network.PacketHandler;
import ivansteklow.vanillaex.tileentities.TileEntityBlockBreaker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.items.CapabilityItemHandler;

public class GuiBlockBreaker extends GuiContainer {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Refs.MOD_ID,
			"textures/gui/container/blockbreaker.png");

	private TileEntityBlockBreaker te;
	private IInventory playerInv;

	public static int cooldown, maxCooldown = 0;

	public static int sync = 0;

	private ProgressBar progressBar;

	public GuiBlockBreaker(IInventory playerInv, TileEntityBlockBreaker te) {
		super(new ContainerBlockBreaker(playerInv, te));

		this.xSize = 176;
		this.ySize = 166;

		this.te = te;
		this.playerInv = playerInv;

		this.progressBar = new ProgressBar(TEXTURE, ProgressBarDirection.LEFT_TO_RIGHT, 14, 14, 135, 36, 176, 0);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format("container.block_breaker");
		this.mc.fontRendererObj.drawString(s, this.xSize / 2 - this.mc.fontRendererObj.getStringWidth(s) / 2, 6,
				4210752);
		this.mc.fontRendererObj.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, 72, 4210752);
		this.progressBar.setMin(cooldown).setMax(maxCooldown);
		this.progressBar.draw(this.mc);

		int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
		int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
		if (actualMouseX >= 134 && actualMouseX <= 149 && actualMouseY >= 17 && actualMouseY <= 32
				&& te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
						.getStackInSlot(9) == ItemStack.EMPTY) {
			List<String> text = new ArrayList<String>();
			text.add(TextFormatting.GRAY + I18n.format("gui.blockbreaker.enchantedbook.tooltip"));
			this.drawHoveringText(text, actualMouseX, actualMouseY);
		}

		sync++;
		sync %= 10;
		if (sync == 0)
			PacketHandler.INSTANCE
					.sendToServer(
							new PacketGetWorker(
									this.te.getPos(),
									this.mc.player.getAdjustedHorizontalFacing(),
							"ivansteklow.vanillaex.client.gui.GuiBlockBreaker",
							"cooldown",
							"maxCooldown"));

	}

}
