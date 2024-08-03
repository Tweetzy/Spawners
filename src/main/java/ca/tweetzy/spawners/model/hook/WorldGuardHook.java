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
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public final class WorldGuardHook extends AbstractRegionPlugin {

	public WorldGuardHook() {
		super("WorldGuard");
	}

	@Override
	public boolean canBreak(Player player, Block block) {
		if (!isEnabled()) return true;

		final LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
		if (hasBypassPermission(localPlayer)) return true;

		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionQuery query = container.createQuery();

		return query.testState(new Location(localPlayer.getWorld(), block.getX(), block.getY(), block.getZ()), localPlayer, Flags.BLOCK_BREAK);
	}

	@Override
	public boolean canBuild(Player player, Block block) {
		if (!isEnabled()) return true;

		final LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
		if (hasBypassPermission(localPlayer)) return true;

		RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
		return query.testState(BukkitAdapter.adapt(block.getLocation()), localPlayer, Flags.BUILD);
	}

	private boolean hasBypassPermission(LocalPlayer localPlayer) {
		return WorldGuard.getInstance().getPlatform().getSessionManager().hasBypass(localPlayer, localPlayer.getWorld());
	}
}
