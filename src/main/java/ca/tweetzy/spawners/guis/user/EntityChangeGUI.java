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
package ca.tweetzy.spawners.guis.user;

import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.gui.Gui;
import ca.tweetzy.flight.gui.events.GuiClickEvent;
import ca.tweetzy.flight.settings.TranslationManager;
import ca.tweetzy.flight.utils.Common;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.MobUpgrade;
import ca.tweetzy.spawners.guis.SpawnersPagedGUI;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translations;
import lombok.NonNull;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.N;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date Created: May 27 2022
 * Time Created: 3:45 p.m.
 *
 * @author Kiran Hart
 */
public final class EntityChangeGUI extends SpawnersPagedGUI<MobUpgrade> {

	private final Consumer<MobUpgrade> selected;

	public EntityChangeGUI(@NonNull final Gui parent, @NonNull final Player player, @NonNull final Consumer<MobUpgrade> selected) {
		super(parent,player, TranslationManager.string(Translations.GUI_ENTITY_CHANGE_TITLE), Settings.GUI_ENTITY_CHANGE_ROWS.getInt(), Stream
				.of(MobUpgrade.values())
				.filter(mobUpgrade -> mobUpgrade.isEnabled() && mobUpgrade.getSpawnerMob().isValid() && player.hasPermission("spawners.upgrade." + mobUpgrade.name().toLowerCase().replace("_", "")))
				.sorted(Comparator.comparing(mob -> mob.getSpawnerMob().getMobName()))
				.collect(Collectors.toList()));


		this.selected = selected;
		setDefaultItem(QuickItem.of(Settings.GUI_ENTITY_CHANGE_BG.getMaterial()).name(" ").make());
		draw();
	}



	@Override
	protected ItemStack makeDisplayItem(MobUpgrade mobUpgrade) {
		return QuickItem
				.of(mobUpgrade.getSpawnerMob().getHeadTexture())
				.name(TranslationManager.string(Translations.GUI_ENTITY_CHANGE_ITEMS_ENTITY_NAME, "entity_name", mobUpgrade.getSpawnerMob().getMobName()))
				.lore(TranslationManager.list(Translations.GUI_ENTITY_CHANGE_ITEMS_ENTITY_LORE, "entity_cost", String.format("%,.2f", mobUpgrade.getCost())))
				.make();
	}

	@Override
	protected void drawFixed() {
		Settings.GUI_ENTITY_CHANGE_FILL_DECORATION.getStringList().forEach(deco -> {
			final String[] split = deco.split(":");

			if (split.length < 2) return;
			if (!NumberUtils.isNumber(split[0])) return;

			int slot = Integer.parseInt(split[0]);
			final ItemStack decoItem = CompMaterial.matchCompMaterial(split[1].toUpperCase()).orElse(Settings.GUI_ENTITY_CHANGE_BG.getMaterial()).parseItem();

			if (!this.fillSlots().contains(slot))
				setItem(slot, decoItem);
		});
	}

	@Override
	protected List<Integer> fillSlots() {
		return Settings.GUI_ENTITY_CHANGE_FILL_SLOTS.getIntList();
	}

	@Override
	protected void onClick(MobUpgrade mobUpgrade, GuiClickEvent clickEvent) {
		final Player player = clickEvent.player;
		if (!Spawners.getEconomy().has(player, mobUpgrade.getCost())) {
			Common.tell(player, TranslationManager.string(Translations.NOT_ENOUGH_MONEY));
			return;
		}

		this.selected.accept(mobUpgrade);
	}
}
