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
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.model.LevelFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

/**
 * Date Created: May 10 2022
 * Time Created: 1:04 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class SpawnerPreset implements Preset {

	private final String id;
	private EntityType entityType;
	private Map<LevelOption, Level> levels;

	@Override
	public String getId() {
		return this.id;
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
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	@Override
	public void setLevels(Map<LevelOption, Level> levels) {
		this.levels = levels;
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

	@Override
	public void sync() {
		Spawners.getDataManager().updateSpawnerPreset(this, null);
	}
}
