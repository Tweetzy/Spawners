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

import java.util.ArrayList;
import java.util.List;

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
	private List<Level> levels;

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public EntityType getEntityType() {
		return this.entityType;
	}

	@Override
	public List<Level> getLevels() {
		return this.levels;
	}


	@Override
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	@Override
	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	@Override
	public String getJsonString() {
		final JsonArray jsonArray = new JsonArray();

		this.levels.forEach(level -> {
			final JsonObject object = new JsonObject();

			object.addProperty("option", level.getLevelOption().name());
			object.addProperty("level", level.getLevelNumber());
			object.addProperty("value", level.getValue());
			object.addProperty("cost", level.getCost());

			jsonArray.add(object);
		});


		return jsonArray.toString();
	}

	public static List<Level> decodeLevelsJson(String json) {
		final JsonArray array = JsonParser.parseString(json).getAsJsonArray();
		final List<Level> parsedLevels = new ArrayList<>();

		array.forEach(jsonElement -> {
			final JsonObject object = jsonElement.getAsJsonObject();
			parsedLevels.add(LevelFactory.build(
					LevelOption.valueOf(object.get("option").getAsString()),
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
