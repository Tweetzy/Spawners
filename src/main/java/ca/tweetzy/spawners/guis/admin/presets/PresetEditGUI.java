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

import ca.tweetzy.feather.comp.NBTEditor;
import ca.tweetzy.feather.comp.enums.CompMaterial;
import ca.tweetzy.feather.gui.template.BaseGUI;
import ca.tweetzy.feather.utils.ChatUtil;
import ca.tweetzy.feather.utils.QuickItem;
import ca.tweetzy.feather.utils.Replacer;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.SpawnerMob;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.guis.selector.EntitySelectorGUI;
import ca.tweetzy.spawners.guis.selector.LevelSelectorGUI;
import lombok.NonNull;

import java.util.Arrays;

/**
 * Date Created: May 16 2022
 * Time Created: 1:28 p.m.
 *
 * @author Kiran Hart
 */
public final class PresetEditGUI extends BaseGUI {

	private final Preset preset;

	public PresetEditGUI(@NonNull final Preset preset) {
		super(new PresetListGUI(), "<GRADIENT:fc67fa>&lPresets</GRADIENT:f4c4f3> &8> &7" + preset.getId());
		this.preset = preset;
		draw();
	}

	@Override
	protected void draw() {

		setButton(1, 4, QuickItem
				.of(NBTEditor.getHead(SpawnerMob.valueOf(this.preset.getEntityType().name()).getHeadTexture()))
				.name("<GRADIENT:fc67fa>&lEntity Type</GRADIENT:f4c4f3>")
				.lore(Replacer.replaceVariables(Arrays.asList(
								"",
								"&7Current&f: &e%entity_type%",
								"",
								"&e&lClick &8» &7To change entity"
						),
						"entity_type", ChatUtil.capitalizeFully(this.preset.getEntityType())
				)).make(), click -> click.manager.showGUI(click.player, new EntitySelectorGUI(this, EntitySelectorGUI.EntityViewMode.ALL, selected -> {

			this.preset.setEntityType(selected);
			this.preset.sync();
			click.manager.showGUI(click.player, new PresetEditGUI(this.preset));
		})));

		// spawn interval
		setButton(3, 1, QuickItem
				.of(CompMaterial.REPEATER)
				.name("<GRADIENT:fc67fa>&lSpawn Interval Level</GRADIENT:f4c4f3>")
				.lore(Replacer.replaceVariables(Arrays.asList(
								"",
								"&7Current&f: &a%spawn_delay_level% &f(&e%spawn_delay%&as&f)",
								"",
								"&e&lClick &8» &7To change level"
						),
						"spawn_delay_level", this.preset.getLevels().get(LevelOption.SPAWN_INTERVAL).getLevelNumber(),
						"spawn_delay", String.format(String.valueOf(this.preset.getLevels().get(LevelOption.SPAWN_INTERVAL).getValue() / 20), "%,.2f")
				)).make(), click -> click.manager.showGUI(click.player, new LevelSelectorGUI(this, LevelOption.SPAWN_INTERVAL, selected -> {

			this.preset.getLevels().put(LevelOption.SPAWN_INTERVAL, selected);
			this.preset.sync();
			click.manager.showGUI(click.player, new PresetEditGUI(this.preset));
		})));

		// spawn count
		setButton(3, 3, QuickItem
				.of(CompMaterial.TRIPWIRE_HOOK)
				.name("<GRADIENT:fc67fa>&lSpawn Count Level</GRADIENT:f4c4f3>")
				.lore(Replacer.replaceVariables(Arrays.asList(
								"",
								"&7Current&f: &a%spawn_count_level% &f(&e%spawn_count%&f)",
								"",
								"&e&lClick &8» &7To change level"
						),
						"spawn_count_level", this.preset.getLevels().get(LevelOption.SPAWN_COUNT).getLevelNumber(),
						"spawn_count", this.preset.getLevels().get(LevelOption.SPAWN_COUNT).getValue()
				)).make(), click -> click.manager.showGUI(click.player, new LevelSelectorGUI(this, LevelOption.SPAWN_COUNT, selected -> {

			this.preset.getLevels().put(LevelOption.SPAWN_COUNT, selected);
			this.preset.sync();
			click.manager.showGUI(click.player, new PresetEditGUI(this.preset));
		})));

		// max nearby
		setButton(3, 5, QuickItem
				.of(CompMaterial.OBSERVER)
				.name("<GRADIENT:fc67fa>&lMax Nearby Mobs Level</GRADIENT:f4c4f3>")
				.lore(Replacer.replaceVariables(Arrays.asList(
								"",
								"&7Current&f: &a%max_nearby_entities_level% &f(&e%max_nearby_entities%&f)",
								"",
								"&e&lClick &8» &7To change level"
						),
						"max_nearby_entities_level", this.preset.getLevels().get(LevelOption.MAX_NEARBY_ENTITIES).getLevelNumber(),
						"max_nearby_entities", this.preset.getLevels().get(LevelOption.MAX_NEARBY_ENTITIES).getValue()
				)).make(), click -> click.manager.showGUI(click.player, new LevelSelectorGUI(this, LevelOption.MAX_NEARBY_ENTITIES, selected -> {

			this.preset.getLevels().put(LevelOption.MAX_NEARBY_ENTITIES, selected);
			this.preset.sync();
			click.manager.showGUI(click.player, new PresetEditGUI(this.preset));
		})));

		// Activation range
		setButton(3, 7, QuickItem
				.of(CompMaterial.COMPARATOR)
				.name("<GRADIENT:fc67fa>&lActivation Range Level</GRADIENT:f4c4f3>")
				.lore(Replacer.replaceVariables(Arrays.asList(
								"",
								"&7Current&f: &a%activation_range_level% &f(&e%activation_range%&f)",
								"",
								"&e&lClick &8» &7To change level"
						),
						"activation_range_level", this.preset.getLevels().get(LevelOption.ACTIVATION_RANGE).getLevelNumber(),
						"activation_range", this.preset.getLevels().get(LevelOption.ACTIVATION_RANGE).getValue()
				)).make(), click -> click.manager.showGUI(click.player, new LevelSelectorGUI(this, LevelOption.ACTIVATION_RANGE, selected -> {

			this.preset.getLevels().put(LevelOption.ACTIVATION_RANGE, selected);
			this.preset.sync();
			click.manager.showGUI(click.player, new PresetEditGUI(this.preset));
		})));

		applyBackExit();
	}
}
