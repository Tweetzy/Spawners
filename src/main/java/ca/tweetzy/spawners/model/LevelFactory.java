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
