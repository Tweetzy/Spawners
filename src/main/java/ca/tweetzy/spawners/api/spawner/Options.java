package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.Jsonable;

/**
 * Date Created: May 04 2022
 * Time Created: 10:52 a.m.
 *
 * @author Kiran Hart
 */
public interface Options extends Jsonable {

	int getSpawnInterval();

	int getSpawnCount();

	int getMaxNearbyEntities();

	int getPlayerActivationRange();

	void setSpawnInterval(int spawnInterval);

	void setSpawnCount(int spawnCount);

	void setMaxNearbyEntities(int maxNearbyEntities);

	void setPlayerActivationRange(int playerActivationRange);
}
