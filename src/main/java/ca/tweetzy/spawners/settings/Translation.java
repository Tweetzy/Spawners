package ca.tweetzy.spawners.settings;

import ca.tweetzy.rose.utils.Replacer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Date Created: April 18 2022
 * Time Created: 3:18 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
@Getter
public enum Translation {

	/*
	============= misc =============
	 */
	MISC_IS_TRUE("misc.is true", "&ATrue"),
	MISC_IS_FALSE("misc.is false", "&cFalse"),
	MISC_IS_ALLOWED("misc.is allowed", "&aAllowed"),
	MISC_IS_DISALLOWED("misc.is disallowed", "&cDisallowed"),
	NOT_ENOUGH_MONEY("misc.not enough money", "&cYou do not have enough money!"),
	NOT_A_NUMBER("misc.not a number", "&cThat is not a valid number"),
	PLAYER_OFFLINE("misc.player not found", "&cThe player &4%player% &cis not online!"),


	;

	final String key;
	final Object value;

	public String getString(Object... replacements) {
		return Replacer.replaceVariables(Locale.getString(this.key), replacements);
	}

	public List<String> getList(Object... replacements) {
		return Replacer.replaceVariables(Locale.getList(this.key), replacements);
	}
}
