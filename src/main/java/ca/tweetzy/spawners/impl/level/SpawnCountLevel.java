/*
 * Spawners
 * Copyright 2022 Kiran Hart
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package ca.tweetzy.spawners.impl.level;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;

/**
 * Date Created: May 13 2022
 * Time Created: 11:13 a.m.
 *
 * @author Kiran Hart
 */
public final class SpawnCountLevel extends Level {

	public SpawnCountLevel(int level, double cost, final int spawnCount) {
		super(LevelOption.SPAWN_COUNT, level, spawnCount, cost);
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public void sync() {
		Spawners.getDataManager().updateLevel(this, null);
	}
}
