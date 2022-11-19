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
import ca.tweetzy.flight.gui.events.GuiClickEvent;
import ca.tweetzy.flight.gui.helper.InventoryBorder;
import ca.tweetzy.flight.gui.template.PagedGUI;
import ca.tweetzy.flight.utils.ChatUtil;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.flight.utils.Replacer;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.model.LevelFactory;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date Created: May 13 2022
 * Time Created: 2:07 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelListGUI extends PagedGUI<Level> {

	private final LevelOption levelOption;

	public LevelListGUI(@NonNull final LevelOption levelOption) {
		super(new LevelOptionSelectGUI(), "<GRADIENT:fc67fa>&LLevels</GRADIENT:f4c4f3> &8> &7" + ChatUtil.capitalizeFully(levelOption.name()), 6, Spawners.getLevelManager().getLevels(levelOption).stream().sorted(Comparator.comparing(Level::getLevelNumber)).collect(Collectors.toList()));
		this.levelOption = levelOption;
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
						"&7Cost: &a$" + String.format("%,.2f", level.getCost()),
						"",
						"&e&lLeft Click &8» &7To edit level",
						"&c&lPress 1 &8» &7To delete level"
				)
				.make();
	}

	@Override
	protected void drawAdditional() {
		setButton(5, 4, QuickItem
				.of(CompMaterial.SLIME_BALL)
				.name("&a&lCreate Level")
				.lore(
						"&7Used to create a new level for",
						"&7the " + ChatUtil.capitalizeFully(levelOption.name()) + " option",
						"",
						"&e&lClick &8» &7Create new level"
				)
				.make(), click -> Spawners.getLevelManager().createLevel(
				LevelFactory.build(this.levelOption, Spawners.getLevelManager().getHighestLevel(this.levelOption) + 1, LevelFactory.getDefaultValue(this.levelOption), Settings.DEFAULT_LEVEL_COST.getDouble()), (created, createdLevel) -> {
					if (created)
						click.manager.showGUI(click.player, new LevelListGUI(this.levelOption));
				}));
	}

	@Override
	protected void onClick(Level level, GuiClickEvent clickEvent) {
		if (clickEvent.clickType == ClickType.LEFT)
			clickEvent.manager.showGUI(clickEvent.player, new LevelEditGUI(level));

		if (clickEvent.clickType == ClickType.NUMBER_KEY)
			Spawners.getLevelManager().deleteLevel(level, (success) -> {
				if (success)
					clickEvent.manager.showGUI(clickEvent.player, new LevelListGUI(this.levelOption));
			});
	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}

	@Override
	protected ItemStack getPreviousButton() {
		return QuickItem.of(CompMaterial.ARROW, Translation.MISC_PREV_PAGE.getString()).make();
	}

	@Override
	protected ItemStack getNextButton() {
		return QuickItem.of(CompMaterial.ARROW, Translation.MISC_NEXT_PAGE.getString()).make();
	}
}
