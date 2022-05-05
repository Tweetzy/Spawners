package ca.tweetzy.spawners.listeners;

import ca.tweetzy.rose.comp.NBTEditor;
import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.utils.Common;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Options;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import ca.tweetzy.spawners.impl.PlacedSpawner;
import ca.tweetzy.spawners.impl.SpawnerOptions;
import ca.tweetzy.spawners.model.SpawnerItem;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import com.jeff_media.morepersistentdatatypes.DataType;
import org.apache.commons.lang.StringUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

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
		final String ownerName = NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:OwnerName");

		if (!Settings.ALLOW_NON_OWNER_PLACE.getBoolean() && !owner.equals(player.getUniqueId())) {
			Translation.SPAWNER_NOT_OWNER_PLACE.send(player, "owner_name", ownerName);
			event.setCancelled(true);
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

		// insert spawner here and check place event
		final Spawner spawner = new PlacedSpawner(UUID.randomUUID(), owner, entityType, level != null ? level.getLevel() : -1, options, placedBlock.getLocation());
		Spawners.getSpawnerManager().createSpawner(spawner, null);

		final CreatureSpawner creatureSpawner = (CreatureSpawner) placedBlock.getState();
		creatureSpawner.setSpawnedType(entityType);

		// apply options
		creatureSpawner.setDelay(level != null ? Math.max(level.getSpawnInterval(), options.getSpawnInterval()) : options.getSpawnInterval());
		creatureSpawner.setSpawnCount(level != null ? Math.max(level.getSpawnCount(), options.getSpawnCount()) : options.getSpawnCount());
		creatureSpawner.setMaxNearbyEntities(level != null ? Math.max(level.getMaxNearbyEntities(), options.getMaxNearbyEntities()) : options.getMaxNearbyEntities());
		creatureSpawner.setRequiredPlayerRange(level != null ? Math.max(level.getPlayerActivationRange(), options.getPlayerActivationRange()) : options.getPlayerActivationRange());

		// apply persistent container stuff
		final NamespacedKey namespacedKey = new NamespacedKey(Spawners.getInstance(), "SpawnersOwner");
		creatureSpawner.getPersistentDataContainer().set(namespacedKey, DataType.UUID, owner);
		creatureSpawner.getPersistentDataContainer().set(namespacedKey, DataType.STRING, ownerName);

		// update
		creatureSpawner.update(true);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onSpawnerBreak(final BlockBreakEvent event) {
		final Player player = event.getPlayer();
		final SpawnerUser spawnerUser = Spawners.getPlayerManager().findUser(player);
		final Block block = event.getBlock();

		if (block.getType() != CompMaterial.SPAWNER.parseMaterial()) return;

		final CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
		final NamespacedKey namespacedKey = new NamespacedKey(Spawners.getInstance(), "SpawnersOwner");
		final Spawner spawner = Spawners.getSpawnerManager().findSpawner(event.getBlock().getLocation());

		// spawner placed by user
		if (spawner != null) {
			final String ownerName = creatureSpawner.getPersistentDataContainer().get(namespacedKey, DataType.STRING);

			// stop exp dropping, since they could repeatedly break/place
			event.setExpToDrop(0);

			// check owner
			if (!player.getUniqueId().equals(spawner.getOwner()) && !Settings.ALLOW_NON_OWNER_BREAK.getBoolean()) {
				Translation.SPAWNER_NOT_OWNER_BREAK.send(player, "owner_name", ownerName);
				event.setCancelled(true);
				return;
			}

			// check entity break perm
			if (!handleEntityBreakPerm(spawnerUser, player, spawner.getEntityType())) {
				event.setCancelled(true);
				return;
			}

			// probs add allowed players
			Spawners.getSpawnerManager().deleteSpawner(spawner, success -> {
				// drop spawner
				ItemStack built = SpawnerItem.make(spawner.getOwner(), ownerName, spawner.getEntityType(), spawner.getLevel(), spawner.getOptions());
				Common.runLater(() -> block.getWorld().dropItemNaturally(block.getLocation(), built));
			});

			return;
		}

		// natural spawner
		// check entity break perm
		if (!handleEntityBreakPerm(spawnerUser, player, creatureSpawner.getSpawnedType())) {
			event.setCancelled(true);
			return;
		}


	}

	private boolean handleEntityBreakPerm(SpawnerUser spawnerUser, Player player, EntityType entityType) {
		if (!spawnerUser.isAllowedToPlaceEntity(player, entityType)) {
			Translation.SPAWNER_CANNOT_BREAK_ENTITY.send(player, "entity_type", StringUtils.capitalize(entityType.name().toLowerCase().replace("_", " ")));
			return false;
		}
		return true;
	}

	private boolean handleEntityPlacePerm(SpawnerUser spawnerUser, Player player, EntityType entityType) {
		if (!spawnerUser.isAllowedToPlaceEntity(player, entityType)) {
			Translation.SPAWNER_CANNOT_PLACE_ENTITY.send(player, "entity_type", StringUtils.capitalize(entityType.name().toLowerCase().replace("_", " ")));
			return false;
		}
		return true;
	}
}
