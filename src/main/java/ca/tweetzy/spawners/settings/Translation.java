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
