package ca.tweetzy.spawners.settings;

import ca.tweetzy.rose.utils.Replacer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Date Created: April 18 2022
 * Time Created: 3:18 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
@Getter
public enum Translation {

	/*
	============= misc =============
	 */
	MISC_IS_TRUE("misc.is true", "&ATrue"),
	MISC_IS_FALSE("misc.is false", "&cFalse"),
	MISC_IS_ALLOWED("misc.is allowed", "&aAllowed"),
	MISC_IS_DISALLOWED("misc.is disallowed", "&cDisallowed"),
	NOT_ENOUGH_MONEY("misc.not enough money", "&cYou do not have enough money!"),
	NOT_A_NUMBER("misc.not a number", "&cThat is not a valid number"),
	PLAYER_OFFLINE("misc.player not found", "&cThe player &4%player% &cis not online!"),

	/*
	============= spawner =============
	 */
	SPAWNER_NAME("spawner.name", "&e%entity_type% Spawner"),
	SPAWNER_LORE("spawner.lore", Arrays.asList(
			"&8Owner",
			"&e%spawner_owner_name%",
			"",
			"&7Spawn Delay &8» &e%spawner_spawn_delay%",
			"&7Spawn Count &8» &e%spawner_spawn_count%",
			"&7Max Nearby Entities &8» &e%spawner_max_nearby_entities%",
			"&7Activation Range &8» &e%spawner_player_activation_range%",
			"",
			"&e&lPlace &8» &7To activate spawner"
	)),

	/*
	============= inputs =============
	 */
	INPUT_LEVEL_EDIT_TITLE("input.level edit.title", "<GRADIENT:fc67fa>&lLevel %level_number%</GRADIENT:f4c4f3>"),
	INPUT_LEVEL_EDIT_SPAWN_DELAY("input.level edit.spawn delay", "&fEnter new spawn delay for level"),
	INPUT_LEVEL_EDIT_SPAWN_COUNT("input.level edit.spawn count", "&fEnter new spawn count for level"),
	INPUT_LEVEL_EDIT_MAX_NEARBY_ENTITIES("input.level edit.max nearby entities", "&fEnter new max nearby entities for level"),
	INPUT_LEVEL_EDIT_ACTIVATION_RANGE("input.level edit.player activation range", "&fEnter new activation range for level"),

	/*
	============= guis =============
	 */
	GUI_ADMIN_MAIN_TITLE("gui.admin main.title", "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &7v%plugin_version%"),
	GUI_ADMIN_MAIN_ITEMS_LEVELS_NAME("gui.admin main.items.level.name", "<GRADIENT:fc67fa>&lLevels</GRADIENT:f4c4f3>"),
	GUI_ADMIN_MAIN_ITEMS_LEVELS_LORE("gui.admin main.items.level.lore", Arrays.asList(
			"&7View all the known levels",
			"",
			"&e&lClick &8» &7To view levels"
	)),

	GUI_LEVEL_ADMIN_LIST_TITLE("gui.level admin list.title", "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7Levels"),
	GUI_LEVEL_ADMIN_LIST_LEVELS_NAME("gui.level admin list.items.level.name", "<GRADIENT:fc67fa>&lLevel %level_number%</GRADIENT:f4c4f3>"),
	GUI_LEVEL_ADMIN_LIST_LEVELS_LORE("gui.level admin list.items.level.lore", Arrays.asList(
			"&8Spawner Level",
			"",
			"&7Spawn Interval&F: &e%level_spawn_interval%",
			"&7Spawn Count&F: &e%level_spawn_count%",
			"&7Max Nearby Entities&F: &e%level_max_nearby_entities%",
			"&7Player Activation Range&F: &e%level_player_activation_range%",
			"",
			"",
			"&e&lLeft Click &8» &7To edit level",
			"&c&lPress 1 &8» &7To delete level"
	)),

	GUI_LEVEL_ADMIN_LIST_NEW_NAME("gui.level admin list.items.create.name", "&a&lCreate Level"),
	GUI_LEVEL_ADMIN_LIST_NEW_LORE("gui.level admin list.items.create.lore", List.of(
			"&e&lClick &8» &7To create new level"
	)),


	// level edit
	GUI_LEVEL_EDIT_TITLE("gui.level edit.title", "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7Level &8> &7%level_number%"),
	GUI_LEVEL_EDIT_ITEMS_SPAWN_DELAY_NAME("gui.level edit.items.spawn delay.name", "<GRADIENT:fc67fa>&lSpawn Delay</GRADIENT:f4c4f3>"),
	GUI_LEVEL_EDIT_ITEMS_SPAWN_DELAY_LORE("gui.level edit.items.spawn delay.lore", Arrays.asList(
			"&8Spawner delay",
			"&7Adjust the time it takes for the",
			"&7mob spawner to activate.",
			"",
			"&7Current&f: &e%level_spawn_interval%",
			"",
			"&e&lClick &8» &7To edit interval"
	)),

	GUI_LEVEL_EDIT_ITEMS_SPAWN_COUNT_NAME("gui.level edit.items.spawn count.name", "<GRADIENT:fc67fa>&lSpawn Count</GRADIENT:f4c4f3>"),
	GUI_LEVEL_EDIT_ITEMS_SPAWN_COUNT_LORE("gui.level edit.items.spawn count.lore", Arrays.asList(
			"&8Spawner Mob Count",
			"&7Adjust the max amount of mobs that",
			"&7the spawner can spawn each run.",
			"",
			"&7Current&f: &e%level_spawn_count%",
			"",
			"&e&lClick &8» &7To edit spawn count"
	)),

	GUI_LEVEL_EDIT_ITEMS_NEARBY_ENTITIES_NAME("gui.level edit.items.nearby entities.name", "<GRADIENT:fc67fa>&lNearby Entities</GRADIENT:f4c4f3>"),
	GUI_LEVEL_EDIT_ITEMS_NEARBY_ENTITIES_LORE("gui.level edit.items.nearby entities.lore", Arrays.asList(
			"&8Max Nearby Entities",
			"&7Adjust the maximum amount of entities",
			"&7that can be near the spawner to spawner",
			"&7before it cannot spawn anymore mobs.",
			"",
			"&7Current&f: &e%level_max_nearby_entities%",
			"",
			"&e&lClick &8» &7To edit max nearby entities"
	)),

	GUI_LEVEL_EDIT_ITEMS_ACTIVATION_RANGE_NAME("gui.level edit.items.activation range.name", "<GRADIENT:fc67fa>&lActivation Range</GRADIENT:f4c4f3>"),
	GUI_LEVEL_EDIT_ITEMS_ACTIVATION_RANGE_LORE("gui.level edit.items.activation range.lore", Arrays.asList(
			"&8Player Activation Range",
			"&7Adjust the range in which a player must",
			"&7be within the spawner for it to activate.",
			"",
			"&7Current&f: &e%level_player_activation_range%",
			"",
			"&e&lClick &8» &7To edit activation range"
	)),
	;

	final String key;
	final Object value;

	public String getString(Object... replacements) {
		return Replacer.replaceVariables(Locale.getString(this.key), replacements);
	}

	public List<String> getList(Object... replacements) {
		return Replacer.replaceVariables(Locale.getList(this.key), replacements);
	}
}
