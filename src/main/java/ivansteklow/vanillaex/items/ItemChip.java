package ivansteklow.vanillaex.items;

import ivansteklow.vanillaex.handlers.EnumHandler.ChipTypes;
import ivansteklow.vanillaex.init.Refs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemChip extends Item {

	public ItemChip() {
		this.setUnlocalizedName("chip");
		this.setRegistryName(new ResourceLocation(Refs.MOD_ID, "chip"));
		this.setHasSubtypes(true);
		this.setCreativeTab(Refs.CREATIVE_TAB);
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < ChipTypes.values().length; i++) {
			items.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for (int i = 0; i < ChipTypes.values().length; i++) {
			if (stack.getItemDamage() == i) {
				return this.getUnlocalizedName() + "." + ChipTypes.values()[i].getName();
			} else {
				continue;
			}
		}
		return this.getUnlocalizedName() + "." + ChipTypes.BASIC.getName();
	}
}
