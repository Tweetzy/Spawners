package ca.tweetzy.spawners.listeners;

import ca.tweetzy.spawners.Spawners;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Date Created: May 04 2022
 * Time Created: 4:48 p.m.
 *
 * @author Kiran Hart
 */
public final class JoinListeners implements Listener {

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();

		if (Spawners.getPlayerManager().findUser(player) == null)
			Spawners.getPlayerManager().createPlayer(player, null);
	}
}
