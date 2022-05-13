package ca.tweetzy.spawners.api.variant;

import org.bukkit.entity.EntityType;

/**
 * Date Created: May 13 2022
 * Time Created: 10:11 a.m.
 *
 * @author Kiran Hart
 */
public enum HorseVariant {

	DONKEY,
	HORSE,
	LLAMA,
	MULE,
	SKELETON_HORSE,
	UNDEAD_HORSE;

	public EntityType getCorrespondingEntityType() {
		return switch (this) {
			case DONKEY -> EntityType.DONKEY;
			case HORSE -> EntityType.HORSE;
			case LLAMA -> EntityType.LLAMA;
			case MULE -> EntityType.MULE;
			case SKELETON_HORSE -> EntityType.SKELETON_HORSE;
			case UNDEAD_HORSE -> EntityType.ZOMBIE_HORSE;
		};
	}
}
