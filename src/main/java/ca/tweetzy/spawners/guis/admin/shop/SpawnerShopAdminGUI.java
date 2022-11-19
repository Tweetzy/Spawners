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
package ca.tweetzy.spawners.guis.admin.shop;

import ca.tweetzy.flight.comp.NBTEditor;
import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.gui.events.GuiClickEvent;
import ca.tweetzy.flight.gui.helper.InventoryBorder;
import ca.tweetzy.flight.gui.template.PagedGUI;
import ca.tweetzy.flight.utils.ChatUtil;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.flight.utils.Replacer;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.SpawnerMob;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.api.spawner.ShopItem;
import ca.tweetzy.spawners.guis.admin.SpawnersAdminGUI;
import ca.tweetzy.spawners.guis.selector.EntitySelectorGUI;
import ca.tweetzy.spawners.guis.selector.PresetSelectorGUI;
import ca.tweetzy.spawners.impl.shopitem.EntityShopItem;
import ca.tweetzy.spawners.impl.shopitem.PresetShopItem;
import ca.tweetzy.spawners.model.UserInput;
import ca.tweetzy.spawners.settings.Translation;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * Date Created: June 09 2022
 * Time Created: 11:13 a.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerShopAdminGUI extends PagedGUI<ShopItem> {

	public SpawnerShopAdminGUI() {
		super(new SpawnersAdminGUI(), "<GRADIENT:fc67fa>&lSpawner Shop</GRADIENT:f4c4f3> &8> &7Edit", 6, Spawners.getShopItemManager().getContents());
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
		quickItem.name(String.format("<GRADIENT:fc67fa>&l%s Spawner</GRADIENT:f4c4f3>", ChatUtil.capitalizeFully(spawnerMob.getMobName())));

		List<String> lore = Arrays.asList(
				"",
				"&7Price&f: &a%shop_item_price%",
				"&7Quantity&f: &a%shop_item_qty%",
				"",
				"&e&lLevels",
				"    &7Spawn Delay&f: &a%spawn_delay_level% &f(&e%spawn_delay%&as&f)",
				"    &7Spawn Count&f: &a%spawn_count_level% &f(&e%spawn_count%&f)",
				"    &7Max Nearby Mobs&f: &a%max_nearby_entities_level% &f(&e%max_nearby_entities%&f)",
				"    &7Activation Range&f: &a%activation_range_level% &f(&e%activation_range%&f)",
				"",
				"&e&LLeft Click &8» &7To edit price",
				"&e&LRight Click &8» &7To edit quantity",
				"&c&lPress 1 &8» &7To delete item"
		);


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

		lore = Replacer.replaceVariables(lore,
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
		);

		return quickItem.lore(lore).make();
	}

	@Override
	protected void drawAdditional() {
		setButton(5, 4, QuickItem
				.of(CompMaterial.SLIME_BALL)
				.name("&a&lCreate Shop Item")
				.lore(
						"&e&lLeft Click &8» &7To create from preset",
						"&e&lRight Click &8» &7To create from entity"
				)
				.make(), click -> {

			if (click.clickType == ClickType.LEFT) {

				if (Spawners.getPresetManager().getContents().isEmpty()) {
					Translation.NO_PRESETS_MADE.send(click.player);
					return;
				}

				click.manager.showGUI(click.player, new PresetSelectorGUI(this, selectedPreset -> {
					UserInput.askInteger(click.player, this, "<GRADIENT:fc67fa>&lNew Shop Item</GRADIENT:f4c4f3>", "&fEnter stack/purchase quantity", quantity -> {
						// run synchronously coz of inventory close
						Bukkit.getScheduler().runTaskLater(Spawners.getInstance(), () -> UserInput.askDouble(click.player, this, "<GRADIENT:fc67fa>&lNew Shop Item</GRADIENT:f4c4f3>", "&fEnter price of item", cost -> {
							Spawners.getShopItemManager().createShopItem(new PresetShopItem(selectedPreset.getId(), quantity, cost), (created, shopItem) -> {
								if (created)
									click.manager.showGUI(click.player, new SpawnerShopAdminGUI());
							});
						}), 1L);
					});
				}));
			}

			if (click.clickType == ClickType.RIGHT) {
				click.manager.showGUI(click.player, new EntitySelectorGUI(this, EntitySelectorGUI.EntityViewMode.ALL, selectedEntity -> {
					UserInput.askInteger(click.player, this, "<GRADIENT:fc67fa>&lNew Shop Item</GRADIENT:f4c4f3>", "&fEnter stack/purchase quantity", quantity -> {
						// run synchronously coz of inventory close
						Bukkit.getScheduler().runTaskLater(Spawners.getInstance(), () -> UserInput.askDouble(click.player, this, "<GRADIENT:fc67fa>&lNew Shop Item</GRADIENT:f4c4f3>", "&fEnter price of item", cost -> {
							Spawners.getShopItemManager().createShopItem(new EntityShopItem(selectedEntity, quantity, cost), (created, shopItem) -> {
								if (created)
									click.manager.showGUI(click.player, new SpawnerShopAdminGUI());
							});
						}), 1L);
					});
				}));
			}
		});
	}

	@Override
	protected void onClick(ShopItem shopItem, GuiClickEvent clickEvent) {
		if (clickEvent.clickType == ClickType.NUMBER_KEY)
			Spawners.getShopItemManager().deleteShopItem(shopItem, success -> {
				if (success)
					clickEvent.manager.showGUI(clickEvent.player, new SpawnerShopAdminGUI());
			});

		if (clickEvent.clickType == ClickType.LEFT)
			UserInput.askDouble(clickEvent.player, this, "<GRADIENT:fc67fa>&lShop Item Edit</GRADIENT:f4c4f3>", "&fEnter new price for item", newPrice -> {
				shopItem.setPrice(newPrice);
				shopItem.sync();

				clickEvent.manager.showGUI(clickEvent.player, new SpawnerShopAdminGUI());
			});

		if (clickEvent.clickType == ClickType.RIGHT)
			UserInput.askInteger(clickEvent.player, this, "<GRADIENT:fc67fa>&lShop Item Edit</GRADIENT:f4c4f3>", "&fEnter new stack quantity", newQty -> {
				shopItem.setQuantity(newQty);
				shopItem.sync();

				clickEvent.manager.showGUI(clickEvent.player, new SpawnerShopAdminGUI());
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
