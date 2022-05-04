package ca.tweetzy.spawners.model;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.interfaces.SpawnerUser;
import ca.tweetzy.spawners.impl.SpawnerPlayer;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

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

	/*
	=================== DATABASE CALLS ===================
	 */

	public void createPlayer(@NonNull final Player player, final BiConsumer<Boolean, SpawnerUser> consumer) {
		Spawners.getDataManager().insertUser(new SpawnerPlayer(player), (error, created) -> {
			if (error == null)
				this.addPlayer(created);

			if (consumer != null)
				consumer.accept(error == null, created);
		});
	}

	@Override
	public void load() {
		// clear player list
		this.players.clear();

		Spawners.getDataManager().getUsers((error, result) -> {
			if (error == null)
				result.forEach(this::addPlayer);
		});
	}
}