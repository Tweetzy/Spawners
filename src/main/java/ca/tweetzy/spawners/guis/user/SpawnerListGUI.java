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

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Date Created: May 06 2022
 * Time Created: 9:38 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerListGUI extends PagedGUI<Spawner> {

	public SpawnerListGUI(@NonNull final SpawnerUser spawnerUser) {
		super(new SpawnersMainGUI(spawnerUser), Translation.GUI_YOUR_SPAWNERS_TITLE.getString(), 6, spawnerUser.getPlacedSpawners());
		setDefaultItem(QuickItem.of(Settings.GUI_YOUR_SPAWNERS_BG.getMaterial()).name(" ").make());
		draw();
	}


	@Override
	protected ItemStack makeDisplayItem(Spawner spawner) {
		final Level delayLevel = spawner.getLevels().get(LevelOption.SPAWN_INTERVAL);
		final Level spawnCountLevel = spawner.getLevels().get(LevelOption.SPAWN_COUNT);
		final Level maxNearbyLevel = spawner.getLevels().get(LevelOption.MAX_NEARBY_ENTITIES);
		final Level activationRangeLevel = spawner.getLevels().get(LevelOption.ACTIVATION_RANGE);

		return QuickItem
				.of(CompMaterial.SPAWNER)
				.name(Translation.GUI_YOUR_SPAWNERS_ITEMS_SPAWNER_NAME.getString())
				.lore(Translation.GUI_YOUR_SPAWNERS_ITEMS_SPAWNER_LORE.getList(
						"world_name", spawner.getLocation().getWorld().getName(),
						"world_x", spawner.getLocation().getBlockX(),
						"world_y", spawner.getLocation().getBlockY(),
						"world_z", spawner.getLocation().getBlockZ(),
						"spawn_delay_level", delayLevel.getLevelNumber(),
						"spawn_count_level", spawnCountLevel.getLevelNumber(),
						"max_nearby_entities_level", maxNearbyLevel.getLevelNumber(),
						"activation_range_level", activationRangeLevel.getLevelNumber(),
						"spawn_delay", String.format(String.valueOf(delayLevel.getValue() / 20), "%,.2f"),
						"spawn_count", spawnCountLevel.getValue(),
						"max_nearby_entities", maxNearbyLevel.getValue(),
						"activation_range", activationRangeLevel.getValue()
				))
				.make();
	}

	@Override
	protected void drawAdditional() {
		Settings.GUI_YOUR_SPAWNERS_FILL_DECORATION.getStringList().forEach(deco -> {
			final String[] split = deco.split(":");

			if (split.length < 2) return;
			if (!NumberUtils.isNumber(split[0])) return;

			int slot = Integer.parseInt(split[0]);
			final ItemStack decoItem = CompMaterial.matchCompMaterial(split[1].toUpperCase()).orElse(Settings.GUI_YOUR_SPAWNERS_BG.getMaterial()).parseItem();

			if (!this.fillSlots().contains(slot))
				setItem(slot, decoItem);
		});
	}

	@Override
	protected void onClick(Spawner spawner, GuiClickEvent event) {
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
