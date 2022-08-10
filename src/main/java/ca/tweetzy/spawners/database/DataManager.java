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
package ca.tweetzy.spawners.database;

import ca.tweetzy.feather.database.Callback;
import ca.tweetzy.feather.database.DataManagerAbstract;
import ca.tweetzy.feather.database.DatabaseConnector;
import ca.tweetzy.feather.database.UpdateCallback;
import ca.tweetzy.spawners.api.LevelOption;
import ca.tweetzy.spawners.api.spawner.*;
import ca.tweetzy.spawners.impl.PlacedSpawner;
import ca.tweetzy.spawners.impl.SpawnerPlayer;
import ca.tweetzy.spawners.impl.SpawnerPreset;
import ca.tweetzy.spawners.impl.shopitem.EntityShopItem;
import ca.tweetzy.spawners.impl.shopitem.PresetShopItem;
import ca.tweetzy.spawners.model.LevelFactory;
import ca.tweetzy.spawners.model.Serialize;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.NonNull;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
			final String query = "INSERT INTO " + this.getTablePrefix() + "player (uuid, username) VALUES (?, ?)";
			final String fetchQuery = "SELECT * FROM " + this.getTablePrefix() + "player WHERE uuid = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				final PreparedStatement fetch = connection.prepareStatement(fetchQuery);

				fetch.setString(1, spawnerUser.getUUID().toString());

				preparedStatement.setString(1, spawnerUser.getUUID().toString());
				preparedStatement.setString(2, spawnerUser.getName());

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
			final String query = "INSERT INTO " + this.getTablePrefix() + "level (type, number, value, cost) VALUES (?, ?, ?, ?)";
			final String fetchQuery = "SELECT * FROM " + this.getTablePrefix() + "level WHERE type = ? AND number = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				final PreparedStatement fetch = connection.prepareStatement(fetchQuery);

				fetch.setString(1, level.getLevelOption().name());
				fetch.setInt(2, level.getLevelNumber());

				preparedStatement.setString(1, level.getLevelOption().name());
				preparedStatement.setInt(2, level.getLevelNumber());
				preparedStatement.setInt(3, level.getValue());
				preparedStatement.setDouble(4, level.getCost());

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
	 * @param spawner  The spawner to insert into the database.
	 * @param callback The callback to be called when the query is finished.
	 */
	public void insertSpawner(@NonNull final Spawner spawner, final Callback<Spawner> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			final String query = "INSERT INTO " + this.getTablePrefix() + "spawner (id, owner, owner_name, entity_type, levels, location) VALUES (?, ?, ?, ?, ?, ?)";
			final String fetchQuery = "SELECT * FROM " + this.getTablePrefix() + "spawner WHERE id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				final PreparedStatement fetch = connection.prepareStatement(fetchQuery);

				fetch.setString(1, spawner.getID().toString());

				preparedStatement.setString(1, spawner.getID().toString());
				preparedStatement.setString(2, spawner.getOwner().toString());
				preparedStatement.setString(3, spawner.getOwnerName());
				preparedStatement.setString(4, spawner.getEntityType().name());
				preparedStatement.setString(5, spawner.getJsonString());
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
	 * @param spawner  The spawner to update.
	 * @param callback The callback to be called when the query is finished.
	 */
	public void updateSpawner(@NonNull final Spawner spawner, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("UPDATE " + this.getTablePrefix() + "spawner SET owner = ?, owner_name = ?, entity_type = ?, levels = ? WHERE id = ?")) {

				statement.setString(1, spawner.getOwner().toString());
				statement.setString(2, spawner.getOwnerName());
				statement.setString(3, spawner.getEntityType().name());
				statement.setString(4, spawner.getJsonString());
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
	 * "Delete a spawner from the database."
	 *
	 * @param uuid     The UUID of the spawner to delete.
	 * @param callback The callback to be called when the query is finished.
	 */
	public void deleteSpawner(@NonNull final UUID uuid, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " + this.getTablePrefix() + "spawner WHERE id = ?")) {
				statement.setString(1, uuid.toString());

				int result = statement.executeUpdate();
				callback.accept(null, result > 0);

			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	public void deleteLevel(@NonNull final Level level, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " + this.getTablePrefix() + "level WHERE type = ? AND number = ?")) {

				statement.setString(1, level.getLevelOption().name());
				statement.setInt(2, level.getLevelNumber());

				int result = statement.executeUpdate();
				callback.accept(null, result > 0);

			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	public void insertShopItem(@NonNull final ShopItem shopItem, final Callback<ShopItem> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			final String query = "INSERT INTO " + this.getTablePrefix() + "shop_item (id, content) VALUES (?, ?)";
			final String fetchQuery = "SELECT * FROM " + this.getTablePrefix() + "shop_item WHERE id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				final PreparedStatement fetch = connection.prepareStatement(fetchQuery);

				fetch.setString(1, shopItem.getUUID().toString());

				preparedStatement.setString(1, shopItem.getUUID().toString());
				preparedStatement.setString(2, shopItem.getJsonString());

				preparedStatement.executeUpdate();

				if (callback != null) {
					final ResultSet res = fetch.executeQuery();
					res.next();
					callback.accept(null, extractShopItem(res));
				}

			} catch (Exception e) {
				e.printStackTrace();
				resolveCallback(callback, e);
			}

		}));
	}

	public void updateShopItem(@NonNull final ShopItem shopItem, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("UPDATE " + this.getTablePrefix() + "shop_item SET content = ? WHERE id = ?")) {

				statement.setString(1, shopItem.getJsonString());
				statement.setString(2, shopItem.getUUID().toString());

				int result = statement.executeUpdate();

				if (callback != null)
					callback.accept(null, result > 0);

			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	public void deleteShopItem(@NonNull final UUID id, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " + this.getTablePrefix() + "shop_item WHERE id = ?")) {
				statement.setString(1, id.toString());

				int result = statement.executeUpdate();
				callback.accept(null, result > 0);

			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	public void getShopItems(@NonNull final Callback<List<ShopItem>> callback) {
		final List<ShopItem> shopItems = new ArrayList<>();
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + this.getTablePrefix() + "shop_item")) {
				final ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					final ShopItem shopItem = extractShopItem(resultSet);
					shopItems.add(shopItem);
				}

				callback.accept(null, shopItems);
			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	public void insertSpawnerPreset(@NonNull final Preset preset, final Callback<Preset> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			final String query = "INSERT INTO " + this.getTablePrefix() + "spawner_preset (id, entity_type, levels) VALUES (?, ?, ?)";
			final String fetchQuery = "SELECT * FROM " + this.getTablePrefix() + "spawner_preset WHERE id = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				final PreparedStatement fetch = connection.prepareStatement(fetchQuery);

				fetch.setString(1, preset.getId().toLowerCase());

				preparedStatement.setString(1, preset.getId().toLowerCase());
				preparedStatement.setString(2, preset.getEntityType().name());
				preparedStatement.setString(3, preset.getJsonString());

				preparedStatement.executeUpdate();

				if (callback != null) {
					final ResultSet res = fetch.executeQuery();
					res.next();
					callback.accept(null, extractSpawnerPreset(res));
				}

			} catch (Exception e) {
				e.printStackTrace();
				resolveCallback(callback, e);
			}

		}));
	}

	public void getSpawnerPresets(@NonNull final Callback<List<Preset>> callback) {
		final List<Preset> presets = new ArrayList<>();
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + this.getTablePrefix() + "spawner_preset")) {
				final ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					final Preset preset = extractSpawnerPreset(resultSet);
					presets.add(preset);
				}

				callback.accept(null, presets);
			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	public void updateSpawnerPreset(@NonNull final Preset preset, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("UPDATE " + this.getTablePrefix() + "spawner_preset SET entity_type = ?, levels = ? WHERE id = ?")) {

				statement.setString(1, preset.getEntityType().name());
				statement.setString(2, preset.getJsonString());
				statement.setString(3, preset.getId().toLowerCase());

				int result = statement.executeUpdate();

				if (callback != null)
					callback.accept(null, result > 0);

			} catch (Exception e) {
				resolveCallback(callback, e);
			}
		}));
	}

	public void deleteSpawnerPreset(@NonNull final String id, Callback<Boolean> callback) {
		this.runAsync(() -> this.databaseConnector.connect(connection -> {
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM " + this.getTablePrefix() + "spawner_preset WHERE id = ?")) {
				statement.setString(1, id);

				int result = statement.executeUpdate();
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
			try (PreparedStatement statement = connection.prepareStatement("UPDATE " + this.getTablePrefix() + "level SET value = ?, cost = ? WHERE type = ? AND number = ?")) {

				statement.setInt(1, level.getValue());
				statement.setDouble(2, level.getCost());
				statement.setString(3, level.getLevelOption().name());
				statement.setInt(4, level.getLevelNumber());

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
		return new SpawnerPlayer(
				UUID.fromString(resultSet.getString("uuid")),
				resultSet.getString("username")
		);
	}

	/**
	 * It extracts a `SpawnerLevel` from a `ResultSet`
	 *
	 * @param resultSet The result set from the database.
	 * @return A SpawnerLevel object.
	 */
	private Level extractLevel(final ResultSet resultSet) throws SQLException {
		return LevelFactory.build(
				LevelOption.valueOf(resultSet.getString("type")),
				resultSet.getInt("number"),
				resultSet.getInt("value"),
				resultSet.getDouble("cost")
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
				resultSet.getString("owner_name"),
				EntityType.valueOf(resultSet.getString("entity_type")),
				PlacedSpawner.decodeLevelsJson(resultSet.getString("levels")),
				Serialize.deserializeLocation(resultSet.getString("location"))
		);
	}

	private Preset extractSpawnerPreset(final ResultSet resultSet) throws SQLException {
		return new SpawnerPreset(
				resultSet.getString("id"),
				EntityType.valueOf(resultSet.getString("entity_type").toUpperCase()),
				SpawnerPreset.decodeLevelsJson(resultSet.getString("levels"))
		);
	}

	private ShopItem extractShopItem(final ResultSet resultSet) throws SQLException {
		final JsonObject content = JsonParser.parseString(resultSet.getString("content")).getAsJsonObject();
		final boolean isPresetItem = content.get("type").getAsString().equalsIgnoreCase("preset");

		return isPresetItem ? PresetShopItem.decodeShopItem(resultSet.getString("content")) : EntityShopItem.decodeShopItem(resultSet.getString("content"));
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
