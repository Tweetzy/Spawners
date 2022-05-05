package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.api.spawner.Options;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.model.Serialize;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
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
	private EntityType entityType;
	private int level;
	private Options options;
	private Location location;

	@Override
	public UUID getID() {
		return this.uuid;
	}

	@Override
	public UUID getOwner() {
		return this.owner;
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

	}

	@Override
	public String getJsonString() {
		final JsonObject object = new JsonObject();

		object.addProperty("uuid", this.uuid.toString());
		object.addProperty("owner", this.owner.toString());
		object.addProperty("entityType", this.entityType.name());
		object.addProperty("level", this.level);
		object.add("options", JsonParser.parseString(this.options.getJsonString()));
		object.addProperty("location", Serialize.serializeLocation(this.location));

		return object.toString();
	}
}
