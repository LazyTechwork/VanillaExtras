package ivansteklow.vanillaex.network;

import io.netty.buffer.ByteBuf;
import ivansteklow.isdev.Refs;
import ivansteklow.isdev.utils.Utils;
import ivansteklow.vanillaex.capabilities.IWork;
import ivansteklow.vanillaex.init.ModCapabilities;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketGetWorker implements IMessage {

	private boolean messageValid;

	private BlockPos pos;
	private EnumFacing side;

	private String className;
	private String cooldownFieldName;
	private String maxCooldownFieldName;

	public PacketGetWorker() {
		this.messageValid = false;
	}

	public PacketGetWorker(BlockPos pos, EnumFacing side, String className, String cooldownFieldName,
			String maxCooldownFieldName) {
		this.pos = pos;
		this.side = side;
		this.className = className;
		this.cooldownFieldName = cooldownFieldName;
		this.maxCooldownFieldName = maxCooldownFieldName;
		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
			this.side = EnumFacing.byName(ByteBufUtils.readUTF8String(buf));
			this.className = ByteBufUtils.readUTF8String(buf);
			this.cooldownFieldName = ByteBufUtils.readUTF8String(buf);
			this.maxCooldownFieldName = ByteBufUtils.readUTF8String(buf);
		} catch (IndexOutOfBoundsException ioe) {
			Utils.getLogger(Refs.MOD_NAME).catching(ioe);
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		ByteBufUtils.writeUTF8String(buf, this.side.getName2());
		ByteBufUtils.writeUTF8String(buf, this.className);
		ByteBufUtils.writeUTF8String(buf, this.cooldownFieldName);
		ByteBufUtils.writeUTF8String(buf, this.maxCooldownFieldName);
	}

	public static class Handler implements IMessageHandler<PacketGetWorker, IMessage> {

		@Override
		public IMessage onMessage(PacketGetWorker message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(PacketGetWorker message, MessageContext ctx) {
			TileEntity te = ctx.getServerHandler().playerEntity.getServerWorld().getTileEntity(message.pos);
			if (te == null)
				return;
			if (!te.hasCapability(ModCapabilities.CAPABILITY_WORKER, message.side))
				return;
			IWork worker = te.getCapability(ModCapabilities.CAPABILITY_WORKER, message.side);
			PacketHandler.NETWORKINSTANCE.sendTo(
					new PacketReturnWorker(worker.getWorkDone(), worker.getMaxWork(), message.className,
							message.cooldownFieldName, message.maxCooldownFieldName),
					ctx.getServerHandler().playerEntity);
		}
	}

}
