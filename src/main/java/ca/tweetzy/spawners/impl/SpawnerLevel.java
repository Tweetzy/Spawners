package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.settings.Settings;
import lombok.AllArgsConstructor;

/**
 * Date Created: May 04 2022
 * Time Created: 9:27 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class SpawnerLevel implements Level {

	private final int level;
	private int spawnInterval;
	private int spawnCount;
	private int maxNearbyEntities;
	private int playerActivationRange;
	private double cost;

	public SpawnerLevel(final int level) {
		this(level, Settings.DEFAULT_SPAWNER_DELAY.getInt(), Settings.DEFAULT_SPAWNER_SPAWN_COUNT.getInt(), Settings.DEFAULT_SPAWNER_MAX_NEARBY_ENTITIES.getInt(), Settings.DEFAULT_SPAWNER_ACTIVATION_RANGE.getInt(), Settings.DEFAULT_LEVEL_COST.getDouble());
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public double getCost() {
		return this.cost;
	}

	@Override
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int getSpawnInterval() {
		return this.spawnInterval;
	}

	@Override
	public int getSpawnCount() {
		return this.spawnCount;
	}

	@Override
	public int getMaxNearbyEntities() {
		return this.maxNearbyEntities;
	}

	@Override
	public int getPlayerActivationRange() {
		return this.playerActivationRange;
	}

	@Override
	public void setSpawnInterval(int spawnInterval) {
		this.spawnInterval = spawnInterval;
	}

	@Override
	public void setSpawnCount(int spawnCount) {
		this.spawnCount = spawnCount;
	}

	@Override
	public void setMaxNearbyEntities(int maxNearbyEntities) {
		this.maxNearbyEntities = maxNearbyEntities;
	}

	@Override
	public void setPlayerActivationRange(int playerActivationRange) {
		this.playerActivationRange = playerActivationRange;
	}

	@Override
	public void sync() {
		Spawners.getDataManager().updateLevel(this, null);
	}

	@Override
	public String getJsonString() {
		return null;
	}
}
