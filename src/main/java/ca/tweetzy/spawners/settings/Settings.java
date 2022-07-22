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

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.files.ConfigSetting;
import ca.tweetzy.rose.files.file.YamlFile;
import ca.tweetzy.rose.gui.helper.InventoryBorder;
import ca.tweetzy.spawners.Spawners;
import lombok.SneakyThrows;
import org.bukkit.entity.EntityType;

import java.util.List;

/**
 * Date Created: May 03 2022
 * Time Created: 4:53 p.m.
 *
 * @author Kiran Hart
 */
public final class Settings {

	static final YamlFile config = Spawners.getInstance().getCoreConfig();

	public static final ConfigSetting PREFIX = new ConfigSetting(config, "prefix", "<GRADIENT:fc67fa>&LSpawners</GRADIENT:f4c4f3> &8»", "The global prefix for the plugin");
	public static final ConfigSetting LANGUAGE = new ConfigSetting(config, "language", "english", "The default language for the plugin");

	public static final ConfigSetting SPAWNER_ITEM = new ConfigSetting(config, "spawner item", CompMaterial.SPAWNER.name(), "This should probably be a block if you decide to change it..");
	public static final ConfigSetting ALLOW_NON_OWNER_PLACE = new ConfigSetting(config, "allow non owner place", false, "If true, player's who don't own the spawner item/block can still place it");
	public static final ConfigSetting ALLOW_NON_OWNER_BREAK = new ConfigSetting(config, "allow non owner break", false, "If true, player's who don't own the spawner item/block can still break it");
	public static final ConfigSetting ENABLE_SHOP = new ConfigSetting(config, "enable shop", true, "If true, the shop icon will appear in /spawners for users to buy spawners (items need to be added first)");

	public static final ConfigSetting SPAWNER_REQUIRE_REDSTONE_SIGNAL = new ConfigSetting(config, "redstone.spawners require redstone signal", false, "If true, if a Spawners' spawner doesn't have a redstone signal", "it will not be able to spawn anything.");

	public static final ConfigSetting MAX_SPAWNER_PER_CHUNK_ENABLED = new ConfigSetting(config, "max spawners per chunk.enabled", true, "If true, spawners will limit the amount of spawners within a chunk");
	public static final ConfigSetting MAX_SPAWNER_PER_CHUNK = new ConfigSetting(config, "max spawners per chunk.max", 16, "If max spawners per chunk.enabled is enabled, it will use this limit");
	public static final ConfigSetting MAX_SPAWNER_PER_PLAYER = new ConfigSetting(config, "max spawners per player", 16, "Can be overridden using the perm: spawners.maxplace.#, # being the new total");

	public static final ConfigSetting DEFAULT_SPAWNER_ENTITY = new ConfigSetting(config, "spawner defaults.entity", EntityType.PIG.name(), "For non Spawners spawners, what should the default mob be when placed");
	public static final ConfigSetting DEFAULT_SPAWNER_DELAY = new ConfigSetting(config, "spawner defaults.delay", 20 * 12);
	public static final ConfigSetting DEFAULT_SPAWNER_SPAWN_COUNT = new ConfigSetting(config, "spawner defaults.spawn count", 4);
	public static final ConfigSetting DEFAULT_SPAWNER_ACTIVATION_RANGE = new ConfigSetting(config, "spawner defaults.activation range", 16);
	public static final ConfigSetting DEFAULT_SPAWNER_MAX_NEARBY_ENTITIES = new ConfigSetting(config, "spawner defaults.max nearby entities", 12);
	public static final ConfigSetting DEFAULT_LEVEL_COST = new ConfigSetting(config, "spawner defaults.default level cost", 1000D);

	public static final ConfigSetting ALLOW_SPAWNER_CHANGE_WITH_EGG = new ConfigSetting(config, "eggs.allow spawner change", true, "If a player right clicks a spawner with a spawn egg, should it update the entity?", "Normal ownership rules will apply");
	public static final ConfigSetting REMOVE_EGG_ON_SPAWNER_CHANGE = new ConfigSetting(config, "eggs.remove egg on spawner change", true, "Should the egg be used when they apply it to the spawner?");
	public static final ConfigSetting ALLOW_SPAWN_EGG_THROW = new ConfigSetting(config, "eggs.allow spawn egg throw", true, "Should players be able to throw spawn eggs?");

	public static final ConfigSetting ASSIGN_OWNER_TO_NATURAL = new ConfigSetting(config, "assign owner to natural", true, "When a natural spawner is broken, should the miner become the owner?");

	public static final ConfigSetting MINE_DROP_CHANCE = new ConfigSetting(config, "mine.drop chance", 50D, "Chance for the spawner to drop when mined");
	public static final ConfigSetting MINE_REQUIRES_SILK_TOUCH = new ConfigSetting(config, "mine.requires silk touch", true, "Should silk touch be required to mine spawners?");

	public static final ConfigSetting EXPLOSION_DROP_ENABLED = new ConfigSetting(config, "explosion.drop enabled", true, "Should spawners break during an explosion");
	public static final ConfigSetting EXPLOSION_DROP_CHANCE = new ConfigSetting(config, "explosion.drop chance", 25D, "The chance for the spawner to drop if exploded by tnt");
	public static final ConfigSetting EXPLOSION_RESETS_OWNER = new ConfigSetting(config, "explosion.reset owner", true, "If the spawner is dropped by explosion, should the owner be reset?");
	public static final ConfigSetting EXPLOSION_PREVENT_UNKNOWN_SOURCE = new ConfigSetting(config, "explosion.prevent unknown source", true, "If the spawner is broken due to an unknown explosion source, should it cancel?");

	public static final ConfigSetting GUI_ENTITY_CHANGE_ROWS = new ConfigSetting(config, "gui.entity change.rows", 6, "The size (rows, min = 2, max = 6) of the spawner mob change gui");
	public static final ConfigSetting GUI_ENTITY_CHANGE_BG = new ConfigSetting(config, "gui.entity change.background", CompMaterial.BLACK_STAINED_GLASS_PANE.name(), "The default background material");
	public static final ConfigSetting GUI_ENTITY_CHANGE_FILL_SLOTS = new ConfigSetting(config, "gui.entity change.fill slots", InventoryBorder.getInsideBorders(5), "The slots in which mob heads will be placed into");
	public static final ConfigSetting GUI_ENTITY_CHANGE_FILL_DECORATION = new ConfigSetting(config, "gui.entity change.decoration", List.of("0:BLACK_STAINED_GLASS_PANE"), "Option Decoration items (slot:material_name)");

	public static final ConfigSetting GUI_PLAYER_SELECT_ROWS = new ConfigSetting(config, "gui.player select.rows", 6, "The size (rows, min = 2, max = 6) of the player select change gui");
	public static final ConfigSetting GUI_PLAYER_SELECT_BG = new ConfigSetting(config, "gui.player select.background", CompMaterial.BLACK_STAINED_GLASS_PANE.name(), "The default background material");
	public static final ConfigSetting GUI_PLAYER_SELECT_FILL_SLOTS = new ConfigSetting(config, "gui.player select.fill slots", InventoryBorder.getInsideBorders(5), "The slots in which player heads will be placed into");
	public static final ConfigSetting GUI_PLAYER_SELECT_FILL_DECORATION = new ConfigSetting(config, "gui.player select.decoration", List.of("0:BLACK_STAINED_GLASS_PANE"), "Option Decoration items (slot:material_name)");

	public static final ConfigSetting GUI_MAIN_ROWS = new ConfigSetting(config, "gui.main.rows", 6, "The size (rows, min = 2, max = 6) of the main player gui");
	public static final ConfigSetting GUI_MAIN_BG = new ConfigSetting(config, "gui.main.background", CompMaterial.BLACK_STAINED_GLASS_PANE.name(), "The default background material");
	public static final ConfigSetting GUI_MAIN_FILL_DECORATION = new ConfigSetting(config, "gui.main.decoration", List.of("0:BLACK_STAINED_GLASS_PANE"), "Option Decoration items (slot:material_name)");

	public static final ConfigSetting GUI_YOUR_SPAWNERS_ROWS = new ConfigSetting(config, "gui.your spawners.rows", 6, "The size (rows, min = 2, max = 6)");
	public static final ConfigSetting GUI_YOUR_SPAWNERS_BG = new ConfigSetting(config, "gui.your spawners.background", CompMaterial.BLACK_STAINED_GLASS_PANE.name(), "The default background material");
	public static final ConfigSetting GUI_YOUR_SPAWNERS_FILL_SLOTS = new ConfigSetting(config, "gui.your spawners.fill slots", InventoryBorder.getInsideBorders(5), "The slots in which items will be placed into");
	public static final ConfigSetting GUI_YOUR_SPAWNERS_FILL_DECORATION = new ConfigSetting(config, "gui.your spawners.decoration", List.of("0:BLACK_STAINED_GLASS_PANE"), "Option Decoration items (slot:material_name)");

	public static final ConfigSetting GUI_SHOP_ROWS = new ConfigSetting(config, "gui.spawner shop.rows", 6, "The size (rows, min = 2, max = 6)");
	public static final ConfigSetting GUI_SHOP_BG = new ConfigSetting(config, "gui.spawner shop.background", CompMaterial.BLACK_STAINED_GLASS_PANE.name(), "The default background material");
	public static final ConfigSetting GUI_SHOP_FILL_SLOTS = new ConfigSetting(config, "gui.spawner shop.fill slots", InventoryBorder.getInsideBorders(5), "The slots in which items will be placed into");
	public static final ConfigSetting GUI_SHOP_FILL_DECORATION = new ConfigSetting(config, "gui.spawner shop.decoration", List.of("0:BLACK_STAINED_GLASS_PANE"), "Option Decoration items (slot:material_name)");


	public static final ConfigSetting MOB_CHANGE_ELDER_GUARDIAN_ENABLED = new ConfigSetting(config, "mob change.elder guardian.enabled", true, "Should players be able to set their spawner to a Elder Guardian");
	public static final ConfigSetting MOB_CHANGE_ELDER_GUARDIAN_COST = new ConfigSetting(config, "mob change.elder guardian.cost", 5000D, "The cost to switch the spawner to a Elder Guardian");
	public static final ConfigSetting MOB_CHANGE_WITHER_SKELETON_ENABLED = new ConfigSetting(config, "mob change.wither skeleton.enabled", true, "Should players be able to set their spawner to a Wither Skeleton");
	public static final ConfigSetting MOB_CHANGE_WITHER_SKELETON_COST = new ConfigSetting(config, "mob change.wither skeleton.cost", 5000D, "The cost to switch the spawner to a Wither Skeleton");
	public static final ConfigSetting MOB_CHANGE_STRAY_ENABLED = new ConfigSetting(config, "mob change.stray.enabled", true, "Should players be able to set their spawner to a Stray");
	public static final ConfigSetting MOB_CHANGE_STRAY_COST = new ConfigSetting(config, "mob change.stray.cost", 5000D, "The cost to switch the spawner to a Stray");
	public static final ConfigSetting MOB_CHANGE_HUSK_ENABLED = new ConfigSetting(config, "mob change.husk.enabled", true, "Should players be able to set their spawner to a Husk");
	public static final ConfigSetting MOB_CHANGE_HUSK_COST = new ConfigSetting(config, "mob change.husk.cost", 5000D, "The cost to switch the spawner to a Husk");
	public static final ConfigSetting MOB_CHANGE_ZOMBIE_VILLAGER_ENABLED = new ConfigSetting(config, "mob change.zombie villager.enabled", true, "Should players be able to set their spawner to a Zombie Villager");
	public static final ConfigSetting MOB_CHANGE_ZOMBIE_VILLAGER_COST = new ConfigSetting(config, "mob change.zombie villager.cost", 5000D, "The cost to switch the spawner to a Zombie Villager");
	public static final ConfigSetting MOB_CHANGE_SKELETON_HORSE_ENABLED = new ConfigSetting(config, "mob change.skeleton horse.enabled", true, "Should players be able to set their spawner to a Skeleton Horse");
	public static final ConfigSetting MOB_CHANGE_SKELETON_HORSE_COST = new ConfigSetting(config, "mob change.skeleton horse.cost", 5000D, "The cost to switch the spawner to a Skeleton Horse");
	public static final ConfigSetting MOB_CHANGE_ZOMBIE_HORSE_ENABLED = new ConfigSetting(config, "mob change.zombie horse.enabled", true, "Should players be able to set their spawner to a Zombie Horse");
	public static final ConfigSetting MOB_CHANGE_ZOMBIE_HORSE_COST = new ConfigSetting(config, "mob change.zombie horse.cost", 5000D, "The cost to switch the spawner to a Zombie Horse");
	public static final ConfigSetting MOB_CHANGE_DONKEY_ENABLED = new ConfigSetting(config, "mob change.donkey.enabled", true, "Should players be able to set their spawner to a Donkey");
	public static final ConfigSetting MOB_CHANGE_DONKEY_COST = new ConfigSetting(config, "mob change.donkey.cost", 5000D, "The cost to switch the spawner to a Donkey");
	public static final ConfigSetting MOB_CHANGE_MULE_ENABLED = new ConfigSetting(config, "mob change.mule.enabled", true, "Should players be able to set their spawner to a Mule");
	public static final ConfigSetting MOB_CHANGE_MULE_COST = new ConfigSetting(config, "mob change.mule.cost", 5000D, "The cost to switch the spawner to a Mule");
	public static final ConfigSetting MOB_CHANGE_EVOKER_ENABLED = new ConfigSetting(config, "mob change.evoker.enabled", true, "Should players be able to set their spawner to a Evoker");
	public static final ConfigSetting MOB_CHANGE_EVOKER_COST = new ConfigSetting(config, "mob change.evoker.cost", 5000D, "The cost to switch the spawner to a Evoker");
	public static final ConfigSetting MOB_CHANGE_VEX_ENABLED = new ConfigSetting(config, "mob change.vex.enabled", true, "Should players be able to set their spawner to a Vex");
	public static final ConfigSetting MOB_CHANGE_VEX_COST = new ConfigSetting(config, "mob change.vex.cost", 5000D, "The cost to switch the spawner to a Vex");
	public static final ConfigSetting MOB_CHANGE_VINDICATOR_ENABLED = new ConfigSetting(config, "mob change.vindicator.enabled", true, "Should players be able to set their spawner to a Vindicator");
	public static final ConfigSetting MOB_CHANGE_VINDICATOR_COST = new ConfigSetting(config, "mob change.vindicator.cost", 5000D, "The cost to switch the spawner to a Vindicator");
	public static final ConfigSetting MOB_CHANGE_ILLUSIONER_ENABLED = new ConfigSetting(config, "mob change.illusioner.enabled", true, "Should players be able to set their spawner to a Illusioner");
	public static final ConfigSetting MOB_CHANGE_ILLUSIONER_COST = new ConfigSetting(config, "mob change.illusioner.cost", 5000D, "The cost to switch the spawner to a Illusioner");
	public static final ConfigSetting MOB_CHANGE_CREEPER_ENABLED = new ConfigSetting(config, "mob change.creeper.enabled", true, "Should players be able to set their spawner to a Creeper");
	public static final ConfigSetting MOB_CHANGE_CREEPER_COST = new ConfigSetting(config, "mob change.creeper.cost", 5000D, "The cost to switch the spawner to a Creeper");
	public static final ConfigSetting MOB_CHANGE_SKELETON_ENABLED = new ConfigSetting(config, "mob change.skeleton.enabled", true, "Should players be able to set their spawner to a Skeleton");
	public static final ConfigSetting MOB_CHANGE_SKELETON_COST = new ConfigSetting(config, "mob change.skeleton.cost", 5000D, "The cost to switch the spawner to a Skeleton");
	public static final ConfigSetting MOB_CHANGE_SPIDER_ENABLED = new ConfigSetting(config, "mob change.spider.enabled", true, "Should players be able to set their spawner to a Spider");
	public static final ConfigSetting MOB_CHANGE_SPIDER_COST = new ConfigSetting(config, "mob change.spider.cost", 5000D, "The cost to switch the spawner to a Spider");
	public static final ConfigSetting MOB_CHANGE_GIANT_ENABLED = new ConfigSetting(config, "mob change.giant.enabled", true, "Should players be able to set their spawner to a Giant");
	public static final ConfigSetting MOB_CHANGE_GIANT_COST = new ConfigSetting(config, "mob change.giant.cost", 5000D, "The cost to switch the spawner to a Giant");
	public static final ConfigSetting MOB_CHANGE_ZOMBIE_ENABLED = new ConfigSetting(config, "mob change.zombie.enabled", true, "Should players be able to set their spawner to a Zombie");
	public static final ConfigSetting MOB_CHANGE_ZOMBIE_COST = new ConfigSetting(config, "mob change.zombie.cost", 5000D, "The cost to switch the spawner to a Zombie");
	public static final ConfigSetting MOB_CHANGE_SLIME_ENABLED = new ConfigSetting(config, "mob change.slime.enabled", true, "Should players be able to set their spawner to a Slime");
	public static final ConfigSetting MOB_CHANGE_SLIME_COST = new ConfigSetting(config, "mob change.slime.cost", 5000D, "The cost to switch the spawner to a Slime");
	public static final ConfigSetting MOB_CHANGE_GHAST_ENABLED = new ConfigSetting(config, "mob change.ghast.enabled", true, "Should players be able to set their spawner to a Ghast");
	public static final ConfigSetting MOB_CHANGE_GHAST_COST = new ConfigSetting(config, "mob change.ghast.cost", 5000D, "The cost to switch the spawner to a Ghast");
	public static final ConfigSetting MOB_CHANGE_ZOMBIFIED_PIGLIN_ENABLED = new ConfigSetting(config, "mob change.zombified piglin.enabled", true, "Should players be able to set their spawner to a Zombified Piglin");
	public static final ConfigSetting MOB_CHANGE_ZOMBIFIED_PIGLIN_COST = new ConfigSetting(config, "mob change.zombified piglin.cost", 5000D, "The cost to switch the spawner to a Zombified Piglin");
	public static final ConfigSetting MOB_CHANGE_ENDERMAN_ENABLED = new ConfigSetting(config, "mob change.enderman.enabled", true, "Should players be able to set their spawner to a Enderman");
	public static final ConfigSetting MOB_CHANGE_ENDERMAN_COST = new ConfigSetting(config, "mob change.enderman.cost", 5000D, "The cost to switch the spawner to a Enderman");
	public static final ConfigSetting MOB_CHANGE_CAVE_SPIDER_ENABLED = new ConfigSetting(config, "mob change.cave spider.enabled", true, "Should players be able to set their spawner to a Cave Spider");
	public static final ConfigSetting MOB_CHANGE_CAVE_SPIDER_COST = new ConfigSetting(config, "mob change.cave spider.cost", 5000D, "The cost to switch the spawner to a Cave Spider");
	public static final ConfigSetting MOB_CHANGE_SILVERFISH_ENABLED = new ConfigSetting(config, "mob change.silverfish.enabled", true, "Should players be able to set their spawner to a Silverfish");
	public static final ConfigSetting MOB_CHANGE_SILVERFISH_COST = new ConfigSetting(config, "mob change.silverfish.cost", 5000D, "The cost to switch the spawner to a Silverfish");
	public static final ConfigSetting MOB_CHANGE_BLAZE_ENABLED = new ConfigSetting(config, "mob change.blaze.enabled", true, "Should players be able to set their spawner to a Blaze");
	public static final ConfigSetting MOB_CHANGE_BLAZE_COST = new ConfigSetting(config, "mob change.blaze.cost", 5000D, "The cost to switch the spawner to a Blaze");
	public static final ConfigSetting MOB_CHANGE_MAGMA_CUBE_ENABLED = new ConfigSetting(config, "mob change.magma cube.enabled", true, "Should players be able to set their spawner to a Magma Cube");
	public static final ConfigSetting MOB_CHANGE_MAGMA_CUBE_COST = new ConfigSetting(config, "mob change.magma cube.cost", 5000D, "The cost to switch the spawner to a Magma Cube");
	public static final ConfigSetting MOB_CHANGE_ENDER_DRAGON_ENABLED = new ConfigSetting(config, "mob change.ender dragon.enabled", true, "Should players be able to set their spawner to a Ender Dragon");
	public static final ConfigSetting MOB_CHANGE_ENDER_DRAGON_COST = new ConfigSetting(config, "mob change.ender dragon.cost", 5000D, "The cost to switch the spawner to a Ender Dragon");
	public static final ConfigSetting MOB_CHANGE_WITHER_ENABLED = new ConfigSetting(config, "mob change.wither.enabled", true, "Should players be able to set their spawner to a Wither");
	public static final ConfigSetting MOB_CHANGE_WITHER_COST = new ConfigSetting(config, "mob change.wither.cost", 5000D, "The cost to switch the spawner to a Wither");
	public static final ConfigSetting MOB_CHANGE_BAT_ENABLED = new ConfigSetting(config, "mob change.bat.enabled", true, "Should players be able to set their spawner to a Bat");
	public static final ConfigSetting MOB_CHANGE_BAT_COST = new ConfigSetting(config, "mob change.bat.cost", 5000D, "The cost to switch the spawner to a Bat");
	public static final ConfigSetting MOB_CHANGE_WITCH_ENABLED = new ConfigSetting(config, "mob change.witch.enabled", true, "Should players be able to set their spawner to a Witch");
	public static final ConfigSetting MOB_CHANGE_WITCH_COST = new ConfigSetting(config, "mob change.witch.cost", 5000D, "The cost to switch the spawner to a Witch");
	public static final ConfigSetting MOB_CHANGE_ENDERMITE_ENABLED = new ConfigSetting(config, "mob change.endermite.enabled", true, "Should players be able to set their spawner to a Endermite");
	public static final ConfigSetting MOB_CHANGE_ENDERMITE_COST = new ConfigSetting(config, "mob change.endermite.cost", 5000D, "The cost to switch the spawner to a Endermite");
	public static final ConfigSetting MOB_CHANGE_GUARDIAN_ENABLED = new ConfigSetting(config, "mob change.guardian.enabled", true, "Should players be able to set their spawner to a Guardian");
	public static final ConfigSetting MOB_CHANGE_GUARDIAN_COST = new ConfigSetting(config, "mob change.guardian.cost", 5000D, "The cost to switch the spawner to a Guardian");
	public static final ConfigSetting MOB_CHANGE_SHULKER_ENABLED = new ConfigSetting(config, "mob change.shulker.enabled", true, "Should players be able to set their spawner to a Shulker");
	public static final ConfigSetting MOB_CHANGE_SHULKER_COST = new ConfigSetting(config, "mob change.shulker.cost", 5000D, "The cost to switch the spawner to a Shulker");
	public static final ConfigSetting MOB_CHANGE_PIG_ENABLED = new ConfigSetting(config, "mob change.pig.enabled", true, "Should players be able to set their spawner to a Pig");
	public static final ConfigSetting MOB_CHANGE_PIG_COST = new ConfigSetting(config, "mob change.pig.cost", 5000D, "The cost to switch the spawner to a Pig");
	public static final ConfigSetting MOB_CHANGE_SHEEP_ENABLED = new ConfigSetting(config, "mob change.sheep.enabled", true, "Should players be able to set their spawner to a Sheep");
	public static final ConfigSetting MOB_CHANGE_SHEEP_COST = new ConfigSetting(config, "mob change.sheep.cost", 5000D, "The cost to switch the spawner to a Sheep");
	public static final ConfigSetting MOB_CHANGE_COW_ENABLED = new ConfigSetting(config, "mob change.cow.enabled", true, "Should players be able to set their spawner to a Cow");
	public static final ConfigSetting MOB_CHANGE_COW_COST = new ConfigSetting(config, "mob change.cow.cost", 5000D, "The cost to switch the spawner to a Cow");
	public static final ConfigSetting MOB_CHANGE_CHICKEN_ENABLED = new ConfigSetting(config, "mob change.chicken.enabled", true, "Should players be able to set their spawner to a Chicken");
	public static final ConfigSetting MOB_CHANGE_CHICKEN_COST = new ConfigSetting(config, "mob change.chicken.cost", 5000D, "The cost to switch the spawner to a Chicken");
	public static final ConfigSetting MOB_CHANGE_SQUID_ENABLED = new ConfigSetting(config, "mob change.squid.enabled", true, "Should players be able to set their spawner to a Squid");
	public static final ConfigSetting MOB_CHANGE_SQUID_COST = new ConfigSetting(config, "mob change.squid.cost", 5000D, "The cost to switch the spawner to a Squid");
	public static final ConfigSetting MOB_CHANGE_WOLF_ENABLED = new ConfigSetting(config, "mob change.wolf.enabled", true, "Should players be able to set their spawner to a Wolf");
	public static final ConfigSetting MOB_CHANGE_WOLF_COST = new ConfigSetting(config, "mob change.wolf.cost", 5000D, "The cost to switch the spawner to a Wolf");
	public static final ConfigSetting MOB_CHANGE_MUSHROOM_COW_ENABLED = new ConfigSetting(config, "mob change.mushroom cow.enabled", true, "Should players be able to set their spawner to a Mushroom Cow");
	public static final ConfigSetting MOB_CHANGE_MUSHROOM_COW_COST = new ConfigSetting(config, "mob change.mushroom cow.cost", 5000D, "The cost to switch the spawner to a Mushroom Cow");
	public static final ConfigSetting MOB_CHANGE_SNOWMAN_ENABLED = new ConfigSetting(config, "mob change.snowman.enabled", true, "Should players be able to set their spawner to a Snowman");
	public static final ConfigSetting MOB_CHANGE_SNOWMAN_COST = new ConfigSetting(config, "mob change.snowman.cost", 5000D, "The cost to switch the spawner to a Snowman");
	public static final ConfigSetting MOB_CHANGE_OCELOT_ENABLED = new ConfigSetting(config, "mob change.ocelot.enabled", true, "Should players be able to set their spawner to a Ocelot");
	public static final ConfigSetting MOB_CHANGE_OCELOT_COST = new ConfigSetting(config, "mob change.ocelot.cost", 5000D, "The cost to switch the spawner to a Ocelot");
	public static final ConfigSetting MOB_CHANGE_IRON_GOLEM_ENABLED = new ConfigSetting(config, "mob change.iron golem.enabled", true, "Should players be able to set their spawner to a Iron Golem");
	public static final ConfigSetting MOB_CHANGE_IRON_GOLEM_COST = new ConfigSetting(config, "mob change.iron golem.cost", 5000D, "The cost to switch the spawner to a Iron Golem");
	public static final ConfigSetting MOB_CHANGE_HORSE_ENABLED = new ConfigSetting(config, "mob change.horse.enabled", true, "Should players be able to set their spawner to a Horse");
	public static final ConfigSetting MOB_CHANGE_HORSE_COST = new ConfigSetting(config, "mob change.horse.cost", 5000D, "The cost to switch the spawner to a Horse");
	public static final ConfigSetting MOB_CHANGE_RABBIT_ENABLED = new ConfigSetting(config, "mob change.rabbit.enabled", true, "Should players be able to set their spawner to a Rabbit");
	public static final ConfigSetting MOB_CHANGE_RABBIT_COST = new ConfigSetting(config, "mob change.rabbit.cost", 5000D, "The cost to switch the spawner to a Rabbit");
	public static final ConfigSetting MOB_CHANGE_POLAR_BEAR_ENABLED = new ConfigSetting(config, "mob change.polar bear.enabled", true, "Should players be able to set their spawner to a Polar Bear");
	public static final ConfigSetting MOB_CHANGE_POLAR_BEAR_COST = new ConfigSetting(config, "mob change.polar bear.cost", 5000D, "The cost to switch the spawner to a Polar Bear");
	public static final ConfigSetting MOB_CHANGE_LLAMA_ENABLED = new ConfigSetting(config, "mob change.llama.enabled", true, "Should players be able to set their spawner to a Llama");
	public static final ConfigSetting MOB_CHANGE_LLAMA_COST = new ConfigSetting(config, "mob change.llama.cost", 5000D, "The cost to switch the spawner to a Llama");
	public static final ConfigSetting MOB_CHANGE_PARROT_ENABLED = new ConfigSetting(config, "mob change.parrot.enabled", true, "Should players be able to set their spawner to a Parrot");
	public static final ConfigSetting MOB_CHANGE_PARROT_COST = new ConfigSetting(config, "mob change.parrot.cost", 5000D, "The cost to switch the spawner to a Parrot");
	public static final ConfigSetting MOB_CHANGE_VILLAGER_ENABLED = new ConfigSetting(config, "mob change.villager.enabled", true, "Should players be able to set their spawner to a Villager");
	public static final ConfigSetting MOB_CHANGE_VILLAGER_COST = new ConfigSetting(config, "mob change.villager.cost", 5000D, "The cost to switch the spawner to a Villager");
	public static final ConfigSetting MOB_CHANGE_TURTLE_ENABLED = new ConfigSetting(config, "mob change.turtle.enabled", true, "Should players be able to set their spawner to a Turtle");
	public static final ConfigSetting MOB_CHANGE_TURTLE_COST = new ConfigSetting(config, "mob change.turtle.cost", 5000D, "The cost to switch the spawner to a Turtle");
	public static final ConfigSetting MOB_CHANGE_PHANTOM_ENABLED = new ConfigSetting(config, "mob change.phantom.enabled", true, "Should players be able to set their spawner to a Phantom");
	public static final ConfigSetting MOB_CHANGE_PHANTOM_COST = new ConfigSetting(config, "mob change.phantom.cost", 5000D, "The cost to switch the spawner to a Phantom");
	public static final ConfigSetting MOB_CHANGE_COD_ENABLED = new ConfigSetting(config, "mob change.cod.enabled", true, "Should players be able to set their spawner to a Cod");
	public static final ConfigSetting MOB_CHANGE_COD_COST = new ConfigSetting(config, "mob change.cod.cost", 5000D, "The cost to switch the spawner to a Cod");
	public static final ConfigSetting MOB_CHANGE_SALMON_ENABLED = new ConfigSetting(config, "mob change.salmon.enabled", true, "Should players be able to set their spawner to a Salmon");
	public static final ConfigSetting MOB_CHANGE_SALMON_COST = new ConfigSetting(config, "mob change.salmon.cost", 5000D, "The cost to switch the spawner to a Salmon");
	public static final ConfigSetting MOB_CHANGE_PUFFERFISH_ENABLED = new ConfigSetting(config, "mob change.pufferfish.enabled", true, "Should players be able to set their spawner to a Pufferfish");
	public static final ConfigSetting MOB_CHANGE_PUFFERFISH_COST = new ConfigSetting(config, "mob change.pufferfish.cost", 5000D, "The cost to switch the spawner to a Pufferfish");
	public static final ConfigSetting MOB_CHANGE_TROPICAL_FISH_ENABLED = new ConfigSetting(config, "mob change.tropical fish.enabled", true, "Should players be able to set their spawner to a Tropical Fish");
	public static final ConfigSetting MOB_CHANGE_TROPICAL_FISH_COST = new ConfigSetting(config, "mob change.tropical fish.cost", 5000D, "The cost to switch the spawner to a Tropical Fish");
	public static final ConfigSetting MOB_CHANGE_DROWNED_ENABLED = new ConfigSetting(config, "mob change.drowned.enabled", true, "Should players be able to set their spawner to a Drowned");
	public static final ConfigSetting MOB_CHANGE_DROWNED_COST = new ConfigSetting(config, "mob change.drowned.cost", 5000D, "The cost to switch the spawner to a Drowned");
	public static final ConfigSetting MOB_CHANGE_DOLPHIN_ENABLED = new ConfigSetting(config, "mob change.dolphin.enabled", true, "Should players be able to set their spawner to a Dolphin");
	public static final ConfigSetting MOB_CHANGE_DOLPHIN_COST = new ConfigSetting(config, "mob change.dolphin.cost", 5000D, "The cost to switch the spawner to a Dolphin");
	public static final ConfigSetting MOB_CHANGE_CAT_ENABLED = new ConfigSetting(config, "mob change.cat.enabled", true, "Should players be able to set their spawner to a Cat");
	public static final ConfigSetting MOB_CHANGE_CAT_COST = new ConfigSetting(config, "mob change.cat.cost", 5000D, "The cost to switch the spawner to a Cat");
	public static final ConfigSetting MOB_CHANGE_PANDA_ENABLED = new ConfigSetting(config, "mob change.panda.enabled", true, "Should players be able to set their spawner to a Panda");
	public static final ConfigSetting MOB_CHANGE_PANDA_COST = new ConfigSetting(config, "mob change.panda.cost", 5000D, "The cost to switch the spawner to a Panda");
	public static final ConfigSetting MOB_CHANGE_PILLAGER_ENABLED = new ConfigSetting(config, "mob change.pillager.enabled", true, "Should players be able to set their spawner to a Pillager");
	public static final ConfigSetting MOB_CHANGE_PILLAGER_COST = new ConfigSetting(config, "mob change.pillager.cost", 5000D, "The cost to switch the spawner to a Pillager");
	public static final ConfigSetting MOB_CHANGE_RAVAGER_ENABLED = new ConfigSetting(config, "mob change.ravager.enabled", true, "Should players be able to set their spawner to a Ravager");
	public static final ConfigSetting MOB_CHANGE_RAVAGER_COST = new ConfigSetting(config, "mob change.ravager.cost", 5000D, "The cost to switch the spawner to a Ravager");
	public static final ConfigSetting MOB_CHANGE_TRADER_LLAMA_ENABLED = new ConfigSetting(config, "mob change.trader llama.enabled", true, "Should players be able to set their spawner to a Trader Llama");
	public static final ConfigSetting MOB_CHANGE_TRADER_LLAMA_COST = new ConfigSetting(config, "mob change.trader llama.cost", 5000D, "The cost to switch the spawner to a Trader Llama");
	public static final ConfigSetting MOB_CHANGE_WANDERING_TRADER_ENABLED = new ConfigSetting(config, "mob change.wandering trader.enabled", true, "Should players be able to set their spawner to a Wandering Trader");
	public static final ConfigSetting MOB_CHANGE_WANDERING_TRADER_COST = new ConfigSetting(config, "mob change.wandering trader.cost", 5000D, "The cost to switch the spawner to a Wandering Trader");
	public static final ConfigSetting MOB_CHANGE_FOX_ENABLED = new ConfigSetting(config, "mob change.fox.enabled", true, "Should players be able to set their spawner to a Fox");
	public static final ConfigSetting MOB_CHANGE_FOX_COST = new ConfigSetting(config, "mob change.fox.cost", 5000D, "The cost to switch the spawner to a Fox");
	public static final ConfigSetting MOB_CHANGE_BEE_ENABLED = new ConfigSetting(config, "mob change.bee.enabled", true, "Should players be able to set their spawner to a Bee");
	public static final ConfigSetting MOB_CHANGE_BEE_COST = new ConfigSetting(config, "mob change.bee.cost", 5000D, "The cost to switch the spawner to a Bee");
	public static final ConfigSetting MOB_CHANGE_HOGLIN_ENABLED = new ConfigSetting(config, "mob change.hoglin.enabled", true, "Should players be able to set their spawner to a Hoglin");
	public static final ConfigSetting MOB_CHANGE_HOGLIN_COST = new ConfigSetting(config, "mob change.hoglin.cost", 5000D, "The cost to switch the spawner to a Hoglin");
	public static final ConfigSetting MOB_CHANGE_PIGLIN_ENABLED = new ConfigSetting(config, "mob change.piglin.enabled", true, "Should players be able to set their spawner to a Piglin");
	public static final ConfigSetting MOB_CHANGE_PIGLIN_COST = new ConfigSetting(config, "mob change.piglin.cost", 5000D, "The cost to switch the spawner to a Piglin");
	public static final ConfigSetting MOB_CHANGE_STRIDER_ENABLED = new ConfigSetting(config, "mob change.strider.enabled", true, "Should players be able to set their spawner to a Strider");
	public static final ConfigSetting MOB_CHANGE_STRIDER_COST = new ConfigSetting(config, "mob change.strider.cost", 5000D, "The cost to switch the spawner to a Strider");
	public static final ConfigSetting MOB_CHANGE_ZOGLIN_ENABLED = new ConfigSetting(config, "mob change.zoglin.enabled", true, "Should players be able to set their spawner to a Zoglin");
	public static final ConfigSetting MOB_CHANGE_ZOGLIN_COST = new ConfigSetting(config, "mob change.zoglin.cost", 5000D, "The cost to switch the spawner to a Zoglin");
	public static final ConfigSetting MOB_CHANGE_PIGLIN_BRUTE_ENABLED = new ConfigSetting(config, "mob change.piglin brute.enabled", true, "Should players be able to set their spawner to a Piglin Brute");
	public static final ConfigSetting MOB_CHANGE_PIGLIN_BRUTE_COST = new ConfigSetting(config, "mob change.piglin brute.cost", 5000D, "The cost to switch the spawner to a Piglin Brute");
	public static final ConfigSetting MOB_CHANGE_AXOLOTL_ENABLED = new ConfigSetting(config, "mob change.axolotl.enabled", true, "Should players be able to set their spawner to a Axolotl");
	public static final ConfigSetting MOB_CHANGE_AXOLOTL_COST = new ConfigSetting(config, "mob change.axolotl.cost", 5000D, "The cost to switch the spawner to a Axolotl");
	public static final ConfigSetting MOB_CHANGE_GLOW_SQUID_ENABLED = new ConfigSetting(config, "mob change.glow squid.enabled", true, "Should players be able to set their spawner to a Glow Squid");
	public static final ConfigSetting MOB_CHANGE_GLOW_SQUID_COST = new ConfigSetting(config, "mob change.glow squid.cost", 5000D, "The cost to switch the spawner to a Glow Squid");
	public static final ConfigSetting MOB_CHANGE_GOAT_ENABLED = new ConfigSetting(config, "mob change.goat.enabled", true, "Should players be able to set their spawner to a Goat");
	public static final ConfigSetting MOB_CHANGE_GOAT_COST = new ConfigSetting(config, "mob change.goat.cost", 5000D, "The cost to switch the spawner to a Goat");

	public static final ConfigSetting MOB_CHANGE_ALLAY_ENABLED = new ConfigSetting(config, "mob change.allay.enabled", true, "Should players be able to set their spawner to a Allay");
	public static final ConfigSetting MOB_CHANGE_ALLAY_COST = new ConfigSetting(config, "mob change.allay.cost", 5000D, "The cost to switch the spawner to a Allay");

	public static final ConfigSetting MOB_CHANGE_FROG_ENABLED = new ConfigSetting(config, "mob change.frog.enabled", true, "Should players be able to set their spawner to a Frog");
	public static final ConfigSetting MOB_CHANGE_FROG_COST = new ConfigSetting(config, "mob change.frog.cost", 5000D, "The cost to switch the spawner to a Frog");

	public static final ConfigSetting MOB_CHANGE_TADPOLE_ENABLED = new ConfigSetting(config, "mob change.tadpole.enabled", true, "Should players be able to set their spawner to a Tadpole");
	public static final ConfigSetting MOB_CHANGE_TADPOLE_COST = new ConfigSetting(config, "mob change.tadpole.cost", 5000D, "The cost to switch the spawner to a Tadpole");

	public static final ConfigSetting MOB_CHANGE_WARDEN_ENABLED = new ConfigSetting(config, "mob change.warden.enabled", true, "Should players be able to set their spawner to a Warden");
	public static final ConfigSetting MOB_CHANGE_WARDEN_COST = new ConfigSetting(config, "mob change.warden.cost", 5000D, "The cost to switch the spawner to a Warden");

	@SneakyThrows
	public static void setup() {
		config.applySettings();
		config.save();
	}

}
