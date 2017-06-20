/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * Progress bar for machines
 * 
 * @author IvanSteklow
 *
 */
public class ProgressBar extends Gui {

	private ResourceLocation texture;
	private ProgressBarDirection direction;
	private int positionX, positionY;
	private int width, height;
	private int textureX, textureY;

	private float min, max = 0;

	public ProgressBar(ResourceLocation texture, ProgressBarDirection direction, int width, int height, int positonX,
			int positionY, int textureX, int textureY) {
		this.texture = texture;
		this.direction = direction;
		this.width = width;
		this.height = height;
		this.positionX = positonX;
		this.positionY = positionY;
		this.textureX = textureX;
		this.textureY = textureY;
	}

	/**
	 * Set minimum value
	 * 
	 * @param min
	 *            Minimum value
	 * @return ProgressBar
	 */
	public ProgressBar setMin(int min) {
		this.min = min;
		return this;
	}

	/**
	 * Set maximum value
	 * 
	 * @param max
	 *            Maximum value
	 * @return ProgressBar
	 */
	public ProgressBar setMax(int max) {
		this.max = max;
		return this;
	}

	/**
	 * Get Adjusted Width of ProgressBar
	 * 
	 * @return width
	 */
	private int getAdjustedWidth() {
		return (int) (min != 0 && max != 0 ? min / max * width : 0);
	}

	/**
	 * Get Adjusted Height of ProgressBar
	 * 
	 * @return height
	 */
	private int getAdjustedHeight() {
		return (int) (min != 0 && max != 0 ? min / max * height : 0);
	}

	/**
	 * Draws Progress bar on screen
	 * 
	 * @param mc
	 *            Minecraft instance
	 */
	public void draw(Minecraft mc) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		switch (direction) {
		case DIAGONAL_UP_LEFT:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
			this.drawTexturedModalRect(positionX, positionY, positionX, positionY, width - getAdjustedWidth(),
					height - getAdjustedHeight());
			break;
		case DIAGONAL_UP_RIGHT:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
			this.drawTexturedModalRect(positionX + getAdjustedWidth(), positionY, positionX + getAdjustedWidth(),
					positionY, width - getAdjustedWidth(), height - getAdjustedHeight());
			break;
		case DIAGONAL_DOWN_LEFT:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
			this.drawTexturedModalRect(positionX, positionY + getAdjustedHeight(), positionX,
					positionY + getAdjustedHeight(), width - getAdjustedWidth(), height - getAdjustedHeight());
			break;
		case DIAGONAL_DOWN_RIGHT:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, getAdjustedWidth(),
					getAdjustedHeight());
			break;
		case DOWN_TO_UP:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
			this.drawTexturedModalRect(positionX, positionY, positionX, positionY, width, height - getAdjustedHeight());
			break;
		case LEFT_TO_RIGHT:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, getAdjustedWidth(), height);
			break;
		case RIGHT_TO_LEFT:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
			this.drawTexturedModalRect(positionX, positionY, positionX, positionY, width - getAdjustedWidth(), height);
			break;
		case UP_TO_DOWN:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, getAdjustedHeight());
			break;
		default:
			this.drawTexturedModalRect(positionX, positionY, textureX, textureY, width, height);
			break;
		}
	}

	public static enum ProgressBarDirection {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT, UP_TO_DOWN, DOWN_TO_UP, DIAGONAL_UP_RIGHT, DIAGONAL_UP_LEFT, DIAGONAL_DOWN_RIGHT, DIAGONAL_DOWN_LEFT;
	}

}
