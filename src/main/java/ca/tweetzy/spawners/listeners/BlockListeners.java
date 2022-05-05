package ca.tweetzy.spawners.listeners;

import ca.tweetzy.rose.comp.NBTEditor;
import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Options;
import ca.tweetzy.spawners.impl.SpawnerOptions;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

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

		final UUID owner = UUID.fromString(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:Owner"));
		if (!Settings.ALLOW_NON_OWNER_PLACE.getBoolean() && !owner.equals(player.getUniqueId())) {
			Translation.SPAWNER_NOT_OWNER_PLACE.send(player, "owner_name", NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:OwnerName"));
			return;
		}

		final Block placedBlock = event.getBlockPlaced();

		// since spawner items can be any block, actually set the type on place to a spawner
		if (placedBlock.getType() != CompMaterial.SPAWNER.parseMaterial()) {
			assert CompMaterial.SPAWNER.parseMaterial() != null;
			placedBlock.setType(CompMaterial.SPAWNER.parseMaterial());
		}

		final EntityType entityType = EntityType.valueOf(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:EntityType").toUpperCase());
		final Options options = SpawnerOptions.decodeJson(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:Options"));
		final Level level = Spawners.getLevelManager().findLevel(Integer.parseInt(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:Level")));

		final CreatureSpawner creatureSpawner = (CreatureSpawner) placedBlock.getState();
		creatureSpawner.setSpawnedType(entityType);

		// apply options
		creatureSpawner.setDelay(level != null ? Math.max(level.getSpawnInterval(), options.getSpawnInterval()) : options.getSpawnInterval());
		creatureSpawner.setSpawnCount(level != null ? Math.max(level.getSpawnCount(), options.getSpawnCount()) : options.getSpawnCount());
		creatureSpawner.setMaxNearbyEntities(level != null ? Math.max(level.getMaxNearbyEntities(), options.getMaxNearbyEntities()) : options.getMaxNearbyEntities());
		creatureSpawner.setRequiredPlayerRange(level != null ? Math.max(level.getPlayerActivationRange(), options.getPlayerActivationRange()) : options.getPlayerActivationRange());

		// update
		creatureSpawner.update(true);
	}
}
