package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Options;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.model.Serialize;
import ca.tweetzy.spawners.model.SpawnerDefault;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

/**
 * Date Created: May 05 2022
 * Time Created: 10:29 a.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class PlacedSpawner implements Spawner {

	private final UUID uuid;
	private UUID owner;
	private String ownerName;
	private EntityType entityType;
	private int level;
	private Options options;
	private Location location;

	public PlacedSpawner() {
		this(UUID.randomUUID(), SpawnerDefault.NULL_UUID, Translation.SPAWNER_NO_OWNER.getString(), EntityType.valueOf(Settings.DEFAULT_SPAWNER_ENTITY.getString()), -1, new SpawnerOptions(), new Location(Bukkit.getWorlds().get(0), 0, 0, 0));
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
	public int getLevel() {
		return this.level;
	}

	@Override
	public Options getOptions() {
		return this.options;
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
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void setOptions(Options options) {
		this.options = options;
	}

	@Override
	public void sync() {
		Spawners.getDataManager().updateSpawner(this, null);
	}

	@Override
	public String getJsonString() {
		final JsonObject object = new JsonObject();

		object.addProperty("uuid", this.uuid.toString());
		object.addProperty("owner", this.owner.toString());
		object.addProperty("ownerName", this.ownerName);
		object.addProperty("entityType", this.entityType.name());
		object.addProperty("level", this.level);
		object.add("options", JsonParser.parseString(this.options.getJsonString()));
		object.addProperty("location", Serialize.serializeLocation(this.location));

		return object.toString();
	}

	public static Spawner decodeJson(String json) {
		final JsonObject object = JsonParser.parseString(json).getAsJsonObject();

		return new PlacedSpawner(
				UUID.fromString(object.get("uuid").getAsString()),
				UUID.fromString(object.get("owner").getAsString()),
				object.get("ownerName").getAsString(),
				EntityType.valueOf(object.get("entityType").getAsString().toUpperCase()),
				object.get("level").getAsInt(),
				SpawnerOptions.decodeJson(object.get("options").getAsString()),
				Serialize.deserializeLocation(object.get("location").getAsString())
		);
	}
}
