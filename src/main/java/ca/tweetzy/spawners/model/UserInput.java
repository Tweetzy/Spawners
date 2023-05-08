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
package ca.tweetzy.spawners.model;

import ca.tweetzy.flight.gui.Gui;
import ca.tweetzy.flight.settings.TranslationManager;
import ca.tweetzy.flight.utils.Common;
import ca.tweetzy.flight.utils.input.TitleInput;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.settings.Translations;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

/**
 * Date Created: May 04 2022
 * Time Created: 9:49 p.m.
 *
 * @author Kiran Hart
 */
@UtilityClass
public final class UserInput {

	public void askString(@NonNull final Player player, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<String> input) {
		askString(player, null, title, subtitle, input);
	}

	public void askString(@NonNull final Player player, final Gui exitGui, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<String> input) {
		player.closeInventory();

		new TitleInput(Spawners.getInstance(), player, Common.colorize(title), Common.colorize(subtitle)) {

			@Override
			public void onExit(Player player) {
				if (exitGui != null)
					Spawners.getGuiManager().showGUI(player, exitGui);
			}

			@Override
			public boolean onResult(String string) {
				input.accept(string);
				return true;
			}
		};
	}

	public void askInteger(@NonNull final Player player, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<Integer> input) {
		askInteger(player, null, title, subtitle, input);
	}

	public void askInteger(@NonNull final Player player, final Gui exitGui, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<Integer> input) {
		player.closeInventory();

		new TitleInput(Spawners.getInstance(), player, Common.colorize(title), Common.colorize(subtitle)) {

			@Override
			public void onExit(Player player) {
				if (exitGui != null)
					Spawners.getGuiManager().showGUI(player, exitGui);
			}

			@Override
			public boolean onResult(String string) {
				if (!NumberUtils.isNumber(ChatColor.stripColor(string))) {
					Common.tell(player, TranslationManager.string(Translations.NOT_A_NUMBER));
					return false;
				}

				input.accept(Integer.parseInt(ChatColor.stripColor(string)));
				return true;
			}
		};
	}

	public void askDouble(@NonNull final Player player, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<Double> input) {
		askDouble(player, null, title, subtitle, input);
	}

	public void askDouble(@NonNull final Player player, final Gui exitGui, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<Double> input) {
		player.closeInventory();

		new TitleInput(Spawners.getInstance(), player, Common.colorize(title), Common.colorize(subtitle)) {

			@Override
			public void onExit(Player player) {
				if (exitGui != null)
					Spawners.getGuiManager().showGUI(player, exitGui);
			}

			@Override
			public boolean onResult(String string) {
				if (!NumberUtils.isNumber(ChatColor.stripColor(string))) {
					Common.tell(player, TranslationManager.string(Translations.NOT_A_NUMBER));
					return false;
				}

				input.accept(Double.parseDouble(ChatColor.stripColor(string)));
				return true;
			}
		};
	}
}
