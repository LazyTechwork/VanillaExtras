package ivansteklow.vanillaex.init;

import ivansteklow.isdev.bases.ItemBlockBase;
import ivansteklow.isdev.handlers.BlockHandler;
import ivansteklow.vanillaex.blocks.BlockBreaker;
import ivansteklow.vanillaex.blocks.BlockJar;
import ivansteklow.vanillaex.handlers.EnumHandler;
import net.minecraft.block.Block;

/**
 * Class for registering blocks
 * @author IvanSteklow
 *
 */
public class ModBlocks {

	public static Block blockJar;
	public static Block blockBreaker;

	public static void init() {
		blockJar = new BlockJar();
		BlockHandler.regBlock(blockJar, Refs.CREATIVE_TAB);
		blockBreaker = new BlockBreaker();
		BlockHandler.regBlock(blockBreaker, new ItemBlockBase(blockBreaker), Refs.CREATIVE_TAB);
	}

	public static void regRenders() {
		BlockHandler.regRender(blockJar, Refs.MOD_ID);
		for (int i = 0; i < EnumHandler.ChipTypes.values().length; i++) {
			BlockHandler.regRender(blockBreaker, Refs.MOD_ID, i,
					"blockBreaker_" + EnumHandler.ChipTypes.values()[i].getName());
		}
	}
}
