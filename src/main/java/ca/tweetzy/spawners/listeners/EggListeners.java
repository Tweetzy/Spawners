package ca.tweetzy.spawners.listeners;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import org.apache.commons.lang.StringUtils;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;

/**
 * Date Created: May 10 2022
 * Time Created: 10:40 p.m.
 *
 * @author Kiran Hart
 */
public final class EggListeners implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onSpawnerClickWithEgg(final PlayerInteractEvent event) {
		if (!Settings.ALLOW_SPAWNER_CHANGE_WITH_EGG.getBoolean()) return;

		if (event.getClickedBlock() == null || event.getClickedBlock().getType() != CompMaterial.SPAWNER.parseMaterial()) return;
		if (event.getItem() == null) return;
		if (!event.getItem().getType().name().endsWith("_SPAWN_EGG")) return;

		final Player player = event.getPlayer();

		final SpawnerUser spawnerUser = Spawners.getPlayerManager().findUser(player);
		final SpawnEggMeta spawnEggMeta = (SpawnEggMeta) event.getItem().getItemMeta();
		final EntityType entityType = spawnEggMeta.getSpawnedType();

		final ItemStack hand = event.getItem();
		final Block block = event.getClickedBlock();
		final CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();

		// check spawner owner
		final Spawner spawner = Spawners.getSpawnerManager().find(block.getLocation());

		if (spawner != null && !spawner.getOwner().equals(player.getUniqueId())) {
			Translation.SPAWNER_NOT_OWNER_CHANGE_WITH_EGG.send(player, "owner_name", spawner.getOwnerName());
			return;
		}

		if (!spawnerUser.isAllowedToChangeWithEgg(player, entityType)) {
			Translation.SPAWNER_CANNOT_CHANGE_WITH_EGG.send(player, "entity_type", StringUtils.capitalize(entityType.name().toLowerCase().replace("_", " ")));
			return;
		}

		creatureSpawner.setSpawnedType(entityType);
		creatureSpawner.update(true);

		if (Settings.REMOVE_EGG_ON_SPAWNER_CHANGE.getBoolean()) {
			if (hand.getAmount() >= 2) {
				hand.setAmount(hand.getAmount() - 1);
				player.getInventory().setItemInMainHand(hand);
				return;
			}

			player.getInventory().setItemInMainHand(CompMaterial.AIR.parseItem());
		}
	}
}
