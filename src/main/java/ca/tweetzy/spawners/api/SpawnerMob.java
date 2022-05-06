package ca.tweetzy.spawners.api;

import ca.tweetzy.spawners.settings.Translation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.EntityType;

/**
 * Date Created: May 05 2022
 * Time Created: 10:15 p.m.
 *
 * @author Kiran Hart
 */
@AllArgsConstructor
@Getter
public enum SpawnerMob {

	// passive
	OCELOT(EntityType.OCELOT, Translation.MOB_NAME_OCELOT.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/8c433c1347313b23b67eec92f8807aed2566ec29fd416bdf7a59c22596628355"),
	MUSHROOM_COW(EntityType.MUSHROOM_COW, Translation.MOB_NAME_MUSHROOM_COW.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/d0bc61b9757a7b83e03cd2507a2157913c2cf016e7c096a4d6cf1fe1b8db"),
	PIG(EntityType.PIG, Translation.MOB_NAME_PIG.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/1a371a06ea7858f89d27cc1055c317b23f105c9125bc516d3891aa4c835c299"),
	SHEEP(EntityType.SHEEP, Translation.MOB_NAME_SHEEP.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/292df216ecd27624ac771bacfbfe006e1ed84a79e9270be0f88e9c8791d1ece4"),
	COW(EntityType.COW, Translation.MOB_NAME_COW.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/82fa683a1c584a9723603548c8c03eaf47e8365bafc16af15b14814268c7c778"),
	CHICKEN(EntityType.CHICKEN, Translation.MOB_NAME_CHICKEN.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/65dc8c491f17e82e3ee075f09fbdea97edf6d3e7db1e4bb8b2001a80d79a5b1f"),
	SQUID(EntityType.SQUID, Translation.MOB_NAME_SQUID.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/464bdc6f600656511bef596c1a16aab1d3f5dbaae8bee19d5c04de0db21ce92c"),
	PARROT(EntityType.PARROT, Translation.MOB_NAME_PARROT.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/a700ea674a6fc540f60f2a08cfe90fbba8408b95d8b4c52f798ec78e19fbdde3"),
	AXOLOTL(EntityType.AXOLOTL, Translation.MOB_NAME_AXOLOTL.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/debf2b113f4a81370a1cf9d2504e8756b66deef79e9433187da774b96c9f35ba"),
	GLOW_SQUID(EntityType.GLOW_SQUID, Translation.MOB_NAME_GLOW_SQUID.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/1501331fe83df67f234ddd6c089f3231d3b60228d37235ca9eb1a91ed6873a82"),
	VILLAGER(EntityType.VILLAGER, Translation.MOB_NAME_VILLAGER.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/f605a50dd59e5227701a51970ab0479ef57fdad9099073538fa713b26cce9fc3"),
	WANDERING_TRADER(EntityType.WANDERING_TRADER, Translation.MOB_NAME_WANDERING_TRADER.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/499d585a9abf59fae277bb684d24070cef21e35609a3e18a9bd5dcf73a46ab93"),
	TURTLE(EntityType.TURTLE, Translation.MOB_NAME_TURTLE.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/0a4050e7aacc4539202658fdc339dd182d7e322f9fbcc4d5f99b5718a"),
	SALMON(EntityType.SALMON, Translation.MOB_NAME_SALMON.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/b770d917d1ccc12f3a1cf73fe7de8c6548f4a842086923c7bb4446bcc7aaebfa"),
	TROPICAL_FISH(EntityType.TROPICAL_FISH, Translation.MOB_NAME_TROPICAL_FISH.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/75ba393b926ee69a14d4d853b25d285a868b16c56c919d2b824413e6566d4e55"),
	COD(EntityType.COD, Translation.MOB_NAME_COD.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/7892d7dd6aadf35f86da27fb63da4edda211df96d2829f691462a4fb1cab0"),
	SKELETON_HORSE(EntityType.SKELETON_HORSE, Translation.MOB_NAME_SKELETON_HORSE.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/47effce35132c86ff72bcae77dfbb1d22587e94df3cbc2570ed17cf8973a"),
	ZOMBIE_HORSE(EntityType.ZOMBIE_HORSE, Translation.MOB_NAME_ZOMBIE_HORSE.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/d22950f2d3efddb18de86f8f55ac518dce73f12a6e0f8636d551d8eb480ceec"),
	DONKEY(EntityType.DONKEY, Translation.MOB_NAME_DONKEY.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/399bb50d1a214c394917e25bb3f2e20698bf98ca703e4cc08b42462df309d6e6"),
	MULE(EntityType.MULE, Translation.MOB_NAME_MULE.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/a0486a742e7dda0bae61ce2f55fa13527f1c3b334c57c034bb4cf132fb5f5f"),
	BAT(EntityType.BAT, Translation.MOB_NAME_BAT.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/6681a72da7263ca9aef066542ecca7a180c40e328c0463fcb114cb3b83057552"),
	CAT(EntityType.CAT, Translation.MOB_NAME_CAT.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/e566de34d04d14b39fcb15daaa7b547fb238e7d3187b424d426958adc514033"),
	SNOWMAN(EntityType.SNOWMAN, Translation.MOB_NAME_SNOWMAN.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/9aed9fe4ed0893e325f4fbd32b093c1cc562cba27ff73359d356f1c288e441f9"),
	HORSE(EntityType.HORSE, Translation.MOB_NAME_HORSE.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/679d0cf0615ff81b1d5d0b791af85494ab6b5af971de18a46a8f911b3b59736e"),
	RABBIT(EntityType.RABBIT, Translation.MOB_NAME_RABBIT.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/99f083ddd0af6c44553dc5ee8d113e07240407e2e8bf841ad44c881b690b668c"),
	FOX(EntityType.FOX, Translation.MOB_NAME_FOX.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/e2f62858a09b09608397a33ad2de0341d39ddbd70fd480e5e83739e0cf79b212"),
	PUFFERFISH(EntityType.PUFFERFISH, Translation.MOB_NAME_PUFFERFISH.getString(), MobBehaviour.PASSIVE, "https://textures.minecraft.net/texture/292350c9f0993ed54db2c7113936325683ffc20104a9b622aa457d37e708d931"),

	// neutral
	LLAMA(EntityType.LLAMA, Translation.MOB_NAME_LLAMA.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/6d2ffce9a174fe1c084e2d82052182d94f95ed436b75ff7ea7a4e94d94c72d8a"),
	WOLF(EntityType.WOLF, Translation.MOB_NAME_WOLF.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/28d408842e76a5a454dc1c7e9ac5c1a8ac3f4ad34d6973b5275491dff8c5c251"),
	IRON_GOLEM(EntityType.IRON_GOLEM, Translation.MOB_NAME_IRON_GOLEM.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/b69d0d4711153a089c5567a749b27879c769d3bdcea6fda9d6f66e93dd8c4512"),
	ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, Translation.MOB_NAME_ZOMBIFIED_PIGLIN.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/93c3b28bf7afa4c60431b549f0d1518d74a771c06ede12634c554f1601f2ddd1"),
	TRADER_LLAMA(EntityType.TRADER_LLAMA, Translation.MOB_NAME_TRADER_LLAMA.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/5a4eed85697c78f462c4eb5653b05b76576c1178f704f3c5676f505d8f3983b4"),
	DOLPHIN(EntityType.DOLPHIN, Translation.MOB_NAME_DOLPHIN.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/279413c788c7f450234bdab0cf0d0291c57f730e380c6d4c7746fde15928381"),
	PANDA(EntityType.PANDA, Translation.MOB_NAME_PANDA.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/2266672e4ba54c5c0be59e0461a6e32ea0a7cf115d711867d2922ee9ca523690"),
	POLAR_BEAR(EntityType.POLAR_BEAR, Translation.MOB_NAME_POLAR_BEAR.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/c4fe926922fbb406f343b34a10bb98992cee4410137d3f88099427b22de3ab90"),
	BEE(EntityType.BEE, Translation.MOB_NAME_BEE.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/90bc9dbb4404b800f8cf0256220ff74b0b71dba8b66600b6734f4d63361618f5"),
	PIGLIN(EntityType.PIGLIN, Translation.MOB_NAME_PIGLIN.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/83e2c2d66967d4715bbf6f14d2205a637d3d01b3fd106311c60737802f2bd757"),
	GOAT(EntityType.GOAT, Translation.MOB_NAME_GOAT.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/c7967cec6ee58153c90783a0f3e0541db22e6e64d5ab921b1dbbc318de3f81ee"),
	ENDERMAN(EntityType.ENDERMAN, Translation.MOB_NAME_ENDERMAN.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/aacb357709d8cdf1cd9c9dbe313e7bab3276ae84234982e93e13839ab7cc5d16"),
	CAVE_SPIDER(EntityType.CAVE_SPIDER, Translation.MOB_NAME_CAVE_SPIDER.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/41645dfd77d09923107b3496e94eeb5c30329f97efc96ed76e226e98224"),
	SPIDER(EntityType.SPIDER, Translation.MOB_NAME_SPIDER.getString(), MobBehaviour.NEUTRAL, "https://textures.minecraft.net/texture/5f7e82446fab1e41577ba70ab40e290ef841c245233011f39459ac6f852c8331"),
	
	// hostile
	ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, Translation.MOB_NAME_ELDER_GUARDIAN.getString(), MobBehaviour.HOSTILE, ""),
	WITHER_SKELETON(EntityType.WITHER_SKELETON, Translation.MOB_NAME_WITHER_SKELETON.getString(), MobBehaviour.HOSTILE, ""),
	STRAY(EntityType.STRAY, Translation.MOB_NAME_STRAY.getString(), MobBehaviour.HOSTILE, ""),
	HUSK(EntityType.HUSK, Translation.MOB_NAME_HUSK.getString(), MobBehaviour.HOSTILE, ""),
	ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, Translation.MOB_NAME_ZOMBIE_VILLAGER.getString(), MobBehaviour.HOSTILE, ""),
	EVOKER(EntityType.EVOKER, Translation.MOB_NAME_EVOKER.getString(), MobBehaviour.HOSTILE, ""),
	VEX(EntityType.VEX, Translation.MOB_NAME_VEX.getString(), MobBehaviour.HOSTILE, ""),
	VINDICATOR(EntityType.VINDICATOR, Translation.MOB_NAME_VINDICATOR.getString(), MobBehaviour.HOSTILE, ""),
	ILLUSIONER(EntityType.ILLUSIONER, Translation.MOB_NAME_ILLUSIONER.getString(), MobBehaviour.HOSTILE, ""),
	CREEPER(EntityType.CREEPER, Translation.MOB_NAME_CREEPER.getString(), MobBehaviour.HOSTILE, ""),
	SKELETON(EntityType.SKELETON, Translation.MOB_NAME_SKELETON.getString(), MobBehaviour.HOSTILE, ""),
	GIANT(EntityType.GIANT, Translation.MOB_NAME_GIANT.getString(), MobBehaviour.HOSTILE, ""),
	ZOMBIE(EntityType.ZOMBIE, Translation.MOB_NAME_ZOMBIE.getString(), MobBehaviour.HOSTILE, ""),
	SLIME(EntityType.SLIME, Translation.MOB_NAME_SLIME.getString(), MobBehaviour.HOSTILE, ""),
	GHAST(EntityType.GHAST, Translation.MOB_NAME_GHAST.getString(), MobBehaviour.HOSTILE, ""),
	SILVERFISH(EntityType.SILVERFISH, Translation.MOB_NAME_SILVERFISH.getString(), MobBehaviour.HOSTILE, ""),
	BLAZE(EntityType.BLAZE, Translation.MOB_NAME_BLAZE.getString(), MobBehaviour.HOSTILE, ""),
	MAGMA_CUBE(EntityType.MAGMA_CUBE, Translation.MOB_NAME_MAGMA_CUBE.getString(), MobBehaviour.HOSTILE, ""),
	ENDER_DRAGON(EntityType.ENDER_DRAGON, Translation.MOB_NAME_ENDER_DRAGON.getString(), MobBehaviour.HOSTILE, ""),
	WITHER(EntityType.WITHER, Translation.MOB_NAME_WITHER.getString(), MobBehaviour.HOSTILE, ""),
	WITCH(EntityType.WITCH, Translation.MOB_NAME_WITCH.getString(), MobBehaviour.HOSTILE, ""),
	ENDERMITE(EntityType.ENDERMITE, Translation.MOB_NAME_ENDERMITE.getString(), MobBehaviour.HOSTILE, ""),
	GUARDIAN(EntityType.GUARDIAN, Translation.MOB_NAME_GUARDIAN.getString(), MobBehaviour.HOSTILE, ""),
	SHULKER(EntityType.SHULKER, Translation.MOB_NAME_SHULKER.getString(), MobBehaviour.HOSTILE, ""),
	PHANTOM(EntityType.PHANTOM, Translation.MOB_NAME_PHANTOM.getString(), MobBehaviour.HOSTILE, ""),
	DROWNED(EntityType.DROWNED, Translation.MOB_NAME_DROWNED.getString(), MobBehaviour.HOSTILE, ""),
	PILLAGER(EntityType.PILLAGER, Translation.MOB_NAME_PILLAGER.getString(), MobBehaviour.HOSTILE, ""),
	RAVAGER(EntityType.RAVAGER, Translation.MOB_NAME_RAVAGER.getString(), MobBehaviour.HOSTILE, ""),
	HOGLIN(EntityType.HOGLIN, Translation.MOB_NAME_HOGLIN.getString(), MobBehaviour.HOSTILE, ""),
	STRIDER(EntityType.STRIDER, Translation.MOB_NAME_STRIDER.getString(), MobBehaviour.HOSTILE, ""),
	ZOGLIN(EntityType.ZOGLIN, Translation.MOB_NAME_ZOGLIN.getString(), MobBehaviour.HOSTILE, ""),
	PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE, Translation.MOB_NAME_PIGLIN_BRUTE.getString(), MobBehaviour.HOSTILE, ""),

	;

	final EntityType entityType;
	final String mobName;
	final MobBehaviour behaviour;
	final String headTexture;

}
