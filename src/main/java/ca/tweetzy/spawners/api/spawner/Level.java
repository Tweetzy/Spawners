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
package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;
import ca.tweetzy.spawners.api.LevelOption;
import lombok.AllArgsConstructor;

/**
 * Date Created: May 13 2022
 * Time Created: 11:07 a.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public abstract class Level implements DataSync {

	protected final LevelOption levelOption;
	protected final int level;
	protected int value;
	protected double cost;

	public LevelOption getLevelOption() {
		return this.levelOption;
	}

	public int getLevelNumber() {
		return this.level;
	}

	public int getValue() {
		return this.value;
	}

	public double getCost() {
		return this.cost;
	}

	public abstract void setValue(final int value);

	public abstract void setCost(final double cost);
}
