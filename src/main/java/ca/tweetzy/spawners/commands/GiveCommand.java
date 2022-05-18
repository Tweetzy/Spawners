package ca.tweetzy.spawners.commands;

import ca.tweetzy.rose.command.AllowedExecutor;
import ca.tweetzy.rose.command.Command;
import ca.tweetzy.rose.command.ReturnType;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.model.SpawnerBuilder;
import ca.tweetzy.spawners.settings.Translation;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Date Created: May 04 2022
 * Time Created: 10:43 p.m.
 *
 * @author Kiran Hart
 */
public final class GiveCommand extends Command {

	public GiveCommand() {
		super(AllowedExecutor.BOTH, "give");
	}

	@Override
	protected ReturnType execute(CommandSender sender, String... args) {
		if (args.length == 0) {

			return ReturnType.SUCCESS;
		}

		final boolean isGivingAll = args[0].equals("*");

		final Player target = Bukkit.getPlayerExact(args[0]);

		if (!isGivingAll)
			if (target == null) {
				Translation.PLAYER_OFFLINE.send(sender, "player", args[0]);
				return ReturnType.FAIL;
			}

		int amount = 1;

		if (args.length > 1) {
			if (NumberUtils.isNumber(args[1]))
				amount = Integer.parseInt(args[1]);
		}

		// check for flags
		final EntityType entityType = CommandFlag.get(EntityType.class, "entity", EntityType.PIG, args);
		final String preset = CommandFlag.get(String.class, "preset", null, args);

		Preset presetFound = null;

		if (preset != null) {
			presetFound = Spawners.getPresetManager().find(preset);
		}

		if (isGivingAll)
			for (Player player : Bukkit.getOnlinePlayers()) {
				final ItemStack spawnerItem = presetFound != null ? SpawnerBuilder.of(player, presetFound).make() : SpawnerBuilder.of(player, entityType).make();

				for (int i = 0; i < amount; i++)
					player.getInventory().addItem(spawnerItem);
			}
		else {
			final ItemStack spawnerItem = presetFound != null ? SpawnerBuilder.of(target, presetFound).make() :SpawnerBuilder.of(target, entityType).make();

			for (int i = 0; i < amount; i++)
				target.getInventory().addItem(spawnerItem);
		}

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
