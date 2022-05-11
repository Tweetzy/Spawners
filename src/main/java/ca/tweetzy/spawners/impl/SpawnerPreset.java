package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.api.spawner.Spawner;
import lombok.AllArgsConstructor;

/**
 * Date Created: May 10 2022
 * Time Created: 1:04 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class SpawnerPreset implements Preset {

	private final String id;
	private final Spawner spawner;

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Spawner getSpawner() {
		return this.spawner;
	}

	@Override
	public void sync() {
		Spawners.getDataManager().updateSpawnerPreset(this, null);
	}
}
