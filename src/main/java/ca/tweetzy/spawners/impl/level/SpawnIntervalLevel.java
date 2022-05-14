package ca.tweetzy.spawners.impl.level;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;

/**
 * Date Created: May 13 2022
 * Time Created: 11:10 a.m.
 *
 * @author Kiran Hart
 */
public final class SpawnIntervalLevel extends Level {

	public SpawnIntervalLevel(int level, double cost, final int spawnInterval) {
		super(LevelOption.SPAWN_INTERVAL, level, spawnInterval, cost);
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public void sync() {
		Spawners.getDataManager().updateLevel(this, null);
	}
}
