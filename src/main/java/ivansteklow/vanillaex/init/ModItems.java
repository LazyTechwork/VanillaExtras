/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.init;

import ivansteklow.isdev.handlers.ItemHandler;
import ivansteklow.vanillaex.handlers.EnumHandler;
import ivansteklow.vanillaex.items.ItemChip;
import ivansteklow.vanillaex.items.ItemChipRaw;
import net.minecraft.item.Item;

/**
 * Class for registering mod items
 * 
 * @author IvanSteklow
 *
 */
public class ModItems {

	public static Item itemChipRaw, itemChip;

	public static void init() {
		itemChipRaw = new ItemChipRaw();
		itemChip = new ItemChip();

		ItemHandler.regItem(itemChipRaw, Refs.CREATIVE_TAB);
		ItemHandler.regItem(itemChip, Refs.CREATIVE_TAB);
	}

	public static void regRenders() {
		for (int i = 0; i < EnumHandler.ChipTypes.values().length; i++) {
			ItemHandler.regRender(itemChipRaw, Refs.MOD_ID, i,
					"chipRaw_" + EnumHandler.ChipTypes.values()[i].getName());
			ItemHandler.regRender(itemChip, Refs.MOD_ID, i, "chip_" + EnumHandler.ChipTypes.values()[i].getName());
		}
	}

}
