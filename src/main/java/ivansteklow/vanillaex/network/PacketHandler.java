package ivansteklow.vanillaex.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	public static SimpleNetworkWrapper NETWORKINSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("VanillaEX");


	private static int ID = 0;

	public static int nextID() {
		return ID++;
	}

	public static void registerMessages(String channelName) {
		NETWORKINSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);

		NETWORKINSTANCE.registerMessage(PacketGetWorker.Handler.class, PacketGetWorker.class, nextID(), Side.SERVER);

		NETWORKINSTANCE.registerMessage(PacketReturnWorker.Handler.class, PacketReturnWorker.class, nextID(), Side.CLIENT);
	}

}
