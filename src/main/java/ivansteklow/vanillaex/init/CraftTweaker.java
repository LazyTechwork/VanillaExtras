package ivansteklow.vanillaex.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Class for registering recipes
 * 
 * @author IvanSteklow
 *
 */
public class CraftTweaker {

	public static void init() {
		registerShapelessRecipes();
		registerShapedRecipes();
		registerFurnaceRecipes();
		registerCustomRecipes();
	}

	public static void registerShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN), Items.WATER_BUCKET, Items.WATER_BUCKET,
				Items.LAVA_BUCKET);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.QUARTZ, 4), Blocks.QUARTZ_BLOCK);

		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 2),
				new ItemStack(ModBlocks.blockBreaker, 1, 1), new ItemStack(ModBlocks.blockMachineCase, 1, 2));
	}

	public static void registerShapedRecipes() {
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.CHEST, 4), "XXX", "X X", "XXX", 'X', Blocks.LOG);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 1), "APS", "RCR", "BBB", 'R',
				Items.REDSTONE, 'C', new ItemStack(ModBlocks.blockMachineCase, 1, 1), 'B', Blocks.IRON_BLOCK, 'A',
				Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S', Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 1), "PAS", "RCR", "BBB", 'R',
				Items.REDSTONE, 'C', new ItemStack(ModBlocks.blockMachineCase, 1, 1), 'B', Blocks.IRON_BLOCK, 'A',
				Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S', Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 1), "SPA", "RCR", "BBB", 'R',
				Items.REDSTONE, 'C', new ItemStack(ModBlocks.blockMachineCase, 1, 1), 'B', Blocks.IRON_BLOCK, 'A',
				Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S', Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 1), "ASP", "RCR", "BBB", 'R',
				Items.REDSTONE, 'C', new ItemStack(ModBlocks.blockMachineCase, 1, 1), 'B', Blocks.IRON_BLOCK, 'A',
				Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S', Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 2), "APS", "RCR", "BBB", 'R',
				Items.REDSTONE, 'C', new ItemStack(ModBlocks.blockMachineCase, 1, 2), 'B', Blocks.IRON_BLOCK, 'A',
				Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S', Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 2), "PAS", "RCR", "BBB", 'R',
				Items.REDSTONE, 'C', new ItemStack(ModBlocks.blockMachineCase, 1, 2), 'B', Blocks.IRON_BLOCK, 'A',
				Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S', Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 2), "SPA", "RCR", "BBB", 'R',
				Items.REDSTONE, 'C', new ItemStack(ModBlocks.blockMachineCase, 1, 2), 'B', Blocks.IRON_BLOCK, 'A',
				Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S', Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker, 1, 2), "ASP", "RCR", "BBB", 'R',
				Items.REDSTONE, 'C', new ItemStack(ModBlocks.blockMachineCase, 1, 2), 'B', Blocks.IRON_BLOCK, 'A',
				Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S', Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockJar), "SWS", "SWS", "SSS", 'S',
				ModBlocks.blockSandbrick, 'W', Items.WATER_BUCKET);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockMachineCase, 1, 1), "III", "BCB", "RPR", 'I',
				Items.IRON_INGOT, 'B', Blocks.IRON_BLOCK, 'C', new ItemStack(ModItems.itemChip, 1, 1), 'R',
				Items.REDSTONE, 'P', Blocks.PISTON);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockMachineCase, 1, 2), "III", "BCB", "RPR", 'I',
				Items.IRON_INGOT, 'B', Blocks.IRON_BLOCK, 'C', new ItemStack(ModItems.itemChip, 1, 2), 'R',
				Items.REDSTONE, 'P', Blocks.PISTON);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockSandbrick), "SSS", "SBS", "SSS", 'S', Blocks.SAND,
				'B', ModBlocks.blockRawSandbrick);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemChipRaw), "RLR", "GGG", "III", 'R', Items.REDSTONE, 'L',
				Items.GOLD_INGOT, 'G', Items.GOLD_NUGGET, 'I', Items.IRON_INGOT);
	}

	public static void registerFurnaceRecipes() {
		GameRegistry.addSmelting(new ItemStack(Blocks.SANDSTONE, 1, 1), new ItemStack(ModBlocks.blockRawSandbrick),
				10.0F);
		GameRegistry.addSmelting(new ItemStack(Blocks.SANDSTONE, 1, 2), new ItemStack(ModBlocks.blockRawSandbrick),
				10.0F);
		GameRegistry.addSmelting(new ItemStack(Blocks.SANDSTONE, 1, 3), new ItemStack(ModBlocks.blockRawSandbrick),
				10.0F);
		GameRegistry.addSmelting(new ItemStack(ModItems.itemChipRaw, 1, 1), new ItemStack(ModItems.itemChip), 10.0F);
		GameRegistry.addSmelting(new ItemStack(ModItems.itemChipRaw, 1, 2), new ItemStack(ModItems.itemChip), 10.0F);
	}

	public static void registerCustomRecipes() {
	}

}
