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
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.model.LevelFactory;
import ca.tweetzy.spawners.settings.Translation;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * Date Created: May 05 2022
 * Time Created: 10:29 a.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class PlacedSpawner implements Spawner {

	private UUID uuid;
	private UUID owner;
	private String ownerName;
	private EntityType entityType;
	private Map<LevelOption, Level> levels;
	private Location location;

	public PlacedSpawner(@NonNull final Player player, @NonNull final EntityType entityType, @NonNull final Location location) {
		this(UUID.randomUUID(), player.getUniqueId(), player.getName(), entityType, new HashMap<>(), location);
	}

	@Override
	public UUID getID() {
		return this.uuid;
	}

	@Override
	public UUID getOwner() {
		return this.owner;
	}

	@Override
	public String getOwnerName() {
		return this.ownerName;
	}

	@Override
	public EntityType getEntityType() {
		return this.entityType;
	}

	@Override
	public Map<LevelOption, Level> getLevels() {
		return this.levels;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	@Override
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	@Override
	public void setLevels(Map<LevelOption, Level> levels) {
		this.levels = levels;
	}

	@Override
	public Level getNextLevel(LevelOption levelOption) {
		final Level level = this.levels.get(levelOption);
		return Spawners.getLevelManager().find(levelOption, level.getLevelNumber() + 1);
	}

	@Override
	public boolean isLevelMax(Level level) {
		return level.getLevelNumber() == Spawners.getLevelManager().getHighestLevel(level.getLevelOption());
	}

	@Override
	public boolean isMaxedOut() {
		for (Level level : this.levels.values()) {
			if (!isLevelMax(level))
				return false;
		}
		return true;
	}

	@Override
	public void tryUpgrade(Player player, LevelOption levelOption) {
		final Level level = this.levels.get(levelOption);
		final Level nextLevel = this.getNextLevel(levelOption);

		if (nextLevel == null) {
			switch (levelOption) {
				case SPAWN_INTERVAL -> Translation.SPAWNER_MAX_DELAY.send(player);
				case SPAWN_COUNT -> Translation.SPAWNER_MAX_SPAWN_COUNT.send(player);
				case MAX_NEARBY_ENTITIES -> Translation.SPAWNER_MAX_NEARBY_MOBS.send(player);
				case ACTIVATION_RANGE -> Translation.SPAWNER_MAX_ACTIVATION_RANGE.send(player);
				default -> {
				}
			}
			return;
		}

		// cost
		if (!Spawners.getEconomy().has(player, nextLevel.getCost())) {
			Translation.NOT_ENOUGH_MONEY.send(player);
			return;
		}

		// withdraw and upgrade
		Spawners.getEconomy().withdrawPlayer(player, nextLevel.getCost());
		Translation.MONEY_REMOVE.send(player, "amount", String.format("%,.2f", nextLevel.getCost()));

		this.levels.put(nextLevel.getLevelOption(), nextLevel);

		// apply changes to block
		final Block block = this.location.getBlock();
		final CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

		Spawners.getSpawnerManager().applySpawnerLevel(creatureSpawner, nextLevel);
		switch (levelOption) {
			case SPAWN_INTERVAL -> Translation.SPAWNER_UPGRADED_DELAY.send(player, "previous_level", level.getLevelNumber(), "current_level", nextLevel.getLevelNumber());
			case SPAWN_COUNT -> Translation.SPAWNER_UPGRADED_SPAWN_COUNT.send(player, "previous_level", level.getLevelNumber(), "current_level", nextLevel.getLevelNumber());
			case MAX_NEARBY_ENTITIES -> Translation.SPAWNER_UPGRADED_NEARBY_MOBS.send(player, "previous_level", level.getLevelNumber(), "current_level", nextLevel.getLevelNumber());
			case ACTIVATION_RANGE -> Translation.SPAWNER_UPGRADED_ACTIVATION_RANGE.send(player, "previous_level", level.getLevelNumber(), "current_level", nextLevel.getLevelNumber());
			default -> {
			}
		}

		this.sync();
	}

	@Override
	public void merge(Spawner spawner, Consumer<Spawner> leftover) {
		for (Map.Entry<LevelOption, Level> levelOptionLevelEntry : this.levels.entrySet()) {
			// the current level of the spawner
			final int currentLevel = levelOptionLevelEntry.getValue().getLevelNumber();
			// the highest possible level this option can be
			final int maxAvailableLevel = Spawners.getLevelManager().getHighestLevel(levelOptionLevelEntry.getKey());
			// the level of the spawner option being merged
			final int mergeSpawnerLevel = spawner.getLevels().get(levelOptionLevelEntry.getKey()).getLevelNumber();

			// the spawner being used to merge ain't got enough levels
			// if the current level is already the max level, we can skip as well
			if (currentLevel == maxAvailableLevel || mergeSpawnerLevel < 2) {
				continue;
			}

			int differenceNeededToMaxOut = maxAvailableLevel - currentLevel;
			// the max amount of levels that can be taken from that merging spawner level option
			final int availableLevelsToBeUsed = mergeSpawnerLevel - 1;

			// if the amount it takes to max out is bigger than the total available levels
			// set the difference to the max available levels...
			if (differenceNeededToMaxOut > availableLevelsToBeUsed)
				differenceNeededToMaxOut = availableLevelsToBeUsed;

			// the new level for this upgrade option
			final int newLevelAmount = currentLevel + differenceNeededToMaxOut;

			// the remaining level for the spawner being merged into current one
			final int remainingLevelFromMergeAmount = mergeSpawnerLevel - differenceNeededToMaxOut;

			// get the new levels from the level manager
			final Level newLevel = Spawners.getLevelManager().find(levelOptionLevelEntry.getKey(), newLevelAmount);
			final Level remainingLevel = Spawners.getLevelManager().find(levelOptionLevelEntry.getKey(), remainingLevelFromMergeAmount);

			this.levels.put(levelOptionLevelEntry.getKey(), newLevel);
			spawner.getLevels().put(remainingLevel.getLevelOption(), remainingLevel);

			leftover.accept(spawner);
		}

		final Block block = this.location.getBlock();
		final CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

		for (Level level : this.levels.values()) {
			Spawners.getSpawnerManager().applySpawnerLevel(creatureSpawner, level);
		}

		sync();
	}

	@Override
	public void reApplyLevels() {
		this.levels.forEach((option, level) -> {
			final Block block = this.location.getBlock();
			final CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

			Spawners.getSpawnerManager().applySpawnerLevel(creatureSpawner, level);
		});

		sync();
	}

	@Override
	public void sync() {
		Spawners.getDataManager().updateSpawner(this, null);
	}

	@Override
	public String getJsonString() {
		final JsonArray jsonArray = new JsonArray();

		this.levels.forEach((option, level) -> {
			final JsonObject object = new JsonObject();

			object.addProperty("option", level.getLevelOption().name());
			object.addProperty("level", level.getLevelNumber());
			object.addProperty("value", level.getValue());
			object.addProperty("cost", level.getCost());

			jsonArray.add(object);
		});

		return jsonArray.toString();
	}

	public static Map<LevelOption, Level> decodeLevelsJson(String json) {
		final JsonArray array = JsonParser.parseString(json).getAsJsonArray();
		final Map<LevelOption, Level> parsedLevels = new HashMap<>();

		array.forEach(jsonElement -> {
			final JsonObject object = jsonElement.getAsJsonObject();
			final LevelOption levelOption = LevelOption.valueOf(object.get("option").getAsString());

			parsedLevels.put(levelOption, LevelFactory.build(
					levelOption,
					object.get("level").getAsInt(),
					object.get("value").getAsInt(),
					object.get("cost").getAsDouble()
			));
		});

		return parsedLevels;
	}
}
