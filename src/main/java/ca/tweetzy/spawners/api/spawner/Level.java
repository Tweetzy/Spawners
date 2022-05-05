package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;

/**
 * Date Created: May 04 2022
 * Time Created: 10:54 a.m.
 *
 * @author Kiran Hart
 */
public interface Level extends Options, DataSync {

	int getLevel();
}
