package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Preset;
import lombok.NonNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

	public void createPreset(@NonNull final Preset preset, final BiConsumer<Boolean, Preset> consumer) {
		Spawners.getDataManager().insertSpawnerPreset(preset, (error, created) -> {
			if (error == null)
				this.add(created);

			if (consumer != null)
				consumer.accept(error == null, created);
		});
	}

	public void deletePreset(@NonNull final Preset preset, final Consumer<Boolean> success) {
		Spawners.getDataManager().deleteSpawnerPreset(preset.getId(), (error, deleted) -> {
			if (error == null && deleted)
				this.remove(preset.getId());

			if (success != null)
				success.accept(error == null && deleted);
		});
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
