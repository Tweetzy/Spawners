package ca.tweetzy.spawners.guis.user;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.template.BaseGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.settings.Translation;
import lombok.NonNull;

import java.util.Arrays;

/**
 * Date Created: May 17 2022
 * Time Created: 12:56 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerOverviewGUI extends BaseGUI {

	private final Spawner spawner;
	private final boolean canUpgrade;

	public SpawnerOverviewGUI(@NonNull final Spawner spawner, final boolean canUpgrade) {
		super(null, Translation.GUI_SPAWNER_OVERVIEW_TITLE.getString(), 6);
		this.spawner = spawner;
		this.canUpgrade = canUpgrade;
		draw();
	}

	public SpawnerOverviewGUI(@NonNull final Spawner spawner) {
		this(spawner, true);
	}

	@Override
	protected void draw() {

		if (!this.canUpgrade)
			Arrays.asList(10, 12, 14, 16).forEach(slot -> {
				setItem(slot, QuickItem.of(CompMaterial.RED_STAINED_GLASS_PANE)
						.name(Translation.GUI_SPAWNER_OVERVIEW_ITEMS_UPGRADE_DISABLED_NAME.getString())
						.lore(Translation.GUI_SPAWNER_OVERVIEW_ITEMS_UPGRADE_DISABLED_LORE.getList())
						.make()
				);
			});

		if (this.canUpgrade) {
			// spawn delay
			setButton(1, 1, QuickItem
					.of(CompMaterial.REPEATER)
					.name(Translation.GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_NAME.getString())
					.lore(spawner.getNextLevel(LevelOption.SPAWN_INTERVAL) == null ? Translation.GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_LORE_MAX.getList() : Translation.GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_LORE.getList(
							"level_number", spawner.getLevels().get(LevelOption.SPAWN_INTERVAL).getLevelNumber(),
							"level_value", String.format("%,.2f", spawner.getLevels().get(LevelOption.SPAWN_INTERVAL).getValue() / 20D),
							"level_upgrade_cost", spawner.getNextLevel(LevelOption.SPAWN_INTERVAL).getCost(),
							"next_level_number", spawner.getNextLevel(LevelOption.SPAWN_INTERVAL).getLevelNumber(),
							"next_level_value", String.format("%,.2f", spawner.getNextLevel(LevelOption.SPAWN_INTERVAL).getValue() / 20D)
					))
					.make(), click -> {
				spawner.tryUpgrade(click.player, LevelOption.SPAWN_INTERVAL);
				click.manager.showGUI(click.player, new SpawnerOverviewGUI(this.spawner));
			});

			// spawn count
			setButton(1, 3, QuickItem
					.of(CompMaterial.TRIPWIRE_HOOK)
					.name(Translation.GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_NAME.getString())
					.lore(spawner.getNextLevel(LevelOption.SPAWN_COUNT) == null ? Translation.GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_LORE_MAX.getList() : Translation.GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_LORE.getList(
							"level_number", spawner.getLevels().get(LevelOption.SPAWN_COUNT).getLevelNumber(),
							"level_value", spawner.getLevels().get(LevelOption.SPAWN_COUNT).getValue(),
							"level_upgrade_cost", spawner.getNextLevel(LevelOption.SPAWN_COUNT).getCost(),
							"next_level_number", spawner.getNextLevel(LevelOption.SPAWN_COUNT).getLevelNumber(),
							"next_level_value", spawner.getNextLevel(LevelOption.SPAWN_COUNT).getValue()
					))
					.make(), click -> {
				spawner.tryUpgrade(click.player, LevelOption.SPAWN_COUNT);
				click.manager.showGUI(click.player, new SpawnerOverviewGUI(this.spawner));
			});

			// max nearby entities
			setButton(1, 5, QuickItem
					.of(CompMaterial.OBSERVER)
					.name(Translation.GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_NAME.getString())
					.lore(spawner.getNextLevel(LevelOption.MAX_NEARBY_ENTITIES) == null ? Translation.GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_LORE_MAX.getList() : Translation.GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_LORE.getList(
							"level_number", spawner.getLevels().get(LevelOption.MAX_NEARBY_ENTITIES).getLevelNumber(),
							"level_value", spawner.getLevels().get(LevelOption.MAX_NEARBY_ENTITIES).getValue(),
							"level_upgrade_cost", spawner.getNextLevel(LevelOption.MAX_NEARBY_ENTITIES).getCost(),
							"next_level_number", spawner.getNextLevel(LevelOption.MAX_NEARBY_ENTITIES).getLevelNumber(),
							"next_level_value", spawner.getNextLevel(LevelOption.MAX_NEARBY_ENTITIES).getValue()
					))
					.make(), click -> {
				spawner.tryUpgrade(click.player, LevelOption.MAX_NEARBY_ENTITIES);
				click.manager.showGUI(click.player, new SpawnerOverviewGUI(this.spawner));
			});

			// activation range
			setButton(1, 7, QuickItem
					.of(CompMaterial.COMPARATOR)
					.name(Translation.GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_NAME.getString())
					.lore(spawner.getNextLevel(LevelOption.ACTIVATION_RANGE) == null ? Translation.GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_LORE_MAX.getList() : Translation.GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_LORE.getList(
							"level_number", spawner.getLevels().get(LevelOption.ACTIVATION_RANGE).getLevelNumber(),
							"level_value", spawner.getLevels().get(LevelOption.ACTIVATION_RANGE).getValue(),
							"level_upgrade_cost", spawner.getNextLevel(LevelOption.ACTIVATION_RANGE).getCost(),
							"next_level_number", spawner.getNextLevel(LevelOption.ACTIVATION_RANGE).getLevelNumber(),
							"next_level_value", spawner.getNextLevel(LevelOption.ACTIVATION_RANGE).getValue()
					))
					.make(), click -> {
				spawner.tryUpgrade(click.player, LevelOption.ACTIVATION_RANGE);
				click.manager.showGUI(click.player, new SpawnerOverviewGUI(this.spawner));
			});
		}

		// entity type

		// additional settings

		// owner
	}
}
