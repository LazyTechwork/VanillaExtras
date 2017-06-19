package ivansteklow.vanillaex.init;

import ivansteklow.isdev.bases.ItemBlockBase;
import ivansteklow.isdev.handlers.BlockHandler;
import ivansteklow.vanillaex.blocks.BlockBreaker;
import ivansteklow.vanillaex.blocks.BlockJar;
import ivansteklow.vanillaex.blocks.BlockMachineCase;
import ivansteklow.vanillaex.blocks.BlockRawSandbrick;
import ivansteklow.vanillaex.blocks.BlockSandbrick;
import ivansteklow.vanillaex.handlers.EnumHandler;
import net.minecraft.block.Block;

/**
 * Class for registering blocks
 * @author IvanSteklow
 *
 */
public class ModBlocks {

	public static Block blockJar, blockBreaker, blockMachineCase, blockSandbrick, blockRawSandbrick;

	public static void init() {
		blockJar = new BlockJar();
		blockBreaker = new BlockBreaker();
		blockSandbrick = new BlockSandbrick();
		blockMachineCase = new BlockMachineCase();
		blockRawSandbrick = new BlockRawSandbrick();

		BlockHandler.regBlock(blockRawSandbrick, Refs.CREATIVE_TAB);
		BlockHandler.regBlock(blockSandbrick, Refs.CREATIVE_TAB);
		BlockHandler.regBlock(blockMachineCase, new ItemBlockBase(blockMachineCase), Refs.CREATIVE_TAB);
		BlockHandler.regBlock(blockJar, Refs.CREATIVE_TAB);
		BlockHandler.regBlock(blockBreaker, new ItemBlockBase(blockBreaker), Refs.CREATIVE_TAB);
	}

	public static void regRenders() {
		BlockHandler.regRender(blockJar, Refs.MOD_ID);
		BlockHandler.regRender(blockSandbrick, Refs.MOD_ID);
		BlockHandler.regRender(blockRawSandbrick, Refs.MOD_ID);
		
		for (int i = 0; i < EnumHandler.ChipTypes.values().length; i++) {
			BlockHandler.regRender(blockBreaker, Refs.MOD_ID, i,
					"blockBreaker_" + EnumHandler.ChipTypes.values()[i].getName());
			BlockHandler.regRender(blockMachineCase, Refs.MOD_ID, i, "blockMachineCase_" + EnumHandler.ChipTypes.values()[i].getName());
		}
	}
}
