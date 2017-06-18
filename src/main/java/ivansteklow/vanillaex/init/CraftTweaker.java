package ivansteklow.vanillaex.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Class for registering recipes
 * @author IvanSteklow
 *
 */
public class CraftTweaker {

	/**
	 * Register recipes
	 */
	public static void register() {
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.OBSIDIAN), Items.WATER_BUCKET, Items.WATER_BUCKET,
				Items.LAVA_BUCKET);
		GameRegistry.addShapelessRecipe(new ItemStack(Items.QUARTZ, 4), Blocks.QUARTZ_BLOCK);
		
		
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.CHEST, 4), "XXX", "X X", "XXX", 'X', Blocks.LOG);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker), "APS", "RCR", "BBB", 'R', Items.REDSTONE,
				'C', ModItems.itemChip, 'B', Blocks.IRON_BLOCK, 'A', Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S',
				Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker), "PAS", "RCR", "BBB", 'R', Items.REDSTONE,
				'C', ModItems.itemChip, 'B', Blocks.IRON_BLOCK, 'A', Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S',
				Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker), "SPA", "RCR", "BBB", 'R', Items.REDSTONE,
				'C', ModItems.itemChip, 'B', Blocks.IRON_BLOCK, 'A', Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S',
				Items.IRON_SHOVEL);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBreaker), "ASP", "RCR", "BBB", 'R', Items.REDSTONE,
				'C', ModItems.itemChip, 'B', Blocks.IRON_BLOCK, 'A', Items.IRON_AXE, 'P', Items.IRON_PICKAXE, 'S',
				Items.IRON_SHOVEL);
	}

}
