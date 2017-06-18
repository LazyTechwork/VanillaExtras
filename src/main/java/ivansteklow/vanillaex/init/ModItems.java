package ivansteklow.vanillaex.init;

import ivansteklow.isdev.handlers.ItemHandler;
import ivansteklow.vanillaex.handlers.EnumHandler;
import ivansteklow.vanillaex.items.ItemChip;
import net.minecraft.item.Item;

/**
 * Class for registering mod items
 * @author IvanSteklow
 *
 */
public class ModItems {

	public static Item itemChip;

	public static void init() {
		itemChip = new ItemChip();
		ItemHandler.regItem(itemChip, Refs.CREATIVE_TAB);
	}

	public static void regRenders() {
		for (int i = 0; i < EnumHandler.ChipTypes.values().length; i++) {
			ItemHandler.regRender(itemChip, Refs.MOD_ID, i, "chip_" + EnumHandler.ChipTypes.values()[i].getName());
		}
	}

}
