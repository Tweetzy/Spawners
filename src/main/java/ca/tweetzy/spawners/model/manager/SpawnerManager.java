package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.settings.Settings;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Date Created: May 05 2022
 * Time Created: 10:47 a.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerManager extends Manager<Location, Spawner> {

	@Override
	public void add(@NotNull Spawner spawner) {
		if (this.contents.containsKey(spawner.getLocation())) return;
		this.contents.put(spawner.getLocation(), spawner);
	}

	@Override
	public void remove(@NotNull Location location) {
		if (!this.contents.containsKey(location)) return;
		this.contents.remove(location);
	}

	@Override
	public Spawner find(@NotNull Location location) {
		return this.contents.getOrDefault(location, null);

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

	public void applySpawnerDefaults(@NonNull final CreatureSpawner spawner, final boolean update) {
		spawner.setDelay(Settings.DEFAULT_SPAWNER_DELAY.getInt());
		spawner.setSpawnCount(Settings.DEFAULT_SPAWNER_SPAWN_COUNT.getInt());
		spawner.setRequiredPlayerRange(Settings.DEFAULT_SPAWNER_ACTIVATION_RANGE.getInt());
		spawner.setMaxNearbyEntities(Settings.DEFAULT_SPAWNER_MAX_NEARBY_ENTITIES.getInt());

		if (update)
			spawner.update(true);
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
