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

import ca.tweetzy.rose.command.AllowedExecutor;
import ca.tweetzy.rose.command.Command;
import ca.tweetzy.rose.command.ReturnType;
import ca.tweetzy.rose.utils.ChatUtil;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.settings.Translation;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

/**
 * Date Created: June 02 2022
 * Time Created: 3:51 p.m.
 *
 * @author Kiran Hart
 */
public final class ButcherCommand extends Command {

	public ButcherCommand() {
		super(AllowedExecutor.BOTH, "butcher");
	}

	@Override
	protected ReturnType execute(CommandSender sender, String... args) {
		final NamespacedKey key = new NamespacedKey(Spawners.getInstance(), "SpawnersEntityOwner");

		if (Bukkit.getWorlds().isEmpty()) return ReturnType.FAIL;

		int count = 0;
		if (args.length == 0) {
			for (World world : Bukkit.getWorlds()) {
				for (LivingEntity livingEntity : world.getLivingEntities()) {

					if (livingEntity.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
						livingEntity.remove();
						count++;
					}
				}
			}

			Translation.REMOVED_ENTITIES_ALL.send(sender, "total", count);
			return ReturnType.SUCCESS;
		}

		EntityType entityType = EntityType.valueOf(args[0].toUpperCase());
		final World world = args.length == 2 ? Bukkit.getWorld(args[1]) : Bukkit.getWorlds().get(0);

		if (entityType == null)
			entityType = EntityType.PIG;

		if (world != null)
			for (LivingEntity livingEntity : world.getLivingEntities()) {
				if (livingEntity.getType() != entityType) continue;

				if (livingEntity.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
					livingEntity.remove();
					count++;
				}
			}

		else
			for (World allWorld : Bukkit.getWorlds()) {
				for (LivingEntity livingEntity : allWorld.getLivingEntities()) {
					if (livingEntity.getType() != entityType) continue;

					if (livingEntity.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
						livingEntity.remove();
						count++;
					}
				}
			}


		Translation.REMOVED_ENTITIES_ENTITY.send(sender, "total", count, "entity_type", ChatUtil.capitalizeFully(entityType));
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> tab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "spawners.command.butcher";
	}

	@Override
	public String getSyntax() {
		return "[entity] [world]";
	}

	@Override
	public String getDescription() {
		return "Remove all mobs spawned from Spawners";
	}
}
