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
package ca.tweetzy.spawners.database.migrations;

import ca.tweetzy.flight.database.DataMigration;

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
					"username VARCHAR(16) NOT NULL" +
					")");

			// level presets
			statement.execute("CREATE TABLE " + tablePrefix + "level (" +
					"type VARCHAR (24) NOT NULL, " +
					"number INTEGER NOT NULL, " +
					"value INTEGER NOT NULL, " +
					"cost DOUBLE NOT NULL " +
					")");

			// spawner
			statement.execute("CREATE TABLE " + tablePrefix + "spawner (" +
					"id VARCHAR(36) PRIMARY KEY, " +
					"owner VARCHAR(36) NOT NULL, " +
					"owner_name VARCHAR(16) NOT NULL, " +
					"entity_type VARCHAR(60) NOT NULL, " +
					"levels TEXT NOT NULL, " +
					"location TEXT NOT NULL " +
					")");
		}
	}
}
