/*
 * Spawners
 * Copyright 2022 Kiran Hart
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.manager.ListManager;
import ca.tweetzy.spawners.api.spawner.Level;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
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
public final class LevelManager extends ListManager<Level> {

	public LevelManager() {
		super("Level");
	}

	public void remove(@NonNull final Level level) {
		synchronized (this.managerContent) {
			if (!this.managerContent.contains(level)) return;
			this.managerContent.remove(level);
		}
	}

	public Level find(@NonNull final LevelOption levelOption, final int levelNumber) {
		synchronized (this.managerContent) {
			return this.managerContent.stream().filter(level -> level.getLevelOption() == levelOption && level.getLevelNumber() == levelNumber).findFirst().orElse(null);
		}
	}

	public List<Level> getContents() {
		synchronized (this.managerContent) {
			return List.copyOf(managerContent);
		}
	}

	public boolean optionsHasAtLeastOneOption() {
		return !getLevels(LevelOption.SPAWN_INTERVAL).isEmpty()
				&& !getLevels(LevelOption.SPAWN_COUNT).isEmpty()
				&& !getLevels(LevelOption.MAX_NEARBY_ENTITIES).isEmpty()
				&& !getLevels(LevelOption.ACTIVATION_RANGE).isEmpty();
	}

	public List<Level> getLevels(@NonNull final LevelOption levelOption) {
		synchronized (this.managerContent) {
			return List.copyOf(this.managerContent.stream().filter(level -> level.getLevelOption() == levelOption).collect(Collectors.toList()));
		}
	}

	public int getHighestLevel(@NonNull final LevelOption levelOption) {
		final List<Level> found = getLevels(levelOption);

		// check if missing number in sequence first
		if (!found.isEmpty()) {
			final int[] levelNumbers = new int[found.size()];
			Arrays.sort(levelNumbers);

			for (int i = 0; i < found.size(); i++) levelNumbers[i] = found.get(i).getLevelNumber();

			final int missingNumber = calculateMissingNumber(levelNumbers);
			if (missingNumber != 0)
				// minus one since it gets re-added during the creation
				return missingNumber - 1;
		}

		return found.isEmpty() ? 0 : Collections.max(found.stream().map(Level::getLevelNumber).toList());
	}


	private int calculateMissingNumber(final int[] numbers) {
		final int max = numbers[numbers.length - 1];
		final int min = numbers[0];
		final int sum = Arrays.stream(numbers).sum();
		final int actual = (((max * (max + 1)) / 2) - min + 1);
		return actual - sum;
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
		clear();

		Spawners.getDataManager().getLevels((error, result) -> {
			if (error == null)
				result.forEach(this::add);
		});
	}
}