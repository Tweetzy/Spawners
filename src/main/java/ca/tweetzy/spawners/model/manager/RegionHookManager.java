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
package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.flight.utils.Common;
import ca.tweetzy.spawners.api.manager.ListManager;
import ca.tweetzy.spawners.api.region.AbstractRegionPlugin;
import ca.tweetzy.spawners.api.region.RegionHook;
import ca.tweetzy.spawners.model.hook.FactionsUUIDHook;
import ca.tweetzy.spawners.model.hook.TownyHook;
import ca.tweetzy.spawners.model.hook.WorldGuardHook;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public final class RegionHookManager extends ListManager<AbstractRegionPlugin> implements RegionHook {

	public RegionHookManager() {
		super("Region Hook");
	}

	@Override
	public boolean canBuild(Player player, Block block) {
		boolean canBuild = true;

		for (AbstractRegionPlugin plugin : this.managerContent) {
			if (!plugin.canBuild(player, block)) {
				canBuild = false;
				break;
			}
		}

		return canBuild;
	}

	@Override
	public boolean canBreak(Player player, Block block) {
		boolean canBreak = true;

		for (AbstractRegionPlugin plugin : this.managerContent) {
			if (!plugin.canBreak(player, block)) {
				canBreak = false;
				break;
			}
		}

		return canBreak;
	}

	@Override
	public void load() {

		if (Bukkit.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
			add(new WorldGuardHook());
			Common.log("&aInitializing WorldGuard Hook");
		}

		if (Bukkit.getServer().getPluginManager().isPluginEnabled("Towny")) {
			add(new TownyHook());
			Common.log("&aInitializing Towny Hook");
		}

		if (Bukkit.getServer().getPluginManager().isPluginEnabled("Factions")) {
			add(new FactionsUUIDHook());
			Common.log("&aInitializing FactionsUUID Hook");
		}
	}
}
