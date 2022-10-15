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

import ca.tweetzy.spawners.api.DataSync;
import ca.tweetzy.spawners.api.Jsonable;
import ca.tweetzy.spawners.api.LevelOption;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

/**
 * Date Created: May 04 2022
 * Time Created: 10:44 a.m.
 *
 * @author Kiran Hart
 */
public interface Spawner extends DataSync, Jsonable {

	UUID getID();

	UUID getOwner();

	String getOwnerName();

	EntityType getEntityType();

	Map<LevelOption, Level> getLevels();

	Location getLocation();

	void setOwner(UUID owner);

	void setOwnerName(String ownerName);

	void setEntityType(EntityType entityType);

	void setLevels(Map<LevelOption, Level> levels);

	Level getNextLevel(LevelOption levelOption);

	void tryUpgrade(Player player, LevelOption levelOption);

	/**
	 * Used to merge the passed spawner into this
	 * spawner
	 *
	 * @param spawner is the spawner that should be merged
	 *                into the current spawner
	 */
	void merge(Spawner spawner);
}
