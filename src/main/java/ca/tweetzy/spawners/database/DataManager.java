package ca.tweetzy.spawners.database;

import ca.tweetzy.rose.database.Callback;
import ca.tweetzy.rose.database.DataManagerAbstract;
import ca.tweetzy.rose.database.DatabaseConnector;
import ca.tweetzy.rose.database.UpdateCallback;
import ca.tweetzy.spawners.api.spawner.Level;
import ca.tweetzy.spawners.api.spawner.Spawner;
import ca.tweetzy.spawners.api.spawner.SpawnerUser;
import ca.tweetzy.spawners.impl.PlacedSpawner;
import ca.tweetzy.spawners.impl.SpawnerLevel;
import ca.tweetzy.spawners.impl.SpawnerOptions;
import ca.tweetzy.spawners.impl.SpawnerPlayer;
import ca.tweetzy.spawners.model.Serialize;
import lombok.NonNull;
import org.bukkit.entity.EntityType;
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
	 * @param callback    The callback to be called when the query is finished.
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
	 * It inserts a level into the database
	 *
	 * @param level    The level to insert
	 * @param callback The callback to be called when the query is finished.
	 */
	public void insertLevel(@NonNull final Level level, final Callback<Level> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			final String query = "INSERT INTO " + this.getTablePrefix() + "level (id, spawn_interval, spawn_count, max_nearby_entities, player_activation_range) VALUES (?, ?, ?, ?, ?)";
			final String fetchQuery = "SELECT * FROM " + this.getTablePrefix() + "level WHERE id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				final PreparedStatement fetch = connection.prepareStatement(fetchQuery);

				fetch.setInt(1, level.getLevel());

				preparedStatement.setInt(1, level.getLevel());
				preparedStatement.setInt(2, level.getSpawnInterval());
				preparedStatement.setInt(3, level.getSpawnCount());
				preparedStatement.setInt(4, level.getMaxNearbyEntities());
				preparedStatement.setInt(5, level.getPlayerActivationRange());

				preparedStatement.executeUpdate();

				if (callback != null) {
					final ResultSet res = fetch.executeQuery();
					res.next();
					callback.accept(null, extractLevel(res));
				}

			} catch (Exception e) {
				e.printStackTrace();
				resolveCallback(callback, e);
			}
		}));
	}

	/**
	 * It inserts a spawner into the database
	 *
	 * @param spawner The spawner to insert into the database.
	 * @param callback The callback to be called when the query is finished.
	 */
	public void insertSpawner(@NonNull final Spawner spawner, final Callback<Spawner> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			final String query = "INSERT INTO " + this.getTablePrefix() + "spawner (id, owner, entity_type, level, options, location) VALUES (?, ?, ?, ?, ?, ?)";
			final String fetchQuery = "SELECT * FROM " + this.getTablePrefix() + "spawner WHERE id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				final PreparedStatement fetch = connection.prepareStatement(fetchQuery);

				fetch.setString(1, spawner.getID().toString());

				preparedStatement.setString(1, spawner.getID().toString());
				preparedStatement.setString(2, spawner.getOwner().toString());
				preparedStatement.setString(3, spawner.getEntityType().name());
				preparedStatement.setInt(4, spawner.getLevel());
				preparedStatement.setString(5, spawner.getOptions().getJsonString());
				preparedStatement.setString(6, Serialize.serializeLocation(spawner.getLocation()));

				preparedStatement.executeUpdate();

				if (callback != null) {
					final ResultSet res = fetch.executeQuery();
					res.next();
					callback.accept(null, extractSpawner(res));
				}

			} catch (Exception e) {
				e.printStackTrace();
				resolveCallback(callback, e);
			}
		}));
	}

	/**
	 * "Get all spawners from the database and return them in a list."
	 *
	 * @param callback The callback to be called when the query is finished.
	 */
	public void getSpawners(@NonNull final Callback<List<Spawner>> callback) {
		final List<Spawner> spawners = new ArrayList<>();
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + this.getTablePrefix() + "spawner")) {
				final ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					spawners.add(extractSpawner(resultSet));
				}

				callback.accept(null, spawners);
			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	/**
	 * It updates a spawner in the database
	 *
	 * @param spawner The spawner to update.
	 * @param callback The callback to be called when the query is finished.
	 */
	public void updateSpawner(@NonNull final Spawner spawner, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("UPDATE " + this.getTablePrefix() + "spawner SET owner = ?, entity_type = ?, level = ?, options = ? WHERE id = ?")) {

				statement.setString(1, spawner.getOwner().toString());
				statement.setString(2, spawner.getEntityType().name());
				statement.setInt(3, spawner.getLevel());
				statement.setString(4, spawner.getOptions().getJsonString());
				statement.setString(5, spawner.getID().toString());

				int result = statement.executeUpdate();

				if (callback != null)
					callback.accept(null, result > 0);

			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	/**
	 * "Get all levels from the database and return them in a list."
	 *
	 * @param callback The callback to be called when the query is finished.
	 */
	public void getLevels(@NonNull final Callback<List<Level>> callback) {
		final List<Level> levels = new ArrayList<>();
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + this.getTablePrefix() + "level")) {
				final ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					levels.add(extractLevel(resultSet));
				}

				callback.accept(null, levels);
			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	/**
	 * It updates the level in the database
	 *
	 * @param level    The level to update
	 * @param callback The callback to be called when the query is finished.
	 */
	public void updateLevel(@NonNull final Level level, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("UPDATE " + this.getTablePrefix() + "level SET spawn_interval = ?, spawn_count = ?, max_nearby_entities = ?, player_activation_range = ? WHERE id = ?")) {

				statement.setInt(1, level.getSpawnInterval());
				statement.setInt(2, level.getSpawnCount());
				statement.setInt(3, level.getMaxNearbyEntities());
				statement.setInt(4, level.getPlayerActivationRange());
				statement.setInt(5, level.getLevel());

				int result = statement.executeUpdate();

				if (callback != null)
					callback.accept(null, result > 0);

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
		final String placedSpawners = resultSet.getString("placed_spawners");
		final String[] split = placedSpawners.split(",");

		return new SpawnerPlayer(
				UUID.fromString(resultSet.getString("uuid")),
				resultSet.getString("username"),
				placedSpawners.length() == 0 || split.length == 0 ? Collections.emptyList() : Arrays.stream(split).map(UUID::fromString).collect(Collectors.toList())
		);
	}

	/**
	 * It extracts a `SpawnerLevel` from a `ResultSet`
	 *
	 * @param resultSet The result set from the database.
	 * @return A SpawnerLevel object.
	 */
	private Level extractLevel(final ResultSet resultSet) throws SQLException {
		return new SpawnerLevel(
				resultSet.getInt("id"),
				resultSet.getInt("spawn_interval"),
				resultSet.getInt("spawn_count"),
				resultSet.getInt("max_nearby_entities"),
				resultSet.getInt("player_activation_range")
		);
	}

	/**
	 * It takes a ResultSet and returns a Spawner
	 *
	 * @param resultSet The result set from the database.
	 * @return A Spawner object
	 */
	private Spawner extractSpawner(final ResultSet resultSet) throws SQLException {
		return new PlacedSpawner(
				UUID.fromString(resultSet.getString("id")),
				UUID.fromString(resultSet.getString("owner")),
				EntityType.valueOf(resultSet.getString("entity_type")),
				resultSet.getInt("level"),
				SpawnerOptions.decodeJson(resultSet.getString("options")),
				Serialize.deserializeLocation(resultSet.getString("location"))
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
