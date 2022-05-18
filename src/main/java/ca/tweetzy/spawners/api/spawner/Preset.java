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
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Map;

/**
 * Date Created: May 10 2022
 * Time Created: 1:03 p.m.
 *
 * @author Kiran Hart
 */
public interface Preset extends DataSync, Jsonable {

	String getId();

	EntityType getEntityType();

	Map<LevelOption, Level> getLevels();

	void setEntityType(EntityType entityType);

	void setLevels(Map<LevelOption, Level> levels);
}
