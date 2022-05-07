package ca.tweetzy.spawners.commands;

import ca.tweetzy.rose.command.AllowedExecutor;
import ca.tweetzy.rose.command.Command;
import ca.tweetzy.rose.command.ReturnType;
import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.EntitySelectorGUI;
import ca.tweetzy.spawners.settings.Translation;
import org.apache.commons.lang.StringUtils;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Date Created: May 06 2022
 * Time Created: 9:27 p.m.
 *
 * @author Kiran Hart
 */
public final class SetCommand extends Command {

	public SetCommand() {
		super(AllowedExecutor.PLAYER, "set");
	}

	@Override
	protected ReturnType execute(CommandSender sender, String... args) {
		final Player player = (Player) sender;
		final Block targetBlock = player.getTargetBlock(null, 25);

		if (targetBlock.getType() != CompMaterial.SPAWNER.parseMaterial()) {
			Translation.NOT_A_SPAWNER.send(player);
			return ReturnType.FAIL;
		}

		final Spawner spawner = Spawners.getSpawnerManager().findSpawner(targetBlock.getLocation());
		final CreatureSpawner creatureSpawner = (CreatureSpawner) targetBlock.getState();

		Spawners.getGuiManager().showGUI(player, new EntitySelectorGUI(null, EntitySelectorGUI.EntityViewMode.ALL, selected -> {

			creatureSpawner.setSpawnedType(selected);
			creatureSpawner.update(true);

			Translation.UPDATED_SPAWN_TYPE.send(player, "entity_type", StringUtils.capitalize(selected.name().toLowerCase().replace("_", " ")));
			player.closeInventory();

			if (spawner != null) {
				spawner.setEntityType(selected);
				spawner.sync();
			}
		}));

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
