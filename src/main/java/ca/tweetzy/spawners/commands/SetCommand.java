/*
 * Spawners
 * Copyright 2022 Kiran Hart
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package ca.tweetzy.spawners.commands;

import ca.tweetzy.flight.command.AllowedExecutor;
import ca.tweetzy.flight.command.Command;
import ca.tweetzy.flight.command.ReturnType;
import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.utils.ChatUtil;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.selector.EntitySelectorGUI;
import ca.tweetzy.spawners.settings.Translation;
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

		final Spawner spawner = Spawners.getSpawnerManager().find(targetBlock.getLocation());
		final CreatureSpawner creatureSpawner = (CreatureSpawner) targetBlock.getState();

		Spawners.getGuiManager().showGUI(player, new EntitySelectorGUI(null, EntitySelectorGUI.EntityViewMode.ALL, selected -> {

			creatureSpawner.setSpawnedType(selected);
			creatureSpawner.update(true);

			Translation.UPDATED_SPAWN_TYPE.send(player, "entity_type", ChatUtil.capitalizeFully(selected));
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
		return "spawners.command.set";
	}

	@Override
	public String getSyntax() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Set a spawner type";
	}
}
