package ca.tweetzy.spawners.guis.admin.spawners;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.Common;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.SpawnersAdminGUI;
import org.bukkit.Location;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Date Created: May 06 2022
 * Time Created: 9:38 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerListAdminGUI extends PagedGUI<Spawner> {

	final UUID spawnerOwner;

	public SpawnerListAdminGUI() {
		this(null);
	}

	public SpawnerListAdminGUI(final UUID spawnerOwner) {
		super(new SpawnersAdminGUI(), "b", 6, spawnerOwner != null ?
				Spawners.getSpawnerManager().getContents().stream().filter(spawner -> spawner.getOwner().equals(spawnerOwner)).collect(Collectors.toList())
				:
				Spawners.getSpawnerManager().getContents()
		);
		this.spawnerOwner = spawnerOwner;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Spawner spawner) {
		return null;
//		return QuickItem
//				.of(CompMaterial.SPAWNER)
//				.name(Translation.GUI_SPAWNER_ADMIN_LIST_SPAWNER_NAME.getString("owner_name", spawner.getOwnerName()))
//				.lore(Translation.GUI_SPAWNER_ADMIN_LIST_SPAWNER_LORE.getList(
//						"world_name", spawner.getLocation().getWorld().getName(),
//						"world_x", spawner.getLocation().getBlockX(),
//						"world_y", spawner.getLocation().getBlockY(),
//						"world_z", spawner.getLocation().getBlockZ(),
//						"entity_type", StringUtils.capitalize(spawner.getEntityType().name().toLowerCase().replace("_", " ")),
//						"spawner_spawn_delay", spawner.getOptions().getSpawnInterval(),
//						"spawner_spawn_count", spawner.getOptions().getSpawnCount(),
//						"spawner_max_nearby_entities", spawner.getOptions().getMaxNearbyEntities(),
//						"spawner_player_activation_range", spawner.getOptions().getPlayerActivationRange()
//				))
//				.make();
	}

	@Override
	protected void onClick(Spawner spawner, GuiClickEvent event) {
		if (event.clickType == ClickType.LEFT)
			event.player.teleport(spawner.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);

		if (event.clickType == ClickType.NUMBER_KEY) {
			final Location location = spawner.getLocation();
			if (!location.isWorldLoaded()) return;

			if (!location.getChunk().isLoaded())
				location.getChunk().load();

			Spawners.getSpawnerManager().deleteSpawner(spawner, success -> {
				assert CompMaterial.AIR.parseMaterial() != null;
				Common.runLater(() -> location.getBlock().setType(CompMaterial.AIR.parseMaterial()));
				event.manager.showGUI(event.player, new SpawnerListAdminGUI());
			});
		}
	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}
}
