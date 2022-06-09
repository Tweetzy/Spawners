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
package ca.tweetzy.spawners.api.spawner;

import ca.tweetzy.spawners.api.DataSync;
import ca.tweetzy.spawners.api.Jsonable;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Date Created: June 08 2022
 * Time Created: 9:20 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
public abstract class ShopItem implements DataSync, Jsonable {

	protected UUID uuid;
	protected int quantity;
	protected double price;

	public abstract void setQuantity(int quantity);

	public abstract void setPrice(double price);

	public abstract void tryPurchase(Player player);

	public UUID getUUID() {
		return this.uuid;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public double getPrice() {
		return this.price;
	}
}
