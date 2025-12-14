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
import ca.tweetzy.flight.settings.TranslationManager;
import ca.tweetzy.flight.utils.PlayerUtil;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.SpawnersBaseGUI;
import ca.tweetzy.spawners.guis.user.SpawnerOverviewGUI;
import ca.tweetzy.spawners.model.SpawnerBuilder;
import ca.tweetzy.spawners.settings.Translations;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public final class SpawnerSplitGUI extends SpawnersBaseGUI {

	private final Spawner spawner;
	private final boolean canUpgrade;

	private final Map<LevelOption, Integer> splitAmount = new HashMap<>();

	public SpawnerSplitGUI(Player player, @NonNull final Spawner spawner, final boolean canUpgrade) {
		super(new MergeSplitGUI(player, spawner, canUpgrade), player,TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_TITLE), 6);
		this.spawner = spawner;
		this.canUpgrade = canUpgrade;

		for (LevelOption value : LevelOption.values()) {
			splitAmount.put(value, 1);
		}

		draw();
	}

	@Override
	protected void draw() {

		for (LevelOption value : LevelOption.values()) {
			drawLevelButtons(value, value.ordinal() + 1);
		}

		drawSplitOffSpawner();
		drawConfirmationButton();

		applyBackExit();
	}

	private void drawLevelButtons(@NonNull final LevelOption option, int row) {

		setButton(row, 1, QuickItem
				.of(CompMaterial.RED_STAINED_GLASS_PANE)
				.name(TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_DECREASE_NAME))
				.lore(TranslationManager.list(Translations.GUI_SPAWNER_SPLIT_ITEMS_DECREASE_LORE))
				.make(), click -> {

			if (this.splitAmount.get(option) > 1) {
				this.splitAmount.put(option, this.splitAmount.get(option) - 1);
				draw();
			}
		});

		final String name = switch (option) {
			case SPAWN_INTERVAL -> TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_DELAY_NAME);
			case SPAWN_COUNT -> TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_SPAWN_COUNT_NAME);
			case MAX_NEARBY_ENTITIES -> TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_NEARBY_MOBS_NAME);
			case ACTIVATION_RANGE -> TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_ACTIVATION_RANGE_NAME);
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
							TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_DELAY_LORE, "current_split_amount", this.splitAmount.get(option), "available_split_amount", this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option));
					case SPAWN_COUNT ->
							TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_SPAWN_COUNT_LORE, "current_split_amount", this.splitAmount.get(option), "available_split_amount", this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option));
					case MAX_NEARBY_ENTITIES ->
							TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_NEARBY_MOBS_LORE, "current_split_amount", this.splitAmount.get(option), "available_split_amount", this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option));
					case ACTIVATION_RANGE ->
							TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_ACTIVATION_RANGE_LORE, "current_split_amount", this.splitAmount.get(option), "available_split_amount", this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option));
				}).make()
		);

		setButton(row, 5, QuickItem
				.of(CompMaterial.LIME_STAINED_GLASS_PANE)
				.name(TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_INCREASE_NAME))
				.lore(TranslationManager.list(Translations.GUI_SPAWNER_SPLIT_ITEMS_INCREASE_LORE))
				.make(), click -> {

			if (this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option) <= 1)
				return;


			this.splitAmount.put(option, this.splitAmount.get(option) + 1);
			draw();
		});
	}

	private void drawSplitOffSpawner() {
		setItem(1, 7, getSpawnerBuilder().make());
	}

	private void drawConfirmationButton() {
		boolean canSplit = true;

		for (LevelOption option : LevelOption.values()) {
			if (this.spawner.getLevels().get(option).getLevelNumber() - 1 - this.splitAmount.get(option) <= 0) {
				canSplit = false;
				break;
			}
		}

		boolean finalCanSplit = canSplit;

		setButton(4, 7, QuickItem
				.of(canSplit ? CompMaterial.LIME_STAINED_GLASS_PANE : CompMaterial.RED_STAINED_GLASS_PANE)
				.name(canSplit ? TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_CONFIRM_NAME) : TranslationManager.string(Translations.GUI_SPAWNER_SPLIT_ITEMS_INVALID_NAME))
				.lore(canSplit ? TranslationManager.list(Translations.GUI_SPAWNER_SPLIT_ITEMS_CONFIRM_LORE) : TranslationManager.list(Translations.GUI_SPAWNER_SPLIT_ITEMS_INVALID_LORE))
				.make(), click -> {

			if (!finalCanSplit)
				return;

			PlayerUtil.giveItem(click.player, getSpawnerBuilder().make());

			final Map<LevelOption, Level> levelMap = new HashMap<>();

			for (LevelOption value : LevelOption.values()) {
				final Level newLevel = Spawners.getLevelManager().find(value, this.spawner.getLevels().get(value).getLevelNumber() - this.splitAmount.get(value));
				levelMap.putIfAbsent(value, newLevel);
			}

			this.spawner.setLevels(levelMap);
			this.spawner.reApplyLevels();

			click.manager.showGUI(click.player, new SpawnerOverviewGUI(click.player, this.spawner, this.canUpgrade));
		});
	}

	private SpawnerBuilder getSpawnerBuilder() {
		final SpawnerBuilder spawner = SpawnerBuilder.of(this.spawner);
		this.splitAmount.forEach(spawner::addLevel);

		return spawner;
	}
}
