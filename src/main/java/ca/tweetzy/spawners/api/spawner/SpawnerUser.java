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
package ca.tweetzy.spawners.api.spawner;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Date Created: May 04 2022
 * Time Created: 10:56 a.m.
 *
 * @author Kiran Hart
 */
public interface SpawnerUser {

	UUID getUUID();

	String getName();

	List<Spawner> getPlacedSpawners();

	int getMaxPlaceableSpawners(Player player);

	boolean isAllowedToPlaceSpawners(Player player);

	boolean isAllowedToPlaceEntity(Player player, EntityType entityType);

	boolean isAllowedToMineEntity(Player player, EntityType entityType);

	boolean isAllowedToChangeWithEgg(Player player, EntityType entityType);

	boolean isAllowedToThrowSpawnEgg(Player player, EntityType entityType);
}
