package ca.tweetzy.spawners.guis.admin.levels;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.template.BaseGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.guis.SpawnersAdminGUI;

/**
 * Date Created: May 13 2022
 * Time Created: 2:07 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelOptionSelectGUI extends BaseGUI {

	public LevelOptionSelectGUI() {
		super(new SpawnersAdminGUI(), "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7Select Option", 4);
		draw();
	}


	@Override
	protected void draw() {

		// spawn interval levels
		setButton(1,1, QuickItem
				.of(CompMaterial.REPEATER)
				.name("<GRADIENT:fc67fa>&LSpawn Interval Levels</GRADIENT:f4c4f3>")
				.lore(
						"&8All spawn interval levels",
						"&7View all created levels for",
						"&7spawner spawn intervals.",
						"",
						"&e&lClick &8» &7To view interval levels"
				)
				.make(), click -> click.manager.showGUI(click.player, new LevelListGUI(LevelOption.SPAWN_INTERVAL)));

		setButton(1,3, QuickItem
				.of(CompMaterial.TRIPWIRE_HOOK)
				.name("<GRADIENT:fc67fa>&LSpawn Count Levels</GRADIENT:f4c4f3>")
				.lore(
						"&8All spawn count levels",
						"&7View all created levels for",
						"&7spawner spawn count.",
						"",
						"&e&lClick &8» &7To view spawn count levels"
				)
				.make(), click -> click.manager.showGUI(click.player, new LevelListGUI(LevelOption.SPAWN_COUNT)));

		setButton(1,5, QuickItem
				.of(CompMaterial.OBSERVER)
				.name("<GRADIENT:fc67fa>&LMax Nearby Levels</GRADIENT:f4c4f3>")
				.lore(
						"&8All max nearby levels",
						"&7View all created levels for spawner",
						"&7max nearby entities.",
						"",
						"&e&lClick &8» &7To view max nearby levels"
				)
				.make(), click -> click.manager.showGUI(click.player, new LevelListGUI(LevelOption.MAX_NEARBY_ENTITIES)));

		setButton(1,7, QuickItem
				.of(CompMaterial.COMPARATOR)
				.name("<GRADIENT:fc67fa>&LActivation Range Levels</GRADIENT:f4c4f3>")
				.lore(
						"&8All activation range levels",
						"&7View all created levels for spawner",
						"&7max player activation range.",
						"",
						"&e&lClick &8» &7To view activation range levels"
				)
				.make(), click -> click.manager.showGUI(click.player, new LevelListGUI(LevelOption.ACTIVATION_RANGE)));

		applyBackExit();
	}
}
