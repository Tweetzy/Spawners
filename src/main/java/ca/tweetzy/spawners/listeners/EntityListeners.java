/*
 * Spawners
 * Copyright 2022 Kiran Hart
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package ca.tweetzy.spawners.listeners;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.persistence.PersistentDataType;

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
		event.getEntity().getPersistentDataContainer().set(key, PersistentDataType.STRING, spawner.getOwnerName() + ":" + spawner.getOwner().toString());
	}

}
