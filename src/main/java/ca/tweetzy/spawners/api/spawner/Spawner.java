package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;
import ca.tweetzy.spawners.api.Jsonable;
import ca.tweetzy.spawners.model.SpawnerItem;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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

	int getLevel();

	Options getOptions();

	Location getLocation();

	void setOwnerName(String ownerName);

	void setEntityType(EntityType entityType);

	void setLevel(int level);

	void setOptions(Options options);

}
