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
import ca.tweetzy.flight.nbtapi.NBT;
import ca.tweetzy.flight.settings.TranslationManager;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.SpawnersBaseGUI;
import ca.tweetzy.spawners.guis.user.SpawnerOverviewGUI;
import ca.tweetzy.spawners.impl.PlacedSpawner;
import ca.tweetzy.spawners.model.SpawnerBuilder;
import ca.tweetzy.spawners.settings.Translations;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public final class SpawnerMergeGUI extends SpawnersBaseGUI {

	private final Spawner spawner;
	private final boolean canUpgrade;
	private boolean isValidSpawner = false;

	private final int[] statusSlots = new int[]{12, 13, 14, 21, 23, 30, 31, 32};

	private BukkitTask spawnerChecker;

	public SpawnerMergeGUI(@NonNull final Spawner spawner, final boolean canUpgrade) {
		super(new SpawnerOverviewGUI(spawner, canUpgrade), TranslationManager.string(Translations.GUI_SPAWNER_MERGE_TITLE));
		this.spawner = spawner;
		this.canUpgrade = canUpgrade;

		setAcceptsItems(true);
		setUnlocked(22);

		setOnOpen(open -> {
			if (this.spawnerChecker == null)
				this.spawnerChecker = Bukkit.getScheduler().runTaskTimerAsynchronously(Spawners.getInstance(), this::drawStatusRing, 0, 1);
		});

		setOnClose(close -> {
			if (this.spawnerChecker != null && !this.spawnerChecker.isCancelled()) {
				this.spawnerChecker.cancel();
				this.spawnerChecker = null;
			}

			if (getItem(22) != null)
				close.player.getInventory().addItem(getItem(22));
		});

		draw();
	}

	@Override
	protected void draw() {
		drawStatusRing();
		setButton(getRows() - 1, 0, getBackButton(), click -> {
			click.gui.close();
			click.manager.showGUI(click.player, new MergeSplitGUI(this.spawner, this.canUpgrade));
		});

		setButton(getRows() - 1, 4, QuickItem.of(CompMaterial.LIME_DYE)
				.name(TranslationManager.string(Translations.GUI_SPAWNER_MERGE_ITEMS_CONFIRM_BTN_NAME))
				.lore(TranslationManager.list(Translations.GUI_SPAWNER_MERGE_ITEMS_CONFIRM_BTN_LORE))
				.make(), e -> {

			if (!this.spawner.isMaxedOut())
				mergeSpawners();
		});
	}

	private void drawStatusRing() {
		final ItemStack spawnerToMerge = getItem(22);

		CompMaterial ringColour = CompMaterial.WHITE_STAINED_GLASS_PANE;
		String ringStatusName = TranslationManager.string(Translations.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_WAIT);
		this.isValidSpawner = false;

		if (spawnerToMerge != null) {

			ringColour = CompMaterial.LIME_STAINED_GLASS_PANE;
			ringStatusName = TranslationManager.string(Translations.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_READY);
			this.isValidSpawner = true;

			if (!NBT.get(spawnerToMerge, nbt -> (boolean) nbt.hasTag("Spawners:entity"))) {
				ringColour = CompMaterial.RED_STAINED_GLASS_PANE;
				ringStatusName = TranslationManager.string(Translations.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_INVALID);
				this.isValidSpawner = false;
			} else {
				final EntityType entityType = NBT.get(spawnerToMerge, nbt -> (EntityType) nbt.getEnum("Spawners:entity", EntityType.class));

				if (this.spawner.getEntityType() != entityType) {
					ringColour = CompMaterial.RED_STAINED_GLASS_PANE;
					ringStatusName = TranslationManager.string(Translations.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_INVALID);
					this.isValidSpawner = false;
				}
			}
		}

		if (this.spawner.isMaxedOut()) {
			ringColour = CompMaterial.RED_STAINED_GLASS_PANE;
			ringStatusName = TranslationManager.string(Translations.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_MAX);
		}

		for (int i : statusSlots)
			setItem(i, QuickItem.of(ringColour).name(ringStatusName).make());
	}

	private void mergeSpawners() {
		if (!this.isValidSpawner) {
			return;
		}

		final ItemStack mergeItem = getItem(22);
		if (mergeItem == null) return;

		final Spawner spawnerToMerge = convertItemstackToSpawner(mergeItem);
		this.spawner.merge(spawnerToMerge, leftoverSpawner -> {
			if (leftoverSpawner != null) {
				final SpawnerBuilder builder = SpawnerBuilder.of(leftoverSpawner);

				for (Level level : leftoverSpawner.getLevels().values()) {
					builder.addLevel(level);
				}

				setItem(22, builder.make());
			}
		});
	}

	private Spawner convertItemstackToSpawner(@NonNull final ItemStack itemStack) {
		if (!NBT.get(itemStack, nbt -> (boolean) nbt.hasTag("Spawners:entity"))) return null;

		final EntityType entityType = NBT.get(itemStack, nbt -> (EntityType) nbt.getEnum("Spawners:entity", EntityType.class));

		final Level delayLevel = Spawners.getLevelManager().find(LevelOption.SPAWN_INTERVAL, Integer.parseInt(NBT.get(itemStack, nbt -> (String) nbt.getString("Spawners:delay"))));
		final Level spawnCountLevel = Spawners.getLevelManager().find(LevelOption.SPAWN_COUNT, Integer.parseInt(NBT.get(itemStack, nbt -> (String) nbt.getString("Spawners:spawnCount"))));
		final Level maxNearbyLevel = Spawners.getLevelManager().find(LevelOption.MAX_NEARBY_ENTITIES, Integer.parseInt(NBT.get(itemStack, nbt -> (String) nbt.getString("Spawners:maxNearby"))));
		final Level activationRangeLevel = Spawners.getLevelManager().find(LevelOption.ACTIVATION_RANGE, Integer.parseInt(NBT.get(itemStack, nbt -> (String) nbt.getString("Spawners:activationRange"))));

		return new PlacedSpawner(SpawnerBuilder.NULL_UUID, SpawnerBuilder.NULL_UUID, TranslationManager.string(Translations.SPAWNER_NO_OWNER), entityType, new HashMap<>() {{
			put(LevelOption.SPAWN_INTERVAL, delayLevel);
			put(LevelOption.SPAWN_COUNT, spawnCountLevel);
			put(LevelOption.MAX_NEARBY_ENTITIES, maxNearbyLevel);
			put(LevelOption.ACTIVATION_RANGE, activationRangeLevel);
		}}, null);
	}
}
