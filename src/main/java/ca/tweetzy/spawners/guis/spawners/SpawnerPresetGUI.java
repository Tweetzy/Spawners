package ca.tweetzy.spawners.guis.spawners;


import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.guis.SpawnersAdminGUI;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

/**
 * Date Created: May 10 2022
 * Time Created: 1:14 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerPresetGUI extends PagedGUI<Preset> {

	public SpawnerPresetGUI() {
		super(new SpawnersAdminGUI(), "title", 6, Collections.emptyList());
	}

	@Override
	protected ItemStack makeDisplayItem(Preset object) {
		return null;
	}

	@Override
	protected void onClick(Preset object, GuiClickEvent clickEvent) {

	}
}
