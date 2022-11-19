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
package ca.tweetzy.spawners.settings;

import ca.tweetzy.flight.utils.Common;
import ca.tweetzy.flight.utils.Replacer;
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
	MISC_NEXT_PAGE("misc.next page", "&eNext Page"),
	MISC_PREV_PAGE("misc.prev page", "&ePrevious Page"),
	NOT_ENOUGH_MONEY("misc.not enough money", "&cYou do not have enough money!"),
	NOT_A_NUMBER("misc.not a number", "&cThat is not a valid number"),
	PLAYER_OFFLINE("misc.player not found", "&cThe player &4%player% &cis not online!"),
	MONEY_REMOVE("misc.money remove", "&c&l- $%amount%"),
	REMOVED_ENTITIES_ALL("misc.entity remove.all", "&cRemoved a total of &4%total% &cspawner mobs."),
	REMOVED_ENTITIES_ENTITY("misc.entity remove.entity", "&cRemoved a total of &4%total% %entity_type% &cspawner mobs."),
	NO_PRESETS_MADE("misc.no presets made", "&cThere are no presets currently made."),
	NO_INVENTORY_SPACE("misc.no inventory space", "&cYou do not have enough inventory space."),

	NOT_A_SPAWNER("misc.not a spawner", "&cThat block is not a spawner!"),
	UPDATED_SPAWN_TYPE("misc.updated spawn type", "&aSuccessfully set spawner's entity type to&f: &e%entity_type%"),
	PRESET_ID_TAKEN("misc.preset id taken", "&cThe preset id&f: &4%preset_id% &cis already in use."),
	NEED_TO_MAKE_LEVELS("misc.need to create levels", "&cYou need to create 1 level for each upgrade first."),
	SPAWNER_CHUNK_LIMIT_REACHED("misc.spawner per chunk limit reached", "&cYou cannot place anymore spawners within this chunk!"),
	SPAWNER_PLACE_LIMIT_REACHED("misc.spawner place limit reached", "&cYou cannot place anymore spawners!"),

	MOB_NAME_ALLAY("mob names.allay", "Allay"),
	MOB_NAME_FROG("mob names.frog", "Frog"),
	MOB_NAME_TADPOLE("mob names.tadpole", "Tadpole"),
	MOB_NAME_WARDEN("mob names.warden", "Warden"),
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
	SPAWNER_NOT_OWNER("spawner.not owner.overview", "&cYou are not the owner of this spawner!"),

	// spawner error
	SPAWNER_NOT_OWNER_PLACE("spawner.not owner.place", "&cYou are not allowed to place &4%owner_name%&c's spawner!"),
	SPAWNER_NOT_OWNER_BREAK("spawner.not owner.break", "&cYou are not allowed to break &4%owner_name%&c's spawner!"),
	SPAWNER_NOT_OWNER_CHANGE_WITH_EGG("spawner.not owner.egg change", "&cYou cannot use eggs &4%owner_name%&c's spawner!"),
	SPAWNER_CANNOT_PLACE_ENTITY("spawner.entity permission.place", "&cYou are not allowed to place &4%entity_type%&c spawners!"),
	SPAWNER_CANNOT_BREAK_ENTITY("spawner.entity permission.break", "&cYou are not allowed to break &4%entity_type%&c spawners!"),
	SPAWNER_CANNOT_CHANGE_WITH_EGG("spawner.entity permission.egg change", "&cYou cannot change spawners with &4%entity_type%&c eggs!"),
	SPAWNER_RECEIVED_OWNERSHIP("spawner.received ownership", "&2%player_name% &Agave you ownership of their spawner"),
	SPAWNER_GAVE_OWNERSHIP("spawner.gave ownership", "&aYou transferred ownership to &2%player_name%"),

	SPAWNER_MAX_DELAY("spawner.upgrade.max.delay", "&cThe spawner is already at the max spawn delay"),
	SPAWNER_MAX_SPAWN_COUNT("spawner.upgrade.max.spawn count", "&cThe spawner is already at the max spawn count"),
	SPAWNER_MAX_NEARBY_MOBS("spawner.upgrade.max.nearby mobs", "&cThe spawner is already at the max nearby mobs"),
	SPAWNER_MAX_ACTIVATION_RANGE("spawner.upgrade.max.activation range", "&cThe spawner is already at the max activation range"),

	SPAWNER_UPGRADED_DELAY("spawner.upgrade.upgraded.delay", "&aUpgraded delay from level &F%previous_level% &ato &f%current_level%"),
	SPAWNER_UPGRADED_SPAWN_COUNT("spawner.upgrade.upgraded.spawn count", "&aUpgraded spawn count from level &F%previous_level% &ato &f%current_level%"),
	SPAWNER_UPGRADED_NEARBY_MOBS("spawner.upgrade.upgraded.nearby mobs", "&aUpgraded max nearby mobs from level &F%previous_level% &ato &f%current_level%"),
	SPAWNER_UPGRADED_ACTIVATION_RANGE("spawner.upgrade.upgraded.activation range", "&aUpgraded activation range from level &F%previous_level% &ato &f%current_level%"),
	SPAWNER_UPGRADED_ENTITY_TYPE("spawner.upgrade.upgraded.entity type", "&aSuccessfully converted spawner mob to&f: &f%entity_type%"),

	NOT_ALLOWED_TO_THROW_EGG("no egg throw permission", "&cYou are not allowed to throw &4%entity_type%&c eggs!"),

	/*
	============= spawner =============
	 */
	SPAWNER_NAME("spawner.name", "&e%entity_type% Spawner"),
	SPAWNER_LORE("spawner.lore", Arrays.asList(
			"&8Owner",
			"&e%spawner_owner_name%",
			"",
			"&7Spawn Delay &8» &e%spawner_spawn_delay_level%",
			"&7Spawn Count &8» &e%spawner_spawn_count_level%",
			"&7Max Nearby Entities &8» &e%spawner_max_nearby_entities_level%",
			"&7Activation Range &8» &e%spawner_player_activation_range_level%",
			"",
			"&e&lPlace &8» &7To activate spawner"
	)),


	/*
	============= guis =============
	 */


	// PLAYER GUIS
	GUI_MAIN_TITLE("gui.main.title", "<GRADIENT:fc67fa>&lSpawners</GRADIENT:f4c4f3>"),
	GUI_MAIN_ITEMS_YOUR_SPAWNERS_NAME("gui.main.items.your spawners.name", "<GRADIENT:fc67fa>&lYour Spawners</GRADIENT:f4c4f3>"),
	GUI_MAIN_ITEMS_YOUR_SPAWNERS_LORE("gui.main.items.your spawners.lore", Arrays.asList(
			"",
			"&7Total&f: &e%total_placed_spawners%",
			"",
			"&e&lClick &8» &7To view placed spawners"
	)),

	GUI_MAIN_ITEMS_SHOP_NAME("gui.main.items.shop.name", "<GRADIENT:fc67fa>&lSpawner Shop</GRADIENT:f4c4f3>"),
	GUI_MAIN_ITEMS_SHOP_LORE("gui.main.items.shop.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To view shop"
	)),

	GUI_SPAWNER_SHOP_TITLE("gui.spawner shop.title", "<GRADIENT:fc67fa>&lSpawner Shop</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_SHOP_ITEMS_SPAWNER_NAME("gui.spawner shop.items.spawner.name", "<GRADIENT:fc67fa>&l%entity_type% Spawner</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_SHOP_ITEMS_SPAWNER_LORE("gui.spawner shop.items.spawner.lore", Arrays.asList(
			"",
			"&7Price&f: &a%shop_item_price%",
			"&7Quantity&f: &a%shop_item_qty%",
			"",
			"&e&lLevels",
			"    &7Spawn Delay&f: &a%spawn_delay_level% &f(&e%spawn_delay%&as&f)",
			"    &7Spawn Count&f: &a%spawn_count_level% &f(&e%spawn_count%&f)",
			"    &7Max Nearby Mobs&f: &a%max_nearby_entities_level% &f(&e%max_nearby_entities%&f)",
			"    &7Activation Range&f: &a%activation_range_level% &f(&e%activation_range%&f)",
			"",
			"&e&lClick &8» &7To purchase item"
	)),

	GUI_YOUR_SPAWNERS_TITLE("gui.your spawners.title", "<GRADIENT:fc67fa>&lSpawners</GRADIENT:f4c4f3> &8> &7Your Spawners"),
	GUI_YOUR_SPAWNERS_ITEMS_SPAWNER_NAME("gui.your spawners.items.spawner.name", "<GRADIENT:fc67fa>&lSpawner</GRADIENT:f4c4f3>"),
	GUI_YOUR_SPAWNERS_ITEMS_SPAWNER_LORE("gui.your spawners.items.spawner.lore", Arrays.asList(
			"",
			"&e&lLocation",
			"    &7%world_name% &F/ &7%world_x% &f/ &7%world_y% &f/ &7%world_z%",
			"",
			"&e&lLevels",
			"    &7Spawn Delay&f: &a%spawn_delay_level% &f(&e%spawn_delay%&as&f)",
			"    &7Spawn Count&f: &a%spawn_count_level% &f(&e%spawn_count%&f)",
			"    &7Max Nearby Mobs&f: &a%max_nearby_entities_level% &f(&e%max_nearby_entities%&f)",
			"    &7Activation Range&f: &a%activation_range_level% &f(&e%activation_range%&f)",
			""
	)),

	GUI_SPAWNER_MERGE_TITLE("gui.spawner merge.title", "<GRADIENT:fc67fa>&lSpawner</GRADIENT:f4c4f3> &8> &7Merge"),

	GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_WAIT("gui.spawner merge.items.status.wait", "&f&lPlace spawner in center"),
	GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_READY("gui.spawner merge.items.status.ready", "&a&lReady to begin merge"),
	GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_INVALID("gui.spawner merge.items.status.invalid", "&c&lMerge not possible"),

	GUI_SPAWNER_MERGE_ITEMS_RING_STATUS_MAX("gui.spawner merge.items.status.maxed out", "&c&lSpawner Maxed Out"),

	GUI_SPAWNER_MERGE_ITEMS_CONFIRM_BTN_NAME("gui.spawner merge.items.confirm.name", "&a&lConfirm Merge"),
	GUI_SPAWNER_MERGE_ITEMS_CONFIRM_BTN_LORE("gui.spawner merge.items.confirm.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To merge levels"
	)),

	GUI_SPAWNER_MERGE_OR_SPLIT_TITLE("gui.spawner merge or split.title", "<GRADIENT:fc67fa>&lSpawner</GRADIENT:f4c4f3> &8> &7Merge or Split?"),

	GUI_SPAWNER_MERGE_OR_SPLIT_ITEMS_MERGE_NAME("gui.spawner merge or split.items.merge.name", "<GRADIENT:fc67fa>&lMerge</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_MERGE_OR_SPLIT_ITEMS_MERGE_LORE("gui.spawner merge or split.items.merge.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To merge with another spawner"
	)),

	GUI_SPAWNER_MERGE_OR_SPLIT_ITEMS_SPLIT_NAME("gui.spawner merge or split.items.split.name", "<GRADIENT:fc67fa>&lSplit</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_MERGE_OR_SPLIT_ITEMS_SPLIT_LORE("gui.spawner merge or split.items.split.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To split spawner levels"
	)),

	GUI_SPAWNER_SPLIT_TITLE("gui.spawner split.title", "<GRADIENT:fc67fa>&lSpawner</GRADIENT:f4c4f3> &8> &7Split Levels"),
	GUI_SPAWNER_SPLIT_ITEMS_DECREASE_NAME("gui.spawner split.items.decrease.name", "&c&lDecrease"),
	GUI_SPAWNER_SPLIT_ITEMS_DECREASE_LORE("gui.spawner split.items.decrease.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To decrease amount"
	)),

	GUI_SPAWNER_SPLIT_ITEMS_INCREASE_NAME("gui.spawner split.items.increase.name", "&a&lIncrease"),
	GUI_SPAWNER_SPLIT_ITEMS_INCREASE_LORE("gui.spawner split.items.increase.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To increase amount"
	)),


	GUI_SPAWNER_SPLIT_ITEMS_DELAY_NAME("gui.spawner split.items.delay.name", "<GRADIENT:fc67fa>&lSpawn Delay</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_SPLIT_ITEMS_DELAY_LORE("gui.spawner split.items.delay.lore", Arrays.asList(
			"",
			"&7Current&f: &e%current_split_amount%",
			"&7Available&f: &e%available_split_amount%",
			""
	)),

	GUI_SPAWNER_SPLIT_ITEMS_SPAWN_COUNT_NAME("gui.spawner split.items.spawn count.name", "<GRADIENT:fc67fa>&lSpawn Count</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_SPLIT_ITEMS_SPAWN_COUNT_LORE("gui.spawner split.items.spawn count.lore", Arrays.asList(
			"",
			"&7Current&f: &e%current_split_amount%",
			"&7Available&f: &e%available_split_amount%",
			""
	)),

	GUI_SPAWNER_SPLIT_ITEMS_NEARBY_MOBS_NAME("gui.spawner split.items.nearby mobs.name", "<GRADIENT:fc67fa>&lMax Nearby Mobs</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_SPLIT_ITEMS_NEARBY_MOBS_LORE("gui.spawner split.items.nearby mobs.lore", Arrays.asList(
			"",
			"&7Current&f: &e%current_split_amount%",
			"&7Available&f: &e%available_split_amount%",
			""
	)),

	GUI_SPAWNER_SPLIT_ITEMS_ACTIVATION_RANGE_NAME("gui.spawner split.items.activation range.name", "<GRADIENT:fc67fa>&lActivation Range</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_SPLIT_ITEMS_ACTIVATION_RANGE_LORE("gui.spawner split.items.activation range.lore", Arrays.asList(
			"",
			"&7Current&f: &e%current_split_amount%",
			"&7Available&f: &e%available_split_amount%",
			""
	)),

	GUI_SPAWNER_OVERVIEW_TITLE("gui.spawner overview.title", "<GRADIENT:fc67fa>&lSpawner</GRADIENT:f4c4f3> &8> &7Overview"),
	GUI_SPAWNER_OVERVIEW_ITEMS_ENTITY_NAME("gui.spawner overview.items.entity.name", "<GRADIENT:fc67fa>&lEntity Type</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_OVERVIEW_ITEMS_ENTITY_LORE("gui.spawner overview.items.entity.lore", Arrays.asList(
			"",
			"&7Current&f: &e%entity_type%",
			"",
			"&e&lClick &8» &7To change entity"
	)),

	GUI_SPAWNER_OVERVIEW_ITEMS_MERGE_NAME("gui.spawner overview.items.merge.name", "<GRADIENT:fc67fa>&lMerge/Split Spawner</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_OVERVIEW_ITEMS_MERGE_LORE("gui.spawner overview.items.merge.lore", Arrays.asList(
			"",
			"&7Used to merge another spawner item",
			"&7levels into this spawner. Or to split",
			"&7current levels into a new spawner",
			"",
			"&e&lClick &8» &7To merge/split spawner"
	)),

	GUI_SPAWNER_OVERVIEW_ITEMS_UPGRADE_DISABLED_NAME("gui.spawner overview.items.upgrade disabled.name", "&C&LUpgrade Disabled"),
	GUI_SPAWNER_OVERVIEW_ITEMS_UPGRADE_DISABLED_LORE("gui.spawner overview.items.upgrade disabled.lore", Arrays.asList(
			"&cYou are not allowed to upgrade",
			"&cthis particular spawner!"
	)),


	GUI_SPAWNER_OVERVIEW_ITEMS_OWNER_NAME("gui.spawner overview.items.owner.name", "<GRADIENT:fc67fa>&lOwner</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_OVERVIEW_ITEMS_OWNER_LORE("gui.spawner overview.items.owner.lore", Arrays.asList(
			"",
			"&7Current&f: &e%owner_name%",
			"",
			"&e&lClick &8» &7To transfer ownership"
	)),

	GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_NAME("gui.spawner overview.items.delay.name", "<GRADIENT:fc67fa>&lSpawn Delay</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_LORE("gui.spawner overview.items.delay.lore", Arrays.asList(
			"",
			"&7Current&f: &a%level_number% &f(&e%level_value%&as&f)",
			"",
			"&e&lNext Level",
			"    &7Level&f: &a%next_level_number% &f(&e%next_level_value%&as&f)",
			"    &7Cost&f: &a$%level_upgrade_cost%",
			"",
			"&e&lClick &8» &7To upgrade"
	)),
	GUI_SPAWNER_OVERVIEW_ITEMS_DELAY_LEVEL_LORE_MAX("gui.spawner overview.items.delay.lore max", Arrays.asList(
			"",
			"&aSpawner delay is at max level!",
			""
	)),

	GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_NAME("gui.spawner overview.items.spawn count.name", "<GRADIENT:fc67fa>&lSpawn Count</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_LORE("gui.spawner overview.items.spawn count.lore", Arrays.asList(
			"",
			"&7Current&f: &a%level_number% &f(&e%level_value%&f)",
			"",
			"&e&lNext Level",
			"    &7Level&f: &a%next_level_number% &f(&e%next_level_value%&f)",
			"    &7Cost&f: &a$%level_upgrade_cost%",
			"",
			"&e&lClick &8» &7To upgrade"
	)),
	GUI_SPAWNER_OVERVIEW_ITEMS_SPAWN_COUNT_LEVEL_LORE_MAX("gui.spawner overview.items.spawn count.lore max", Arrays.asList(
			"",
			"&aSpawn count is at max level!",
			""
	)),

	GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_NAME("gui.spawner overview.items.max nearby mobs.name", "<GRADIENT:fc67fa>&lMax Nearby Mobs</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_LORE("gui.spawner overview.items.max nearby mobs.lore", Arrays.asList(
			"",
			"&7Current&f: &a%level_number% &f(&e%level_value%&f)",
			"",
			"&e&lNext Level",
			"    &7Level&f: &a%next_level_number% &f(&e%next_level_value%&f)",
			"    &7Cost&f: &a$%level_upgrade_cost%",
			"",
			"&e&lClick &8» &7To upgrade"
	)),
	GUI_SPAWNER_OVERVIEW_ITEMS_MAX_NEARBY_LEVEL_LORE_MAX("gui.spawner overview.items.max nearby mobs.lore max", Arrays.asList(
			"",
			"&aMax nearby mobs is at max level!",
			""
	)),

	GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_NAME("gui.spawner overview.items.activation range.name", "<GRADIENT:fc67fa>&lActivation Range</GRADIENT:f4c4f3>"),
	GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_LORE("gui.spawner overview.items.activation range.lore", Arrays.asList(
			"",
			"&7Current&f: &a%level_number% &f(&e%level_value%&f)",
			"",
			"&e&lNext Level",
			"    &7Level&f: &a%next_level_number% &f(&e%next_level_value%&f)",
			"    &7Cost&f: &a$%level_upgrade_cost%",
			"",
			"&e&lClick &8» &7To upgrade"
	)),
	GUI_SPAWNER_OVERVIEW_ITEMS_ACTIVATION_RANGE_LEVEL_LORE_MAX("gui.spawner overview.items.activation range.lore max", Arrays.asList(
			"",
			"&aActivation range is at max level!",
			""
	)),

	GUI_ENTITY_CHANGE_TITLE("gui.entity change.title", "<GRADIENT:fc67fa>&lSpawners</GRADIENT:f4c4f3> &8> &7Change Mob"),
	GUI_ENTITY_CHANGE_ITEMS_ENTITY_NAME("gui.entity change.items.entity.name", "<GRADIENT:fc67fa>&l%entity_name%</GRADIENT:f4c4f3>"),
	GUI_ENTITY_CHANGE_ITEMS_ENTITY_LORE("gui.entity change.items.entity.lore", Arrays.asList(
			"",
			"&7Cost&f: &a$%entity_cost%",
			"",
			"&e&lClick &8» &7To select entity"
	)),

	GUI_PLAYER_SELECT_TITLE("gui.player select.title", "<GRADIENT:fc67fa>&lSpawners</GRADIENT:f4c4f3> &8> &7Select Player"),
	GUI_PLAYER_SELECT_ITEMS_PLAYER_NAME("gui.player select.items.player.name", "<GRADIENT:fc67fa>&l%player_name%</GRADIENT:f4c4f3>"),
	GUI_PLAYER_SELECT_ITEMS_PLAYER_LORE("gui.player select.items.player.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To select player"
	)),

	GUI_CONFIRM_TITLE("gui.confirm.title", "<GRADIENT:fc67fa>&lSpawners</GRADIENT:f4c4f3> &8> &7Confirm"),
	GUI_CONFIRM_ITEMS_CANCEL_NAME("gui.confirm.items.cancel.name", "&c&lCancel"),
	GUI_CONFIRM_ITEMS_CANCEL_LORE("gui.confirm.items.cancel.lore", Arrays.asList(
			"",
			"&c&lClick &8» &7To cancel action"
	)),
	GUI_CONFIRM_ITEMS_CONFIRM_NAME("gui.confirm.items.confirm.name", "&A&LConfirm"),
	GUI_CONFIRM_ITEMS_CONFIRM_LORE("gui.confirm.items.confirm.lore", Arrays.asList(
			"",
			"&e&lClick &8» &7To confirm action",
			"&c(This cannot be undone)"
	)),

	// ADMIN GUIS

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

	GUI_ADMIN_MAIN_ITEMS_SHOP_NAME("gui.admin main.items.spawner shop.name", "<GRADIENT:fc67fa>&LSpawner Shop</GRADIENT:f4c4f3>"),
	GUI_ADMIN_MAIN_ITEMS_SHOP_LORE("gui.admin main.items.spawner shop.lore", Arrays.asList(
			"&7View all spawner shop items",
			"",
			"&e&lClick &8» &7To edit shop"
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
