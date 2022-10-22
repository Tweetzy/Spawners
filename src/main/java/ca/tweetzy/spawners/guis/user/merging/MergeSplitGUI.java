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

import ca.tweetzy.feather.comp.enums.CompMaterial;
import ca.tweetzy.feather.gui.template.BaseGUI;
import ca.tweetzy.feather.utils.QuickItem;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.user.SpawnerOverviewGUI;
import ca.tweetzy.spawners.settings.Settings;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;

public final class MergeSplitGUI extends BaseGUI {

	private final Spawner spawner;
	private final boolean canUpgrade;

	public MergeSplitGUI(@NonNull final Spawner spawner, final boolean canUpgrade) {
		super(new SpawnerOverviewGUI(spawner, canUpgrade), Translation.GUI_SPAWNER_MERGE_OR_SPLIT_TITLE.getString(), Settings.GUI_MERGE_OR_SPLIT_ROWS.getInt());
		this.spawner = spawner;
		this.canUpgrade = canUpgrade;

		setDefaultItem(QuickItem.of(Settings.GUI_MERGE_OR_SPLIT_BG.getMaterial()).name(" ").make());
		draw();
	}

	@Override
	protected void draw() {
		applyBackExit();

		setButton(1, 2, QuickItem
				.of(CompMaterial.SHULKER_BOX)
				.name(Translation.GUI_SPAWNER_MERGE_OR_SPLIT_ITEMS_MERGE_NAME.getString())
				.lore(Translation.GUI_SPAWNER_MERGE_OR_SPLIT_ITEMS_MERGE_LORE.getList())
				.make(), click -> click.manager.showGUI(click.player, new SpawnerMergeGUI(this.spawner, this.canUpgrade)));

		setButton(1, 6, QuickItem
				.of(CompMaterial.PRISMARINE_SHARD)
				.name(Translation.GUI_SPAWNER_MERGE_OR_SPLIT_ITEMS_SPLIT_NAME.getString())
				.lore(Translation.GUI_SPAWNER_MERGE_OR_SPLIT_ITEMS_SPLIT_LORE.getList())
				.make(), click -> click.manager.showGUI(click.player, new SpawnerSplitGUI(this.spawner, this.canUpgrade)));
	}
}
