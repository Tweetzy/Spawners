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
package ca.tweetzy.spawners.guis.user.merging;

import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.gui.template.BaseGUI;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public final class SpawnerSplitGUI extends BaseGUI {

	private final Spawner spawner;
	private final boolean canUpgrade;

	private final Map<LevelOption, Integer> splitAmount = new HashMap<>();

	public SpawnerSplitGUI(@NonNull final Spawner spawner, final boolean canUpgrade) {
		super(new MergeSplitGUI(spawner, canUpgrade), Translation.GUI_SPAWNER_SPLIT_TITLE.getString(), 6);
		this.spawner = spawner;
		this.canUpgrade = canUpgrade;

		for (LevelOption value : LevelOption.values()) {
			splitAmount.put(value, 0);
		}

		draw();
	}

	@Override
	protected void draw() {

		for (LevelOption value : LevelOption.values()) {
			drawLevelButtons(value, value.ordinal() + 1);
		}

		applyBackExit();
	}

	private void drawLevelButtons(@NonNull final LevelOption option, int row) {

		setButton(row, 1, QuickItem
				.of(CompMaterial.RED_STAINED_GLASS_PANE)
				.name(Translation.GUI_SPAWNER_SPLIT_ITEMS_DECREASE_NAME.getString())
				.lore(Translation.GUI_SPAWNER_SPLIT_ITEMS_DECREASE_LORE.getList())
				.make(), click -> {

			if (this.splitAmount.get(option) > 0) {
				this.splitAmount.put(option, this.splitAmount.get(option) - 1);
				draw();
			}
		});

		final String name = switch (option) {
			case SPAWN_INTERVAL -> Translation.GUI_SPAWNER_SPLIT_ITEMS_DELAY_NAME.getString();
			case SPAWN_COUNT -> Translation.GUI_SPAWNER_SPLIT_ITEMS_SPAWN_COUNT_NAME.getString();
			case MAX_NEARBY_ENTITIES -> Translation.GUI_SPAWNER_SPLIT_ITEMS_NEARBY_MOBS_NAME.getString();
			case ACTIVATION_RANGE -> Translation.GUI_SPAWNER_SPLIT_ITEMS_ACTIVATION_RANGE_NAME.getString();
		};

		final CompMaterial material = switch (option) {
			case SPAWN_INTERVAL -> CompMaterial.REPEATER;
			case SPAWN_COUNT -> CompMaterial.TRIPWIRE_HOOK;
			case MAX_NEARBY_ENTITIES -> CompMaterial.OBSERVER;
			case ACTIVATION_RANGE -> CompMaterial.COMPARATOR;
		};

		setItem(row, 3, QuickItem
				.of(material)
				.name(name)
				.lore(switch (option) {
					case SPAWN_INTERVAL ->
							Translation.GUI_SPAWNER_SPLIT_ITEMS_DELAY_LORE.getList("current_split_amount", this.splitAmount.get(option), "available_split_amount", this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option));
					case SPAWN_COUNT ->
							Translation.GUI_SPAWNER_SPLIT_ITEMS_SPAWN_COUNT_LORE.getList("current_split_amount", this.splitAmount.get(option), "available_split_amount", this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option));
					case MAX_NEARBY_ENTITIES ->
							Translation.GUI_SPAWNER_SPLIT_ITEMS_NEARBY_MOBS_LORE.getList("current_split_amount", this.splitAmount.get(option), "available_split_amount", this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option));
					case ACTIVATION_RANGE ->
							Translation.GUI_SPAWNER_SPLIT_ITEMS_ACTIVATION_RANGE_LORE.getList("current_split_amount", this.splitAmount.get(option), "available_split_amount", this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option));
				}).make()
		);

		setButton(row, 5, QuickItem
				.of(CompMaterial.LIME_STAINED_GLASS_PANE)
				.name(Translation.GUI_SPAWNER_SPLIT_ITEMS_INCREASE_NAME.getString())
				.lore(Translation.GUI_SPAWNER_SPLIT_ITEMS_INCREASE_LORE.getList())
				.make(), click -> {

			if (this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option) == 0)
				return;


			this.splitAmount.put(option, this.splitAmount.get(option) + 1);
			draw();
		});
	}
}
