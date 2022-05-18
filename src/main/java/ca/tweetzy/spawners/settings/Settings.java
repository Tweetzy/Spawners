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
import ca.tweetzy.spawners.Spawners;
import lombok.SneakyThrows;
import org.bukkit.entity.EntityType;

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

	public static final ConfigSetting MAX_SPAWNER_PER_CHUNK_ENABLED = new ConfigSetting(config, "max spawners per chunk.enabled", true, "If true, spawners will limit the amount of spawners within a chunk");
	public static final ConfigSetting MAX_SPAWNER_PER_CHUNK = new ConfigSetting(config, "max spawners per chunk.max", 16, "If max spawners per chunk.enabled is enabled, it will use this limit");

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


	@SneakyThrows
	public static void setup() {
		config.applySettings();
		config.save();
	}

}
