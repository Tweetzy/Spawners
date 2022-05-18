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
