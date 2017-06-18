package ivansteklow.vanillaex.container;

import ivansteklow.isdev.inventory.SlotItemEnchantedBook;
import ivansteklow.vanillaex.tileentities.TileEntityBlockBreaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Container for Block Breaker
 * 
 * @author IvanSteklow
 *
 */
public class ContainerBlockBreaker extends Container {

	private TileEntityBlockBreaker te;
	private IItemHandler handler;

	/**
	 * Adds slots to Block Breaker: * 9 slots for breaked block * 1 slot for
	 * enchanted book * Inventory slots
	 * 
	 * @param playerInv
	 *            Player inventory for registering it in Gui
	 * @param te
	 *            Tile Entity
	 */
	public ContainerBlockBreaker(IInventory playerInv, TileEntityBlockBreaker te) {
		this.te = te;
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

		this.addSlotToContainer(new SlotItemHandler(handler, 0, 62, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 80, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 98, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 3, 62, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 4, 80, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 5, 98, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 6, 62, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 7, 80, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 8, 98, 53));
		this.addSlotToContainer(new SlotItemEnchantedBook(handler, 9, 134, 17));

		int xPos = 8;
		int yPos = 84;

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.te.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < this.handler.getSlots()) {
				// From the block breaker inventory to player's inventory
				if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
					return ItemStack.EMPTY;
			} else {
				// From the player's inventory to block breaker's inventory
				if (current.getItem() == Items.ENCHANTED_BOOK) {
					if (!this.mergeItemStack(current, 9, handler.getSlots(), false))
						return ItemStack.EMPTY;
				}
				if (!this.mergeItemStack(current, 0, handler.getSlots(), false))
					return ItemStack.EMPTY;
			}

			if (current.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			if (current.getCount() == previous.getCount())
				return null;
			slot.onTake(playerIn, current);
		}
		return previous;
	}

}
