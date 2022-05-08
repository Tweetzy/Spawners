package ca.tweetzy.spawners.commands;

import ca.tweetzy.rose.command.AllowedExecutor;
import ca.tweetzy.rose.command.Command;
import ca.tweetzy.rose.command.ReturnType;
import ca.tweetzy.spawners.impl.SpawnerOptions;
import ca.tweetzy.spawners.model.SpawnerItem;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Date Created: May 04 2022
 * Time Created: 10:43 p.m.
 *
 * @author Kiran Hart
 */
public final class GiveCommand extends Command {

	public GiveCommand() {
		super(AllowedExecutor.PLAYER, "give");
	}

	@Override
	protected ReturnType execute(CommandSender sender, String... args) {
		final Player player = (Player) sender;

		player.getInventory().addItem(SpawnerItem.make(player, EntityType.COW, -1, new SpawnerOptions()));

		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> tab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return null;
	}

	@Override
	public String getSyntax() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}
}
