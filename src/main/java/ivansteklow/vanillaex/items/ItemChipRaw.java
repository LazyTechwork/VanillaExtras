package ivansteklow.vanillaex.items;

import ivansteklow.vanillaex.init.Refs;
import net.minecraft.util.ResourceLocation;

public class ItemChipRaw extends ItemChipBase {
	
	public ItemChipRaw() {
		this.setUnlocalizedName("chipRaw");
		this.setRegistryName(new ResourceLocation(Refs.MOD_ID, "chipRaw"));
		this.setHasSubtypes(true);
	}

}
