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
