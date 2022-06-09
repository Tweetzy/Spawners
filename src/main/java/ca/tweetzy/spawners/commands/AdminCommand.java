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
package ca.tweetzy.spawners.commands;

import ca.tweetzy.rose.command.AllowedExecutor;
import ca.tweetzy.rose.command.Command;
import ca.tweetzy.rose.command.ReturnType;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.guis.admin.SpawnersAdminGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Date Created: June 02 2022
 * Time Created: 3:51 p.m.
 *
 * @author Kiran Hart
 */
public final class AdminCommand extends Command {

	public AdminCommand() {
		super(AllowedExecutor.PLAYER, "admin");
	}

	@Override
	protected ReturnType execute(CommandSender sender, String... args) {
		if (!(sender instanceof final Player player)) return ReturnType.FAIL;
		Spawners.getGuiManager().showGUI(player, new SpawnersAdminGUI());
		return ReturnType.SUCCESS;
	}

	@Override
	protected List<String> tab(CommandSender sender, String... args) {
		return null;
	}

	@Override
	public String getPermissionNode() {
		return "spawners.command.admin";
	}

	@Override
	public String getSyntax() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Opens the administration menu";
	}
}
