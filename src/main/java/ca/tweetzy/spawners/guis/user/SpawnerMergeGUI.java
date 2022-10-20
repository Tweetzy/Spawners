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

import ca.tweetzy.feather.comp.NBTEditor;
import ca.tweetzy.feather.comp.enums.CompMaterial;
import ca.tweetzy.feather.gui.template.BaseGUI;
import ca.tweetzy.feather.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.impl.PlacedSpawner;
import ca.tweetzy.spawners.model.SpawnerBuilder;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public final class SpawnerMergeGUI extends BaseGUI {

	private final Spawner spawner;
	private boolean isValidSpawner = false;

	private final int[] statusSlots = new int[]{12, 13, 14, 21, 23, 30, 31, 32};

	private BukkitTask spawnerChecker;

	public SpawnerMergeGUI(@NonNull final Spawner spawner, final boolean canUpgrade) {
		super(new SpawnerOverviewGUI(spawner, canUpgrade), Translation.GUI_SPAWNER_MERGE_TITLE.getString());
		this.spawner = spawner;

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
		});

		draw();
	}

	@Override
	protected void draw() {
		drawStatusRing();
		applyBackExit();

		setButton(getRows() - 1, 4, QuickItem.of(CompMaterial.LIME_DYE).name("&ahehe").make(), e -> mergeSpawners());
	}

	private void drawStatusRing() {
		final ItemStack spawnerToMerge = getItem(22);

		CompMaterial ringColour = CompMaterial.WHITE_STAINED_GLASS_PANE;
		String ringStatusName = Translation.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_WAIT.getString();
		this.isValidSpawner = false;

		if (spawnerToMerge != null) {
			ringColour = CompMaterial.LIME_STAINED_GLASS_PANE;
			ringStatusName = Translation.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_READY.getString();
			this.isValidSpawner = true;

			if (!NBTEditor.contains(spawnerToMerge, "Spawners:entity")) {
				ringColour = CompMaterial.RED_STAINED_GLASS_PANE;
				ringStatusName = Translation.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_INVALID.getString();
				this.isValidSpawner = false;
			} else {
				final EntityType entityType = EntityType.valueOf(NBTEditor.getString(spawnerToMerge, "Spawners:entity").toUpperCase());

				if (this.spawner.getEntityType() != entityType) {
					ringColour = CompMaterial.RED_STAINED_GLASS_PANE;
					ringStatusName = Translation.GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_INVALID.getString();
					this.isValidSpawner = false;
				}
			}
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
		this.spawner.merge(spawnerToMerge);
	}

	private Spawner convertItemstackToSpawner(@NonNull final ItemStack itemStack) {
		if (!NBTEditor.contains(itemStack, "Spawners:entity")) return null;

		final EntityType entityType = EntityType.valueOf(NBTEditor.getString(itemStack, "Spawners:entity").toUpperCase());

		final Level delayLevel = Spawners.getLevelManager().find(LevelOption.SPAWN_INTERVAL, Integer.parseInt(NBTEditor.getString(itemStack, "Spawners:delay")));
		final Level spawnCountLevel = Spawners.getLevelManager().find(LevelOption.SPAWN_COUNT, Integer.parseInt(NBTEditor.getString(itemStack, "Spawners:spawnCount")));
		final Level maxNearbyLevel = Spawners.getLevelManager().find(LevelOption.MAX_NEARBY_ENTITIES, Integer.parseInt(NBTEditor.getString(itemStack, "Spawners:maxNearby")));
		final Level activationRangeLevel = Spawners.getLevelManager().find(LevelOption.ACTIVATION_RANGE, Integer.parseInt(NBTEditor.getString(itemStack, "Spawners:activationRange")));

		return new PlacedSpawner(SpawnerBuilder.NULL_UUID, SpawnerBuilder.NULL_UUID, Translation.SPAWNER_NO_OWNER.getString(), entityType, new HashMap<>() {{
			put(LevelOption.SPAWN_INTERVAL, delayLevel);
			put(LevelOption.SPAWN_COUNT, spawnCountLevel);
			put(LevelOption.MAX_NEARBY_ENTITIES, maxNearbyLevel);
			put(LevelOption.ACTIVATION_RANGE, activationRangeLevel);
		}}, null);
	}
}
