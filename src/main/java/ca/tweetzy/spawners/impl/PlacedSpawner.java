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
