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

import ca.tweetzy.spawners.Spawners;
import ca.tweetzy.spawners.api.AbstractCurrency;
import ca.tweetzy.spawners.model.loader.UltraEconomyLoader;
import lombok.NonNull;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CurrencyManager implements Manager {

	private final List<AbstractCurrency> currencies = Collections.synchronizedList(new ArrayList<>());

	public boolean has(@NonNull final OfflinePlayer offlinePlayer, @NonNull final String owningPlugin, @NonNull final String currencyName, final double amount) {
		if (owningPlugin.equalsIgnoreCase("vault") || currencyName.equalsIgnoreCase("vault"))
			return Spawners.getEconomy().has(offlinePlayer, amount);

		return locateCurrency(owningPlugin, currencyName).has(offlinePlayer, amount);
	}

	public boolean withdraw(@NonNull final OfflinePlayer offlinePlayer, @NonNull final String owningPlugin, @NonNull final String currencyName, final double amount) {
		if (owningPlugin.equalsIgnoreCase("vault") || currencyName.equalsIgnoreCase("vault")) {
			Spawners.getEconomy().withdrawPlayer(offlinePlayer, amount);
			return true;
		}

		return locateCurrency(owningPlugin, currencyName).withdraw(offlinePlayer, amount);
	}

	public AbstractCurrency locateCurrency(@NonNull final String owningPlugin, @NonNull final String currencyName) {
		synchronized (this.currencies) {
			return this.currencies.stream().filter(currency -> currency.getOwningPlugin().equals(owningPlugin) && currency.getCurrencyName().equals(currencyName)).findFirst().orElse(null);
		}
	}

	@Override
	public void load() {
		synchronized (this.currencies) {
			this.currencies.clear();
			this.currencies.addAll(UltraEconomyLoader.getAllCurrencies());
		}
	}

}
