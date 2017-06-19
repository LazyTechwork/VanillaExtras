package ivansteklow.vanillaex.items;

import ivansteklow.vanillaex.init.Refs;
import net.minecraft.util.ResourceLocation;

/**
 * Class for item chip
 * @author IvanSteklow
 *
 */
public class ItemChip extends ItemChipBase {

	public ItemChip() {
		this.setUnlocalizedName("chip");
		this.setRegistryName(new ResourceLocation(Refs.MOD_ID, "chip"));
		this.setHasSubtypes(true);
	}
	
}
