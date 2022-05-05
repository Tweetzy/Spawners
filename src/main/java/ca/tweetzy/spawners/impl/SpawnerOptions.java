package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.api.spawner.Options;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;

/**
 * Date Created: May 04 2022
 * Time Created: 11:06 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class SpawnerOptions implements Options {

	private int spawnInterval;
	private int spawnCount;
	private int maxNearbyEntities;
	private int playerActivationRange;

	@Override
	public int getSpawnInterval() {
		return this.spawnInterval;
	}

	@Override
	public int getSpawnCount() {
		return this.spawnCount;
	}

	@Override
	public int getMaxNearbyEntities() {
		return this.maxNearbyEntities;
	}

	@Override
	public int getPlayerActivationRange() {
		return this.playerActivationRange;
	}

	@Override
	public void setSpawnInterval(int spawnInterval) {
		this.spawnInterval = spawnInterval;
	}

	@Override
	public void setSpawnCount(int spawnCount) {
		this.spawnCount = spawnCount;
	}

	@Override
	public void setMaxNearbyEntities(int maxNearbyEntities) {
		this.maxNearbyEntities = maxNearbyEntities;
	}

	@Override
	public void setPlayerActivationRange(int playerActivationRange) {
		this.playerActivationRange = playerActivationRange;
	}

	@Override
	public String getJsonString() {
		JsonObject object = new JsonObject();
		object.addProperty("delay", this.spawnInterval);
		object.addProperty("spawnCount", this.spawnCount);
		object.addProperty("maxNearbyEntities", this.maxNearbyEntities);
		object.addProperty("activationRange", this.playerActivationRange);
		return object.toString();
	}

	public static Options decodeJson(String json) {
		final JsonObject object = JsonParser.parseString(json).getAsJsonObject();

		return new SpawnerOptions(
				object.get("delay").getAsInt(),
				object.get("spawnCount").getAsInt(),
				object.get("maxNearbyEntities").getAsInt(),
				object.get("activationRange").getAsInt()
		);
	}
}
