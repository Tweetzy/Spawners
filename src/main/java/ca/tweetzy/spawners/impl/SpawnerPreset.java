package ca.tweetzy.spawners.impl;

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

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public EntityType getEntityType() {
		return this.entityType;
	}


	@Override
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}


	@Override
	public void sync() {
//		Spawners.getDataManager().updateSpawnerPreset(this, null);
	}
}
