package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;
import ca.tweetzy.spawners.api.Jsonable;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

/**
 * Date Created: May 04 2022
 * Time Created: 10:44 a.m.
 *
 * @author Kiran Hart
 */
public interface Spawner extends DataSync, Jsonable {

	UUID getID();

	UUID getOwner();

	EntityType getEntityType();

	int getLevel();

	Options getOptions();

	Location getLocation();

	void setEntityType(EntityType entityType);

	void setLevel(int level);

	void setOptions(Options options);

}
