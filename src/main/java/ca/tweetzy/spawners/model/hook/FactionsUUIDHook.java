/*
 * Spawners
 * Copyright 2024 Kiran Hart
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
package ca.tweetzy.spawners.model.hook;

import ca.tweetzy.spawners.api.region.AbstractRegionPlugin;
import com.massivecraft.factions.listeners.FactionsBlockListener;
import com.massivecraft.factions.perms.PermissibleActions;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public final class FactionsUUIDHook extends AbstractRegionPlugin {

	public FactionsUUIDHook() {
		super("Factions");
	}

	@Override
	public boolean canBreak(Player player, Block block) {
		if (!isEnabled()) return true;
		return FactionsBlockListener.playerCanBuildDestroyBlock(player, block.getLocation(), PermissibleActions.DESTROY, true);
	}

	@Override
	public boolean canBuild(Player player, Block block) {
		if (!isEnabled()) return true;
		return FactionsBlockListener.playerCanBuildDestroyBlock(player, block.getLocation(), PermissibleActions.BUILD, true);
	}
}
