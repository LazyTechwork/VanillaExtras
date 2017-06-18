package ivansteklow.vanillaex.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ivansteklow.vanillaex.init.Refs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement.DummyCategoryElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.GuiConfigEntries.CategoryEntry;
import net.minecraftforge.fml.client.config.IConfigElement;

/**
 * Gui factory for mod
 * 
 * @author IvanSteklow
 *
 */
public class VExGuiFactory implements IModGuiFactory {

	@Override
	public void initialize(Minecraft minecraftInstance) {
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return VExConfigGui.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}

	public static class VExConfigGui extends GuiConfig {

		public VExConfigGui(GuiScreen parentScreen) {
			super(parentScreen, getConfigElements(), Refs.MOD_ID, false, false, I18n.format("gui.config.main_title"));
		}

		private static List<IConfigElement> getConfigElements() {
			List<IConfigElement> list = new ArrayList<IConfigElement>();
			list.add(new DummyCategoryElement(I18n.format("gui.config.category.blocks"), "gui.config.category.blocks",
					CategoryEntryBlocks.class));
			return list;
		}

		public static class CategoryEntryBlocks extends CategoryEntry {

			public CategoryEntryBlocks(GuiConfig owningScreen, GuiConfigEntries owningEntryList,
					IConfigElement configElement) {
				super(owningScreen, owningEntryList, configElement);
			}

			@Override
			protected GuiScreen buildChildScreen() {
				Configuration config = VExConfig.getConfig();
				ConfigElement categoryBlocks = new ConfigElement(config.getCategory(VExConfig.CATEGORY_NAME_BLOCKS));
				List<IConfigElement> propertiesOnScreen = categoryBlocks.getChildElements();
				String windowTitle = I18n.format("gui.config.category.blocks");
				return new GuiConfig(owningScreen, propertiesOnScreen, owningScreen.modID,
						this.configElement.requiresWorldRestart() || this.owningScreen.allRequireWorldRestart,
						this.configElement.requiresMcRestart() || this.owningScreen.allRequireMcRestart, windowTitle);
			}

		}

	}

	public boolean hasConfigGui() {
		return true;
	}

	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new VExConfigGui(parentScreen);
	}

}
