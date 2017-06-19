package ivansteklow.vanillaex.blocks;

import ivansteklow.vanillaex.handlers.EnumHandler.ChipTypes;
import ivansteklow.vanillaex.init.Refs;
import net.minecraft.util.ResourceLocation;

public class BlockMachineCase extends BlockMachine{
	
	public BlockMachineCase(){
		setUnlocalizedName("blockMachineCase");
		this.setRegistryName(new ResourceLocation(Refs.MOD_ID, "blockMachineCase"));
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, ChipTypes.BASIC));
	}

}
