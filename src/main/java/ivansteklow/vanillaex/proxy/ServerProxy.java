/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.proxy;

import ivansteklow.vanillaex.ModCore;
import ivansteklow.vanillaex.client.gui.GuiHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ServerProxy {

	public void preInit(FMLPreInitializationEvent e) {

	}

	public void Init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(ModCore.instance, new GuiHandler());
	}

	public void postInit(FMLPostInitializationEvent e) {

	}

	public void registerModelBakeryVariants() {

	}

}
