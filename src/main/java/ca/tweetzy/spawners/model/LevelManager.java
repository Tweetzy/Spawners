package ca.tweetzy.spawners.model;

import ca.tweetzy.spawners.api.interfaces.Level;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date Created: May 04 2022
 * Time Created: 11:13 a.m.
 *
 * @author Kiran Hart
 */
public final class LevelManager implements Manager {

	private final Map<Integer, Level> levels = new ConcurrentHashMap<>();

	public void addLevel(@NonNull final Level level) {
		if (this.levels.containsKey(level.getLevel())) return;
		this.levels.put(level.getLevel(), level);
	}

	public void removeLevel(final int level) {
		if (!this.levels.containsKey(level)) return;
		this.levels.remove(level);
	}

	public Level findLevel(final int level) {
		return this.levels.getOrDefault(level, null);
	}

	public Level findLevel(@NonNull final Level level) {
		return findLevel(level.getLevel());
	}

	public List<Level> getLevels() {
		return List.copyOf(this.levels.values());
	}

	/*
	=================== DATABASE CALLS ===================
	 */

//	public void createPlayer(@NonNull final Player player, final BiConsumer<Boolean, SpawnerUser> consumer) {
//		Spawners.getDataManager().insertUser(new SpawnerPlayer(player), (error, created) -> {
//			if (error == null)
//				this.addLevel(created);
//
//			if (consumer != null)
//				consumer.accept(error == null, created);
//		});
//	}

	@Override
	public void load() {
		// clear player list
		this.levels.clear();


	}
}