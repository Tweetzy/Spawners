package ca.tweetzy.spawners.model;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Date Created: May 05 2022
 * Time Created: 10:34 a.m.
 *
 * @author Kiran Hart
 */
@UtilityClass
public final class Serialize {

	public String serializeLocation(final Location location) {
		if (location == null)
			return "";

		return String.format(
				"%s %f %f %f %f %f",
				location.getWorld().getName(),
				location.getX(),
				location.getY(),
				location.getZ(),
				location.getYaw(),
				location.getPitch()
		);
	}

	public Location deserializeLocation(final String raw) {


		final String[] split = raw.split(" ");

		return new Location(
				Bukkit.getWorld(split[0]),
				Double.parseDouble(split[1]),
				Double.parseDouble(split[2]),
				Double.parseDouble(split[3]),
				Float.parseFloat(split[4]),
				Float.parseFloat(split[5])
		);
	}
}
