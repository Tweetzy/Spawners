package ca.tweetzy.spawners.commands;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.EntityType;

/**
 * Date Created: May 11 2022
 * Time Created: 12:57 p.m.
 *
 * @author Kiran Hart
 */
@UtilityClass
public final class CommandFlag {

	public <T> T get(Class<T> type, @NonNull final String flagName, T def, String... args) {
		int flagIndex = -1;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("-" + flagName)) {
				flagIndex = i;
				break;
			}
		}

		if (flagIndex == -1) return type.cast(def);

		try {
			if (type.isAssignableFrom(EntityType.class)) {
				try {
					return (T) EntityType.valueOf(args[flagIndex + 1].toUpperCase());
				} catch (IllegalArgumentException e) {
					return type.cast(def);
				}
			}

			if (type.isAssignableFrom(Integer.class)) {
				try {
					return (T) Integer.valueOf(Integer.parseInt(args[flagIndex + 1]));
				} catch (NumberFormatException e) {
					return type.cast(def);
				}
			}

			return type.cast(args[flagIndex + 1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return type.cast(def);
		}
	}
}
