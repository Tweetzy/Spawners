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
package ca.tweetzy.spawners.model.loader;

import ca.tweetzy.spawners.api.AbstractCurrency;
import ca.tweetzy.spawners.impl.currency.UltraEconomyCurrency;
import lombok.experimental.UtilityClass;
import me.TechsCode.UltraEconomy.UltraEconomy;
import me.TechsCode.UltraEconomy.objects.Currency;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public final class UltraEconomyLoader {

	private final String OWNING_PLUGIN = "UltraEconomy";

	public List<AbstractCurrency> getAllCurrencies() {
		final List<AbstractCurrency> currencies = new ArrayList<>();

		for (Currency currency : UltraEconomy.getAPI().getCurrencies()) {
			currencies.add(new UltraEconomyCurrency(currency.getName()));
		}

		return currencies;
	}
}
