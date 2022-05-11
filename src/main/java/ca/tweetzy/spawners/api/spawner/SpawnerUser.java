package ca.tweetzy.spawners.api.spawner;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Date Created: May 04 2022
 * Time Created: 10:56 a.m.
 *
 * @author Kiran Hart
 */
public interface SpawnerUser {

	UUID getUUID();

	String getName();

	List<UUID> getPlacedSpawners();

	boolean isAllowedToPlaceEntity(Player player, EntityType entityType);

	boolean isAllowedToMineEntity(Player player, EntityType entityType);

	boolean isAllowedToChangeWithEgg(Player player, EntityType entityType);
}
