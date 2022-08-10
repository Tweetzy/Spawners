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

import ca.tweetzy.feather.comp.enums.CompMaterial;
import ca.tweetzy.feather.gui.Gui;
import ca.tweetzy.feather.gui.events.GuiClickEvent;
import ca.tweetzy.feather.gui.template.PagedGUI;
import ca.tweetzy.feather.utils.QuickItem;
import ca.tweetzy.spawners.guis.ConfirmGUI;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Date Created: June 02 2022
 * Time Created: 4:56 p.m.
 *
 * @author Kiran Hart
 */
public final class PlayerSelectorGUI extends PagedGUI<Player> {

	private final BiConsumer<Player, Boolean> selected;

	public PlayerSelectorGUI(Gui parent, @NonNull final Player viewer, @NonNull final BiConsumer<Player, Boolean> selected) {
		super(parent, Translation.GUI_PLAYER_SELECT_TITLE.getString(), Settings.GUI_PLAYER_SELECT_ROWS.getInt(), Bukkit.getOnlinePlayers().stream().filter(player -> !player.getUniqueId().equals(viewer.getUniqueId())).collect(Collectors.toList()));
		setDefaultItem(QuickItem.of(Settings.GUI_PLAYER_SELECT_BG.getMaterial()).name(" ").make());
		this.selected = selected;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Player player) {
		return QuickItem.of(player)
				.name(Translation.GUI_PLAYER_SELECT_ITEMS_PLAYER_NAME.getString("player_name", player.getName()))
				.lore(Translation.GUI_PLAYER_SELECT_ITEMS_PLAYER_LORE.getList())
				.make();
	}

	@Override
	protected void drawAdditional() {
		Settings.GUI_PLAYER_SELECT_FILL_DECORATION.getStringList().forEach(deco -> {
			final String[] split = deco.split(":");

			if (split.length < 2) return;
			if (!NumberUtils.isNumber(split[0])) return;

			int slot = Integer.parseInt(split[0]);
			final ItemStack decoItem = CompMaterial.matchCompMaterial(split[1].toUpperCase()).orElse(Settings.GUI_PLAYER_SELECT_BG.getMaterial()).parseItem();

			if (!this.fillSlots().contains(slot))
				setItem(slot, decoItem);
		});
	}

	@Override
	protected void onClick(Player player, GuiClickEvent clickEvent) {
		clickEvent.manager.showGUI(clickEvent.player, new ConfirmGUI(this, confirmed -> this.selected.accept(player, confirmed)));
	}

	@Override
	protected List<Integer> fillSlots() {
		return Settings.GUI_PLAYER_SELECT_FILL_SLOTS.getIntegerList();
	}
}
