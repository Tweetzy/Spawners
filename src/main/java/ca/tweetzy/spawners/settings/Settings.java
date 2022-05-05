package ca.tweetzy.spawners.settings;

import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.files.ConfigSetting;
import ca.tweetzy.rose.files.file.YamlFile;
import ca.tweetzy.spawners.Spawners;
import lombok.SneakyThrows;

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

	@SneakyThrows
	public static void setup() {
		config.applySettings();
		config.save();
	}

}
