package ivansteklow.vanillaex.init;

import ivansteklow.vanillaex.creativetabs.TabVanillaEx;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Mod reference
 * @author IvanSteklow
 *
 */
public class Refs {

	public static final String MOD_ID = "vanillaex";
	public static final String NAME = "Vanilla Extras";
	public static final String VERSION = "1.0.0";
	public static final String ACCEPTED_VERSIONS = "[1.11.2]";
	public static final String MOD_DEPENDENCIES = "required-after:isdev@[1.0.0,);"
			+ "required-after:forge@[13.20.0.2228,);";

	public static final String CLIENT_PROXY_CLASS = "ivansteklow.vanillaex.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "ivansteklow.vanillaex.proxy.ServerProxy";
	
	public static final String GUI_FACTORY = "ivansteklow.vanillaex.config.VExGuiFactory";

	public static final CreativeTabs CREATIVE_TAB = new TabVanillaEx();

}
