package ca.tweetzy.spawners.commands;

import ca.tweetzy.rose.command.AllowedExecutor;
import ca.tweetzy.rose.command.Command;
import ca.tweetzy.rose.command.ReturnType;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Date Created: May 04 2022
 * Time Created: 10:42 a.m.
 *
 * @author Kiran Hart
 */
public final class SpawnersCommand extends Command {

	public SpawnersCommand() {
		super(AllowedExecutor.BOTH, "spawners");
	}

	@Override
	protected ReturnType execute(CommandSender sender, String... args) {
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> tab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "spawners.cmd";
	}

	@Override
	public String getSyntax() {
		return null;
	}

	@Override
	public String getDescription() {
		return "The main command for the plugin";
	}
}
