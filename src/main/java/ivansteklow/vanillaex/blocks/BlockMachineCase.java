/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.blocks;

import ivansteklow.vanillaex.handlers.EnumHandler.ChipTypes;
import ivansteklow.vanillaex.init.Refs;
import net.minecraft.util.ResourceLocation;

public class BlockMachineCase extends BlockMachine {

	@SuppressWarnings("unchecked")
	public BlockMachineCase() {
		setUnlocalizedName("blockMachineCase");
		this.setRegistryName(new ResourceLocation(Refs.MOD_ID, "blockMachineCase"));
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, ChipTypes.BASIC));
	}

}
