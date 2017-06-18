package ivansteklow.vanillaex.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ivansteklow.vanillaex.init.Refs;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Vanilla Extras configuration file
 * 
 * @author IvanSteklow
 *
 */
public class VExConfig {

	private static Configuration config = null;

	public static final String CATEGORY_NAME_BLOCKS = "blocks";

	public static int machineCooldownBasic;
	public static int machineCooldownAdvanced;

	/**
	 * Initialization config in Minecraft Add this method in fml pre
	 * initialization event
	 */
	public static void preInit() {
		File configFile = new File(Loader.instance().getConfigDir(), "VanillaExConfig.cfg");
		config = new Configuration(configFile);
		syncFromFiles();
	}

	/**
	 * Returns configuration instance
	 * 
	 * @return Configuration
	 */
	public static Configuration getConfig() {
		return config;
	}

	/**
	 * Initialization config in Minecraft Add this method on client side pre
	 * initialization
	 */
	public static void clientPreInit() {
		MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
	}

	public static void syncFromFiles() {
		syncConfig(true, true);
	}

	public static void syncFromGui() {
		syncConfig(false, true);
	}

	public static void syncFromFields() {
		syncConfig(false, false);
	}

	private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {
		if (loadFromConfigFile)
			config.load();

		Property propertyMachineCooldownBasic = config.get(CATEGORY_NAME_BLOCKS, "machine_cooldown_basic", 100);
		propertyMachineCooldownBasic.setLanguageKey("gui.config.blocks.machine_cooldown_basic.name");
		propertyMachineCooldownBasic.setComment(I18n.format("gui.config.blocks.machine_cooldown_basic.comment"));
		propertyMachineCooldownBasic.setMinValue(10);
		propertyMachineCooldownBasic.setMaxValue(200);
		Property propertyMachineCooldownAdvanced = config.get(CATEGORY_NAME_BLOCKS, "machine_cooldown_advanced", 50);
		propertyMachineCooldownAdvanced.setLanguageKey("gui.config.blocks.machine_cooldown_advanced.name");
		propertyMachineCooldownAdvanced.setComment(I18n.format("gui.config.blocks.machine_cooldown_advanced.comment"));
		propertyMachineCooldownAdvanced.setMinValue(10);
		propertyMachineCooldownAdvanced.setMaxValue(200);

		List<String> propertyOrderBlocks = new ArrayList<String>();
		propertyOrderBlocks.add(propertyMachineCooldownBasic.getName());
		propertyOrderBlocks.add(propertyMachineCooldownAdvanced.getName());
		config.setCategoryPropertyOrder(CATEGORY_NAME_BLOCKS, propertyOrderBlocks);

		if (readFieldsFromConfig) {
			machineCooldownBasic = propertyMachineCooldownBasic.getInt();
			machineCooldownAdvanced = propertyMachineCooldownAdvanced.getInt();
		}

		propertyMachineCooldownBasic.set(machineCooldownBasic);
		propertyMachineCooldownAdvanced.set(machineCooldownAdvanced);

		if (config.hasChanged())
			config.save();
	}

	public static class ConfigEventHandler {

		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Refs.MOD_ID)) {
				syncFromGui();
			}
		}

	}

}
