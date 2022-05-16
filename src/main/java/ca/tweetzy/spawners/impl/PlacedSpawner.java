package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.model.LevelFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bukkit.Location;
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
