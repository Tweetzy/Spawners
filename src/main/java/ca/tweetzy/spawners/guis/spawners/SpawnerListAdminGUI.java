package ca.tweetzy.spawners.guis.spawners;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.SpawnersAdminGUI;
import ca.tweetzy.spawners.settings.Translation;
import org.apache.commons.lang.StringUtils;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Locale;
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
		super(new SpawnersAdminGUI(), Translation.GUI_SPAWNER_ADMIN_LIST_TITLE.getString(), 6, spawnerOwner != null ?
				Spawners.getSpawnerManager().getSpawners().stream().filter(spawner -> spawner.getOwner().equals(spawnerOwner)).collect(Collectors.toList())
				:
				Spawners.getSpawnerManager().getSpawners()
		);
		this.spawnerOwner = spawnerOwner;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Spawner spawner) {
		return QuickItem
				.of(CompMaterial.SPAWNER)
				.name(Translation.GUI_SPAWNER_ADMIN_LIST_SPAWNER_NAME.getString("owner_name", spawner.getOwnerName()))
				.lore(Translation.GUI_SPAWNER_ADMIN_LIST_SPAWNER_LORE.getList(
						"world_name", spawner.getLocation().getWorld().getName(),
						"world_x", spawner.getLocation().getBlockX(),
						"world_y", spawner.getLocation().getBlockY(),
						"world_z", spawner.getLocation().getBlockZ(),
						"entity_type", StringUtils.capitalize(spawner.getEntityType().name().toLowerCase().replace("_", " ")),
						"spawner_spawn_delay", spawner.getOptions().getSpawnInterval(),
						"spawner_spawn_count", spawner.getOptions().getSpawnCount(),
						"spawner_max_nearby_entities", spawner.getOptions().getMaxNearbyEntities(),
						"spawner_player_activation_range", spawner.getOptions().getPlayerActivationRange()
				))
				.make();
	}

	@Override
	protected void onClick(Spawner spawner, GuiClickEvent event) {

	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}
}
