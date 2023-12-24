/*
 * Spawners
 * Copyright 2022 Kiran Hart
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package ca.tweetzy.spawners.guis.admin;

import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.settings.TranslationManager;
import ca.tweetzy.flight.utils.ChatUtil;
import ca.tweetzy.flight.utils.Common;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.guis.SpawnersBaseGUI;
import ca.tweetzy.spawners.guis.admin.levels.LevelOptionSelectGUI;
import ca.tweetzy.spawners.guis.admin.presets.PresetListGUI;
import ca.tweetzy.spawners.guis.admin.shop.SpawnerShopAdminGUI;
import ca.tweetzy.spawners.guis.admin.spawners.SpawnerListAdminGUI;
import ca.tweetzy.spawners.settings.Translations;

/**
 * Date Created: May 04 2022
 * Time Created: 5:16 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnersAdminGUI extends SpawnersBaseGUI {

	public SpawnersAdminGUI() {
		super(null, TranslationManager.string(Translations.GUI_ADMIN_MAIN_TITLE, "plugin_version", Spawners.getInstance().getVersion()), 5);
		draw();
	}

	@Override
	protected void draw() {
		setButton(1, 1, QuickItem
				.of(CompMaterial.TRIPWIRE_HOOK)
				.name(TranslationManager.string(Translations.GUI_ADMIN_MAIN_ITEMS_LEVELS_NAME))
				.lore(TranslationManager.list(Translations.GUI_ADMIN_MAIN_ITEMS_LEVELS_LORE))
				.make(), click -> click.manager.showGUI(click.player, new LevelOptionSelectGUI()));

		setButton(1, 4, QuickItem
				.of(CompMaterial.SPAWNER)
				.name(TranslationManager.string(Translations.GUI_ADMIN_MAIN_ITEMS_SPAWNERS_NAME))
				.lore(TranslationManager.list(Translations.GUI_ADMIN_MAIN_ITEMS_SPAWNERS_LORE))
				.hideTags(true)
				.make(), click -> click.manager.showGUI(click.player, new SpawnerListAdminGUI()));

		setButton(1, 7, QuickItem
				.of(CompMaterial.CARTOGRAPHY_TABLE)
				.name(TranslationManager.string(Translations.GUI_ADMIN_MAIN_ITEMS_PRESETS_NAME))
				.lore(TranslationManager.list(Translations.GUI_ADMIN_MAIN_ITEMS_PRESETS_LORE))
				.make(), click -> click.manager.showGUI(click.player, new PresetListGUI()));

		setButton(3, 2, QuickItem
				.of(CompMaterial.GOLD_INGOT)
				.name(TranslationManager.string(Translations.GUI_ADMIN_MAIN_ITEMS_SHOP_NAME))
				.lore(TranslationManager.list(Translations.GUI_ADMIN_MAIN_ITEMS_SHOP_LORE))
				.make(), click -> {

			if (!Spawners.getLevelManager().optionsHasAtLeastOneOption()) {
				Common.tell(click.player, TranslationManager.string(Translations.NEED_TO_MAKE_LEVELS));
				return;
			}

			click.manager.showGUI(click.player, new SpawnerShopAdminGUI());
		});

		setButton(3, 6, QuickItem.of(CompMaterial.DIAMOND)
				.name("&e&lPatreon")
				.lore(
						"&8Support me on Patreon",
						"&7By supporting me on Patreon you will",
						"&7be helping me be able to continue updating",
						"&7and creating free & paid plugins.",
						"",
						"&e&lClick &8» &7To view Patreon"
				)
				.glow(true)
				.make(), click -> {

			click.gui.close();
			Common.tellNoPrefix(click.player,
					"&8&m-----------------------------------------------------",
					"",
					ChatUtil.centerMessage("&E&lKiran Hart Patreon"),
					ChatUtil.centerMessage("&bhttps://www.patreon.com/kiranhart"),
					"&8&m-----------------------------------------------------"
			);
		});
	}
}
