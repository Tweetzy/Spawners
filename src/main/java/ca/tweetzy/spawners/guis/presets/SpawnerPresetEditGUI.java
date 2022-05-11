package ca.tweetzy.spawners.guis.presets;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.template.BaseGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.model.UserInput;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;

/**
 * Date Created: May 10 2022
 * Time Created: 9:44 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerPresetEditGUI extends BaseGUI {

	private final Preset preset;

	public SpawnerPresetEditGUI(@NonNull final Preset preset) {
		super(new SpawnerPresetListGUI(), Translation.GUI_PRESET_EDIT_TITLE.getString("preset_id", preset.getId()), 6);
		this.preset = preset;
		draw();
	}

	@Override
	protected void draw() {
		setButton(1, 1, QuickItem.of(CompMaterial.REPEATER)
				.name(Translation.GUI_PRESET_EDIT_ITEMS_SPAWN_DELAY_NAME.getString())
				.lore(Translation.GUI_PRESET_EDIT_ITEMS_SPAWN_DELAY_LORE.getList("level_spawn_interval", this.preset.getSpawner().getOptions().getSpawnInterval()))
				.make(), click -> UserInput.askInteger(click.player, Translation.INPUT_PRESET_EDIT_TITLE.getString("preset_id", this.preset.getId()), Translation.INPUT_PRESET_EDIT_SPAWN_DELAY.getString(), value -> {

			this.preset.getSpawner().getOptions().setSpawnInterval(value);
			this.preset.sync();
			click.manager.showGUI(click.player, new SpawnerPresetEditGUI(this.preset));
		}));

		setButton(1, 3, QuickItem.of(CompMaterial.REDSTONE_TORCH)
				.name(Translation.GUI_PRESET_EDIT_ITEMS_SPAWN_COUNT_NAME.getString())
				.lore(Translation.GUI_PRESET_EDIT_ITEMS_SPAWN_COUNT_LORE.getList("level_spawn_count", this.preset.getSpawner().getOptions().getSpawnCount()))
				.make(), click -> UserInput.askInteger(click.player, Translation.INPUT_PRESET_EDIT_TITLE.getString("preset_id", this.preset.getId()), Translation.INPUT_PRESET_EDIT_SPAWN_COUNT.getString(), value -> {

			this.preset.getSpawner().getOptions().setSpawnCount(value);
			this.preset.sync();
			click.manager.showGUI(click.player, new SpawnerPresetEditGUI(this.preset));
		}));

		setButton(1, 5, QuickItem.of(CompMaterial.OBSERVER)
				.name(Translation.GUI_PRESET_EDIT_ITEMS_NEARBY_ENTITIES_NAME.getString())
				.lore(Translation.GUI_PRESET_EDIT_ITEMS_NEARBY_ENTITIES_LORE.getList("level_max_nearby_entities", this.preset.getSpawner().getOptions().getMaxNearbyEntities()))
				.make(), click -> UserInput.askInteger(click.player, Translation.INPUT_PRESET_EDIT_TITLE.getString("preset_id", this.preset.getId()), Translation.INPUT_PRESET_EDIT_MAX_NEARBY_ENTITIES.getString(), value -> {

			this.preset.getSpawner().getOptions().setMaxNearbyEntities(value);
			this.preset.sync();
			click.manager.showGUI(click.player, new SpawnerPresetEditGUI(this.preset));
		}));

		setButton(1, 7, QuickItem.of(CompMaterial.COMPASS)
				.name(Translation.GUI_PRESET_EDIT_ITEMS_ACTIVATION_RANGE_NAME.getString())
				.lore(Translation.GUI_PRESET_EDIT_ITEMS_ACTIVATION_RANGE_LORE.getList("level_player_activation_range", this.preset.getSpawner().getOptions().getPlayerActivationRange()))
				.make(), click -> UserInput.askInteger(click.player, Translation.INPUT_PRESET_EDIT_TITLE.getString("preset_id", this.preset.getId()), Translation.INPUT_PRESET_EDIT_ACTIVATION_RANGE.getString(), value -> {

			this.preset.getSpawner().getOptions().setPlayerActivationRange(value);
			this.preset.sync();
			click.manager.showGUI(click.player, new SpawnerPresetEditGUI(this.preset));
		}));

		applyBackExit();
	}
}
