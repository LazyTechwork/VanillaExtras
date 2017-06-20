/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.client.gui;

import ivansteklow.vanillaex.container.ContainerBlockBreaker;
import ivansteklow.vanillaex.tileentities.TileEntityBlockBreaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Mod Gui Handler
 * 
 * @author IvanSteklow
 *
 */
public class GuiHandler implements IGuiHandler {

	public static final int BLOCKBREAKER = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == BLOCKBREAKER) {
			return new ContainerBlockBreaker(player.inventory,
					(TileEntityBlockBreaker) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == BLOCKBREAKER) {
			return new GuiBlockBreaker(player.inventory,
					(TileEntityBlockBreaker) world.getTileEntity(new BlockPos(x, y, z)),
					world.getTileEntity(new BlockPos(x, y, z)).getBlockMetadata());
		}
		return null;
	}

}
