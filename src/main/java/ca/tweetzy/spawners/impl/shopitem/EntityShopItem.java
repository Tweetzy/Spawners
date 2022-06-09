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
package ca.tweetzy.spawners.impl.shopitem;

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.ShopItem;
import ca.tweetzy.spawners.model.SpawnerBuilder;
import ca.tweetzy.spawners.settings.Translation;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Date Created: June 08 2022
 * Time Created: 9:26 p.m.
 *
 * @author Kiran Hart
 */
public final class EntityShopItem extends ShopItem {

	@Getter
	private final EntityType entityType;

	public EntityShopItem(@NonNull final UUID uuid, @NonNull final EntityType entityType, int quantity, double price) {
		super(uuid, quantity, price);
		this.entityType = entityType;
	}

	public EntityShopItem(@NonNull final EntityType entityType, int quantity, double price) {
		this(UUID.randomUUID(), entityType, quantity, price);
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void tryPurchase(Player player) {
		if (Spawners.getEconomy() == null) return;

		if (!Spawners.getEconomy().has(player, this.price)) {
			Translation.NOT_ENOUGH_MONEY.send(player);
			return;
		}

		if (player.getInventory().firstEmpty() == -1) {
			Translation.NO_INVENTORY_SPACE.send(player);
			return;
		}

		Spawners.getEconomy().withdrawPlayer(player, this.price);
		Translation.MONEY_REMOVE.send(player, "amount", String.format("%,.2f", this.price));

		final ItemStack item = SpawnerBuilder.of(player, this.entityType).addDefaultLevels().make();

		// give them the item
		for (int i = 0; i < this.quantity; i++)
			player.getInventory().addItem(item);
	}

	@Override
	public String getJsonString() {
		final JsonObject object = new JsonObject();

		object.addProperty("id", this.uuid.toString());
		object.addProperty("type", "entity");
		object.addProperty("entity", this.entityType.name());
		object.addProperty("quantity", this.quantity);
		object.addProperty("price", this.price);

		return object.toString();
	}

	public static ShopItem decodeShopItem(String json) {
		final JsonObject object = JsonParser.parseString(json).getAsJsonObject();

		return new EntityShopItem(
				UUID.fromString(object.get("id").getAsString()),
				EntityType.valueOf(object.get("entity").getAsString()),
				object.get("quantity").getAsInt(),
				object.get("price").getAsDouble()

		);
	}

	@Override
	public void sync() {
		Spawners.getDataManager().updateShopItem(this, null);
	}
}
