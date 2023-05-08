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

import ca.tweetzy.flight.comp.NBTEditor;
import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.gui.Gui;
import ca.tweetzy.flight.gui.events.GuiClickEvent;
import ca.tweetzy.flight.gui.helper.InventoryBorder;
import ca.tweetzy.flight.gui.template.PagedGUI;
import ca.tweetzy.flight.settings.TranslationManager;
import ca.tweetzy.flight.utils.ChatUtil;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.api.MobBehaviour;
import ca.tweetzy.spawners.api.SpawnerMob;
import ca.tweetzy.spawners.settings.Translations;
import lombok.NonNull;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Date Created: May 06 2022
 * Time Created: 1:53 p.m.
 *
 * @author Kiran Hart
 */
public final class EntitySelectorGUI extends PagedGUI<SpawnerMob> {

	private final Gui parent;
	private final Consumer<EntityType> selected;
	private final EntityViewMode entityViewMode;

	public EntitySelectorGUI(Gui parent, @NonNull final EntityViewMode entityViewMode, @NonNull final Consumer<EntityType> selected) {
		super(parent, TranslationManager.string(Translations.GUI_ENTITY_SELECTOR_TITLE), 6,
				entityViewMode == EntityViewMode.ALL ?
						Arrays.stream(SpawnerMob.values()).sorted(Comparator.comparing(SpawnerMob::getMobName)).collect(Collectors.toList())
						:
						Arrays.stream(SpawnerMob.values()).filter(mob -> mob.getBehaviour() == MobBehaviour.valueOf(entityViewMode.name())).sorted(Comparator.comparing(SpawnerMob::getMobName)).collect(Collectors.toList())
		);

		this.parent = parent;
		this.selected = selected;
		this.entityViewMode = entityViewMode;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(SpawnerMob spawnerMob) {
		return QuickItem
				.of(NBTEditor.getHead(spawnerMob.getHeadTexture()))
				.name(TranslationManager.string(Translations.GUI_ENTITY_SELECTOR_ITEMS_ENTITY_NAME, "entity_name", spawnerMob.getMobName()))
				.lore(TranslationManager.list(Translations.GUI_ENTITY_SELECTOR_ITEMS_ENTITY_LORE))
				.make();
	}

	@Override
	protected void onClick(SpawnerMob spawnerMob, GuiClickEvent clickEvent) {
		this.selected.accept(spawnerMob.getEntityType());
	}

	@Override
	protected void drawAdditional() {
		setButton(5, 4, QuickItem.of(CompMaterial.REPEATER)
				.name(TranslationManager.string(Translations.GUI_ENTITY_SELECTOR_ITEMS_MODE_NAME))
				.lore(TranslationManager.list(Translations.GUI_ENTITY_SELECTOR_ITEMS_MODE_LORE, "entity_behaviour", ChatUtil.capitalizeFully(this.entityViewMode)))
				.make(), click -> click.manager.showGUI(click.player, new EntitySelectorGUI(this.parent, this.entityViewMode.next(), this.selected)));
	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}

	@Override
	protected ItemStack getPreviousButton() {
		return QuickItem.of(CompMaterial.ARROW, TranslationManager.string(Translations.MISC_PREV_PAGE)).make();
	}

	@Override
	protected ItemStack getNextButton() {
		return QuickItem.of(CompMaterial.ARROW, TranslationManager.string(Translations.MISC_NEXT_PAGE)).make();
	}

	public enum EntityViewMode {
		PASSIVE, HOSTILE, NEUTRAL, ALL;

		private static final EntityViewMode[] val = values();

		public EntityViewMode next() {
			return val[(this.ordinal() + 1) % val.length];
		}
	}
}
