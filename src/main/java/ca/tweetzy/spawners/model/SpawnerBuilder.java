package ca.tweetzy.spawners.model;

import ca.tweetzy.rose.utils.ChatUtil;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Date Created: May 15 2022
 * Time Created: 2:47 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class SpawnerBuilder {

	public static final UUID NULL_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

	private UUID ownerUUID;

	private String ownerName;

	private EntityType entityType;

	private boolean levelCanBeChanged;

	private final List<Level> levels = new ArrayList<>();
	private final Map<LevelOption, Integer> levelOverrides = new HashMap<>();

	public static SpawnerBuilder of(@NonNull final Player player) {
		return SpawnerBuilder.of(player, EntityType.PIG);
	}

	public static SpawnerBuilder of(@NonNull final Player player, final EntityType entityType) {
		return new SpawnerBuilder(player.getUniqueId(), player.getName(), entityType, true);
	}

	public SpawnerBuilder setNoOwner() {
		this.ownerName = Translation.SPAWNER_NO_OWNER.getString();
		this.ownerUUID = NULL_UUID;
		return this;
	}

	public SpawnerBuilder setOwner(@NonNull final Player player) {
		this.ownerUUID = player.getUniqueId();
		this.ownerName = player.getName();
		return this;
	}

	public SpawnerBuilder setEntity(@NonNull final EntityType entity) {
		this.entityType = entity;
		return this;
	}

	public SpawnerBuilder setLevelCanBeChanged(final boolean allowLevelUpgrade) {
		this.levelCanBeChanged = allowLevelUpgrade;
		return this;
	}


	public SpawnerBuilder addLevel(@NonNull final Level level) {
		this.levels.remove(level);
		this.levels.add(level);
		return this;
	}

	public SpawnerBuilder addLevel(@NonNull final LevelOption option, final int levelNumber) {
		final Level foundLevel = Spawners.getLevelManager().find(option, levelNumber);
		if (foundLevel != null) {
			this.levels.remove(foundLevel);
			this.levels.add(foundLevel);
		}

		return this;
	}

	public SpawnerBuilder addLevelOverride(@NonNull final LevelOption option, final int value) {
		this.levelOverrides.put(option, value);
		return this;
	}

	public ItemStack make() {
		final QuickItem itemStack = QuickItem.of(Settings.SPAWNER_ITEM.getMaterial());

		final Level delay = this.levels.stream().filter(level -> level.getLevelOption() == LevelOption.SPAWN_INTERVAL).findFirst().orElse(null);
		final Level spawnCount = this.levels.stream().filter(level -> level.getLevelOption() == LevelOption.SPAWN_COUNT).findFirst().orElse(null);
		final Level maxNearby = this.levels.stream().filter(level -> level.getLevelOption() == LevelOption.MAX_NEARBY_ENTITIES).findFirst().orElse(null);
		final Level activationRange = this.levels.stream().filter(level -> level.getLevelOption() == LevelOption.ACTIVATION_RANGE).findFirst().orElse(null);

		final int delayOverride = this.levelOverrides.getOrDefault(LevelOption.SPAWN_INTERVAL, 0);
		final int spawnCountOverride = this.levelOverrides.getOrDefault(LevelOption.SPAWN_COUNT, 0);
		final int maxNearbyOverride = this.levelOverrides.getOrDefault(LevelOption.MAX_NEARBY_ENTITIES, 0);
		final int activationRangeOverride = this.levelOverrides.getOrDefault(LevelOption.ACTIVATION_RANGE, 0);

		itemStack.name(Translation.SPAWNER_NAME.getString("entity_type", ChatUtil.capitalizeFully(this.entityType)));
		itemStack.lore(Translation.SPAWNER_LORE.getList(
				"spawner_owner_name", this.ownerName,
				"spawner_spawn_delay_level", Math.max(delay == null ? Settings.DEFAULT_SPAWNER_DELAY.getInt() : delay.getValue(), delayOverride),
				"spawner_spawn_count_level", Math.max(spawnCount == null ? Settings.DEFAULT_SPAWNER_SPAWN_COUNT.getInt() : spawnCount.getValue(), spawnCountOverride),
				"spawner_max_nearby_entities_level", Math.max(maxNearby == null ? Settings.DEFAULT_SPAWNER_MAX_NEARBY_ENTITIES.getInt() : maxNearby.getValue(), maxNearbyOverride),
				"spawner_player_activation_range_level", Math.max(activationRange == null ? Settings.DEFAULT_SPAWNER_ACTIVATION_RANGE.getInt() : activationRange.getValue(), activationRangeOverride)
		));

		final JsonObject spawnerData = new JsonObject();
		spawnerData.addProperty("ownerName", this.ownerName);
		spawnerData.addProperty("ownerUUID", this.ownerUUID.toString());
		spawnerData.addProperty("entity", this.entityType.name());
		spawnerData.addProperty("upgradeable", this.levelCanBeChanged);

		final JsonArray levelData = new JsonArray();
		for (Level level : this.levels) {
			final JsonObject object = new JsonObject();
			final LevelOption option = level.getLevelOption();

			object.addProperty("type", level.getLevelOption().name());
			object.addProperty("levelNumber", level.getLevelNumber());
			object.addProperty("value", level.getValue());
			object.addProperty("valueOverride", option == LevelOption.SPAWN_INTERVAL ? delayOverride : option == LevelOption.SPAWN_COUNT ? spawnCountOverride : option == LevelOption.MAX_NEARBY_ENTITIES ? maxNearbyOverride : activationRangeOverride);

			levelData.add(object);
		}

		spawnerData.add("levels", levelData);

		itemStack.tag("SpawnersData", spawnerData.toString());
		itemStack.hideTags(true);
		return itemStack.make();
	}
}
