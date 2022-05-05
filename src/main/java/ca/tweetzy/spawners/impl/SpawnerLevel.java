package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.api.interfaces.Level;
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

	public SpawnerLevel(final int level) {
		this(level, 20, 4, 16, 16);
	}

	@Override
	public int getLevel() {
		return this.level;
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
}
