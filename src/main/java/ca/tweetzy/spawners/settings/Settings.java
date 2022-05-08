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

	public static final ConfigSetting DEFAULT_SPAWNER_ENTITY = new ConfigSetting(config, "spawner defaults.entity", EntityType.PIG.name(), "For non Spawners spawners, what should the default mob be when placed");
	public static final ConfigSetting DEFAULT_SPAWNER_DELAY = new ConfigSetting(config, "spawner defaults.delay", 20 * 12);
	public static final ConfigSetting DEFAULT_SPAWNER_SPAWN_COUNT = new ConfigSetting(config, "spawner defaults.spawn count", 4);
	public static final ConfigSetting DEFAULT_SPAWNER_ACTIVATION_RANGE = new ConfigSetting(config, "spawner defaults.activation range", 16);
	public static final ConfigSetting DEFAULT_SPAWNER_MAX_NEARBY_ENTITIES = new ConfigSetting(config, "spawner defaults.max nearby entities", 12);


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
