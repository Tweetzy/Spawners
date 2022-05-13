package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date Created: May 04 2022
 * Time Created: 11:13 a.m.
 *
 * @author Kiran Hart
 */
public final class LevelManager implements Manager {

	private final List<Level> levels = Collections.synchronizedList(new ArrayList<>());



	/*
	=================== DATABASE CALLS ===================
	 */

	@Override
	public void load() {

		Spawners.getDataManager().getLevels((error, result) -> {
			if (error == null) return;
		});
	}
}