package ivansteklow.vanillaex.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class MainCmd extends CommandBase {

	@Override
	public String getName() {
		return "ish";
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return "ish help (?)";
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
				player.sendMessage(new TextComponentString("[ISH] Usage:"));
				player.sendMessage(new TextComponentString("[ISH] /ish killa (killall) - kill all mobs and drops"));
				player.sendMessage(new TextComponentString("[ISH] /ish heal (healme) - heal player"));
				player.sendMessage(
						new TextComponentString("[ISH] /ish oheart (oneheart) - set minimal health and hunger"));
				return;
			}
			if (args[0].equals("killa") || args[0].equals("killall")) {
				server.commandManager.executeCommand(server.getServer(), "/kill @e[type=!Player]");
			} else if (args[0].equals("heal") || args[0].equals("healme")) {
				player.setHealth(player.getMaxHealth());
				player.getFoodStats().setFoodLevel(20);
			} else if (args[0].equals("oheart") || args[0].equals("oneheart")) {
				player.setHealth(1.0f);
				player.getFoodStats().setFoodLevel(1);
			} else if (args[0].equals("help") || args[0].equals("?")) {
				player.sendMessage(new TextComponentString("[ISH] Usage:"));
				player.sendMessage(new TextComponentString("[ISH] /ish killa (killall) - kill all mobs and drops"));
				player.sendMessage(new TextComponentString("[ISH] /ish heal (healme) - heal player"));
				player.sendMessage(
						new TextComponentString("[ISH] /ish oheart (oneheart) - set minimal health and hunger"));
			} else if (args[0].equals("out")) {
				if (args[1].equals("0")) {
					server.commandManager.executeCommand(player.getCommandSenderEntity(),
							"/gamerule commandBlockOutput false");
				} else if (args[1].equals("1")) {
					server.commandManager.executeCommand(player.getCommandSenderEntity(),
							"/gamerule commandBlockOutput true");
				}
			} else {
				player.sendMessage(new TextComponentString("[ISH] Wrong usage! Usage:"));
				player.sendMessage(new TextComponentString("[ISH] /ish killa (killall) - kill all mobs and drops"));
				player.sendMessage(new TextComponentString("[ISH] /ish heal (healme) - heal player"));
				player.sendMessage(
						new TextComponentString("[ISH] /ish oheart (oneheart) - set minimal health and hunger"));
			}
		}
	}
}