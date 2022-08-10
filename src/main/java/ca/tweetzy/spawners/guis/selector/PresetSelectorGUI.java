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
import ca.tweetzy.feather.gui.helper.InventoryBorder;
import ca.tweetzy.feather.gui.template.PagedGUI;
import ca.tweetzy.feather.utils.ChatUtil;
import ca.tweetzy.feather.utils.QuickItem;
import ca.tweetzy.feather.utils.Replacer;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Date Created: May 16 2022
 * Time Created: 2:14 p.m.
 *
 * @author Kiran Hart
 */
public final class PresetSelectorGUI extends PagedGUI<Preset> {

	private final Consumer<Preset> selected;

	public PresetSelectorGUI(Gui parent, @NonNull final Consumer<Preset> selected) {
		super(parent, "<GRADIENT:fc67fa>&lPresets</GRADIENT:f4c4f3> &8> &7Select Preset", 6, Spawners.getPresetManager().getContents());
		this.selected = selected;
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Preset preset) {
		final Level delayLevel = preset.getLevels().get(LevelOption.SPAWN_INTERVAL);
		final Level spawnCountLevel = preset.getLevels().get(LevelOption.SPAWN_COUNT);
		final Level maxNearbyLevel = preset.getLevels().get(LevelOption.MAX_NEARBY_ENTITIES);
		final Level activationRangeLevel = preset.getLevels().get(LevelOption.ACTIVATION_RANGE);


		return QuickItem.of(CompMaterial.PAPER)
				.name(Replacer.replaceVariables("<GRADIENT:fc67fa>&l%preset%</GRADIENT:f4c4f3>", "preset", preset.getId()))
				.lore(Replacer.replaceVariables(Arrays.asList(
								"",
								"&7Entity Type&f: &e%entity_type%",
								"",
								"&e&lLevels",
								"    &7Spawn Delay&f: &a%spawn_delay_level% &f(&e%spawn_delay%&as&f)",
								"    &7Spawn Count&f: &a%spawn_count_level% &f(&e%spawn_count%&f)",
								"    &7Max Nearby Mobs&f: &a%max_nearby_entities_level% &f(&e%max_nearby_entities%&f)",
								"    &7Activation Range&f: &a%activation_range_level% &f(&e%activation_range%&f)",
								"",
								"&e&lLeft Click &8» &7To edit preset",
								"&c&lPress 1 &8» &7To delete preset"
						),
						"entity_type", ChatUtil.capitalizeFully(preset.getEntityType()),
						"spawn_delay_level", delayLevel.getLevelNumber(),
						"spawn_count_level", spawnCountLevel.getLevelNumber(),
						"max_nearby_entities_level", maxNearbyLevel.getLevelNumber(),
						"activation_range_level", activationRangeLevel.getLevelNumber(),
						"spawn_delay", String.format(String.valueOf(delayLevel.getValue() / 20), "%,.2f"),
						"spawn_count", spawnCountLevel.getValue(),
						"max_nearby_entities", maxNearbyLevel.getValue(),
						"activation_range", activationRangeLevel.getValue()
				)).make();
	}

	@Override
	protected void onClick(Preset preset, GuiClickEvent clickEvent) {
		this.selected.accept(preset);
	}

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
