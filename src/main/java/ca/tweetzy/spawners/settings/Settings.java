package ca.tweetzy.spawners.settings;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.files.ConfigSetting;
import ca.tweetzy.rose.files.file.YamlFile;
import ca.tweetzy.spawners.Spawners;
import lombok.SneakyThrows;
import net.minecraft.world.entity.EntityTameableAnimal;
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

	public static final ConfigSetting DEFAULT_SPAWNER_ENTITY = new ConfigSetting(config, "default spawner entity", EntityType.PIG.name(), "For non Spawners spawners, what should the default mob be when placed");
	public static final ConfigSetting ASSIGN_OWNER_TO_NATURAL = new ConfigSetting(config, "assign owner to natural", true, "When a natural spawner is broken, should the miner become the owner?");

	public static final ConfigSetting EXPLOSION_DROP_ENABLED = new ConfigSetting(config, "explosion.drop enabled", false, "Should spawners break during an explosion");
	public static final ConfigSetting EXPLOSION_DROP_CHANCE = new ConfigSetting(config, "explosion.drop chance", 25D, "The chance for the spawner to drop if exploded by tnt");
	public static final ConfigSetting EXPLOSION_RESETS_OWNER = new ConfigSetting(config, "explosion.reset owner", true, "If the spawner is dropped by explosion, should the owner be reset?");


	@SneakyThrows
	public static void setup() {
		config.applySettings();
		config.save();
	}

}
