package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Preset;
import lombok.NonNull;

/**
 * Date Created: May 10 2022
 * Time Created: 1:15 p.m.
 *
 * @author Kiran Hart
 */
public final class PresetManager extends Manager<String, Preset> {

	@Override
	public void add(@NonNull Preset preset) {
		if (this.contents.containsKey(preset.getId())) return;
		this.contents.put(preset.getId().toLowerCase(), preset);
	}

	@Override
	public void remove(@NonNull String key) {
		if (!this.contents.containsKey(key.toLowerCase())) return;
		this.contents.remove(key.toLowerCase());
	}

	@Override
	public Preset find(@NonNull String key) {
		return this.contents.getOrDefault(key.toLowerCase(), null);
	}

	@Override
	public void load() {
		this.contents.clear();

		Spawners.getDataManager().getSpawnerPresets((error, results) -> {
			if (error == null)
				results.forEach(this::add);
		});
	}

}
