package ivansteklow.vanillaex;

import ivansteklow.vanillaex.commands.GamemodeCmd;
import ivansteklow.vanillaex.commands.MainCmd;
import ivansteklow.vanillaex.config.VExConfig;
import ivansteklow.vanillaex.init.CraftTweaker;
import ivansteklow.vanillaex.init.ModBlocks;
import ivansteklow.vanillaex.init.ModItems;
import ivansteklow.vanillaex.init.Refs;
import ivansteklow.vanillaex.network.PacketGetWorker;
import ivansteklow.vanillaex.network.PacketHandler;
import ivansteklow.vanillaex.network.PacketReturnWorker;
import ivansteklow.vanillaex.proxy.ServerProxy;
import ivansteklow.vanillaex.tileentities.TileEntityBlockBreaker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Refs.MOD_ID, name = Refs.NAME, version = Refs.VERSION, acceptedMinecraftVersions = Refs.ACCEPTED_VERSIONS, guiFactory = Refs.GUI_FACTORY, dependencies = Refs.MOD_DEPENDENCIES)
public class ModCore {

	@Instance
	public static ModCore instance;

	@SidedProxy(clientSide = Refs.CLIENT_PROXY_CLASS, serverSide = Refs.SERVER_PROXY_CLASS)
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		VExConfig.preInit();
		ModBlocks.init();
		ModItems.init();
		GameRegistry.registerTileEntity(TileEntityBlockBreaker.class, Refs.MOD_ID + ":blockBreaker");
		proxy.preInit(e);

	}

	@EventHandler
	public void Init(FMLInitializationEvent e) {
		proxy.Init(e);
		proxy.registerModelBakeryVariants();
		CraftTweaker.register();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		PacketHandler.NETWORKINSTANCE.registerMessage(PacketGetWorker.Handler.class, PacketGetWorker.class, PacketHandler.nextID(), Side.SERVER);
		PacketHandler.NETWORKINSTANCE.registerMessage(PacketReturnWorker.Handler.class, PacketReturnWorker.class, PacketHandler.nextID(), Side.CLIENT);
		event.registerServerCommand(new MainCmd());
		event.registerServerCommand(new GamemodeCmd());
	}

}
