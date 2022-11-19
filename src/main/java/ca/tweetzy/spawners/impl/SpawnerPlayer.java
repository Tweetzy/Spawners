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
package ca.tweetzy.spawners.impl;

import ca.tweetzy.flight.utils.PlayerUtil;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import ca.tweetzy.spawners.settings.Settings;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Date Created: May 04 2022
 * Time Created: 10:59 a.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class SpawnerPlayer implements SpawnerUser {

	private final UUID uuid;
	private final String name;

	public SpawnerPlayer(@NonNull final Player player) {
		this(player.getUniqueId(), player.getName());
	}

	@Override
	public UUID getUUID() {
		return this.uuid;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Spawner> getPlacedSpawners() {
		return Spawners.getSpawnerManager().getContents().stream().filter(spawner -> spawner.getOwner().equals(this.uuid)).toList();
	}

	@Override
	public int getMaxPlaceableSpawners(Player player) {
		return PlayerUtil.getNumberPermission(player, "spawners.maxplace", Settings.MAX_SPAWNER_PER_PLAYER.getInt());
	}

	@Override
	public boolean isAllowedToPlaceSpawners(Player player) {
		return getPlacedSpawners().size() < getMaxPlaceableSpawners(player);
	}

	@Override
	public boolean isAllowedToMineEntity(Player player, EntityType entityType) {
		return player.hasPermission("spawners.mine." + entityType.name().toLowerCase().replace("_", "")) || player.isOp();
	}

	@Override
	public boolean isAllowedToPlaceEntity(Player player, EntityType entityType) {
		return player.hasPermission("spawners.place." + entityType.name().toLowerCase().replace("_", "")) || player.isOp();
	}

	@Override
	public boolean isAllowedToChangeWithEgg(Player player, EntityType entityType) {
		return player.hasPermission("spawners.eggchange." + entityType.name().toLowerCase().replace("_", "")) || player.isOp();
	}

	@Override
	public boolean isAllowedToThrowSpawnEgg(Player player, EntityType entityType) {
		return player.hasPermission("spawners.throwegg." + entityType.name().toLowerCase().replace("_", "")) || player.isOp();
	}
}
