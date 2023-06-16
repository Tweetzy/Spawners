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
package ca.tweetzy.spawners.guis.user;

import ca.tweetzy.flight.comp.enums.CompMaterial;
import ca.tweetzy.flight.gui.template.BaseGUI;
import ca.tweetzy.flight.settings.TranslationManager;
import ca.tweetzy.flight.utils.ChatUtil;
import ca.tweetzy.flight.utils.Common;
import ca.tweetzy.flight.utils.QuickItem;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.SpawnerMob;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.guis.selector.PlayerSelectorGUI;
import ca.tweetzy.spawners.guis.user.merging.MergeSplitGUI;
import ca.tweetzy.spawners.settings.Translations;
import lombok.NonNull;
import org.bukkit.block.CreatureSpawner;

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
		super(null, TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_TITLE), 5);
		this.spawner = spawner;
		this.canUpgrade = canUpgrade;
		draw();
	}

	public SpawnerOverviewGUI(@NonNull final Spawner spawner) {
		this(spawner, true);
	}

	@Override
	protected void draw() {

		if (!this.canUpgrade || Spawners.getEconomy() == null)
			Arrays.asList(10, 12, 14, 16, 29, 33).forEach(slot -> setItem(slot, QuickItem.of(CompMaterial.RED_STAINED_GLASS_PANE)
					.name(TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_UPGRADE_DISABLED_NAME))
					.lore(TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_UPGRADE_DISABLED_LORE))
					.make()
			));

		// merge
		setButton(3, 4, QuickItem
				.of(CompMaterial.PACKED_ICE)
				.name(TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_MERGE_NAME))
				.lore(TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_MERGE_LORE))
				.make(), click -> click.manager.showGUI(click.player, new MergeSplitGUI(this.spawner, this.canUpgrade)));

		if (this.canUpgrade && Spawners.getEconomy() != null) {
			// entity type
			setButton(3, 2, QuickItem
					.of(SpawnerMob.valueOf(this.spawner.getEntityType().name()).getHeadTexture())
					.name(TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_ENTITY_NAME))
					.lore(TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_ENTITY_LORE,
							"entity_type", ChatUtil.capitalizeFully(this.spawner.getEntityType())
					))
					.make(), click -> click.manager.showGUI(click.player, new EntityChangeGUI(this, selected -> {

				Spawners.getEconomy().withdrawPlayer(click.player, selected.getCost());
				Common.tell(click.player, TranslationManager.string(Translations.MONEY_REMOVE, "amount", String.format("%,.2f", selected.getCost())));
				Common.tell(click.player, TranslationManager.string(Translations.SPAWNER_UPGRADED_ENTITY_TYPE, "entity_type", ChatUtil.capitalizeFully(selected.getSpawnerMob().getEntityType())));

				final CreatureSpawner creatureSpawner = (CreatureSpawner) spawner.getLocation().getBlock().getState();
				Spawners.getSpawnerManager().changeSpawnerEntity(creatureSpawner, selected.getSpawnerMob().getEntityType());

				this.spawner.setEntityType(selected.getSpawnerMob().getEntityType());
				this.spawner.sync();
			})));

			// ownership
			setButton(3, 6, QuickItem
					.of(CompMaterial.LECTERN)
					.name(TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_OWNER_NAME))
					.lore(TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_OWNER_LORE,
							"owner_name", spawner.getOwnerName()
					))
					.make(), click -> click.manager.showGUI(click.player, new PlayerSelectorGUI(this, click.player, (selectedUser, confirmed) -> {

				if (!confirmed) {
					click.manager.showGUI(click.player, this);
					return;
				}

				this.spawner.setOwner(selectedUser.getUniqueId());
				this.spawner.setOwnerName(selectedUser.getName());
				click.player.closeInventory();

				Common.tell(click.player, TranslationManager.string(Translations.SPAWNER_RECEIVED_OWNERSHIP, "player_name", click.player.getName()));
				Common.tell(click.player, TranslationManager.string(Translations.SPAWNER_GAVE_OWNERSHIP, "player_name", selectedUser.getName()));

				this.spawner.sync();
			})));


			// todo future update
			// additional settings

			// spawn delay
			setButton(1, 1, QuickItem
					.of(CompMaterial.REPEATER)
					.name(TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_NAME))
					.lore(spawner.getNextLevel(LevelOption.SPAWN_INTERVAL) == null ? TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_LORE_MAX) : TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_LORE,
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
					.name(TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_NAME))
					.lore(spawner.getNextLevel(LevelOption.SPAWN_COUNT) == null ? TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_LORE_MAX) : TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_LORE,
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
					.name(TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_NAME))
					.lore(spawner.getNextLevel(LevelOption.MAX_NEARBY_ENTITIES) == null ? TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_LORE_MAX) : TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_LORE,
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
					.name(TranslationManager.string(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_NAME))
					.lore(spawner.getNextLevel(LevelOption.ACTIVATION_RANGE) == null ? TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_LORE_MAX) : TranslationManager.list(Translations.GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_LORE,
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
	}
}
