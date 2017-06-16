package ivansteklow.vanillaex.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler {

	protected ItemStack turnBottleIntoItem(ItemStack originalstack, int ItemsCount, EntityPlayer player,
			ItemStack stack) {
		originalstack.shrink(ItemsCount);

		if (originalstack.isEmpty()) {
			return stack;
		} else {
			if (!player.inventory.addItemStackToInventory(stack)) {
				player.dropItem(stack, false);
			}

			return originalstack;
		}
	}

	@SubscribeEvent
	public void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
		if (event.getItemStack().getItem() == Items.GLASS_BOTTLE) {
			if (event.getEntityPlayer().experienceLevel >= 1) {
				int j = 26 / (3 + event.getWorld().rand.nextInt(5) + event.getWorld().rand.nextInt(5));
				if (j >= event.getEntityPlayer().getActiveItemStack().getCount()) {
					event.getEntityPlayer().removeExperienceLevel(1);
					event.getEntityPlayer().attackEntityFrom(DamageSource.GENERIC, 0.1f);
					event.getEntityPlayer().heal(0.1f);
					turnBottleIntoItem(event.getItemStack(), j, event.getEntityPlayer(),
							new ItemStack(Items.EXPERIENCE_BOTTLE, j));
				}
			}
		}
	}

}
