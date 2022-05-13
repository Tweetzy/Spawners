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
public final class _2_SpawnerPresetMigration extends DataMigration {

	public _2_SpawnerPresetMigration() {
		super(2);
	}

	@Override
	public void migrate(Connection connection, String tablePrefix) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.execute("CREATE TABLE " + tablePrefix + "spawner_preset (" +
					"id VARCHAR(48) PRIMARY KEY, " +
					"entity_type VARCHAR(60) NOT NULL, " +
					"level INTEGER NOT NULL, " +
					"options TEXT NOT NULL " +
					")");
		}
	}
}
