package myshampooisdrunk.salvaris;

import com.mojang.datafixers.util.Pair;
import myshampooisdrunk.salvaris.config.Config;
import myshampooisdrunk.salvaris.mixin.StackChanger;
import myshampooisdrunk.salvaris.mixin.StatusEffectChanger;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.*;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Salvaris implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("salvaris-tweaks");

	@Override
	public void onInitialize() {
		Config.builder.read();
		//Config.builder.getItems().forEach(i -> LOGGER.info(i.getId() +" has the value of: " + i.get()));
		StatusEffects.STRENGTH.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, UUID.randomUUID().toString(),
				3D * Config.STRENGTH_EFFECT_MULTIPLIER.get().doubleValue(), EntityAttributeModifier.Operation.ADDITION);
		StatusEffects.WEAKNESS.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, UUID.randomUUID().toString(),
			-4D * Config.WEAKNESS_EFFECT_MULTIPLIER.get().doubleValue(), EntityAttributeModifier.Operation.ADDITION);
		if(Config.REWORKED_GOD_APPLE.get()){
			((StatusEffectChanger) FoodComponents.ENCHANTED_GOLDEN_APPLE).setStatusEffects(getStatusEffects(new StatusEffectInstance[] {
							new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,12000,0),
							new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 2),
							new StatusEffectInstance(StatusEffects.REGENERATION, 150, 4)}
					,new float[]{1F,1F,1F,1F}
			));
		}
		((StackChanger) Items.MINECART).setMaxCount(Config.MAX_MINECART_STACK.get().intValue());
		((StackChanger) Items.CHEST_MINECART).setMaxCount(Config.MAX_MINECART_STACK.get().intValue());
		((StackChanger) Items.FURNACE_MINECART).setMaxCount(Config.MAX_MINECART_STACK.get().intValue());
		((StackChanger) Items.HOPPER_MINECART).setMaxCount(Config.MAX_MINECART_STACK.get().intValue());
		((StackChanger) Items.TNT_MINECART).setMaxCount(Config.MAX_TNT_CART_STACK.get().intValue());
		((StackChanger) Items.OAK_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.CHERRY_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.BIRCH_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.ACACIA_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.DARK_OAK_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.JUNGLE_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.MANGROVE_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.SPRUCE_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.BAMBOO_RAFT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.OAK_CHEST_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.CHERRY_CHEST_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.BIRCH_CHEST_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.ACACIA_CHEST_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.DARK_OAK_CHEST_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.JUNGLE_CHEST_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.MANGROVE_CHEST_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.SPRUCE_CHEST_BOAT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.BAMBOO_CHEST_RAFT).setMaxCount(Config.MAX_BOAT_STACK.get().intValue());
		((StackChanger) Items.CAKE).setMaxCount(Config.MAX_CAKE_STACK.get().intValue());
		((StackChanger) Items.MUSHROOM_STEW).setMaxCount(Config.MAX_STEW_STACK.get().intValue());
		((StackChanger) Items.RABBIT_STEW).setMaxCount(Config.MAX_STEW_STACK.get().intValue());
		((StackChanger) Items.BEETROOT_SOUP).setMaxCount(Config.MAX_STEW_STACK.get().intValue());
		((StackChanger) Items.SUSPICIOUS_STEW).setMaxCount(Config.MAX_SUS_STEW_STACK.get().intValue());
		((StackChanger) Items.TOTEM_OF_UNDYING).setMaxCount(Config.MAX_TOTEM_STACK.get().intValue());
		((StackChanger) Items.COBWEB).setMaxCount(Config.MAX_COBWEB_STACK.get().intValue());
		((StackChanger) Items.ENDER_PEARL).setMaxCount(Config.MAX_ENDER_PEARL_STACK.get().intValue());
		((StackChanger) Items.POTION).setMaxCount(Config.MAX_POTION_STACK.get().intValue());
		((StackChanger) Items.SPLASH_POTION).setMaxCount(Config.MAX_SPLASH_POTION_STACK.get().intValue());
		((StackChanger) Items.LINGERING_POTION).setMaxCount(Config.MAX_LINGERING_POTION_STACK.get().intValue());
		((StackChanger) Items.BLACK_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.BLUE_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.BROWN_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.CYAN_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.GRAY_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.GREEN_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.LIGHT_BLUE_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.LIGHT_GRAY_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.LIME_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.MAGENTA_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.ORANGE_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.PINK_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.PURPLE_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.RED_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.WHITE_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.YELLOW_BED).setMaxCount(Config.MAX_BED_STACK.get().intValue());
		((StackChanger) Items.ENCHANTED_BOOK).setMaxCount(Config.MAX_ENCHANTED_BOOK_STACK.get().intValue());

	}

	public static List<Pair<StatusEffectInstance,Float>> getStatusEffects(StatusEffectInstance[] effects, float[] chance) {
		List list = new ArrayList<Pair<StatusEffectInstance,Float>>();
		for(int i = 0; i< effects.length;i++){
			list.add(new Pair<>(effects[i], chance[i]));
		}
		return list;
	}

}