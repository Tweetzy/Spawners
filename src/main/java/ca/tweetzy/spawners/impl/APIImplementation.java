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
package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.SpawnersAPI;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import lombok.NonNull;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Date Created: May 10 2022
 * Time Created: 9:56 p.m.
 *
 * @author Kiran Hart
 */
public final class APIImplementation implements SpawnersAPI {

	@Override
	public void addLevel(@NonNull Level level) {
		Spawners.getLevelManager().add(level);
	}

	@Override
	public void removeLevel(@NonNull Level level) {
		Spawners.getLevelManager().remove(level);
	}

	@Override
	public void createLevel(@NonNull Level level, BiConsumer<Boolean, Level> consumer) {
		Spawners.getLevelManager().createLevel(level, consumer);
	}

	@Override
	public void deleteLevel(@NonNull Level level, Consumer<Boolean> consumer) {
		Spawners.getLevelManager().deleteLevel(level, consumer);
	}

	@Override
	public Level findLevel(@NonNull LevelOption levelOption, int levelNumber) {
		return Spawners.getLevelManager().find(levelOption, levelNumber);
	}

	@Override
	public List<Level> getLevels() {
		return Spawners.getLevelManager().getContents();
	}

	@Override
	public List<Level> getLevels(@NonNull LevelOption levelOption) {
		return Spawners.getLevelManager().getLevels(levelOption);
	}

	@Override
	public int getHighestLevel(@NonNull LevelOption levelOption) {
		return Spawners.getLevelManager().getHighestLevel(levelOption);
	}

	@Override
	public void addSpawner(@NotNull Spawner spawner) {
		Spawners.getSpawnerManager().add(spawner);
	}

	@Override
	public void removeSpawner(@NotNull Location location) {
		Spawners.getSpawnerManager().remove(location);
	}

	@Override
	public void createSpawner(@NonNull Spawner spawner, BiConsumer<Boolean, Spawner> consumer) {
		Spawners.getSpawnerManager().createSpawner(spawner, consumer);
	}

	@Override
	public void deleteSpawner(@NonNull Spawner spawner, Consumer<Boolean> success) {
		Spawners.getSpawnerManager().deleteSpawner(spawner, success);
	}

	@Override
	public Spawner findSpawner(@NotNull Location location) {
		return Spawners.getSpawnerManager().find(location);
	}

	@Override
	public List<Spawner> getSpawners() {
		return Spawners.getSpawnerManager().getContents();
	}

	@Override
	public int getSpawnerCountWithinChunk(@NonNull Chunk chunk) {
		return Spawners.getAPI().getSpawnerCountWithinChunk(chunk);
	}

	@Override
	public boolean canPlaceSpawnerInChunk(@NonNull Chunk chunk) {
		return Spawners.getAPI().canPlaceSpawnerInChunk(chunk);
	}

	@Override
	public void changeSpawnerEntity(@NonNull CreatureSpawner spawner, @NonNull EntityType entityType) {
		Spawners.getSpawnerManager().changeSpawnerEntity(spawner, entityType);
	}

	@Override
	public void applySpawnerDefaults(@NonNull CreatureSpawner spawner, boolean update) {
		Spawners.getSpawnerManager().applySpawnerDefaults(spawner, update);
	}

	@Override
	public void applySpawnerLevel(@NonNull CreatureSpawner spawner, @NonNull Level level) {
		Spawners.getSpawnerManager().applySpawnerLevel(spawner, level);
	}

	@Override
	public void addPreset(@NonNull Preset preset) {
		Spawners.getPresetManager().add(preset);
	}

	@Override
	public void removePreset(@NonNull String key) {
		Spawners.getPresetManager().remove(key);
	}

	@Override
	public void createPreset(@NonNull Preset preset, BiConsumer<Boolean, Preset> consumer) {
		Spawners.getPresetManager().createPreset(preset, consumer);
	}

	@Override
	public void deletePreset(@NonNull Preset preset, Consumer<Boolean> success) {
		Spawners.getPresetManager().deletePreset(preset, success);
	}

	@Override
	public Preset findPreset(@NonNull String key) {
		return Spawners.getPresetManager().find(key);
	}

	@Override
	public List<Preset> getPresets() {
		return Spawners.getPresetManager().getContents();
	}

	@Override
	public void addUser(@NonNull SpawnerUser spawnerUser) {
		Spawners.getPlayerManager().add(spawnerUser);
	}

	@Override
	public void removeUser(@NonNull UUID userUUID) {
		Spawners.getPlayerManager().remove(userUUID);
	}

	@Override
	public void createUser(@NonNull Player player, BiConsumer<Boolean, SpawnerUser> consumer) {
		Spawners.getPlayerManager().createPlayer(player, consumer);
	}

	@Override
	public SpawnerUser findUser(@NonNull UUID userUUID) {
		return Spawners.getPlayerManager().find(userUUID);
	}

	@Override
	public SpawnerUser findUser(@NonNull Player player) {
		return Spawners.getPlayerManager().findUser(player);
	}

	@Override
	public List<SpawnerUser> getUsers() {
		return Spawners.getPlayerManager().getContents();
	}
}
