package ca.tweetzy.spawners.database.migrations;

import ca.tweetzy.rose.database.DataMigration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Date Created: May 04 2022
 * Time Created: 11:01 a.m.
 *
 * @author Kiran Hart
 */
public final class _1_InitialMigration extends DataMigration {

	public _1_InitialMigration() {
		super(1);
	}

	@Override
	public void migrate(Connection connection, String tablePrefix) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.execute("CREATE TABLE " + tablePrefix + "player (" +
					"uuid VARCHAR(36) PRIMARY KEY, " +
					"username VARCHAR(16) NOT NULL, " +
					"placed_spawners TEXT NULL " +
					")");

			// level presets
			statement.execute("CREATE TABLE " + tablePrefix + "level (" +
					"id INTEGER PRIMARY KEY, " +
					"spawn_interval INTEGER NOT NULL, " +
					"spawn_count INTEGER NOT NULL, " +
					"max_nearby_entities INTEGER NOT NULL, " +
					"player_activation_range INTEGER NOT NULL " +
					")");

			// spawner
			statement.execute("CREATE TABLE " + tablePrefix + "spawner (" +
					"id VARCHAR(36) PRIMARY KEY, " +
					"owner VARCHAR(36) NOT NULL, " +
					"entity_type VARCHAR(60) NOT NULL, " +
					"level INTEGER NOT NULL, " +
					"options INTEGER NOT NULL, " +
					"location TEXT NOT NULL " +
					")");
		}
	}
}
