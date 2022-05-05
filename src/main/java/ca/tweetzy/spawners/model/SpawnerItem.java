package ca.tweetzy.spawners.model;

import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.api.spawner.Options;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Date Created: May 04 2022
 * Time Created: 10:47 p.m.
 *
 * @author Kiran Hart
 */
@UtilityClass
public final class SpawnerItem {

	public ItemStack make(@NonNull final Player owner, @NonNull final EntityType entityType, final int levelOverride, final Options options) {

		return QuickItem
				.of(Settings.SPAWNER_ITEM.getMaterial())
				.name(Translation.SPAWNER_NAME.getString("entity_type", StringUtils.capitalize(entityType.name().toLowerCase().replace("_", " "))))
				.lore(Translation.SPAWNER_LORE.getList(
						"spawner_owner_name", owner.getName(),
						"spawner_spawn_delay", options.getSpawnInterval(),
						"spawner_spawn_count", options.getSpawnCount(),
						"spawner_max_nearby_entities", options.getMaxNearbyEntities(),
						"spawner_player_activation_range", options.getPlayerActivationRange()
				))
				.tag("Spawners:Spawner:Owner", owner.getUniqueId().toString())
				.tag("Spawners:Spawner:Options", options.getJsonString())
				.tag("Spawners:Spawner:Level", String.valueOf(levelOverride))
				.make();
	}
}