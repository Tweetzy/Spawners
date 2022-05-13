package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;
import org.bukkit.entity.EntityType;

/**
 * Date Created: May 10 2022
 * Time Created: 1:03 p.m.
 *
 * @author Kiran Hart
 */
public interface Preset extends DataSync {

	String getId();

	EntityType getEntityType();

	void setEntityType(EntityType entityType);
}
