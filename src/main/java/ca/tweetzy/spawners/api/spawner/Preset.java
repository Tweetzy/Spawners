package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;
import ca.tweetzy.spawners.api.Jsonable;
import org.bukkit.entity.EntityType;

import java.util.List;

/**
 * Date Created: May 10 2022
 * Time Created: 1:03 p.m.
 *
 * @author Kiran Hart
 */
public interface Preset extends DataSync, Jsonable {

	String getId();

	EntityType getEntityType();

	List<Level> getLevels();

	void setEntityType(EntityType entityType);

	void setLevels(List<Level> levels);
}
