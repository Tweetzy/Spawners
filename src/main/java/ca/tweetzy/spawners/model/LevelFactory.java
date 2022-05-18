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
package ca.tweetzy.spawners.model;

import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.impl.level.ActivationRangeLevel;
import ca.tweetzy.spawners.impl.level.NearbyEntitiesLevel;
import ca.tweetzy.spawners.impl.level.SpawnCountLevel;
import ca.tweetzy.spawners.impl.level.SpawnIntervalLevel;
import ca.tweetzy.spawners.settings.Settings;
import lombok.experimental.UtilityClass;

/**
 * Date Created: May 13 2022
 * Time Created: 12:15 p.m.
 *
 * @author Kiran Hart
 */
@UtilityClass
public final class LevelFactory {

	public Level build(LevelOption levelOption, int level, int value, double cost) {
		return switch (levelOption) {
			case SPAWN_INTERVAL -> new SpawnIntervalLevel(level, cost, value);
			case SPAWN_COUNT -> new SpawnCountLevel(level, cost, value);
			case MAX_NEARBY_ENTITIES -> new NearbyEntitiesLevel(level, cost, value);
			case ACTIVATION_RANGE -> new ActivationRangeLevel(level, cost, value);
		};
	}

	public int getDefaultValue(LevelOption levelOption) {
		return switch (levelOption) {
			case SPAWN_INTERVAL -> Settings.DEFAULT_SPAWNER_DELAY.getInt();
			case SPAWN_COUNT -> Settings.DEFAULT_SPAWNER_SPAWN_COUNT.getInt();
			case MAX_NEARBY_ENTITIES -> Settings.DEFAULT_SPAWNER_MAX_NEARBY_ENTITIES.getInt();
			case ACTIVATION_RANGE -> Settings.DEFAULT_SPAWNER_ACTIVATION_RANGE.getInt();
		};
	}
}
