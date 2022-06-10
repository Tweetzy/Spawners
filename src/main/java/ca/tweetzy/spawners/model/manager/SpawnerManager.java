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
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.settings.Settings;
import lombok.NonNull;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Date Created: May 05 2022
 * Time Created: 10:47 a.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerManager implements Manager {

	public Map<Location, Spawner> contents = new ConcurrentHashMap<>();

	public void add(@NotNull Spawner spawner) {
		if (this.contents.containsKey(spawner.getLocation())) return;
		this.contents.put(spawner.getLocation(), spawner);
	}

	public void remove(@NotNull Location location) {
		if (!this.contents.containsKey(location)) return;
		this.contents.remove(location);
	}

	public Spawner find(@NotNull Location location) {
		return this.contents.getOrDefault(location, null);

	}

	public List<Spawner> getContents() {
		return List.copyOf(this.contents.values());
	}

	/*
	=================== Spawner Helper ===================
	 */

	public int getSpawnerCountWithinChunk(@NonNull final Chunk chunk) {
		int count = 0;
		if (!chunk.isLoaded()) return count;

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = chunk.getWorld().getMinHeight(); y < chunk.getWorld().getMaxHeight(); y++)
					if (find(chunk.getBlock(x, y, z).getLocation()) != null)
						count++;
			}
		}

		return count;
	}

	public boolean canPlaceSpawnerInChunk(@NonNull final Chunk chunk) {
		return getSpawnerCountWithinChunk(chunk) < Settings.MAX_SPAWNER_PER_CHUNK.getInt();
	}

	public void changeSpawnerEntity(@NonNull final CreatureSpawner spawner, @NonNull final EntityType entityType) {
		spawner.setSpawnedType(entityType);
		spawner.update(true);
	}

	public void applySpawnerDefaults(@NonNull final CreatureSpawner spawner, final boolean update) {
		spawner.setDelay(Settings.DEFAULT_SPAWNER_DELAY.getInt());
		spawner.setMaxSpawnDelay(Settings.DEFAULT_SPAWNER_DELAY.getInt());
		spawner.setMinSpawnDelay(Settings.DEFAULT_SPAWNER_DELAY.getInt());
		spawner.setSpawnCount(Settings.DEFAULT_SPAWNER_SPAWN_COUNT.getInt());
		spawner.setRequiredPlayerRange(Settings.DEFAULT_SPAWNER_ACTIVATION_RANGE.getInt());
		spawner.setMaxNearbyEntities(Settings.DEFAULT_SPAWNER_MAX_NEARBY_ENTITIES.getInt());

		if (update)
			spawner.update(true);
	}

	public void applySpawnerLevel(@NonNull final CreatureSpawner spawner, @NonNull final Level level) {

		switch (level.getLevelOption()) {
			case SPAWN_INTERVAL -> {
				spawner.setMinSpawnDelay(0);
				spawner.setMaxSpawnDelay(level.getValue());
				spawner.setDelay(level.getValue());

			}
			case SPAWN_COUNT -> spawner.setSpawnCount(level.getValue());
			case MAX_NEARBY_ENTITIES -> spawner.setMaxNearbyEntities(level.getValue());
			case ACTIVATION_RANGE -> spawner.setRequiredPlayerRange(level.getValue());
			default -> {
			}
		}

		spawner.update(true);
	}

	/*
	=================== DATABASE CALLS ===================
	 */

	public void createSpawner(@NonNull final Spawner spawner, final BiConsumer<Boolean, Spawner> consumer) {
		Spawners.getDataManager().insertSpawner(spawner, (error, created) -> {
			if (error == null)
				this.add(created);

			if (consumer != null)
				consumer.accept(error == null, created);
		});
	}

	public void deleteSpawner(@NonNull final Spawner spawner, final Consumer<Boolean> success) {
		Spawners.getDataManager().deleteSpawner(spawner.getID(), (error, deleted) -> {
			if (error == null && deleted)
				this.remove(spawner.getLocation());

			if (success != null)
				success.accept(error == null && deleted);
		});
	}

	@Override
	public void load() {
		this.contents.clear();

		Spawners.getDataManager().getSpawners((error, result) -> {
			if (error == null)
				result.forEach(this::add);
		});
	}
}
