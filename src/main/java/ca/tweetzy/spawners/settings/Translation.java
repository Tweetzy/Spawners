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

	MOB_NAME_ELDER_GUARDIAN("mob names.elder guardian", "&eElder guardian"),
	MOB_NAME_WITHER_SKELETON("mob names.wither skeleton", "&eWither skeleton"),
	MOB_NAME_STRAY("mob names.stray", "&eStray"),
	MOB_NAME_HUSK("mob names.husk", "&eHusk"),
	MOB_NAME_ZOMBIE_VILLAGER("mob names.zombie villager", "&eZombie villager"),
	MOB_NAME_SKELETON_HORSE("mob names.skeleton horse", "&eSkeleton horse"),
	MOB_NAME_ZOMBIE_HORSE("mob names.zombie horse", "&eZombie horse"),
	MOB_NAME_DONKEY("mob names.donkey", "&eDonkey"),
	MOB_NAME_MULE("mob names.mule", "&eMule"),
	MOB_NAME_EVOKER("mob names.evoker", "&eEvoker"),
	MOB_NAME_VEX("mob names.vex", "&eVex"),
	MOB_NAME_VINDICATOR("mob names.vindicator", "&eVindicator"),
	MOB_NAME_ILLUSIONER("mob names.illusioner", "&eIllusioner"),
	MOB_NAME_CREEPER("mob names.creeper", "&eCreeper"),
	MOB_NAME_SKELETON("mob names.skeleton", "&eSkeleton"),
	MOB_NAME_SPIDER("mob names.spider", "&eSpider"),
	MOB_NAME_GIANT("mob names.giant", "&eGiant"),
	MOB_NAME_ZOMBIE("mob names.zombie", "&eZombie"),
	MOB_NAME_SLIME("mob names.slime", "&eSlime"),
	MOB_NAME_GHAST("mob names.ghast", "&eGhast"),
	MOB_NAME_ZOMBIFIED_PIGLIN("mob names.zombified piglin", "&eZombified piglin"),
	MOB_NAME_ENDERMAN("mob names.enderman", "&eEnderman"),
	MOB_NAME_CAVE_SPIDER("mob names.cave spider", "&eCave spider"),
	MOB_NAME_SILVERFISH("mob names.silverfish", "&eSilverfish"),
	MOB_NAME_BLAZE("mob names.blaze", "&eBlaze"),
	MOB_NAME_MAGMA_CUBE("mob names.magma cube", "&eMagma cube"),
	MOB_NAME_ENDER_DRAGON("mob names.ender dragon", "&eEnder dragon"),
	MOB_NAME_WITHER("mob names.wither", "&eWither"),
	MOB_NAME_BAT("mob names.bat", "&eBat"),
	MOB_NAME_WITCH("mob names.witch", "&eWitch"),
	MOB_NAME_ENDERMITE("mob names.endermite", "&eEndermite"),
	MOB_NAME_GUARDIAN("mob names.guardian", "&eGuardian"),
	MOB_NAME_SHULKER("mob names.shulker", "&eShulker"),
	MOB_NAME_PIG("mob names.pig", "&ePig"),
	MOB_NAME_SHEEP("mob names.sheep", "&eSheep"),
	MOB_NAME_COW("mob names.cow", "&eCow"),
	MOB_NAME_CHICKEN("mob names.chicken", "&eChicken"),
	MOB_NAME_SQUID("mob names.squid", "&eSquid"),
	MOB_NAME_WOLF("mob names.wolf", "&eWolf"),
	MOB_NAME_MUSHROOM_COW("mob names.mushroom cow", "&eMushroom cow"),
	MOB_NAME_SNOWMAN("mob names.snowman", "&eSnowman"),
	MOB_NAME_OCELOT("mob names.ocelot", "&eOcelot"),
	MOB_NAME_IRON_GOLEM("mob names.iron golem", "&eIron golem"),
	MOB_NAME_HORSE("mob names.horse", "&eHorse"),
	MOB_NAME_RABBIT("mob names.rabbit", "&eRabbit"),
	MOB_NAME_POLAR_BEAR("mob names.polar bear", "&ePolar bear"),
	MOB_NAME_LLAMA("mob names.llama", "&eLlama"),
	MOB_NAME_PARROT("mob names.parrot", "&eParrot"),
	MOB_NAME_VILLAGER("mob names.villager", "&eVillager"),
	MOB_NAME_TURTLE("mob names.turtle", "&eTurtle"),
	MOB_NAME_PHANTOM("mob names.phantom", "&ePhantom"),
	MOB_NAME_COD("mob names.cod", "&eCod"),
	MOB_NAME_SALMON("mob names.salmon", "&eSalmon"),
	MOB_NAME_PUFFERFISH("mob names.pufferfish", "&ePufferfish"),
	MOB_NAME_TROPICAL_FISH("mob names.tropical fish", "&eTropical fish"),
	MOB_NAME_DROWNED("mob names.drowned", "&eDrowned"),
	MOB_NAME_DOLPHIN("mob names.dolphin", "&eDolphin"),
	MOB_NAME_CAT("mob names.cat", "&eCat"),
	MOB_NAME_PANDA("mob names.panda", "&ePanda"),
	MOB_NAME_PILLAGER("mob names.pillager", "&ePillager"),
	MOB_NAME_RAVAGER("mob names.ravager", "&eRavager"),
	MOB_NAME_TRADER_LLAMA("mob names.trader llama", "&eTrader llama"),
	MOB_NAME_WANDERING_TRADER("mob names.wandering trader", "&eWandering trader"),
	MOB_NAME_FOX("mob names.fox", "&eFox"),
	MOB_NAME_BEE("mob names.bee", "&eBee"),
	MOB_NAME_HOGLIN("mob names.hoglin", "&eHoglin"),
	MOB_NAME_PIGLIN("mob names.piglin", "&ePiglin"),
	MOB_NAME_STRIDER("mob names.strider", "&eStrider"),
	MOB_NAME_ZOGLIN("mob names.zoglin", "&eZoglin"),
	MOB_NAME_PIGLIN_BRUTE("mob names.piglin brute", "&ePiglin brute"),
	MOB_NAME_AXOLOTL("mob names.axolotl", "&eAxolotl"),
	MOB_NAME_GLOW_SQUID("mob names.glow squid", "&eGlow squid"),
	MOB_NAME_GOAT("mob names.goat", "&eGoat"),


	SPAWNER_NO_OWNER("spawner.no owner", "No Owner"),
	SPAWNER_REQUIRE_SILK("spawner.requires silk touch", "&cYou need silk touch to mine spawners!"),
	SPAWNER_REQUIRE_PICKAXE("spawner.requires pickaxe", "&cYou need a pickaxe to mine spawners!"),

	// spawner error
	SPAWNER_NOT_OWNER_PLACE("spawner.not owner.place", "&cYou are not allowed to place &4%owner_name%&c's spawner!"),
	SPAWNER_NOT_OWNER_BREAK("spawner.not owner.break", "&cYou are not allowed to break &4%owner_name%&c's spawner!"),
	SPAWNER_CANNOT_PLACE_ENTITY("spawner.entity permission.place", "&cYou are not allowed to place &4%entity_type%&c spawners!"),
	SPAWNER_CANNOT_BREAK_ENTITY("spawner.entity permission.break", "&cYou are not allowed to break &4%entity_type%&c spawners!"),

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
