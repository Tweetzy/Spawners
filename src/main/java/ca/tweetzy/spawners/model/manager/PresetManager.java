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
import ca.tweetzy.spawners.api.manager.KeyValueManager;
import ca.tweetzy.spawners.api.spawner.Preset;
import lombok.NonNull;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Date Created: May 10 2022
 * Time Created: 1:15 p.m.
 *
 * @author Kiran Hart
 */
public final class PresetManager extends KeyValueManager<String, Preset> {

	public PresetManager() {
		super("Preset");
	}

	public void add(@NonNull Preset preset) {
		if (this.managerContent.containsKey(preset.getId())) return;
		this.managerContent.put(preset.getId().toLowerCase(), preset);
	}

	public void remove(@NonNull String key) {
		if (!this.managerContent.containsKey(key.toLowerCase())) return;
		this.managerContent.remove(key.toLowerCase());
	}

	public Preset find(@NonNull String key) {
		return this.managerContent.getOrDefault(key.toLowerCase(), null);
	}

	public List<Preset> getContents() {
		return List.copyOf(this.managerContent.values());
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
		clear();

		Spawners.getDataManager().getSpawnerPresets((error, results) -> {
			if (error == null)
				results.forEach(this::add);
		});
	}
}
