package myshampooisdrunk.salvaris;

import com.mojang.datafixers.util.Pair;
import myshampooisdrunk.salvaris.mixin.StackChanger;
import myshampooisdrunk.salvaris.mixin.StatusEffectChanger;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.*;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Salvaris implements ModInitializer {

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("salvaris-tweaks");

	@Override
	public void onInitialize() {

		((StatusEffectChanger) FoodComponents.ENCHANTED_GOLDEN_APPLE).setStatusEffects(getStatusEffects(new StatusEffectInstance[] {
						new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,12000,0),
						new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 2),
						new StatusEffectInstance(StatusEffects.REGENERATION, 140, 4)}
				,new float[]{1F,1F,1F,1F}
		));

		((StackChanger) Items.MINECART).setMaxCount(16);
		((StackChanger) Items.CHEST_MINECART).setMaxCount(16);
		((StackChanger) Items.FURNACE_MINECART).setMaxCount(16);
		((StackChanger) Items.HOPPER_MINECART).setMaxCount(16);
		((StackChanger) Items.OAK_BOAT).setMaxCount(16);
		((StackChanger) Items.CHERRY_BOAT).setMaxCount(16);
		((StackChanger) Items.BIRCH_BOAT).setMaxCount(16);
		((StackChanger) Items.ACACIA_BOAT).setMaxCount(16);
		((StackChanger) Items.DARK_OAK_BOAT).setMaxCount(16);
		((StackChanger) Items.JUNGLE_BOAT).setMaxCount(16);
		((StackChanger) Items.MANGROVE_BOAT).setMaxCount(16);
		((StackChanger) Items.SPRUCE_BOAT).setMaxCount(16);
		((StackChanger) Items.BAMBOO_RAFT).setMaxCount(16);
		((StackChanger) Items.OAK_CHEST_BOAT).setMaxCount(16);
		((StackChanger) Items.CHERRY_CHEST_BOAT).setMaxCount(16);
		((StackChanger) Items.BIRCH_CHEST_BOAT).setMaxCount(16);
		((StackChanger) Items.ACACIA_CHEST_BOAT).setMaxCount(16);
		((StackChanger) Items.DARK_OAK_CHEST_BOAT).setMaxCount(16);
		((StackChanger) Items.JUNGLE_CHEST_BOAT).setMaxCount(16);
		((StackChanger) Items.MANGROVE_CHEST_BOAT).setMaxCount(16);
		((StackChanger) Items.SPRUCE_CHEST_BOAT).setMaxCount(16);
		((StackChanger) Items.BAMBOO_CHEST_RAFT).setMaxCount(16);
		((StackChanger) Items.CAKE).setMaxCount(16);
		((StackChanger) Items.MUSHROOM_STEW).setMaxCount(8);
		((StackChanger) Items.RABBIT_STEW).setMaxCount(8);
		((StackChanger) Items.BEETROOT_SOUP).setMaxCount(8);
		((StackChanger) Items.SUSPICIOUS_STEW).setMaxCount(4);

	}

	public static List<Pair<StatusEffectInstance,Float>> getStatusEffects(StatusEffectInstance[] effects, float[] chance) {
		List list = new ArrayList<Pair<StatusEffectInstance,Float>>();
		for(int i = 0; i< effects.length;i++){
			list.add(new Pair<>(effects[i], chance[i]));
		}
		return list;
	}

}