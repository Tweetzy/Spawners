package ca.tweetzy.spawners.api.interfaces;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

/**
 * Date Created: May 04 2022
 * Time Created: 10:44 a.m.
 *
 * @author Kiran Hart
 */
public interface Spawner {

	UUID getID();

	UUID getOwner();

	EntityType getEntityType();

	int getLevel();

	Options getOptions();

	Location getLocation();

	void setEntityType();

	void setLevel(int level);

	void setOptions(Options options);
}
