package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.LevelOption;
import lombok.AllArgsConstructor;

/**
 * Date Created: May 13 2022
 * Time Created: 11:07 a.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public abstract class Level {

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
