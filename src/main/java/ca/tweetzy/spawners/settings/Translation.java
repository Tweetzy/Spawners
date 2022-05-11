package ca.tweetzy.spawners.settings;

import ca.tweetzy.rose.utils.Common;
import ca.tweetzy.rose.utils.Replacer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;

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

	NOT_A_SPAWNER("misc.not a spawner", "&cThat block is not a spawner!"),
	UPDATED_SPAWN_TYPE("misc.updated spawn type", "&aSuccessfully set spawner's entity type to&f: &e%entity_type%"),
	PRESET_ID_TAKEN("misc.preset id taken", "&cThe preset id&f: &4%preset_id% &cis already in use."),


	MOB_NAME_ELDER_GUARDIAN("mob names.elder guardian", "Elder guardian"),
	MOB_NAME_WITHER_SKELETON("mob names.wither skeleton", "Wither skeleton"),
	MOB_NAME_STRAY("mob names.stray", "Stray"),
	MOB_NAME_HUSK("mob names.husk", "Husk"),
	MOB_NAME_ZOMBIE_VILLAGER("mob names.zombie villager", "Zombie villager"),
	MOB_NAME_SKELETON_HORSE("mob names.skeleton horse", "Skeleton horse"),
	MOB_NAME_ZOMBIE_HORSE("mob names.zombie horse", "Zombie horse"),
	MOB_NAME_DONKEY("mob names.donkey", "Donkey"),
	MOB_NAME_MULE("mob names.mule", "Mule"),
	MOB_NAME_EVOKER("mob names.evoker", "Evoker"),
	MOB_NAME_VEX("mob names.vex", "Vex"),
	MOB_NAME_VINDICATOR("mob names.vindicator", "Vindicator"),
	MOB_NAME_ILLUSIONER("mob names.illusioner", "Illusioner"),
	MOB_NAME_CREEPER("mob names.creeper", "Creeper"),
	MOB_NAME_SKELETON("mob names.skeleton", "Skeleton"),
	MOB_NAME_SPIDER("mob names.spider", "Spider"),
	MOB_NAME_GIANT("mob names.giant", "Giant"),
	MOB_NAME_ZOMBIE("mob names.zombie", "Zombie"),
	MOB_NAME_SLIME("mob names.slime", "Slime"),
	MOB_NAME_GHAST("mob names.ghast", "Ghast"),
	MOB_NAME_ZOMBIFIED_PIGLIN("mob names.zombified piglin", "Zombified piglin"),
	MOB_NAME_ENDERMAN("mob names.enderman", "Enderman"),
	MOB_NAME_CAVE_SPIDER("mob names.cave spider", "Cave spider"),
	MOB_NAME_SILVERFISH("mob names.silverfish", "Silverfish"),
	MOB_NAME_BLAZE("mob names.blaze", "Blaze"),
	MOB_NAME_MAGMA_CUBE("mob names.magma cube", "Magma cube"),
	MOB_NAME_ENDER_DRAGON("mob names.ender dragon", "Ender dragon"),
	MOB_NAME_WITHER("mob names.wither", "Wither"),
	MOB_NAME_BAT("mob names.bat", "Bat"),
	MOB_NAME_WITCH("mob names.witch", "Witch"),
	MOB_NAME_ENDERMITE("mob names.endermite", "Endermite"),
	MOB_NAME_GUARDIAN("mob names.guardian", "Guardian"),
	MOB_NAME_SHULKER("mob names.shulker", "Shulker"),
	MOB_NAME_PIG("mob names.pig", "Pig"),
	MOB_NAME_SHEEP("mob names.sheep", "Sheep"),
	MOB_NAME_COW("mob names.cow", "Cow"),
	MOB_NAME_CHICKEN("mob names.chicken", "Chicken"),
	MOB_NAME_SQUID("mob names.squid", "Squid"),
	MOB_NAME_WOLF("mob names.wolf", "Wolf"),
	MOB_NAME_MUSHROOM_COW("mob names.mushroom cow", "Mushroom cow"),
	MOB_NAME_SNOWMAN("mob names.snowman", "Snowman"),
	MOB_NAME_OCELOT("mob names.ocelot", "Ocelot"),
	MOB_NAME_IRON_GOLEM("mob names.iron golem", "Iron golem"),
	MOB_NAME_HORSE("mob names.horse", "Horse"),
	MOB_NAME_RABBIT("mob names.rabbit", "Rabbit"),
	MOB_NAME_POLAR_BEAR("mob names.polar bear", "Polar bear"),
	MOB_NAME_LLAMA("mob names.llama", "Llama"),
	MOB_NAME_PARROT("mob names.parrot", "Parrot"),
	MOB_NAME_VILLAGER("mob names.villager", "Villager"),
	MOB_NAME_TURTLE("mob names.turtle", "Turtle"),
	MOB_NAME_PHANTOM("mob names.phantom", "Phantom"),
	MOB_NAME_COD("mob names.cod", "Cod"),
	MOB_NAME_SALMON("mob names.salmon", "Salmon"),
	MOB_NAME_PUFFERFISH("mob names.pufferfish", "Pufferfish"),
	MOB_NAME_TROPICAL_FISH("mob names.tropical fish", "Tropical fish"),
	MOB_NAME_DROWNED("mob names.drowned", "Drowned"),
	MOB_NAME_DOLPHIN("mob names.dolphin", "Dolphin"),
	MOB_NAME_CAT("mob names.cat", "Cat"),
	MOB_NAME_PANDA("mob names.panda", "Panda"),
	MOB_NAME_PILLAGER("mob names.pillager", "Pillager"),
	MOB_NAME_RAVAGER("mob names.ravager", "Ravager"),
	MOB_NAME_TRADER_LLAMA("mob names.trader llama", "Trader llama"),
	MOB_NAME_WANDERING_TRADER("mob names.wandering trader", "Wandering trader"),
	MOB_NAME_FOX("mob names.fox", "Fox"),
	MOB_NAME_BEE("mob names.bee", "Bee"),
	MOB_NAME_HOGLIN("mob names.hoglin", "Hoglin"),
	MOB_NAME_PIGLIN("mob names.piglin", "Piglin"),
	MOB_NAME_STRIDER("mob names.strider", "Strider"),
	MOB_NAME_ZOGLIN("mob names.zoglin", "Zoglin"),
	MOB_NAME_PIGLIN_BRUTE("mob names.piglin brute", "Piglin brute"),
	MOB_NAME_AXOLOTL("mob names.axolotl", "Axolotl"),
	MOB_NAME_GLOW_SQUID("mob names.glow squid", "Glow squid"),
	MOB_NAME_GOAT("mob names.goat", "Goat"),


	SPAWNER_NO_OWNER("spawner.no owner", "No Owner"),
	SPAWNER_REQUIRE_SILK("spawner.requires silk touch", "&cYou need silk touch to mine spawners!"),
	SPAWNER_REQUIRE_PICKAXE("spawner.requires pickaxe", "&cYou need a pickaxe to mine spawners!"),

	// spawner error
	SPAWNER_NOT_OWNER_PLACE("spawner.not owner.place", "&cYou are not allowed to place &4%owner_name%&c's spawner!"),
	SPAWNER_NOT_OWNER_BREAK("spawner.not owner.break", "&cYou are not allowed to break &4%owner_name%&c's spawner!"),
	SPAWNER_NOT_OWNER_CHANGE_WITH_EGG("spawner.not owner.egg change", "&cYou cannot use eggs &4%owner_name%&c's spawner!"),
	SPAWNER_CANNOT_PLACE_ENTITY("spawner.entity permission.place", "&cYou are not allowed to place &4%entity_type%&c spawners!"),
	SPAWNER_CANNOT_BREAK_ENTITY("spawner.entity permission.break", "&cYou are not allowed to break &4%entity_type%&c spawners!"),
	SPAWNER_CANNOT_CHANGE_WITH_EGG("spawner.entity permission.egg change", "&cYou cannot change spawners with &4%entity_type%&c eggs!"),

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

	INPUT_NEW_PRESET_TITLE("input.new preset.title", "<GRADIENT:fc67fa>&lNew Preset</GRADIENT:f4c4f3>"),
	INPUT_NEW_PRESET_SUBTITLE("input.new preset.subtitle", "&fEnter the id for the new preset"),

	INPUT_PRESET_EDIT_TITLE("input.preset edit.title", "<GRADIENT:fc67fa>&lPreset %preset_id%</GRADIENT:f4c4f3>"),
	INPUT_PRESET_EDIT_SPAWN_DELAY("input.preset edit.spawn delay", "&fEnter new spawn delay for preset"),
	INPUT_PRESET_EDIT_SPAWN_COUNT("input.preset edit.spawn count", "&fEnter new spawn count for preset"),
	INPUT_PRESET_EDIT_MAX_NEARBY_ENTITIES("input.preset edit.max nearby entities", "&fEnter new max nearby entities for preset"),
	INPUT_PRESET_EDIT_ACTIVATION_RANGE("input.preset edit.player activation range", "&fEnter new activation range for preset"),


	/*
	============= guis =============
	 */
	GUI_ENTITY_SELECTOR_TITLE("gui.entity selector.title", "<GRADIENT:fc67fa>&lSpawners</GRADIENT:f4c4f3> &8> &7Select Entity"),
	GUI_ENTITY_SELECTOR_ITEMS_ENTITY_NAME("gui.entity selector.items.entity.name", "<GRADIENT:fc67fa>&l%entity_name%</GRADIENT:f4c4f3>"),
	GUI_ENTITY_SELECTOR_ITEMS_ENTITY_LORE("gui.entity selector.items.entity.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To select entity"
	)),

	GUI_ENTITY_SELECTOR_ITEMS_MODE_NAME("gui.entity selector.items.mode.name", "&a&lSwitch Mode"),
	GUI_ENTITY_SELECTOR_ITEMS_MODE_LORE("gui.entity selector.items.mode.lore", Arrays.asList(
			"&8Entity Behaviour",
			"",
			"&7Current&f: &e%entity_behaviour%",
			"",
			"&e&lClick &8» &7To switch mode"
	)),

	GUI_ADMIN_MAIN_TITLE("gui.admin main.title", "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &7v%plugin_version%"),
	GUI_ADMIN_MAIN_ITEMS_LEVELS_NAME("gui.admin main.items.level.name", "<GRADIENT:fc67fa>&lLevels</GRADIENT:f4c4f3>"),
	GUI_ADMIN_MAIN_ITEMS_LEVELS_LORE("gui.admin main.items.level.lore", Arrays.asList(
			"&7View all the known levels",
			"",
			"&e&lClick &8» &7To view levels"
	)),

	GUI_ADMIN_MAIN_ITEMS_SPAWNERS_NAME("gui.admin main.items.spawners.name", "<GRADIENT:fc67fa>&lSpawners</GRADIENT:f4c4f3>"),
	GUI_ADMIN_MAIN_ITEMS_SPAWNERS_LORE("gui.admin main.items.spawners.lore", Arrays.asList(
			"&7View all the known spawners",
			"",
			"&e&lClick &8» &7To view player spawners"
	)),

	GUI_ADMIN_MAIN_ITEMS_PRESETS_NAME("gui.admin main.items.spawner presets.name", "<GRADIENT:fc67fa>&LSpawner Presets</GRADIENT:f4c4f3>"),
	GUI_ADMIN_MAIN_ITEMS_PRESETS_LORE("gui.admin main.items.spawner presets.lore", Arrays.asList(
			"&7View all created spawner presets",
			"",
			"&e&lClick &8» &7To view presets"
	)),

	GUI_SPAWNER_PRESET_LIST_TITLE("gui.spawner preset list.title", "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7Presets"),
	GUI_SPAWNER_PRESET_LIST_PRESET_NAME("gui.spawner preset list.items.preset.name", "<GRADIENT:fc67fa>&l%preset_id%</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_PRESET_LIST_PRESET_LORE("gui.spawner preset list.items.preset.lore", Arrays.asList(
			"",
			"&7Entity&f: &e%entity_type%",
			"&7Level&f: &e%spawner_level%",
			"",
			"&7Spawn Delay &8» &e%spawner_spawn_delay%",
			"&7Spawn Count &8» &e%spawner_spawn_count%",
			"&7Max Nearby Entities &8» &e%spawner_max_nearby_entities%",
			"&7Activation Range &8» &e%spawner_player_activation_range%",
			"",
			"&e&lLeft Click &8» &7To edit preset",
			"&c&lPress 1 &8» &7To delete preset"
	)),

	GUI_SPAWNER_PRESET_LIST_NEW_NAME("gui.spawner preset list.items.create.name", "&a&lCreate Preset"),
	GUI_SPAWNER_PRESET_LIST_NEW_LORE("gui.spawner preset list.items.create.lore", List.of(
			"&e&lClick &8» &7To create new preset"
	)),


	GUI_SPAWNER_ADMIN_LIST_TITLE("gui.spawner admin list.title", "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7Known Spawners"),
	GUI_SPAWNER_ADMIN_LIST_SPAWNER_NAME("gui.spawner admin list.items.spawner.name", "<GRADIENT:fc67fa>&l%owner_name%'s Spawner</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_ADMIN_LIST_SPAWNER_LORE("gui.spawner admin list.items.spawner.lore", Arrays.asList(
			"",
			"&7Location&f: &e%world_name% %world_x%&f,&e %world_y%&f,&e %world_z%",
			"&7Entity&f: &e%entity_type%",
			"",
			"&7Spawn Delay &8» &e%spawner_spawn_delay%",
			"&7Spawn Count &8» &e%spawner_spawn_count%",
			"&7Max Nearby Entities &8» &e%spawner_max_nearby_entities%",
			"&7Activation Range &8» &e%spawner_player_activation_range%",
			"",
			"&e&lLeft Click &8» &7To teleport to spawner",
			"&c&lPress 1 &8» &7To delete spawner"
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

	// preset edit
	GUI_PRESET_EDIT_TITLE("gui.preset edit.title", "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8> &7Preset &8> &7%preset_id%"),
	GUI_PRESET_EDIT_ITEMS_SPAWN_DELAY_NAME("gui.preset edit.items.spawn delay.name", "<GRADIENT:fc67fa>&lSpawn Delay</GRADIENT:f4c4f3>"),
	GUI_PRESET_EDIT_ITEMS_SPAWN_DELAY_LORE("gui.preset edit.items.spawn delay.lore", Arrays.asList(
			"&8Spawner delay",
			"&7Adjust the time it takes for the",
			"&7mob spawner to activate.",
			"",
			"&7Current&f: &e%level_spawn_interval%",
			"",
			"&e&lClick &8» &7To edit interval"
	)),

	GUI_PRESET_EDIT_ITEMS_SPAWN_COUNT_NAME("gui.preset edit.items.spawn count.name", "<GRADIENT:fc67fa>&lSpawn Count</GRADIENT:f4c4f3>"),
	GUI_PRESET_EDIT_ITEMS_SPAWN_COUNT_LORE("gui.preset edit.items.spawn count.lore", Arrays.asList(
			"&8Spawner Mob Count",
			"&7Adjust the max amount of mobs that",
			"&7the spawner can spawn each run.",
			"",
			"&7Current&f: &e%level_spawn_count%",
			"",
			"&e&lClick &8» &7To edit spawn count"
	)),

	GUI_PRESET_EDIT_ITEMS_NEARBY_ENTITIES_NAME("gui.preset edit.items.nearby entities.name", "<GRADIENT:fc67fa>&lNearby Entities</GRADIENT:f4c4f3>"),
	GUI_PRESET_EDIT_ITEMS_NEARBY_ENTITIES_LORE("gui.preset edit.items.nearby entities.lore", Arrays.asList(
			"&8Max Nearby Entities",
			"&7Adjust the maximum amount of entities",
			"&7that can be near the spawner to spawner",
			"&7before it cannot spawn anymore mobs.",
			"",
			"&7Current&f: &e%level_max_nearby_entities%",
			"",
			"&e&lClick &8» &7To edit max nearby entities"
	)),

	GUI_PRESET_EDIT_ITEMS_ACTIVATION_RANGE_NAME("gui.preset edit.items.activation range.name", "<GRADIENT:fc67fa>&lActivation Range</GRADIENT:f4c4f3>"),
	GUI_PRESET_EDIT_ITEMS_ACTIVATION_RANGE_LORE("gui.preset edit.items.activation range.lore", Arrays.asList(
			"&8Player Activation Range",
			"&7Adjust the range in which a player must",
			"&7be within the spawner for it to activate.",
			"",
			"&7Current&f: &e%level_player_activation_range%",
			"",
			"&e&lClick &8» &7To edit activation range"
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

	public void send(CommandSender sender, Object... replacements) {
		Common.tell(sender, getString(replacements));
	}

	public String getString(Object... replacements) {
		return Replacer.replaceVariables(Locale.getString(this.key), replacements);
	}

	public List<String> getList(Object... replacements) {
		return Replacer.replaceVariables(Locale.getList(this.key), replacements);
	}
}
