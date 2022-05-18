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

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.template.BaseGUI;
import ca.tweetzy.rose.utils.ChatUtil;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.rose.utils.Replacer;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.model.UserInput;
import lombok.NonNull;
import org.bukkit.event.inventory.ClickType;

/**
 * Date Created: May 04 2022
 * Time Created: 9:39 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelEditGUI extends BaseGUI {

	private final Level level;

	public LevelEditGUI(@NonNull final Level level) {
		super(new LevelListGUI(level.getLevelOption()), "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7" + ChatUtil.capitalizeFully(level.getLevelOption()), 6);
		this.level = level;
		draw();
	}

	@Override
	protected void draw() {

		final CompMaterial material = switch (this.level.getLevelOption()) {
			case SPAWN_INTERVAL -> CompMaterial.REPEATER;
			case SPAWN_COUNT -> CompMaterial.TRIPWIRE_HOOK;
			case MAX_NEARBY_ENTITIES -> CompMaterial.OBSERVER;
			case ACTIVATION_RANGE -> CompMaterial.COMPARATOR;
		};

		final String name = switch (this.level.getLevelOption()) {
			case SPAWN_INTERVAL -> "<GRADIENT:fc67fa>&LSpawn Interval</GRADIENT:f4c4f3>";
			case SPAWN_COUNT -> "<GRADIENT:fc67fa>&LSpawn Count</GRADIENT:f4c4f3>";
			case MAX_NEARBY_ENTITIES -> "<GRADIENT:fc67fa>&LMax Nearby Entities</GRADIENT:f4c4f3>";
			case ACTIVATION_RANGE -> "<GRADIENT:fc67fa>&LActivation Range</GRADIENT:f4c4f3>";
		};

		setItem(1, 4, QuickItem.of(material).name(name).lore("&7Editing level &e" + this.level.getLevelNumber()).make());
		setButton(3, 4, QuickItem.of(CompMaterial.PAPER)
				.name("&A&lValues")
				.lore(
						"",
						"&7Current&f: " + this.level.getValue(),
						"&7Cost&f: &a$" + this.level.getCost(),
						"",
						"&e&lLeft Click &8» &7To edit value",
						"&e&lRight Click &8» &7To edit cost"
				)
				.make(), event -> {

			if (event.clickType == ClickType.LEFT)
				UserInput.askInteger(event.player, this,
						Replacer.replaceVariables("<GRADIENT:fc67fa>&l%level_option%</GRADIENT:f4c4f3>", "level_option", ChatUtil.capitalizeFully(level.getLevelOption())),
						"&fEnter new value in chat",
						input -> {
							this.level.setValue(input);
							event.manager.showGUI(event.player, new LevelEditGUI(this.level));
							this.level.sync();
						});

			if (event.clickType == ClickType.RIGHT)
				UserInput.askDouble(event.player, this,
						Replacer.replaceVariables("<GRADIENT:fc67fa>&l%level_option%</GRADIENT:f4c4f3>", "level_option", ChatUtil.capitalizeFully(level.getLevelOption())),
						"&fEnter new cost in chat",
						input -> {
							this.level.setCost(input);
							event.manager.showGUI(event.player, new LevelEditGUI(this.level));
							this.level.sync();
						});
		});

		applyBackExit();
	}
}
