package ivansteklow.vanillaex.handlers;

import ivansteklow.isdev.utils.Utils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Mod event bus for detecting events
 * @author IvanSteklow
 *
 */
public class ModEventHandler {

	@SubscribeEvent
	public void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
		if (event.getItemStack().getItem() == Items.GLASS_BOTTLE) {
			if (event.getEntityPlayer().experienceLevel >= 1) {
				int j = 26 / (3 + event.getWorld().rand.nextInt(5) + event.getWorld().rand.nextInt(5));
				if (j >= event.getEntityPlayer().getActiveItemStack().getCount()) {
					event.getEntityPlayer().removeExperienceLevel(1);
					event.getEntityPlayer().attackEntityFrom(DamageSource.GENERIC, 0.1f);
					event.getEntityPlayer().heal(0.1f);
					Utils.itemExchange(event.getItemStack(), j, event.getEntityPlayer(),
							new ItemStack(Items.EXPERIENCE_BOTTLE, j));
				}
			}
		}
	}

}
