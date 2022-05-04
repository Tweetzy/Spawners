package ca.tweetzy.spawners.api.interfaces;

import java.util.List;
import java.util.UUID;

/**
 * Date Created: May 04 2022
 * Time Created: 10:56 a.m.
 *
 * @author Kiran Hart
 */
public interface SpawnerUser {

	UUID getUUID();

	String getName();

	List<UUID> getPlacedSpawners();
}
