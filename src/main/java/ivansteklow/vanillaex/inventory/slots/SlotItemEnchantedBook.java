package ivansteklow.vanillaex.inventory.slots;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotItemEnchantedBook extends SlotItemHandler {

	public SlotItemEnchantedBook(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack.getItem() == Items.ENCHANTED_BOOK)
			return true;
		return false;
	}
}
