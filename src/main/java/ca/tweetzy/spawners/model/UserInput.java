package ca.tweetzy.spawners.model;

import ca.tweetzy.rose.gui.Gui;
import ca.tweetzy.rose.utils.Common;
import ca.tweetzy.rose.utils.input.TitleInput;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.settings.Locale;
import ca.tweetzy.spawners.settings.Translation;
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

	public void askDouble(@NonNull final Player player, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<Double> input) {
		askDouble(player, null, title, subtitle, input);
	}

	public void askInteger(@NonNull final Player player, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<Integer> input) {
		askInteger(player, null, title, subtitle, input);
	}

	public void askString(@NonNull final Player player, final Gui exitGui, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<String> input) {
		player.closeInventory();

		new TitleInput(player, Common.colorize(title), Common.colorize(subtitle)) {

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

	public void askInteger(@NonNull final Player player, final Gui exitGui, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<Integer> input) {
		player.closeInventory();

		new TitleInput(player, Common.colorize(title), Common.colorize(subtitle)) {

			@Override
			public void onExit(Player player) {
				if (exitGui != null)
					Spawners.getGuiManager().showGUI(player, exitGui);
			}

			@Override
			public boolean onResult(String string) {
				if (!NumberUtils.isNumber(ChatColor.stripColor(string))) {
					Locale.tell(player, Translation.NOT_A_NUMBER.getKey());
					return false;
				}

				input.accept(Integer.parseInt(ChatColor.stripColor(string)));
				return true;
			}
		};
	}

	public void askDouble(@NonNull final Player player, final Gui exitGui, @NonNull final String title, @NonNull final String subtitle, @NonNull final Consumer<Double> input) {
		player.closeInventory();

		new TitleInput(player, Common.colorize(title), Common.colorize(subtitle)) {

			@Override
			public void onExit(Player player) {
				if (exitGui != null)
					Spawners.getGuiManager().showGUI(player, exitGui);
			}

			@Override
			public boolean onResult(String string) {
				if (!NumberUtils.isNumber(ChatColor.stripColor(string))) {
					Locale.tell(player, Translation.NOT_A_NUMBER.getKey());
					return false;
				}

				input.accept(Double.parseDouble(ChatColor.stripColor(string)));
				return true;
			}
		};
	}
}
