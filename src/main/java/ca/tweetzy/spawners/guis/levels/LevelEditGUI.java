package ca.tweetzy.spawners.guis.levels;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.template.BaseGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.api.interfaces.Level;
import ca.tweetzy.spawners.model.UserInput;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;

/**
 * Date Created: May 04 2022
 * Time Created: 9:39 p.m.
 *
 * @author Kiran Hart
 */
public final class LevelEditGUI extends BaseGUI {

	private final Level level;

	public LevelEditGUI(@NonNull final Level level) {
		super(new LevelListAdminGUI(), Translation.GUI_LEVEL_EDIT_TITLE.getString("level_number", level.getLevel()), 6);
		this.level = level;
		draw();
	}

	@Override
	protected void draw() {
		setButton(1, 1, QuickItem.of(CompMaterial.REPEATER)
				.name(Translation.GUI_LEVEL_EDIT_ITEMS_SPAWN_DELAY_NAME.getString())
				.lore(Translation.GUI_LEVEL_EDIT_ITEMS_SPAWN_DELAY_LORE.getList("level_spawn_interval", this.level.getSpawnInterval()))
				.make(), click -> UserInput.askInteger(click.player, Translation.INPUT_LEVEL_EDIT_TITLE.getString(), Translation.INPUT_LEVEL_EDIT_SPAWN_DELAY.getString(), value -> {

			this.level.setSpawnInterval(value);
			click.manager.showGUI(click.player, new LevelEditGUI(this.level));
		}));

		setButton(1, 3, QuickItem.of(CompMaterial.REDSTONE_TORCH)
				.name(Translation.GUI_LEVEL_EDIT_ITEMS_SPAWN_COUNT_NAME.getString())
				.lore(Translation.GUI_LEVEL_EDIT_ITEMS_SPAWN_COUNT_LORE.getList("level_spawn_count", this.level.getSpawnCount()))
				.make(), click -> UserInput.askInteger(click.player, Translation.INPUT_LEVEL_EDIT_TITLE.getString(), Translation.INPUT_LEVEL_EDIT_SPAWN_COUNT.getString(), value -> {

			this.level.setSpawnCount(value);
			click.manager.showGUI(click.player, new LevelEditGUI(this.level));
		}));

		setButton(1, 5, QuickItem.of(CompMaterial.OBSERVER)
				.name(Translation.GUI_LEVEL_EDIT_ITEMS_NEARBY_ENTITIES_NAME.getString())
				.lore(Translation.GUI_LEVEL_EDIT_ITEMS_NEARBY_ENTITIES_LORE.getList("level_max_nearby_entities", this.level.getMaxNearbyEntities()))
				.make(), click -> UserInput.askInteger(click.player, Translation.INPUT_LEVEL_EDIT_TITLE.getString(), Translation.INPUT_LEVEL_EDIT_MAX_NEARBY_ENTITIES.getString(), value -> {

			this.level.setMaxNearbyEntities(value);
			click.manager.showGUI(click.player, new LevelEditGUI(this.level));
		}));

		setButton(1, 7, QuickItem.of(CompMaterial.COMPASS)
				.name(Translation.GUI_LEVEL_EDIT_ITEMS_ACTIVATION_RANGE_NAME.getString())
				.lore(Translation.GUI_LEVEL_EDIT_ITEMS_ACTIVATION_RANGE_LORE.getList("level_max_nearby_entities", this.level.getPlayerActivationRange()))
				.make(), click -> UserInput.askInteger(click.player, Translation.INPUT_LEVEL_EDIT_TITLE.getString(), Translation.INPUT_LEVEL_EDIT_ACTIVATION_RANGE.getString(), value -> {

			this.level.setPlayerActivationRange(value);
			click.manager.showGUI(click.player, new LevelEditGUI(this.level));
		}));
	}
}
