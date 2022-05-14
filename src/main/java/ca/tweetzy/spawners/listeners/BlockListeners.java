package ca.tweetzy.spawners.listeners;

import ca.tweetzy.rose.comp.NBTEditor;
import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.utils.ChatUtil;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import ca.tweetzy.spawners.model.SpawnerDefault;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Date Created: May 04 2022
 * Time Created: 11:00 p.m.
 *
 * @author Kiran Hart
 */
public final class BlockListeners implements Listener {

	@EventHandler(priority = EventPriority.LOW)
	public void onNonSpawnersSpawnerPlace(final BlockPlaceEvent event) {
		final ItemStack hand = event.getItemInHand();

		if (hand.getType() != CompMaterial.SPAWNER.parseMaterial()) return;
		if (NBTEditor.contains(hand, "Spawners:Spawner")) return;

		final CreatureSpawner creatureSpawner = (CreatureSpawner) event.getBlock().getState();
		creatureSpawner.setSpawnedType(EntityType.valueOf(Settings.DEFAULT_SPAWNER_ENTITY.getString()));
		Spawners.getSpawnerManager().applySpawnerDefaults(creatureSpawner, true);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onSpawnerPlace(final BlockPlaceEvent event) {
		final Player player = event.getPlayer();
		final SpawnerUser spawnerUser = Spawners.getPlayerManager().findUser(player);

		if (!NBTEditor.contains(event.getItemInHand(), "Spawners:Spawner")) return;

		final UUID owner = UUID.fromString(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:Owner"));
		final String ownerName = NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:OwnerName");

		final boolean noOwner = owner.equals(SpawnerDefault.NULL_UUID);

		if (!noOwner && !Settings.ALLOW_NON_OWNER_PLACE.getBoolean() && !owner.equals(player.getUniqueId())) {
			Translation.SPAWNER_NOT_OWNER_PLACE.send(player, "owner_name", ownerName);
			event.setCancelled(true);
			return;
		}

		final EntityType entityType = EntityType.valueOf(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:EntityType").toUpperCase());
		if (!handleEntityPlacePerm(spawnerUser, player, entityType)) {
			event.setCancelled(true);
			return;
		}

		final Block placedBlock = event.getBlockPlaced();

		// since spawner items can be any block, actually set the type on place to a spawner
		if (placedBlock.getType() != CompMaterial.SPAWNER.parseMaterial()) {
			assert CompMaterial.SPAWNER.parseMaterial() != null;
			placedBlock.setType(CompMaterial.SPAWNER.parseMaterial());
		}

//		final Options options = SpawnerOptions.decodeJson(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:Options"));
//		final Level level = Spawners.getLevelManager().find(Integer.parseInt(NBTEditor.getString(event.getItemInHand(), "Spawners:Spawner:Level")));
//
//		// insert spawner here and check place event
//		final Spawner spawner = new PlacedSpawner(UUID.randomUUID(), noOwner ? player.getUniqueId() : owner, noOwner ? Translation.SPAWNER_NO_OWNER.getString() : player.getName(), entityType, level != null ? level.getLevel() : -1, options, placedBlock.getLocation());
//		Spawners.getSpawnerManager().createSpawner(spawner, null);
//
//		final CreatureSpawner creatureSpawner = (CreatureSpawner) placedBlock.getState();
//		creatureSpawner.setSpawnedType(entityType);
//
//		// apply options
//		creatureSpawner.setDelay(level != null ? Math.min(level.getSpawnInterval(), options.getSpawnInterval()) : options.getSpawnInterval());
//		creatureSpawner.setMinSpawnDelay(level != null ? Math.min(level.getSpawnInterval(), options.getSpawnInterval()) : options.getSpawnInterval());
//
//		creatureSpawner.setMaxSpawnDelay(level != null ? Math.min(level.getSpawnInterval(), options.getSpawnInterval()) : options.getSpawnInterval());
//		creatureSpawner.setSpawnCount(level != null ? Math.max(level.getSpawnCount(), options.getSpawnCount()) : options.getSpawnCount());
//		creatureSpawner.setMaxNearbyEntities(level != null ? Math.max(level.getMaxNearbyEntities(), options.getMaxNearbyEntities()) : options.getMaxNearbyEntities());
//		creatureSpawner.setRequiredPlayerRange(level != null ? Math.max(level.getPlayerActivationRange(), options.getPlayerActivationRange()) : options.getPlayerActivationRange());

		// apply persistent container stuff
//		final NamespacedKey namespacedKey = new NamespacedKey(Spawners.getInstance(), "SpawnersOwner");
//		creatureSpawner.getPersistentDataContainer().set(namespacedKey, DataType.UUID, noOwner ? player.getUniqueId() : owner);
//		creatureSpawner.getPersistentDataContainer().set(namespacedKey, DataType.STRING, noOwner ? player.getName() : ownerName);
//
//		// update
//		creatureSpawner.update(true);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onSpawnerBreak(final BlockBreakEvent event) {
		final Player player = event.getPlayer();
		final SpawnerUser spawnerUser = Spawners.getPlayerManager().findUser(player);
		final Block block = event.getBlock();

		if (block.getType() != CompMaterial.SPAWNER.parseMaterial()) return;

		final CreatureSpawner creatureSpawner = (CreatureSpawner) block.getState();
		final NamespacedKey namespacedKey = new NamespacedKey(Spawners.getInstance(), "SpawnersOwner");
		final Spawner spawner = Spawners.getSpawnerManager().find(event.getBlock().getLocation());

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

			if (!handleTool(player)) {
				event.setCancelled(true);
				return;
			}

			// todo add allowed players
			Spawners.getSpawnerManager().deleteSpawner(spawner, success -> {
				// drop spawner
				if (breakChanceSucceeded(player)) {
//					ItemStack built = SpawnerItem.make(spawner.getOwner(), ownerName, spawner.getEntityType(), spawner.getLevel(), spawner.getOptions());
//					Common.runLater(() -> block.getWorld().dropItemNaturally(block.getLocation(), built));
				}
			});

			return;
		}

		if (!handleTool(player)) {
			event.setCancelled(true);
			return;
		}

		// natural spawner
		// check entity break perm
		if (!handleEntityBreakPerm(spawnerUser, player, creatureSpawner.getSpawnedType())) {
			event.setCancelled(true);
			return;
		}

		if (breakChanceSucceeded(player)) {
			Spawners.getSpawnerManager().applySpawnerDefaults(creatureSpawner, true);

//			block.getWorld().dropItemNaturally(block.getLocation(), SpawnerItem.make(
//					Settings.ASSIGN_OWNER_TO_NATURAL.getBoolean() ? player.getUniqueId() : SpawnerDefault.NULL_UUID,
//					Settings.ASSIGN_OWNER_TO_NATURAL.getBoolean() ? player.getName() : Translation.SPAWNER_NO_OWNER.getString(),
//					creatureSpawner.getSpawnedType(),
//					-1,
//					new SpawnerOptions(
//							creatureSpawner.getDelay(),
//							creatureSpawner.getSpawnCount(),
//							creatureSpawner.getMaxNearbyEntities(),
//							creatureSpawner.getRequiredPlayerRange()
//					)
//			));
		}
	}

	/*
	======================= KABOOM =======================
	 */
	@EventHandler(priority = EventPriority.LOW)
	public void onBlockExplodeEntity(final EntityExplodeEvent event) {
		if (!Settings.EXPLOSION_DROP_ENABLED.getBoolean()) return;
		final NamespacedKey namespacedKey = new NamespacedKey(Spawners.getInstance(), "SpawnersOwner");

		for (final Block explodedBlock : event.blockList()) {
			if (explodedBlock.getType() != CompMaterial.SPAWNER.parseMaterial()) continue;

			final Spawner spawner = Spawners.getSpawnerManager().find(explodedBlock.getLocation());
			final boolean success = new Random().nextDouble() < Settings.EXPLOSION_DROP_CHANCE.getDouble() / 100;

			if (!success) {
				Spawners.getSpawnerManager().deleteSpawner(spawner, null);
				continue;
			}

			final CreatureSpawner creatureSpawner = (CreatureSpawner) explodedBlock.getState();
			final String ownerName = creatureSpawner.getPersistentDataContainer().get(namespacedKey, DataType.STRING);

//			ItemStack spawnerStack;
//			if (spawner != null) {
//				// is a player spawner
//				spawnerStack = SpawnerItem.make(
//						Settings.EXPLOSION_RESETS_OWNER.getBoolean() ? SpawnerDefault.NULL_UUID : spawner.getOwner(),
//						Settings.EXPLOSION_RESETS_OWNER.getBoolean() ? Translation.SPAWNER_NO_OWNER.getString() : ownerName,
//						spawner.getEntityType(),
//						spawner.getLevel(),
//						spawner.getOptions()
//				);
//
//				Spawners.getSpawnerManager().deleteSpawner(spawner, null);
//			} else {
//				// natural
//				spawnerStack = SpawnerItem.make(
//						SpawnerDefault.NULL_UUID,
//						Translation.SPAWNER_NO_OWNER.getString(),
//						creatureSpawner.getSpawnedType(),
//						-1,
//						new SpawnerOptions(
//								creatureSpawner.getDelay(),
//								creatureSpawner.getSpawnCount(),
//								creatureSpawner.getMaxNearbyEntities(),
//								creatureSpawner.getRequiredPlayerRange()
//						)
//				);
//			}

//			explodedBlock.getWorld().dropItemNaturally(explodedBlock.getLocation(), spawnerStack);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onBlockExplodeUnknown(final BlockExplodeEvent event) {        // unknown source
		final Block block = event.getBlock();
		if (block.getType() != CompMaterial.SPAWNER.parseMaterial()) return;

		final Spawner spawner = Spawners.getSpawnerManager().find(block.getLocation());
		if (spawner == null) return;

		if (Settings.EXPLOSION_PREVENT_UNKNOWN_SOURCE.getBoolean())
			event.setCancelled(true);
	}

	private boolean handleEntityBreakPerm(SpawnerUser spawnerUser, Player player, EntityType entityType) {
		if (!spawnerUser.isAllowedToPlaceEntity(player, entityType)) {
			Translation.SPAWNER_CANNOT_BREAK_ENTITY.send(player, "entity_type", ChatUtil.capitalizeFully(entityType));
			return false;
		}
		return true;
	}

	private boolean handleEntityPlacePerm(SpawnerUser spawnerUser, Player player, EntityType entityType) {
		if (!spawnerUser.isAllowedToPlaceEntity(player, entityType)) {
			Translation.SPAWNER_CANNOT_PLACE_ENTITY.send(player, "entity_type", ChatUtil.capitalizeFully(entityType));
			return false;
		}
		return true;
	}

	private boolean handleTool(Player player) {
		final ItemStack stack = player.getInventory().getItemInMainHand();

		if (stack.getType() == CompMaterial.AIR.parseMaterial()) {
			Translation.SPAWNER_REQUIRE_PICKAXE.send(player);
			return false;
		}

		if (!stack.getType().name().endsWith("_PICKAXE")) {
			Translation.SPAWNER_REQUIRE_PICKAXE.send(player);
			return false;
		}

		if (Settings.MINE_REQUIRES_SILK_TOUCH.getBoolean() && !stack.containsEnchantment(Enchantment.SILK_TOUCH)) {
			Translation.SPAWNER_REQUIRE_SILK.send(player);
			return false;
		}

		return true;
	}

	private boolean breakChanceSucceeded(Player player) {
		final Pattern spawnerBreakChancePerm = Pattern.compile("spawners\\.minechance\\.(\\d+)");
		double defaultBreakChance = Settings.MINE_DROP_CHANCE.getDouble();

		int max = player.getEffectivePermissions().stream().map(i -> {
			Matcher matcher = spawnerBreakChancePerm.matcher(i.getPermission());
			if (matcher.matches()) {
				return Integer.parseInt(matcher.group(1));
			}
			return 0;
		}).max(Integer::compareTo).orElse(0);

		if (player.hasPermission("spawners.minechance.100") || player.isOp()) {
			defaultBreakChance = 100D;
		}

		if (max > defaultBreakChance)
			defaultBreakChance = max;

		if (defaultBreakChance <= 0D) return false;

		return new Random().nextDouble() < defaultBreakChance / 100;
	}
}
