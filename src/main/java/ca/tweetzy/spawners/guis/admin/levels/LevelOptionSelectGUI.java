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
package ca.tweetzy.spawners.guis.admin.levels;

import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.guis.SpawnersBaseGUI;
import ca.tweetzy.spawners.guis.admin.SpawnersAdminGUI;
import org.bukkit.entity.Player;

/**
 * Date Created: May 13 2022
 * Time Created: 2:07 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelOptionSelectGUI extends SpawnersBaseGUI {

	public LevelOptionSelectGUI(Player player) {
		super(new SpawnersAdminGUI(player),player, "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7Select Option", 4);
		draw();
	}


	@Override
	protected void draw() {

		// spawn interval levels
		setButton(1, 1, QuickItem
				.of(CompMaterial.REPEATER)
				.name("<GRADIENT:fc67fa>&LSpawn Interval Levels</GRADIENT:f4c4f3>")
				.lore(
						"&8All spawn interval levels",
						"&7View all created levels for",
						"&7spawner spawn intervals.",
						"",
						"&e&lClick &8» &7To view interval levels"
				)
				.make(), click -> click.manager.showGUI(click.player, new LevelListGUI(this.player, LevelOption.SPAWN_INTERVAL)));

		setButton(1, 3, QuickItem
				.of(CompMaterial.TRIPWIRE_HOOK)
				.name("<GRADIENT:fc67fa>&LSpawn Count Levels</GRADIENT:f4c4f3>")
				.lore(
						"&8All spawn count levels",
						"&7View all created levels for",
						"&7spawner spawn count.",
						"",
						"&e&lClick &8» &7To view spawn count levels"
				)
				.make(), click -> click.manager.showGUI(click.player, new LevelListGUI(this.player, LevelOption.SPAWN_COUNT)));

		setButton(1, 5, QuickItem
				.of(CompMaterial.OBSERVER)
				.name("<GRADIENT:fc67fa>&LMax Nearby Levels</GRADIENT:f4c4f3>")
				.lore(
						"&8All max nearby levels",
						"&7View all created levels for spawner",
						"&7max nearby entities.",
						"",
						"&e&lClick &8» &7To view max nearby levels"
				)
				.make(), click -> click.manager.showGUI(click.player, new LevelListGUI(this.player, LevelOption.MAX_NEARBY_ENTITIES)));

		setButton(1, 7, QuickItem
				.of(CompMaterial.COMPARATOR)
				.name("<GRADIENT:fc67fa>&LActivation Range Levels</GRADIENT:f4c4f3>")
				.lore(
						"&8All activation range levels",
						"&7View all created levels for spawner",
						"&7max player activation range.",
						"",
						"&e&lClick &8» &7To view activation range levels"
				)
				.make(), click -> click.manager.showGUI(click.player, new LevelListGUI(this.player, LevelOption.ACTIVATION_RANGE)));

		applyBackExit();
	}
}
