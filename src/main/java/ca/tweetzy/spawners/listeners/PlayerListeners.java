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

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.user.SpawnerOverviewGUI;
import ca.tweetzy.spawners.settings.Translation;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

/**
 * Date Created: May 17 2022
 * Time Created: 1:43 p.m.
 *
 * @author Kiran Hart
 */
public final class PlayerListeners implements Listener {

	@EventHandler
	public void onSpawnerClick(final PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if (event.getHand() != EquipmentSlot.OFF_HAND) return;

		final Player player = event.getPlayer();

		if (!player.isSneaking()) return;

		final Block block = event.getClickedBlock();
		if (block == null) return;
		if (block.getType() != CompMaterial.SPAWNER.parseMaterial()) return;

		final Spawner spawner = Spawners.getSpawnerManager().find(block.getLocation());
		final CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

		if (spawner == null) return;

		if (!spawner.getOwner().equals(player.getUniqueId())) {
			Translation.SPAWNER_NOT_OWNER.send(player);
			return;
		}

		Spawners.getGuiManager().showGUI(player, new SpawnerOverviewGUI(spawner, Boolean.parseBoolean(creatureSpawner.getPersistentDataContainer().get(new NamespacedKey(Spawners.getInstance(), "SpawnersUpgradeable"), PersistentDataType.STRING))));
		event.setUseItemInHand(Event.Result.DENY);
	}
}
