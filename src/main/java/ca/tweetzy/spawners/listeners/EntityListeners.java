package ca.tweetzy.spawners.listeners;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;

/**
 * Date Created: May 11 2022
 * Time Created: 1:37 p.m.
 *
 * @author Kiran Hart
 */
public final class EntityListeners implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onSpawnerSpawn(SpawnerSpawnEvent event) {
		final Spawner spawner = Spawners.getSpawnerManager().find(event.getSpawner().getLocation());
		if (spawner == null) return;

		final NamespacedKey key = new NamespacedKey(Spawners.getInstance(), "SpawnersEntityOwner");
		event.getEntity().getPersistentDataContainer().set(key, DataType.STRING, spawner.getOwnerName() + ":" + spawner.getOwner().toString());
	}

	@EventHandler()
	public void onEntityAttack(final EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof final Player attacker)) return;

		final NamespacedKey key = new NamespacedKey(Spawners.getInstance(), "SpawnersEntityOwner");

		if (!event.getEntity().getPersistentDataContainer().has(key, DataType.STRING)) {
			return;
		}

		String[] ownerParts = event.getEntity().getPersistentDataContainer().get(key, DataType.STRING).split(":");


	}
}
