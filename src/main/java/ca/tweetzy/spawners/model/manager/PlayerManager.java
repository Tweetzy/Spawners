package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
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

	private final Map<UUID, SpawnerUser> contents = new ConcurrentHashMap<>();

	public void add(@NonNull SpawnerUser spawnerUser) {
		if (this.contents.containsKey(spawnerUser.getUUID())) return;
		this.contents.put(spawnerUser.getUUID(), spawnerUser);
	}

	public void remove(@NonNull UUID userUUID) {
		if (!this.contents.containsKey(userUUID)) return;
		this.contents.remove(userUUID);
	}

	public SpawnerUser find(@NonNull UUID userUUID) {
		return this.contents.getOrDefault(userUUID, null);
	}

	public SpawnerUser findUser(@NonNull final Player player) {
		return this.find(player.getUniqueId());
	}

	/*
	=================== DATABASE CALLS ===================
	 */

	public void createPlayer(@NonNull final Player player, final BiConsumer<Boolean, SpawnerUser> consumer) {
		Spawners.getDataManager().insertUser(new SpawnerPlayer(player), (error, created) -> {
			if (error == null)
				this.add(created);

			if (consumer != null)
				consumer.accept(error == null, created);
		});
	}

	@Override
	public void load() {
		// clear player list
		this.contents.clear();

		Spawners.getDataManager().getUsers((error, result) -> {
			if (error == null)
				result.forEach(this::add);
		});
	}
}