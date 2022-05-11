package ca.tweetzy.spawners.guis.presets;


import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.gui.events.GuiClickEvent;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.rose.gui.template.PagedGUI;
import ca.tweetzy.rose.utils.QuickItem;
import ca.tweetzy.rose.utils.input.TitleInput;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.Preset;
import ca.tweetzy.spawners.guis.SpawnersAdminGUI;
import ca.tweetzy.spawners.impl.SpawnerOptions;
import ca.tweetzy.spawners.impl.SpawnerPreset;
import ca.tweetzy.spawners.settings.Translation;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Date Created: May 10 2022
 * Time Created: 1:14 p.m.
 *
 * @author Kiran Hart
 */
public final class SpawnerPresetListGUI extends PagedGUI<Preset> {

	public SpawnerPresetListGUI() {
		super(new SpawnersAdminGUI(), Translation.GUI_SPAWNER_PRESET_LIST_TITLE.getString(), 6, Spawners.getPresetManager().getContents());
		draw();
	}

	@Override
	protected ItemStack makeDisplayItem(Preset preset) {
		return QuickItem
				.of(CompMaterial.CREEPER_BANNER_PATTERN)
				.name(Translation.GUI_SPAWNER_PRESET_LIST_PRESET_NAME.getString("preset_id", preset.getId()))
				.lore(Translation.GUI_SPAWNER_PRESET_LIST_PRESET_LORE.getList(
						"entity_type", StringUtils.capitalize(preset.getEntityType().name().toLowerCase().replace("_", " ")),
						"spawner_spawn_delay", preset.getOptions().getSpawnInterval(),
						"spawner_spawn_count", preset.getOptions().getSpawnCount(),
						"spawner_max_nearby_entities", preset.getOptions().getMaxNearbyEntities(),
						"spawner_player_activation_range", preset.getOptions().getPlayerActivationRange(),
						"spawner_level", preset.getLevel()
				))
				.hideTags(true)
				.make();
	}

	@Override
	protected void drawAdditional() {
		setButton(5, 4, QuickItem.of(CompMaterial.SLIME_BALL)
				.name(Translation.GUI_SPAWNER_PRESET_LIST_NEW_NAME.getString())
				.lore(Translation.GUI_SPAWNER_PRESET_LIST_NEW_LORE.getList())
				.make(), click -> new TitleInput(click.player, Translation.INPUT_NEW_PRESET_TITLE.getString(), Translation.INPUT_NEW_PRESET_SUBTITLE.getString()) {

			@Override
			public void onExit(Player player) {
				click.manager.showGUI(click.player, new SpawnerPresetListGUI());
			}

			@Override
			public boolean onResult(String string) {
				if (Spawners.getPresetManager().find(string) != null) {
					Translation.PRESET_ID_TAKEN.send(click.player, "preset_id", string);
					return false;
				}

				Spawners.getPresetManager().createPreset(new SpawnerPreset(string, EntityType.COW, new SpawnerOptions(), -1), (success, created) -> {
					if (success)
						click.manager.showGUI(click.player, new SpawnerPresetListGUI());
				});
				return true;
			}
		});
	}

	@Override
	protected void onClick(Preset preset, GuiClickEvent event) {
		if (event.clickType == ClickType.LEFT)
			event.manager.showGUI(event.player, new SpawnerPresetEditGUI(preset));

		if (event.clickType == ClickType.NUMBER_KEY)
			Spawners.getPresetManager().deletePreset(preset, success -> event.manager.showGUI(event.player, new SpawnerPresetListGUI()));
	}

	@Override
	protected List<Integer> fillSlots() {
		return InventoryBorder.getInsideBorders(5);
	}
}
