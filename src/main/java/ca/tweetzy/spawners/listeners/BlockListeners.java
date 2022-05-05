package ca.tweetzy.spawners.listeners;

import ca.tweetzy.rose.comp.NBTEditor;
import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.spawners.api.spawner.Options;
import ca.tweetzy.spawners.impl.SpawnerOptions;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Date Created: May 04 2022
 * Time Created: 11:00 p.m.
 *
 * @author Kiran Hart
 */
public final class BlockListeners implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onSpawnerPlace(final BlockPlaceEvent event) {
		final Player player = event.getPlayer();
		if (!NBTEditor.contains(event.getItemInHand(), "Spawners:Spawner")) return;

		final Block placedBlock = event.getBlockPlaced();

		if (placedBlock.getType() != CompMaterial.SPAWNER.parseMaterial()) {
			assert CompMaterial.SPAWNER.parseMaterial() != null;
			placedBlock.setType(CompMaterial.SPAWNER.parseMaterial());
		}

		final EntityType entityType = EntityType.valueOf(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:EntityType").toUpperCase());
		final Options options = SpawnerOptions.decodeJson(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:Options"));

		final CreatureSpawner creatureSpawner = (CreatureSpawner) placedBlock.getState();
		creatureSpawner.setSpawnedType(entityType);
		creatureSpawner.update(true);
	}
}
