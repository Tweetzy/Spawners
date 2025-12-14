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
package ca.tweetzy.spawners.guis.selector;

import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.gui.Gui;
import ca.tweetzy.flight.gui.events.GuiClickEvent;
import ca.tweetzy.flight.gui.helper.InventoryBorder;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.flight.utils.Replacer;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.guis.SpawnersPagedGUI;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Consumer;

/**
 * Date Created: May 16 2022
 * Time Created: 2:14 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelSelectorGUI extends SpawnersPagedGUI<Level> {

	private final Consumer<Level> selected;

	public LevelSelectorGUI(Gui parent, Player player, @NonNull final LevelOption option, @NonNull final Consumer<Level> selected) {
		super(parent, player,"<GRADIENT:fc67fa>&lLevels</GRADIENT:f4c4f3> &8> &7Select Level", 6, Spawners.getLevelManager().getLevels(option));
		this.selected = selected;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Level level) {
		return QuickItem
				.of(CompMaterial.PAPER)
				.name(Replacer.replaceVariables("<GRADIENT:fc67fa>&LLevel %level%</GRADIENT:f4c4f3>", "level", level.getLevelNumber()))
				.lore(
						"&7Level #&f: &e" + level.getLevelNumber(),
						"&7Value&f: &e" + level.getValue(),
						"",
						"&E&lClick &8» &7To select level"
				)
				.make();
	}

	@Override
	protected void onClick(Level level, GuiClickEvent clickEvent) {
		this.selected.accept(level);
	}

	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}

}
