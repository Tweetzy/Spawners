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
package ca.tweetzy.spawners.guis.admin.presets;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.ChatUtil;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.rose.utils.Replacer;
import ca.tweetzy.rose.utils.input.TitleInput;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.guis.SpawnersAdminGUI;
import ca.tweetzy.spawners.impl.SpawnerPreset;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Date Created: May 16 2022
 * Time Created: 1:10 p.m.
 *
 * @author Kiran Hart
 */
public final class PresetListGUI extends PagedGUI<Preset> {

	public PresetListGUI() {
		super(new SpawnersAdminGUI(), "<GRADIENT:fc67fa>&LLevels</GRADIENT:f4c4f3> &8> &7Presets", 6, Spawners.getPresetManager().getContents());
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
	protected void drawAdditional() {
		setButton(5, 4, QuickItem
				.of(CompMaterial.SLIME_BALL)
				.name("&a&lNew Preset")
				.lore("&e&lClick &8» &7To make new preset")
				.make(), clickEvent -> {

			if (!Spawners.getLevelManager().optionsHasAtLeastOneOption()) {
				Translation.NEED_TO_MAKE_LEVELS.send(clickEvent.player);
				return;
			}

			new TitleInput(clickEvent.player, "<GRADIENT:fc67fa>&lNew Preset</GRADIENT:f4c4f3>", "&fEnter an ID for the preset") {

				@Override
				public void onExit(Player player) {
					clickEvent.manager.showGUI(clickEvent.player, PresetListGUI.this);
				}

				@Override
				public boolean onResult(String string) {
					string = ChatColor.stripColor(string.trim().toLowerCase());

					if (Spawners.getPresetManager().find(string) != null) {
						Translation.PRESET_ID_TAKEN.send(clickEvent.player, "preset_id", string);
						return false;
					}

					final Preset preset = new SpawnerPreset(
							string,
							EntityType.valueOf(Settings.DEFAULT_SPAWNER_ENTITY.getString().toUpperCase()),
							new HashMap<>() {{
								put(LevelOption.SPAWN_INTERVAL, Spawners.getLevelManager().find(LevelOption.SPAWN_INTERVAL, 1));
								put(LevelOption.SPAWN_COUNT, Spawners.getLevelManager().find(LevelOption.SPAWN_COUNT, 1));
								put(LevelOption.MAX_NEARBY_ENTITIES, Spawners.getLevelManager().find(LevelOption.MAX_NEARBY_ENTITIES, 1));
								put(LevelOption.ACTIVATION_RANGE, Spawners.getLevelManager().find(LevelOption.ACTIVATION_RANGE, 1));
							}}
					);

					Spawners.getPresetManager().createPreset(preset, (created, createdPreset) -> {
						if (created)
							clickEvent.manager.showGUI(clickEvent.player, new PresetListGUI());
					});

					return true;
				}
			};
		});
	}

	@Override
	protected void onClick(Preset preset, GuiClickEvent clickEvent) {
		if (clickEvent.clickType == ClickType.LEFT)
			clickEvent.manager.showGUI(clickEvent.player, new PresetEditGUI(preset));

		if (clickEvent.clickType == ClickType.NUMBER_KEY)
			Spawners.getPresetManager().deletePreset(preset, success -> clickEvent.manager.showGUI(clickEvent.player, new PresetListGUI()));
	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}
}
