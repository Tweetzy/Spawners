package ca.tweetzy.spawners.settings;

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

	public static final ConfigSetting PREFIX = new ConfigSetting(config, "prefix", "&8[&ESpawners&8]", "The global prefix for the plugin");
	public static final ConfigSetting LANGUAGE = new ConfigSetting(config, "language", "english", "The default language for the plugin");

	@SneakyThrows
	public static void setup() {
		config.applySettings();
		config.save();
	}

}
