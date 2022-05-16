package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;
import ca.tweetzy.spawners.api.Jsonable;
import ca.tweetzy.spawners.api.LevelOption;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Map;
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

	String getOwnerName();

	EntityType getEntityType();

	Map<LevelOption, Level> getLevels();

	Location getLocation();

	void setOwnerName(String ownerName);

	void setEntityType(EntityType entityType);

	void setLevels(Map<LevelOption, Level> levels);
}
