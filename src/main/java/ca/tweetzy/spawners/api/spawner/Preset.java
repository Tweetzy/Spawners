package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;
import ca.tweetzy.spawners.api.Jsonable;
import ca.tweetzy.spawners.api.LevelOption;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Map;

/**
 * Date Created: May 10 2022
 * Time Created: 1:03 p.m.
 *
 * @author Kiran Hart
 */
public interface Preset extends DataSync, Jsonable {

	String getId();

	EntityType getEntityType();

	Map<LevelOption, Level> getLevels();

	void setEntityType(EntityType entityType);

	void setLevels(Map<LevelOption, Level> levels);
}
