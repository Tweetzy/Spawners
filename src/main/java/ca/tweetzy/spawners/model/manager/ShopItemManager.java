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
package ca.tweetzy.spawners.model.manager;

import ca.tweetzy.shops.api.shop.Shop;
import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.spawner.ShopItem;
import ca.tweetzy.spawners.model.hook.ShopsHook;
import ca.tweetzy.spawners.settings.Settings;
import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Date Created: June 08 2022
 * Time Created: 9:55 p.m.
 *
 * @author Kiran Hart
 */
public final class ShopItemManager implements Manager {

	private final Map<UUID, ShopItem> contents = new ConcurrentHashMap<>();

	public void add(@NonNull ShopItem shopItem) {
		if (this.contents.containsKey(shopItem.getUUID())) return;
		this.contents.put(shopItem.getUUID(), shopItem);
	}

	public void remove(@NonNull UUID id) {
		if (!this.contents.containsKey(id)) return;
		this.contents.remove(id);
	}

	public ShopItem find(@NonNull UUID id) {
		return this.contents.getOrDefault(id, null);
	}

	public List<ShopItem> getContents() {
		return List.copyOf(this.contents.values());
	}

		/*
	=================== DATABASE CALLS ===================
	 */

	public void createShopItem(@NonNull final ShopItem shopItem, final BiConsumer<Boolean, ShopItem> consumer) {
		Spawners.getDataManager().insertShopItem(shopItem, (error, created) -> {
			if (error == null)
				this.add(created);

			if (consumer != null)
				consumer.accept(error == null, created);
		});
	}

	public void deleteShopItem(@NonNull final ShopItem shopItem, final Consumer<Boolean> success) {
		Spawners.getDataManager().deleteShopItem(shopItem.getUUID(), (error, deleted) -> {
			if (error == null && deleted)
				this.remove(shopItem.getUUID());

			if (success != null)
				success.accept(error == null && deleted);
		});
	}

	@Override
	public void load() {
		// clear player list
		this.contents.clear();


		Spawners.getDataManager().getShopItems((error, result) -> {
			if (error == null)
				result.forEach(this::add);
		});
	}
}
