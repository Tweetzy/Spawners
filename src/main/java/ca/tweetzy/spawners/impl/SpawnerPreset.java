package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Preset;
import lombok.AllArgsConstructor;
import org.bukkit.entity.EntityType;

import java.util.List;

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
	private List<Level> levels;

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public EntityType getEntityType() {
		return this.entityType;
	}

	@Override
	public List<Level> getLevels() {
		return this.levels;
	}


	@Override
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	@Override
	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	@Override
	public void sync() {
//		Spawners.getDataManager().updateSpawnerPreset(this, null);
	}
}
