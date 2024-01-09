package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingRecipeRegistryMixin {

    @ModifyVariable(
            method = "registerPotionRecipe",
            at = @At("HEAD"),
            argsOnly = true
    )
    private static Item modifyPotionIngredient(Item ingredient, Potion inputPotion, Item inputItem, Potion outputPotion) {
        if(inputPotion == Potions.WATER && ingredient == Items.GLISTERING_MELON_SLICE && outputPotion == Potions.MUNDANE){
            Config.builder.readValue(Config.WEAKNESS_TAKES_NETHERITE_SCRAP);
            Config.builder.readValue(Config.STRENGTH_TAKES_DIAMOND_BLOCK);
        }
        if (outputPotion == Potions.WEAKNESS && Config.WEAKNESS_TAKES_NETHERITE_SCRAP.get()) {
            return Items.NETHERITE_SCRAP;
        } else if (outputPotion == Potions.STRENGTH && Config.STRENGTH_TAKES_DIAMOND_BLOCK.get()) {
            return Items.DIAMOND_BLOCK;
        }
        return ingredient;
    }
}
