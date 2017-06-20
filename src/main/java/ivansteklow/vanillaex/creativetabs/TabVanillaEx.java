/*
 * Copyright 2017 (c) IvanSteklow
 * Licensed under the Apache License, Version 2.0
 */
package ivansteklow.vanillaex.creativetabs;

import ivansteklow.vanillaex.init.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Vanilla Extras creative tab
 * 
 * @author IvanSteklow
 *
 */
public class TabVanillaEx extends CreativeTabs {

	public TabVanillaEx() {
		super("vanillaextab");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Item.getItemFromBlock(ModBlocks.blockJar));
	}

}
