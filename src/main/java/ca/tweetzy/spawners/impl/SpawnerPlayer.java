package ca.tweetzy.spawners.impl;

import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Date Created: May 04 2022
 * Time Created: 10:59 a.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public final class SpawnerPlayer implements SpawnerUser {

	private final UUID uuid;
	private final String name;
	private final List<UUID> placedSpawners;

	public SpawnerPlayer(@NonNull final UUID uuid, @NonNull final String name) {
		this(uuid, name, new ArrayList<>());
	}

	public SpawnerPlayer(@NonNull final Player player) {
		this(player.getUniqueId(), player.getName());
	}

	@Override
	public UUID getUUID() {
		return this.uuid;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<UUID> getPlacedSpawners() {
		return this.placedSpawners;
	}

	@Override
	public boolean isAllowedToMineEntity(Player player, EntityType entityType) {
		return player.hasPermission("spawners.mine." + entityType.name().toLowerCase().replace("_", "")) || player.isOp();
	}

	@Override
	public boolean isAllowedToPlaceEntity(Player player, EntityType entityType) {
		return player.hasPermission("spawners.place." + entityType.name().toLowerCase().replace("_", "")) || player.isOp();
	}

	@Override
	public boolean isAllowedToChangeWithEgg(Player player, EntityType entityType) {
		return player.hasPermission("spawners.eggchange." + entityType.name().toLowerCase().replace("_", "")) || player.isOp();
	}
}
