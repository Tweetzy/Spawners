package ca.tweetzy.spawners;

import ca.tweetzy.rose.RoseCore;
import ca.tweetzy.rose.RosePlugin;
import ca.tweetzy.rose.command.CommandManager;
import ca.tweetzy.rose.comp.enums.CompMaterial;
import ca.tweetzy.rose.database.DataMigrationManager;
import ca.tweetzy.rose.database.DatabaseConnector;
import ca.tweetzy.rose.database.SQLiteConnector;
import ca.tweetzy.rose.gui.GuiManager;
import ca.tweetzy.rose.utils.Common;
import ca.tweetzy.spawners.api.SpawnersAPI;
import ca.tweetzy.spawners.commands.GiveCommand;
import ca.tweetzy.spawners.commands.SetCommand;
import ca.tweetzy.spawners.commands.SpawnersCommand;
import ca.tweetzy.spawners.database.DataManager;
import ca.tweetzy.spawners.database.migrations._1_InitialMigration;
import ca.tweetzy.spawners.database.migrations._2_SpawnerPresetMigration;
import ca.tweetzy.spawners.impl.APIImplementation;
import ca.tweetzy.spawners.listeners.BlockListeners;
import ca.tweetzy.spawners.listeners.EggListeners;
import ca.tweetzy.spawners.listeners.EntityListeners;
import ca.tweetzy.spawners.listeners.JoinListeners;
import ca.tweetzy.spawners.model.manager.*;
import ca.tweetzy.spawners.settings.Locale;
import ca.tweetzy.spawners.settings.Settings;

import java.util.Arrays;

/**
 * Date Created: May 04 2022
 * Time Created: 10:35 a.m.
 *
 * @author Kiran Hart
 */
public final class Spawners extends RosePlugin {

	private final GuiManager guiManager = new GuiManager(this);
	private final CommandManager commandManager = new CommandManager(this);

	private final PlayerManager playerManager = new PlayerManager();
	private final LevelManager levelManager = new LevelManager();
	private final SpawnerManager spawnerManager = new SpawnerManager();
	private final PresetManager presetManager = new PresetManager();

	private final SpawnersAPI spawnersAPI = new APIImplementation();

	// database
	@SuppressWarnings("FieldCanBeLocal")
	private DatabaseConnector databaseConnector;

	@SuppressWarnings("FieldCanBeLocal")
	private DataManager dataManager;

	@Override
	protected void onWake() {
		// Set up the database if enabled
		this.databaseConnector = new SQLiteConnector(this);
		this.dataManager = new DataManager(this.databaseConnector, this);

		final DataMigrationManager dataMigrationManager = new DataMigrationManager(this.databaseConnector, this.dataManager,
				new _1_InitialMigration(),
				new _2_SpawnerPresetMigration()
		);

		// run migrations for tables
		dataMigrationManager.runMigrations();
	}

	@Override
	protected void onFlight() {
		RoseCore.registerPlugin(this, 9, CompMaterial.SPAWNER.name());

		// settings & locale setup
		Settings.setup();
		Locale.setup();
		Common.setPrefix(Settings.PREFIX.getString());

		Arrays.asList(
				this.playerManager,
				this.levelManager,
				this.spawnerManager,
				this.presetManager
		).forEach(Manager::load);

		// setup command manager
		this.commandManager.registerCommandDynamically(new SpawnersCommand()).addSubCommands(
				new GiveCommand(),
				new SetCommand()
		);

		// initialize gui manager
		this.guiManager.init();

		getServer().getPluginManager().registerEvents(new JoinListeners(), this);
		getServer().getPluginManager().registerEvents(new BlockListeners(), this);
		getServer().getPluginManager().registerEvents(new EntityListeners(), this);
		getServer().getPluginManager().registerEvents(new EggListeners(), this);

	}

	@Override
	protected void onSleep() {
		shutdownDataManager(this.dataManager);
	}

	// instance
	public static Spawners getInstance() {
		return (Spawners) RosePlugin.getInstance();
	}

	// data manager
	public static DataManager getDataManager() {
		return getInstance().dataManager;
	}

	// gui manager
	public static GuiManager getGuiManager() {
		return getInstance().guiManager;
	}

	// player manager
	public static PlayerManager getPlayerManager() {
		return getInstance().playerManager;
	}

	// level manager
	public static LevelManager getLevelManager() {
		return getInstance().levelManager;
	}

	// spawner manager
	public static SpawnerManager getSpawnerManager() {
		return getInstance().spawnerManager;
	}

	// spawner preset manager
	public static PresetManager getPresetManager() {
		return getInstance().presetManager;
	}

	public static SpawnersAPI getAPI() {
		return getInstance().spawnersAPI;
	}

	@Override
	protected int getBStatsId() {
		return 12416;
	}
}
