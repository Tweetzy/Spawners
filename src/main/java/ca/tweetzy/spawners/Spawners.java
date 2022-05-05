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
import ca.tweetzy.spawners.commands.GiveCommand;
import ca.tweetzy.spawners.commands.SpawnersCommand;
import ca.tweetzy.spawners.database.DataManager;
import ca.tweetzy.spawners.database.migrations._1_InitialMigration;
import ca.tweetzy.spawners.listeners.BlockListeners;
import ca.tweetzy.spawners.listeners.JoinListeners;
import ca.tweetzy.spawners.model.LevelManager;
import ca.tweetzy.spawners.model.PlayerManager;
import ca.tweetzy.spawners.settings.Locale;
import ca.tweetzy.spawners.settings.Settings;

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
				new _1_InitialMigration()
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

		// players
		this.playerManager.load();
		this.levelManager.load();

		// setup command manager
		this.commandManager.registerCommandDynamically(new SpawnersCommand()).addSubCommands(new GiveCommand());

		// initialize gui manager
		this.guiManager.init();

		getServer().getPluginManager().registerEvents(new JoinListeners(), this);
		getServer().getPluginManager().registerEvents(new BlockListeners(), this);
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

	@Override
	protected int getBStatsId() {
		return 12416;
	}
}
