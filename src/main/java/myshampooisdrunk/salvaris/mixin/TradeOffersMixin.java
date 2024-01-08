package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(TradeOffers.SellPotionHoldingItemFactory.class)
public class TradeOffersMixin {
    @ModifyVariable(method = "create", at = @At(value = "STORE", ordinal = 0))
    private List<Potion> modifyPotionList(List<Potion> list, Entity entity, Random random) {
        if(Config.DISABLE_HARMING_ARROW.get()){
            return Registries.POTION.stream().filter(
                    potion -> !potion.getEffects().isEmpty() && BrewingRecipeRegistry.isBrewable(potion) && (potion != Potions.STRONG_HARMING)).collect(Collectors.toList()
            );
        }
        return Registries.POTION.stream().filter(potion -> !potion.getEffects().isEmpty() && BrewingRecipeRegistry.isBrewable(potion)).collect(Collectors.toList());

    }

}
