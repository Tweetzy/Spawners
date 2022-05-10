package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Level;
import lombok.NonNull;

/**
 * Date Created: May 04 2022
 * Time Created: 11:13 a.m.
 *
 * @author Kiran Hart
 */
public final class LevelManager extends Manager<Integer, Level> {

	@Override
	public void add(@NonNull Level level) {
		if (this.contents.containsKey(level.getLevel())) return;
		this.contents.put(level.getLevel(), level);
	}

	@Override
	public void remove(@NonNull Integer level) {
		if (!this.contents.containsKey(level)) return;
		this.contents.remove(level);
	}

	@Override
	public Level find(@NonNull Integer level) {
		return this.contents.getOrDefault(level, null);

	}
	
	public int getHighestLevel() {
		return this.contents.keySet().stream().max(Integer::compare).orElse(0);
	}

	/*
	=================== DATABASE CALLS ===================
	 */

	@Override
	public void load() {
		// clear player list
		this.contents.clear();

		Spawners.getDataManager().getLevels((error, result) -> {
			if (error == null)
				result.forEach(this::add);
		});
	}
}