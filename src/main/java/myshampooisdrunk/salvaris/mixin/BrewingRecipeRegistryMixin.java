package myshampooisdrunk.salvaris.mixin;

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
        if (outputPotion == Potions.WEAKNESS) {
            return Items.NETHERITE_SCRAP;
        } else if (outputPotion == Potions.STRENGTH) {
            return Items.DIAMOND_BLOCK;
        }
        return ingredient;
    }
}
