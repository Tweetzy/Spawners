package ca.tweetzy.spawners.guis.levels;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.interfaces.Level;
import ca.tweetzy.spawners.guis.SpawnersAdminGUI;
import ca.tweetzy.spawners.impl.SpawnerLevel;
import ca.tweetzy.spawners.settings.Translation;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Date Created: May 04 2022
 * Time Created: 5:43 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelListAdminGUI extends PagedGUI<Level> {

	public LevelListAdminGUI() {
		super(new SpawnersAdminGUI(), Translation.GUI_LEVEL_ADMIN_LIST_TITLE.getString(), 6, Spawners.getLevelManager().getLevels());
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Level level) {
		return QuickItem.of(CompMaterial.PAPER)
				.name(Translation.GUI_LEVEL_ADMIN_LIST_LEVELS_NAME.getString("level_number", level.getLevel()))
				.lore(Translation.GUI_LEVEL_ADMIN_LIST_LEVELS_LORE.getList(
						"level_spawn_interval", level.getSpawnInterval(),
						"level_spawn_count", level.getSpawnCount(),
						"level_max_nearby_entities", level.getMaxNearbyEntities(),
						"level_player_activation_range", level.getPlayerActivationRange()
				))
				.make();
	}

	@Override
	protected void drawAdditional() {
		setButton(5, 4, QuickItem.of(CompMaterial.SLIME_BALL)
				.name(Translation.GUI_LEVEL_ADMIN_LIST_NEW_NAME.getString())
				.lore(Translation.GUI_LEVEL_ADMIN_LIST_NEW_LORE.getList())
				.make(), click -> Spawners.getDataManager().insertLevel(new SpawnerLevel(Spawners.getLevelManager().getHighestLevel() + 1), (error, createdLevel) -> {

			if (error != null) return;
			Spawners.getLevelManager().addLevel(createdLevel);

			click.manager.showGUI(click.player, new LevelListAdminGUI());
		}));
	}

	@Override
	protected void onClick(Level level, GuiClickEvent event) {

	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}
}
