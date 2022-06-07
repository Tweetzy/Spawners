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
package ca.tweetzy.spawners.guis.user;

import ca.tweetzy.rose.gui.template.BaseGUI;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import lombok.NonNull;

/**
 * Date Created: June 07 2022
 * Time Created: 6:27 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnersMainGUI extends BaseGUI {

	final SpawnerUser spawnerUser;

	public SpawnersMainGUI(@NonNull final SpawnerUser spawnerUser) {
		super(null, "title", 3);
		this.spawnerUser = spawnerUser;
		draw();
	}

	@Override
	protected void draw() {

	}
}
