package ca.tweetzy.spawners.database;

import ca.tweetzy.rose.database.Callback;
import ca.tweetzy.rose.database.DataManagerAbstract;
import ca.tweetzy.rose.database.DatabaseConnector;
import ca.tweetzy.rose.database.UpdateCallback;
import ca.tweetzy.spawners.api.interfaces.SpawnerUser;
import ca.tweetzy.spawners.impl.SpawnerPlayer;
import lombok.NonNull;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Date Created: May 03 2022
 * Time Created: 4:50 p.m.
 *
 * @author Kiran Hart
 */
public final class DataManager extends DataManagerAbstract {

	public DataManager(DatabaseConnector databaseConnector, Plugin plugin) {
		super(databaseConnector, plugin);
	}

	/**
	 * It inserts a new user into the database
	 *
	 * @param spawnerUser The SpawnerUser object to insert into the database.
	 * @param callback The callback to be called when the query is finished.
	 */
	public void insertUser(@NonNull final SpawnerUser spawnerUser, final Callback<SpawnerUser> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			final String query = "INSERT INTO " + this.getTablePrefix() + "player (uuid, username, placed_spawners) VALUES (?, ?, ?)";
			final String fetchQuery = "SELECT * FROM " + this.getTablePrefix() + "player WHERE uuid = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				final PreparedStatement fetch = connection.prepareStatement(fetchQuery);

				fetch.setString(1, spawnerUser.getUUID().toString());

				preparedStatement.setString(1, spawnerUser.getUUID().toString());
				preparedStatement.setString(2, spawnerUser.getName());
				preparedStatement.setString(3, spawnerUser.getPlacedSpawners().stream().map(UUID::toString).collect(Collectors.joining(",")));

				preparedStatement.executeUpdate();

				if (callback != null) {
					final ResultSet res = fetch.executeQuery();
					res.next();
					callback.accept(null, extractSpawnerUser(res));
				}

			} catch (Exception e) {
				e.printStackTrace();
				resolveCallback(callback, e);
			}

		}));
	}

	/**
	 * "Get all the users from the database and return them in a list."
	 *
	 * @param callback The callback to be called when the query is finished.
	 */
	public void getUsers(@NonNull final Callback<List<SpawnerUser>> callback) {
		final List<SpawnerUser> players = new ArrayList<>();
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + this.getTablePrefix() + "player")) {
				final ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					players.add(extractSpawnerUser(resultSet));
				}

				callback.accept(null, players);
			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	/**
	 * It takes a ResultSet and returns a SpawnerUser
	 *
	 * @param resultSet The result set from the database.
	 * @return A SpawnerUser object
	 */
	private SpawnerUser extractSpawnerUser(final ResultSet resultSet) throws SQLException {
		return new SpawnerPlayer(
				UUID.fromString(resultSet.getString("uuid")),
				resultSet.getString("username"),
				Arrays.stream(resultSet.getString("placed_spawners").split(",")).map(UUID::fromString).collect(Collectors.toList())
		);
	}

	private void resolveUpdateCallback(@Nullable UpdateCallback callback, @Nullable Exception ex) {
		if (callback != null) {
			callback.accept(ex);
		} else if (ex != null) {
			ex.printStackTrace();
		}
	}

	private void resolveCallback(@Nullable Callback<?> callback, @NotNull Exception ex) {
		if (callback != null) {
			callback.accept(ex, null);
		} else {
			ex.printStackTrace();
		}
	}

	private boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++) {
			if (columnName.equals(rsmd.getColumnName(x))) {
				return true;
			}
		}
		return false;
	}
}
