package ca.tweetzy.spawners.guis;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.template.BaseGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.guis.levels.LevelListAdminGUI;
import ca.tweetzy.spawners.guis.spawners.SpawnerListAdminGUI;
import ca.tweetzy.spawners.guis.spawners.SpawnerPresetListGUI;
import ca.tweetzy.spawners.settings.Translation;

/**
 * Date Created: May 04 2022
 * Time Created: 5:16 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnersAdminGUI extends BaseGUI {

	public SpawnersAdminGUI() {
		super(null, Translation.GUI_ADMIN_MAIN_TITLE.getString("plugin_version", Spawners.getInstance().getVersion()), 6);
		draw();
	}

	@Override
	protected void draw() {
		// level presets
		setButton(1, 1, QuickItem
				.of(CompMaterial.TRIPWIRE_HOOK)
				.name(Translation.GUI_ADMIN_MAIN_ITEMS_LEVELS_NAME.getString())
				.lore(Translation.GUI_ADMIN_MAIN_ITEMS_LEVELS_LORE.getList())
				.make(), click -> click.manager.showGUI(click.player, new LevelListAdminGUI()));

		setButton(1, 4, QuickItem
				.of(CompMaterial.SPAWNER)
				.name(Translation.GUI_ADMIN_MAIN_ITEMS_SPAWNERS_NAME.getString())
				.lore(Translation.GUI_ADMIN_MAIN_ITEMS_SPAWNERS_LORE.getList())
				.make(), click -> click.manager.showGUI(click.player, new SpawnerListAdminGUI()));

		setButton(1, 7, QuickItem
				.of(CompMaterial.CARTOGRAPHY_TABLE)
				.name(Translation.GUI_ADMIN_MAIN_ITEMS_PRESETS_NAME.getString())
				.lore(Translation.GUI_ADMIN_MAIN_ITEMS_PRESETS_LORE.getList())
				.make(), click -> click.manager.showGUI(click.player, new SpawnerPresetListGUI()));
	}
}
