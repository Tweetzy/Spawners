package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Options;
import ca.tweetzy.spawners.api.spawner.Preset;
import lombok.AllArgsConstructor;
import org.bukkit.entity.EntityType;

/**
 * Date Created: May 10 2022
 * Time Created: 1:04 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class SpawnerPreset implements Preset {

	private final String id;
	private EntityType entityType;
	private Options options;
	private int level;

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public EntityType getEntityType() {
		return this.entityType;
	}

	@Override
	public Options getOptions() {
		return this.options;
	}

	@Override
	public int getLevel() {
		return this.level;
	}

	@Override
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void setOptions(Options options) {
		this.options = options;
	}


	@Override
	public void sync() {
		Spawners.getDataManager().updateSpawnerPreset(this, null);
	}
}
