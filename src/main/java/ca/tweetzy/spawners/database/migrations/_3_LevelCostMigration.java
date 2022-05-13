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
public final class _3_LevelCostMigration extends DataMigration {

	public _3_LevelCostMigration() {
		super(3);
	}

	@Override
	public void migrate(Connection connection, String tablePrefix) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			statement.execute("ALTER TABLE " + tablePrefix + "level ADD cost DOUBLE");

		}
	}
}
