package ca.tweetzy.spawners.impl.level;

import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Level;

/**
 * Date Created: May 13 2022
 * Time Created: 11:10 a.m.
 *
 * @author Kiran Hart
 */
public final class ActivationRangeLevel extends Level {

	public ActivationRangeLevel(int level, double cost, final int activationRange) {
		super(LevelOption.ACTIVATION_RANGE, level, activationRange, cost);
	}

	@Override
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void setCost(double cost) {
		this.cost = cost;
	}
}
