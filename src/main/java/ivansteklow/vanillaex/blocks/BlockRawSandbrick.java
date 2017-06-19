package ivansteklow.vanillaex.blocks;

import ivansteklow.isdev.bases.BlockBase;
import ivansteklow.vanillaex.init.Refs;
import net.minecraft.block.material.Material;

public class BlockRawSandbrick extends BlockBase{

	public BlockRawSandbrick() {
		super(Material.CLAY, "blockRawSandbrick", Refs.MOD_ID);
		setHardness(0.8F);
		setHarvestLevel("shovel", 0);
	}

}
