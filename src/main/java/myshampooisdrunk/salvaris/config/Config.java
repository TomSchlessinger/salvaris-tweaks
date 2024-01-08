package myshampooisdrunk.salvaris.config;

import java.io.File;

public class Config {
   public static ConfigBuilder builder = new ConfigBuilder(new File("config","salvaris-tweaks.toml"));
   //explosion tweaks
   public static final ConfigItem<Number> BED_EXPLOSION_SIZE_MULTIPLIER;
   public static final ConfigItem<Number> BED_EXPLOSION_DAMAGE_MULTIPLIER;
   public static final ConfigItem<Boolean> ANCHOR_LEVEL_AFFECTS_STRENGTH;
   public static final ConfigItem<Number> ANCHOR_EXPLOSION_SIZE_MULTIPLIER;
   public static final ConfigItem<Number> ANCHOR_EXPLOSION_DAMAGE_MULTIPLIER;
   public static final ConfigItem<Number> TNT_CART_EXPLOSION_SIZE_MULTIPLIER;
   public static final ConfigItem<Number> TNT_CART_EXPLOSION_DAMAGE_MULTIPLIER;
   public static final ConfigItem<Number> CRYSTAL_EXPLOSION_SIZE_MULTIPLIER;
   public static final ConfigItem<Number> CRYSTAL_EXPLOSION_DAMAGE_MULTIPLIER;
   public static final ConfigItem<Number> CREEPER_EXPLOSION_SIZE_MULTIPLIER;
   public static final ConfigItem<Number> CREEPER_EXPLOSION_DAMAGE_MULTIPLIER;
   public static final ConfigItem<Number> CHARGED_CREEPER_EXPLOSION_SIZE_MULTIPLIER;
   public static final ConfigItem<Number> CHARGED_CREEPER_EXPLOSION_DAMAGE_MULTIPLIER;
   //enchant tweaks
   public static final ConfigItem<Number> MAX_SHARPNESS_LEVEL;
   public static final ConfigItem<Number> MAX_BANE_LEVEL;
   public static final ConfigItem<Number> MAX_SMITE_LEVEL;
   public static final ConfigItem<Number> MAX_RIPTIDE_LEVEL;
   public static final ConfigItem<Number> MAX_POWER_LEVEL;
   public static final ConfigItem<Number> MAX_PROTECTION_LEVEL;
   public static final ConfigItem<Number> MAX_PROJ_PROT_LEVEL;
   public static final ConfigItem<Number> MAX_BLAST_PROT_LEVEL;
   public static final ConfigItem<Number> MAX_FIRE_PROT_LEVEL;
   public static final ConfigItem<Number> MAX_FEATHER_FALLING_LEVEL;
   //potion tweaks
   public static final ConfigItem<Boolean> REWORKED_GOD_APPLE;
   public static final ConfigItem<Boolean> INVIS_HIDES_KILLER_NAME;
   public static final ConfigItem<Boolean> DISABLE_HARMING_ARROW;
   public static final ConfigItem<Number> HARMING_EFFECT_MULTIPLIER;
   public static final ConfigItem<Number> HEALING_EFFECT_MULTIPLIER;
   public static final ConfigItem<Number> WEAKNESS_EFFECT_MULTIPLIER;
   public static final ConfigItem<Number> STRENGTH_EFFECT_MULTIPLIER;
   public static final ConfigItem<Number> SPLASH_POTION_COOLDOWN;
   public static final ConfigItem<Boolean> STRENGTH_TAKES_DIAMOND_BLOCK;
   public static final ConfigItem<Boolean> WEAKNESS_TAKES_NETHERITE_SCRAP;
   //stack tweaks
   public static final ConfigItem<Number> MAX_BOAT_STACK;
   public static final ConfigItem<Number> MAX_STEW_STACK;
   public static final ConfigItem<Number> MAX_SUS_STEW_STACK;
   public static final ConfigItem<Number> MAX_POTION_STACK;
   public static final ConfigItem<Number> MAX_SPLASH_POTION_STACK;
   public static final ConfigItem<Number> MAX_LINGERING_POTION_STACK;
   public static final ConfigItem<Number> MAX_CAKE_STACK;
   public static final ConfigItem<Number> MAX_BED_STACK;
   public static final ConfigItem<Number> MAX_MINECART_STACK;
   public static final ConfigItem<Number> MAX_TNT_CART_STACK;
   public static final ConfigItem<Number> MAX_ENDER_PEARL_STACK;
   public static final ConfigItem<Number> MAX_TOTEM_STACK;
   public static final ConfigItem<Number> MAX_COBWEB_STACK;
   public static final ConfigItem<Number> MAX_ENCHANTED_BOOK_STACK;
   //other tweaks
   public static final ConfigItem<Boolean> DISABLE_TRIDENTS;
   public static final ConfigItem<Number> TOTEM_COOLDOWN;
   static{
      builder.comment("explosion tweaks");
      BED_EXPLOSION_SIZE_MULTIPLIER = builder.add("bedExplosionSizeMultiplier",1,"How large a bed explosion is");
      BED_EXPLOSION_DAMAGE_MULTIPLIER = builder.add("bedExplosionDamageMultiplier",1,"How much damage a bed explosion does");
      ANCHOR_LEVEL_AFFECTS_STRENGTH = builder.add("anchorChargeExplosionStrength",false,
              "a more charged anchor yields a larger and stronger explosion; if false strength is equal to 2.5 charges");
      ANCHOR_EXPLOSION_SIZE_MULTIPLIER = builder.add("anchorExplosionSizeMultiplier",1,"How large a respawn anchor explosion is");
      ANCHOR_EXPLOSION_DAMAGE_MULTIPLIER = builder.add("anchorExplosionDamageMultiplier",1,"How much damage a respawn anchor explosion does");
      CRYSTAL_EXPLOSION_SIZE_MULTIPLIER = builder.add("crystalExplosionSizeMultiplier",1,"How large an end crystal explosion is");
      CRYSTAL_EXPLOSION_DAMAGE_MULTIPLIER = builder.add("crystalExplosionDamageMultiplier",1,"How much damage an end crystal explosion does");
      TNT_CART_EXPLOSION_SIZE_MULTIPLIER = builder.add("tntMinecartExplosionSizeMultiplier",1,"How large a tnt minecart explosion is");
      TNT_CART_EXPLOSION_DAMAGE_MULTIPLIER = builder.add("tntMinecartExplosionDamageMultiplier",1,"How much damage a tnt minecart explosion does");
      CREEPER_EXPLOSION_SIZE_MULTIPLIER = builder.add("creeperExplosionSizeMultiplier",1,"How large a creeper explosion is");
      CREEPER_EXPLOSION_DAMAGE_MULTIPLIER = builder.add("creeperExplosionDamageMultiplier",1,"How much damage a creeper explosion does");
      CHARGED_CREEPER_EXPLOSION_SIZE_MULTIPLIER = builder.add("chargedCreeperExplosionSizeMultiplier",1,"How large a charged creeper explosion is");
      CHARGED_CREEPER_EXPLOSION_DAMAGE_MULTIPLIER = builder.add("chargedCreeperExplosionDamageMultiplier",1,"How much damage a charged creeper explosion does");
      builder.comment("enchant tweaks");
      MAX_SHARPNESS_LEVEL = builder.add("maxSharpnessLevel",5);
      MAX_BANE_LEVEL = builder.add("maxBaneLevel",5);
      MAX_SMITE_LEVEL = builder.add("maxSmiteLevel",5);
      MAX_PROTECTION_LEVEL = builder.add("maxProtectionLevel",4);
      MAX_BLAST_PROT_LEVEL = builder.add("maxBlastProtectionLevel",4);
      MAX_FIRE_PROT_LEVEL = builder.add("maxFireProtectionLevel",4);
      MAX_PROJ_PROT_LEVEL = builder.add("maxProjectileProtectionLevel",4);
      MAX_FEATHER_FALLING_LEVEL = builder.add("maxFeatherFallingLevel",4);
      MAX_POWER_LEVEL = builder.add("maxPowerLevel",5);
      MAX_RIPTIDE_LEVEL = builder.add("maxRiptideLevel",3);
      builder.comment("potion tweaks");
      REWORKED_GOD_APPLE = builder.add("reworkedGodApple",false,
              "reworks god apple to give fire res for 10 min, absorption 3 for 2 min, and regen 5 for 7.5 sec");
      INVIS_HIDES_KILLER_NAME = builder.add("invisHidesKiller", false,
              "makes it so that invisibility hides the name of someone in chat if they kill someone else");
      DISABLE_HARMING_ARROW = builder.add("noHarmingArrow",false,"disables harming arrows entirely");
      HARMING_EFFECT_MULTIPLIER = builder.add("harmingEffectMultiplier",1,"multiplies the amount of damage instant damage does");
      HEALING_EFFECT_MULTIPLIER = builder.add("healingEffectMultiplier",1,"multiplies the amount of damage instant health heals");
      WEAKNESS_EFFECT_MULTIPLIER = builder.add("strengthEffectMultiplier",1,"multiplies the amount of damage strength gives");
      STRENGTH_EFFECT_MULTIPLIER = builder.add("weaknessEffectMultiplier",1,"multiplies the amount of damage weakness takes away");
      SPLASH_POTION_COOLDOWN = builder.add("splashPotionCooldown",0,
              "gives splash/lingering potions a cooldown(in ticks); 0 for no cooldown, -1 to disable splash potions entirely");
      STRENGTH_TAKES_DIAMOND_BLOCK = builder.add("strengthTakesDiamondBlock",false,
              "makes strength take 1 diamond block instead of 1 blaze powder to make");
      WEAKNESS_TAKES_NETHERITE_SCRAP = builder.add("weaknessTakesNetheriteScrap",false,
              "makes weakness take 1 netherite scrap instead of 1 fermented spider eye to make");
      builder.comment("stack size tweaks");
      MAX_BED_STACK = builder.add("maxBedStack",1,"",1,64);
      MAX_CAKE_STACK = builder.add("maxCakeStack",1,"",1,64);
      MAX_BOAT_STACK = builder.add("maxBoatStack",1,"modifies chest boats too",1,64);
      MAX_MINECART_STACK = builder.add("maxMinecartStack",1,"applies to all minecarts except for tnt carts",1,64);
      MAX_TNT_CART_STACK = builder.add("maxTNTCartStack",1,"",1,64);
      MAX_STEW_STACK = builder.add("maxStewStack",1,"applies to all stews/soups except for sus stew",1,64);
      MAX_SUS_STEW_STACK = builder.add("maxSusStewStack",1,"",1,64);
      MAX_POTION_STACK = builder.add("maxPotionStack",1,"",1,64);
      MAX_SPLASH_POTION_STACK = builder.add("maxSplashPotionStack",1,"",1,64);
      MAX_LINGERING_POTION_STACK = builder.add("maxLingeringPotionStack",1,"",1,64);
      MAX_ENDER_PEARL_STACK = builder.add("maxEnderPearlStack",16,"",1,64);
      MAX_COBWEB_STACK = builder.add("maxCobwebStack",64,"",1,64);
      MAX_TOTEM_STACK = builder.add("maxTotemStack",1,"",1,64);
      MAX_ENCHANTED_BOOK_STACK = builder.add("maxEnchantedBookStack",1,"",1,64);
      builder.comment("other tweaks");
      DISABLE_TRIDENTS = builder.add("disableTridents",false,"disables tridents from being obtainable");
      TOTEM_COOLDOWN = builder.add("totemCooldown",0,"gives totems a cooldown; 0 for no cooldown, -1 for disable totem; time in ticks");
   }

}
