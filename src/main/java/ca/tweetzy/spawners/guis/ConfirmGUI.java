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
package ca.tweetzy.spawners.guis;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.Gui;
import ca.tweetzy.rose.gui.template.BaseGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;

import java.util.function.Consumer;

/**
 * Date Created: June 02 2022
 * Time Created: 5:10 p.m.
 *
 * @author Kiran Hart
 */
public final class ConfirmGUI extends BaseGUI {

	private final Consumer<Boolean> confirmed;

	public ConfirmGUI(Gui parent, @NonNull final Consumer<Boolean> confirmed) {
		super(parent, Translation.GUI_CONFIRM_TITLE.getString(), 3);
		this.confirmed = confirmed;
		draw();
	}

	@Override
	protected void draw() {

		for (int i = 10; i < 13; i++)
			setButton(i, QuickItem.of(CompMaterial.RED_STAINED_GLASS_PANE, Translation.GUI_CONFIRM_ITEMS_CANCEL_NAME.getString()).lore(Translation.GUI_CONFIRM_ITEMS_CANCEL_LORE.getList()).make(), click -> this.confirmed.accept(false));

		for (int i = 14; i < 17; i++)
			setButton(i, QuickItem.of(CompMaterial.LIME_STAINED_GLASS_PANE, Translation.GUI_CONFIRM_ITEMS_CONFIRM_NAME.getString()).lore(Translation.GUI_CONFIRM_ITEMS_CONFIRM_LORE.getList()).make(), click -> this.confirmed.accept(true));


		applyBackExit();
	}
}
