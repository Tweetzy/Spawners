package ca.tweetzy.spawners.commands;

import ca.tweetzy.rose.command.AllowedExecutor;
import ca.tweetzy.rose.command.Command;
import ca.tweetzy.rose.command.ReturnType;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.impl.SpawnerOptions;
import ca.tweetzy.spawners.model.SpawnerItem;
import ca.tweetzy.spawners.settings.Settings;
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

		final Player target = Bukkit.getPlayerExact(args[0]);
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

		ItemStack spawnerItem = SpawnerItem.make(
				target.getUniqueId(),
				target.getName(),
				presetFound != null ? presetFound.getEntityType() : entityType,
				presetFound != null ? presetFound.getLevel() : -1,
				new SpawnerOptions(
						presetFound != null ? presetFound.getOptions().getSpawnInterval() : Settings.DEFAULT_SPAWNER_DELAY.getInt(),
						presetFound != null ? presetFound.getOptions().getSpawnCount() : Settings.DEFAULT_SPAWNER_SPAWN_COUNT.getInt(),
						presetFound != null ? presetFound.getOptions().getMaxNearbyEntities() : Settings.DEFAULT_SPAWNER_MAX_NEARBY_ENTITIES.getInt(),
						presetFound != null ? presetFound.getOptions().getPlayerActivationRange() : Settings.DEFAULT_SPAWNER_ACTIVATION_RANGE.getInt()
				)
		);

		for (int i = 0; i < amount; i++)
			target.getInventory().addItem(spawnerItem);

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
