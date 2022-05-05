package ca.tweetzy.spawners.model;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import lombok.NonNull;
import org.bukkit.Location;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date Created: May 05 2022
 * Time Created: 10:47 a.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerManager implements Manager {

	private final Map<Location, Spawner> spawners = new ConcurrentHashMap<>();

	public void addSpawner(@NonNull final Spawner spawner) {
		if (this.spawners.containsKey(spawner.getLocation())) return;
		this.spawners.put(spawner.getLocation(), spawner);
	}

	public void removeSpawner(@NonNull final Location location) {
		if (!this.spawners.containsKey(location)) return;
		this.spawners.remove(location);
	}

	public Spawner findSpawner(@NonNull final Location location) {
		return this.spawners.getOrDefault(location, null);
	}

	public Spawner findSpawner(@NonNull final Spawner spawner) {
		return findSpawner(spawner.getLocation());
	}

	@Override
	public void load() {
		this.spawners.clear();

		Spawners.getDataManager().getSpawners((error, result) -> {
			if (error == null)
				result.forEach(this::addSpawner);
		});
	}
}
