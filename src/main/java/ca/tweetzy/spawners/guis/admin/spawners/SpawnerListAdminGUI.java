package ca.tweetzy.spawners.guis.admin.spawners;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.Common;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.rose.utils.Replacer;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.SpawnersAdminGUI;
import org.bukkit.Location;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
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
		super(new SpawnersAdminGUI(), "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7Known Spawners", 6, spawnerOwner != null ?
				Spawners.getSpawnerManager().getContents().stream().filter(spawner -> spawner.getOwner().equals(spawnerOwner)).collect(Collectors.toList())
				:
				Spawners.getSpawnerManager().getContents()
		);
		this.spawnerOwner = spawnerOwner;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Spawner spawner) {
		return QuickItem
				.of(CompMaterial.SPAWNER)
				.name(String.format("<GRADIENT:fc67fa>&l%s Spawner</GRADIENT:f4c4f3>&f", spawner.getOwnerName()))
				.lore(Replacer.replaceVariables(Arrays.asList(
								"",
								"&e&lLocation",
								"    &7%world_name% &F/ &7%world_x% &f/ &7%world_y% &f/ &7%world_z%",
								"",
								"&e&lLevels",
								"    &7Spawn Delay&f: &a%spawn_delay%",
								"    &7Spawn Count&f: &a%spawn_count%",
								"    &7Max Nearby Mobs&f: &a%max_nearby_entities%",
								"    &7Activation Range&f: &a%activation_range%",
								"",
								"&e&LLeft Click &8» &7To teleport to spawner",
								"&c&lPress 1 &8» &7To delete spawner"
						),
						"world_name", spawner.getLocation().getWorld().getName(),
						"world_x", spawner.getLocation().getBlockX(),
						"world_y", spawner.getLocation().getBlockY(),
						"world_z", spawner.getLocation().getBlockZ(),
						"spawn_delay", spawner.getLevels().stream().filter(level -> level.getLevelOption() == LevelOption.SPAWN_INTERVAL).findFirst().map(Level::getLevelNumber).orElse(0),
						"spawn_count", spawner.getLevels().stream().filter(level -> level.getLevelOption() == LevelOption.SPAWN_COUNT).findFirst().map(Level::getLevelNumber).orElse(0),
						"max_nearby_entities", spawner.getLevels().stream().filter(level -> level.getLevelOption() == LevelOption.MAX_NEARBY_ENTITIES).findFirst().map(Level::getLevelNumber).orElse(0),
						"activation_range", spawner.getLevels().stream().filter(level -> level.getLevelOption() == LevelOption.ACTIVATION_RANGE).findFirst().map(Level::getLevelNumber).orElse(0)
				))
				.make();
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
