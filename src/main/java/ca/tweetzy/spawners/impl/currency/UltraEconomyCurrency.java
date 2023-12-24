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
package ca.tweetzy.spawners.impl.currency;

import ca.tweetzy.spawners.api.AbstractCurrency;
import me.TechsCode.UltraEconomy.UltraEconomy;
import me.TechsCode.UltraEconomy.objects.Account;
import me.TechsCode.UltraEconomy.objects.Currency;
import org.bukkit.OfflinePlayer;

public final class UltraEconomyCurrency extends AbstractCurrency {

	private final Currency currency;

	public UltraEconomyCurrency(String currencyName) {
		super("UltraEconomy", currencyName);
		this.currency = UltraEconomy.getInstance().getCurrencies().name(currencyName).orElse(null);
	}

	@Override
	public boolean has(OfflinePlayer player, double amount) {
		final Account account = UltraEconomy.getInstance().getAccounts().uuid(player.getUniqueId()).orElse(null);
		if (account == null) return false;

		return account.getBalance(this.currency).getSum() >= amount;
	}

	@Override
	public boolean withdraw(OfflinePlayer player, double amount) {
		final Account account = UltraEconomy.getInstance().getAccounts().uuid(player.getUniqueId()).orElse(null);
		if (account == null) return false;

		account.removeBalance(this.currency, amount);
		return true;
	}
}
