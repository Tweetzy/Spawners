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
package ca.tweetzy.spawners.api;

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
 * Date Created: May 04 2022
 * Time Created: 10:44 a.m.
 *
 * @author Kiran Hart
 */
public interface SpawnersAPI {

	void addLevel(@NonNull final Level level);

	void removeLevel(@NonNull final Level level);

	void createLevel(@NonNull final Level level, final BiConsumer<Boolean, Level> consumer);

	void deleteLevel(@NonNull final Level level, final Consumer<Boolean> consumer);

	Level findLevel(@NonNull final LevelOption levelOption, final int levelNumber);

	List<Level> getLevels();

	List<Level> getLevels(@NonNull final LevelOption levelOption);

	int getHighestLevel(@NonNull final LevelOption levelOption);

	void addSpawner(@NotNull Spawner spawner);

	void removeSpawner(@NotNull Location location);

	void createSpawner(@NonNull final Spawner spawner, final BiConsumer<Boolean, Spawner> consumer);

	void deleteSpawner(@NonNull final Spawner spawner, final Consumer<Boolean> success);

	Spawner findSpawner(@NotNull Location location);

	List<Spawner> getSpawners();

	int getSpawnerCountWithinChunk(@NonNull final Chunk chunk);

	boolean canPlaceSpawnerInChunk(@NonNull final Chunk chunk);

	void changeSpawnerEntity(@NonNull final CreatureSpawner spawner, @NonNull final EntityType entityType);

	void applySpawnerDefaults(@NonNull final CreatureSpawner spawner, final boolean update);

	void applySpawnerLevel(@NonNull final CreatureSpawner spawner, @NonNull final Level level);

	void addPreset(@NonNull Preset preset);

	void removePreset(@NonNull String key);

	void createPreset(@NonNull final Preset preset, final BiConsumer<Boolean, Preset> consumer);

	void deletePreset(@NonNull final Preset preset, final Consumer<Boolean> success);

	Preset findPreset(@NonNull String key);

	List<Preset> getPresets();

	void addUser(@NonNull SpawnerUser spawnerUser);

	void removeUser(@NonNull UUID userUUID);

	void createUser(@NonNull final Player player, final BiConsumer<Boolean, SpawnerUser> consumer);

	SpawnerUser findUser(@NonNull UUID userUUID);

	SpawnerUser findUser(@NonNull final Player player);

	List<SpawnerUser> getUsers();

}
