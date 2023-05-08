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

import ca.tweetzy.flight.comp.NBTEditor;
import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.gui.events.GuiClickEvent;
import ca.tweetzy.flight.gui.template.PagedGUI;
import ca.tweetzy.flight.settings.TranslationManager;
import ca.tweetzy.flight.utils.ChatUtil;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.SpawnerMob;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.api.spawner.ShopItem;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import ca.tweetzy.spawners.impl.shopitem.EntityShopItem;
import ca.tweetzy.spawners.impl.shopitem.PresetShopItem;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translations;
import lombok.NonNull;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Date Created: June 09 2022
 * Time Created: 11:13 a.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerShopGUI extends PagedGUI<ShopItem> {

	public SpawnerShopGUI(@NonNull final SpawnerUser spawnerUser) {
		super(new SpawnersMainGUI(spawnerUser), TranslationManager.string(Translations.GUI_SPAWNER_SHOP_TITLE), Settings.GUI_SHOP_ROWS.getInt(), Spawners.getShopItemManager().getContents());
		setDefaultItem(QuickItem.of(Settings.GUI_SHOP_BG.getMaterial()).name(" ").make());
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(ShopItem shopItem) {
		SpawnerMob spawnerMob;

		if (shopItem instanceof final EntityShopItem eShopItem)
			spawnerMob = SpawnerMob.valueOf(eShopItem.getEntityType().name());
		else {
			spawnerMob = SpawnerMob.valueOf(Spawners.getPresetManager().find(((PresetShopItem) shopItem).getPresetId()).getEntityType().name());
		}

		QuickItem quickItem = QuickItem.of(NBTEditor.getHead(spawnerMob.getHeadTexture()));
		quickItem.name(TranslationManager.string(Translations.GUI_SPAWNER_SHOP_ITEMS_SPAWNER_NAME, "entity_type", ChatUtil.capitalizeFully(spawnerMob.getMobName())));

		Level delayLevel;
		Level spawnCountLevel;
		Level maxNearbyLevel;
		Level activationRangeLevel;

		if (shopItem instanceof EntityShopItem) {
			delayLevel = Spawners.getLevelManager().find(LevelOption.SPAWN_INTERVAL, 1);
			spawnCountLevel = Spawners.getLevelManager().find(LevelOption.SPAWN_COUNT, 1);
			maxNearbyLevel = Spawners.getLevelManager().find(LevelOption.MAX_NEARBY_ENTITIES, 1);
			activationRangeLevel = Spawners.getLevelManager().find(LevelOption.ACTIVATION_RANGE, 1);

		} else {
			final PresetShopItem presetShopItem = (PresetShopItem) shopItem;
			final Preset preset = Spawners.getPresetManager().find(presetShopItem.getPresetId());

			delayLevel = preset.getLevels().get(LevelOption.SPAWN_INTERVAL);
			spawnCountLevel = preset.getLevels().get(LevelOption.SPAWN_COUNT);
			maxNearbyLevel = preset.getLevels().get(LevelOption.MAX_NEARBY_ENTITIES);
			activationRangeLevel = preset.getLevels().get(LevelOption.ACTIVATION_RANGE);
		}

		return quickItem.lore(TranslationManager.list(Translations.GUI_SPAWNER_SHOP_ITEMS_SPAWNER_LORE,
				"spawn_delay_level", delayLevel.getLevelNumber(),
				"spawn_count_level", spawnCountLevel.getLevelNumber(),
				"max_nearby_entities_level", maxNearbyLevel.getLevelNumber(),
				"activation_range_level", activationRangeLevel.getLevelNumber(),
				"spawn_delay", String.format(String.valueOf(delayLevel.getValue() / 20), "%,.2f"),
				"spawn_count", spawnCountLevel.getValue(),
				"max_nearby_entities", maxNearbyLevel.getValue(),
				"activation_range", activationRangeLevel.getValue(),
				"shop_item_price", String.format("%,.2f", shopItem.getPrice()),
				"shop_item_qty", shopItem.getQuantity()
		)).make();
	}

	@Override
	protected void drawAdditional() {
		Settings.GUI_SHOP_FILL_DECORATION.getStringList().forEach(deco -> {
			final String[] split = deco.split(":");

			if (split.length < 2) return;
			if (!NumberUtils.isNumber(split[0])) return;

			int slot = Integer.parseInt(split[0]);
			final ItemStack decoItem = CompMaterial.matchCompMaterial(split[1].toUpperCase()).orElse(Settings.GUI_SHOP_BG.getMaterial()).parseItem();

			if (!this.fillSlots().contains(slot))
				setItem(slot, decoItem);
		});
	}

	@Override
	protected void onClick(ShopItem shopItem, GuiClickEvent clickEvent) {
		shopItem.tryPurchase(clickEvent.player);
	}

	@Override
	protected List<Integer> fillSlots() {
		return (List<Integer>) Settings.GUI_SHOP_FILL_SLOTS.get();
	}

	@Override
	protected ItemStack getPreviousButton() {
		return QuickItem.of(CompMaterial.ARROW, TranslationManager.string(Translations.MISC_PREV_PAGE)).make();
	}

	@Override
	protected ItemStack getNextButton() {
		return QuickItem.of(CompMaterial.ARROW, TranslationManager.string(Translations.MISC_NEXT_PAGE)).make();
	}
}
