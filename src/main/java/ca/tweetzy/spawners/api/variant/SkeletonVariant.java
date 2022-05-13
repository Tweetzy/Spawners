package ca.tweetzy.spawners.api.variant;

import org.bukkit.entity.EntityType;

/**
 * Date Created: May 13 2022
 * Time Created: 10:16 a.m.
 *
 * @author Kiran Hart
 */
public enum SkeletonVariant {

	NORMAL,
	STRAY,
	WITHER;

	public EntityType getCorrespondingEntityType() {
		return switch (this) {
			case NORMAL -> EntityType.SKELETON;
			case STRAY -> EntityType.STRAY;
			case WITHER -> EntityType.WITHER_SKELETON;
		};
	}
}
