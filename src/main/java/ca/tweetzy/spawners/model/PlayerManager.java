package ca.tweetzy.spawners.model;

import ca.tweetzy.spawners.api.interfaces.SpawnerUser;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Date Created: May 04 2022
 * Time Created: 11:13 a.m.
 *
 * @author Kiran Hart
 */
public final class PlayerManager implements Manager {

	private final Map<UUID, SpawnerUser> players = new ConcurrentHashMap<>();

	public void addPlayer(@NonNull final SpawnerUser spawnerUser) {
		if (this.players.containsKey(spawnerUser.getUUID())) return;
		this.players.put(spawnerUser.getUUID(), spawnerUser);
	}

	public void removePlayer(@NonNull final UUID userUUID) {
		if (!this.players.containsKey(userUUID)) return;
		this.players.remove(userUUID);
	}

	public SpawnerUser findUser(@NonNull final UUID userUUID) {
		return this.players.getOrDefault(userUUID, null);
	}

	public SpawnerUser findUser(@NonNull final Player player) {
		return findUser(player.getUniqueId());
	}

	@Override
	public void load() {
		// clear player list
		this.players.clear();
	}
}