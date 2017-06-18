package ivansteklow.vanillaex.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

/**
 * The easy gamemode change command
 * 
 * @author IvanSteklow
 *
 */
public class GamemodeCmd extends CommandBase {

	@Override
	public String getName() {
		return "gm";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return "gm <gamemode>";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		World world = sender.getEntityWorld();
		if (!world.isRemote) {
			EntityPlayerMP player = getCommandSenderAsPlayer(sender);

			if (args.length <= 0) {
				player.sendMessage(new TextComponentString("[ISH] /gm <gamemode>"));
				return;
			}
			if (args[0].equals("0") || args[0].equals("s") || args[0].equals("survival")) {
				player.setGameType(GameType.SURVIVAL);
				player.sendMessage(new TextComponentString("[ISH] Gamemode changed: SURVIVAL"));
			} else if (args[0].equals("1") || args[0].equals("c") || args[0].equals("creative")) {
				player.setGameType(GameType.CREATIVE);
				player.sendMessage(new TextComponentString("[ISH] Gamemode changed: CREATIVE"));
			} else if (args[0].equals("2") || args[0].equals("a") || args[0].equals("adventure")) {
				player.setGameType(GameType.ADVENTURE);
				player.sendMessage(new TextComponentString("[ISH] Gamemode changed: ADVENTURE"));
			} else if (args[0].equals("3") || args[0].equals("sp") || args[0].equals("spectator")) {
				player.setGameType(GameType.SPECTATOR);
				player.sendMessage(new TextComponentString("[ISH] Gamemode changed: SPECTATOR"));
			} else {
				player.sendMessage(new TextComponentString("[ISH] /gm <gamemode>"));
			}
		}
	}
}