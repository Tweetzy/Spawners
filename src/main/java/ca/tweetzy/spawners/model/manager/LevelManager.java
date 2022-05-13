package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import ca.tweetzy.spawners.impl.SpawnerPlayer;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Date Created: May 04 2022
 * Time Created: 11:13 a.m.
 *
 * @author Kiran Hart
 */
public final class LevelManager implements Manager {

	private final List<Level> levels = Collections.synchronizedList(new ArrayList<>());

	public void add(@NonNull final Level level) {
		synchronized (this.levels) {
			if (this.levels.contains(level)) return;
			this.levels.add(level);
		}
	}

	public void remove(@NonNull final Level level) {
		synchronized (this.levels) {
			if (!this.levels.contains(level)) return;
			this.levels.remove(level);
		}
	}

	public Level find(@NonNull final LevelOption levelOption, final int levelNumber) {
		synchronized (this.levels) {
			return this.levels.stream().filter(level -> level.getLevelOption() == levelOption && level.getLevelNumber() == levelNumber).findFirst().orElse(null);
		}
	}

	public List<Level> getContents() {
		synchronized (this.levels) {
			return List.copyOf(levels);
		}
	}

	public List<Level> getLevels(@NonNull final LevelOption levelOption) {
		synchronized (this.levels) {
			return List.copyOf(this.levels.stream().filter(level -> level.getLevelOption() == levelOption).collect(Collectors.toList()));
		}
	}

	public int getHighestLevel(@NonNull final LevelOption levelOption) {
		final List<Level> found = getLevels(levelOption);
		return found.isEmpty() ? 0 : Collections.max(found.stream().map(Level::getLevelNumber).collect(Collectors.toList()));
	}

	/*
	=================== DATABASE CALLS ===================
	 */
	public void createLevel(@NonNull final Level level, final BiConsumer<Boolean, Level> consumer) {
		Spawners.getDataManager().insertLevel(level, (error, created) -> {
			if (error == null)
				this.add(created);

			if (consumer != null)
				consumer.accept(error == null, created);
		});
	}

	public void deleteLevel(@NonNull final Level level, final Consumer<Boolean> consumer) {
		Spawners.getDataManager().deleteLevel(level, (error, deleted) -> {
			if (error == null && deleted)
				this.remove(level);

			if (consumer != null)
				consumer.accept(error == null && deleted);
		});
	}


	@Override
	public void load() {

		Spawners.getDataManager().getLevels((error, result) -> {
			if (error == null)
				result.forEach(this::add);
		});
	}
}