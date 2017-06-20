/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.proxy;

import ivansteklow.vanillaex.ModCore;
import ivansteklow.vanillaex.client.gui.GuiHandler;
import ivansteklow.vanillaex.config.VExConfig;
import ivansteklow.vanillaex.init.ModBlocks;
import ivansteklow.vanillaex.init.ModItems;
import ivansteklow.vanillaex.init.Refs;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends ServerProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		VExConfig.clientPreInit();
		ModBlocks.regRenders();
		ModItems.regRenders();
	}

	@Override
	public void Init(FMLInitializationEvent e) {
		super.Init(e);
		NetworkRegistry.INSTANCE.registerGuiHandler(ModCore.instance, new GuiHandler());
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

	@Override
	public void registerModelBakeryVariants() {
		ModelBakery.registerItemVariants(ModItems.itemChip, new ResourceLocation(Refs.MOD_ID, "chip_basic"),
				new ResourceLocation(Refs.MOD_ID, "chip_advanced"));
		ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.blockBreaker),
				new ResourceLocation(Refs.MOD_ID, "blockBreaker_basic"),
				new ResourceLocation(Refs.MOD_ID, "blockBreaker_advanced"));
	}
}
