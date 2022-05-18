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
package ca.tweetzy.spawners.api;

import ca.tweetzy.spawners.settings.Settings;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Date Created: May 18 2022
 * Time Created: 12:55 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
@Getter
public enum MobUpgrade {

	ELDER_GUARDIAN(Settings.MOB_CHANGE_ELDER_GUARDIAN_ENABLED.getBoolean(), Settings.MOB_CHANGE_ELDER_GUARDIAN_COST.getDouble()),
	WITHER_SKELETON(Settings.MOB_CHANGE_WITHER_SKELETON_ENABLED.getBoolean(), Settings.MOB_CHANGE_WITHER_SKELETON_COST.getDouble()),
	STRAY(Settings.MOB_CHANGE_STRAY_ENABLED.getBoolean(), Settings.MOB_CHANGE_STRAY_COST.getDouble()),
	HUSK(Settings.MOB_CHANGE_HUSK_ENABLED.getBoolean(), Settings.MOB_CHANGE_HUSK_COST.getDouble()),
	ZOMBIE_VILLAGER(Settings.MOB_CHANGE_ZOMBIE_VILLAGER_ENABLED.getBoolean(), Settings.MOB_CHANGE_ZOMBIE_VILLAGER_COST.getDouble()),
	SKELETON_HORSE(Settings.MOB_CHANGE_SKELETON_HORSE_ENABLED.getBoolean(), Settings.MOB_CHANGE_SKELETON_HORSE_COST.getDouble()),
	ZOMBIE_HORSE(Settings.MOB_CHANGE_ZOMBIE_HORSE_ENABLED.getBoolean(), Settings.MOB_CHANGE_ZOMBIE_HORSE_COST.getDouble()),
	DONKEY(Settings.MOB_CHANGE_DONKEY_ENABLED.getBoolean(), Settings.MOB_CHANGE_DONKEY_COST.getDouble()),
	MULE(Settings.MOB_CHANGE_MULE_ENABLED.getBoolean(), Settings.MOB_CHANGE_MULE_COST.getDouble()),
	EVOKER(Settings.MOB_CHANGE_EVOKER_ENABLED.getBoolean(), Settings.MOB_CHANGE_EVOKER_COST.getDouble()),
	VEX(Settings.MOB_CHANGE_VEX_ENABLED.getBoolean(), Settings.MOB_CHANGE_VEX_COST.getDouble()),
	VINDICATOR(Settings.MOB_CHANGE_VINDICATOR_ENABLED.getBoolean(), Settings.MOB_CHANGE_VINDICATOR_COST.getDouble()),
	ILLUSIONER(Settings.MOB_CHANGE_ILLUSIONER_ENABLED.getBoolean(), Settings.MOB_CHANGE_ILLUSIONER_COST.getDouble()),
	CREEPER(Settings.MOB_CHANGE_CREEPER_ENABLED.getBoolean(), Settings.MOB_CHANGE_CREEPER_COST.getDouble()),
	SKELETON(Settings.MOB_CHANGE_SKELETON_ENABLED.getBoolean(), Settings.MOB_CHANGE_SKELETON_COST.getDouble()),
	SPIDER(Settings.MOB_CHANGE_SPIDER_ENABLED.getBoolean(), Settings.MOB_CHANGE_SPIDER_COST.getDouble()),
	GIANT(Settings.MOB_CHANGE_GIANT_ENABLED.getBoolean(), Settings.MOB_CHANGE_GIANT_COST.getDouble()),
	ZOMBIE(Settings.MOB_CHANGE_ZOMBIE_ENABLED.getBoolean(), Settings.MOB_CHANGE_ZOMBIE_COST.getDouble()),
	SLIME(Settings.MOB_CHANGE_SLIME_ENABLED.getBoolean(), Settings.MOB_CHANGE_SLIME_COST.getDouble()),
	GHAST(Settings.MOB_CHANGE_GHAST_ENABLED.getBoolean(), Settings.MOB_CHANGE_GHAST_COST.getDouble()),
	ZOMBIFIED_PIGLIN(Settings.MOB_CHANGE_ZOMBIFIED_PIGLIN_ENABLED.getBoolean(), Settings.MOB_CHANGE_ZOMBIFIED_PIGLIN_COST.getDouble()),
	ENDERMAN(Settings.MOB_CHANGE_ENDERMAN_ENABLED.getBoolean(), Settings.MOB_CHANGE_ENDERMAN_COST.getDouble()),
	CAVE_SPIDER(Settings.MOB_CHANGE_CAVE_SPIDER_ENABLED.getBoolean(), Settings.MOB_CHANGE_CAVE_SPIDER_COST.getDouble()),
	SILVERFISH(Settings.MOB_CHANGE_SILVERFISH_ENABLED.getBoolean(), Settings.MOB_CHANGE_SILVERFISH_COST.getDouble()),
	BLAZE(Settings.MOB_CHANGE_BLAZE_ENABLED.getBoolean(), Settings.MOB_CHANGE_BLAZE_COST.getDouble()),
	MAGMA_CUBE(Settings.MOB_CHANGE_MAGMA_CUBE_ENABLED.getBoolean(), Settings.MOB_CHANGE_MAGMA_CUBE_COST.getDouble()),
	ENDER_DRAGON(Settings.MOB_CHANGE_ENDER_DRAGON_ENABLED.getBoolean(), Settings.MOB_CHANGE_ENDER_DRAGON_COST.getDouble()),
	WITHER(Settings.MOB_CHANGE_WITHER_ENABLED.getBoolean(), Settings.MOB_CHANGE_WITHER_COST.getDouble()),
	BAT(Settings.MOB_CHANGE_BAT_ENABLED.getBoolean(), Settings.MOB_CHANGE_BAT_COST.getDouble()),
	WITCH(Settings.MOB_CHANGE_WITCH_ENABLED.getBoolean(), Settings.MOB_CHANGE_WITCH_COST.getDouble()),
	ENDERMITE(Settings.MOB_CHANGE_ENDERMITE_ENABLED.getBoolean(), Settings.MOB_CHANGE_ENDERMITE_COST.getDouble()),
	GUARDIAN(Settings.MOB_CHANGE_GUARDIAN_ENABLED.getBoolean(), Settings.MOB_CHANGE_GUARDIAN_COST.getDouble()),
	SHULKER(Settings.MOB_CHANGE_SHULKER_ENABLED.getBoolean(), Settings.MOB_CHANGE_SHULKER_COST.getDouble()),
	PIG(Settings.MOB_CHANGE_PIG_ENABLED.getBoolean(), Settings.MOB_CHANGE_PIG_COST.getDouble()),
	SHEEP(Settings.MOB_CHANGE_SHEEP_ENABLED.getBoolean(), Settings.MOB_CHANGE_SHEEP_COST.getDouble()),
	COW(Settings.MOB_CHANGE_COW_ENABLED.getBoolean(), Settings.MOB_CHANGE_COW_COST.getDouble()),
	CHICKEN(Settings.MOB_CHANGE_CHICKEN_ENABLED.getBoolean(), Settings.MOB_CHANGE_CHICKEN_COST.getDouble()),
	SQUID(Settings.MOB_CHANGE_SQUID_ENABLED.getBoolean(), Settings.MOB_CHANGE_SQUID_COST.getDouble()),
	WOLF(Settings.MOB_CHANGE_WOLF_ENABLED.getBoolean(), Settings.MOB_CHANGE_WOLF_COST.getDouble()),
	MUSHROOM_COW(Settings.MOB_CHANGE_MUSHROOM_COW_ENABLED.getBoolean(), Settings.MOB_CHANGE_MUSHROOM_COW_COST.getDouble()),
	SNOWMAN(Settings.MOB_CHANGE_SNOWMAN_ENABLED.getBoolean(), Settings.MOB_CHANGE_SNOWMAN_COST.getDouble()),
	OCELOT(Settings.MOB_CHANGE_OCELOT_ENABLED.getBoolean(), Settings.MOB_CHANGE_OCELOT_COST.getDouble()),
	IRON_GOLEM(Settings.MOB_CHANGE_IRON_GOLEM_ENABLED.getBoolean(), Settings.MOB_CHANGE_IRON_GOLEM_COST.getDouble()),
	HORSE(Settings.MOB_CHANGE_HORSE_ENABLED.getBoolean(), Settings.MOB_CHANGE_HORSE_COST.getDouble()),
	RABBIT(Settings.MOB_CHANGE_RABBIT_ENABLED.getBoolean(), Settings.MOB_CHANGE_RABBIT_COST.getDouble()),
	POLAR_BEAR(Settings.MOB_CHANGE_POLAR_BEAR_ENABLED.getBoolean(), Settings.MOB_CHANGE_POLAR_BEAR_COST.getDouble()),
	LLAMA(Settings.MOB_CHANGE_LLAMA_ENABLED.getBoolean(), Settings.MOB_CHANGE_LLAMA_COST.getDouble()),
	PARROT(Settings.MOB_CHANGE_PARROT_ENABLED.getBoolean(), Settings.MOB_CHANGE_PARROT_COST.getDouble()),
	VILLAGER(Settings.MOB_CHANGE_VILLAGER_ENABLED.getBoolean(), Settings.MOB_CHANGE_VILLAGER_COST.getDouble()),
	TURTLE(Settings.MOB_CHANGE_TURTLE_ENABLED.getBoolean(), Settings.MOB_CHANGE_TURTLE_COST.getDouble()),
	PHANTOM(Settings.MOB_CHANGE_PHANTOM_ENABLED.getBoolean(), Settings.MOB_CHANGE_PHANTOM_COST.getDouble()),
	COD(Settings.MOB_CHANGE_COD_ENABLED.getBoolean(), Settings.MOB_CHANGE_COD_COST.getDouble()),
	SALMON(Settings.MOB_CHANGE_SALMON_ENABLED.getBoolean(), Settings.MOB_CHANGE_SALMON_COST.getDouble()),
	PUFFERFISH(Settings.MOB_CHANGE_PUFFERFISH_ENABLED.getBoolean(), Settings.MOB_CHANGE_PUFFERFISH_COST.getDouble()),
	TROPICAL_FISH(Settings.MOB_CHANGE_TROPICAL_FISH_ENABLED.getBoolean(), Settings.MOB_CHANGE_TROPICAL_FISH_COST.getDouble()),
	DROWNED(Settings.MOB_CHANGE_DROWNED_ENABLED.getBoolean(), Settings.MOB_CHANGE_DROWNED_COST.getDouble()),
	DOLPHIN(Settings.MOB_CHANGE_DOLPHIN_ENABLED.getBoolean(), Settings.MOB_CHANGE_DOLPHIN_COST.getDouble()),
	CAT(Settings.MOB_CHANGE_CAT_ENABLED.getBoolean(), Settings.MOB_CHANGE_CAT_COST.getDouble()),
	PANDA(Settings.MOB_CHANGE_PANDA_ENABLED.getBoolean(), Settings.MOB_CHANGE_PANDA_COST.getDouble()),
	PILLAGER(Settings.MOB_CHANGE_PILLAGER_ENABLED.getBoolean(), Settings.MOB_CHANGE_PILLAGER_COST.getDouble()),
	RAVAGER(Settings.MOB_CHANGE_RAVAGER_ENABLED.getBoolean(), Settings.MOB_CHANGE_RAVAGER_COST.getDouble()),
	TRADER_LLAMA(Settings.MOB_CHANGE_TRADER_LLAMA_ENABLED.getBoolean(), Settings.MOB_CHANGE_TRADER_LLAMA_COST.getDouble()),
	WANDERING_TRADER(Settings.MOB_CHANGE_WANDERING_TRADER_ENABLED.getBoolean(), Settings.MOB_CHANGE_WANDERING_TRADER_COST.getDouble()),
	FOX(Settings.MOB_CHANGE_FOX_ENABLED.getBoolean(), Settings.MOB_CHANGE_FOX_COST.getDouble()),
	BEE(Settings.MOB_CHANGE_BEE_ENABLED.getBoolean(), Settings.MOB_CHANGE_BEE_COST.getDouble()),
	HOGLIN(Settings.MOB_CHANGE_HOGLIN_ENABLED.getBoolean(), Settings.MOB_CHANGE_HOGLIN_COST.getDouble()),
	PIGLIN(Settings.MOB_CHANGE_PIGLIN_ENABLED.getBoolean(), Settings.MOB_CHANGE_PIGLIN_COST.getDouble()),
	STRIDER(Settings.MOB_CHANGE_STRIDER_ENABLED.getBoolean(), Settings.MOB_CHANGE_STRIDER_COST.getDouble()),
	ZOGLIN(Settings.MOB_CHANGE_ZOGLIN_ENABLED.getBoolean(), Settings.MOB_CHANGE_ZOGLIN_COST.getDouble()),
	PIGLIN_BRUTE(Settings.MOB_CHANGE_PIGLIN_BRUTE_ENABLED.getBoolean(), Settings.MOB_CHANGE_PIGLIN_BRUTE_COST.getDouble()),
	AXOLOTL(Settings.MOB_CHANGE_AXOLOTL_ENABLED.getBoolean(), Settings.MOB_CHANGE_AXOLOTL_COST.getDouble()),
	GLOW_SQUID(Settings.MOB_CHANGE_GLOW_SQUID_ENABLED.getBoolean(), Settings.MOB_CHANGE_GLOW_SQUID_COST.getDouble()),
	GOAT(Settings.MOB_CHANGE_GOAT_ENABLED.getBoolean(), Settings.MOB_CHANGE_GOAT_COST.getDouble());

	private boolean enabled;
	private double cost;
}
