/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.init;

import ivansteklow.vanillaex.capabilities.IWork;
import ivansteklow.vanillaex.capabilities.Worker;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

/**
 * Class for registering mod capabilities
 * 
 * @author IvanSteklow
 *
 */
public class ModCapabilities {

	@CapabilityInject(IWork.class)
	public static Capability<IWork> CAPABILITY_WORKER = null;

	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(IWork.class, new CapabilityWorker(), Worker.class);
	}

	public static class CapabilityWorker implements IStorage<IWork> {

		@Override
		public NBTBase writeNBT(Capability<IWork> capability, IWork instance, EnumFacing side) {
			return null;
		}

		@Override
		public void readNBT(Capability<IWork> capability, IWork instance, EnumFacing side, NBTBase nbt) {
		}

	}

}
