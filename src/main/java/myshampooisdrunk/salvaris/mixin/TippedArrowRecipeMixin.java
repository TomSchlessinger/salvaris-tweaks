package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.TippedArrowRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(TippedArrowRecipe.class)
public class TippedArrowRecipeMixin {
    @Inject(at = @At("HEAD"), method = "craft*",cancellable = true)
    public void modifiedCraft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager, CallbackInfoReturnable<ItemStack> cir){
        if(Config.DISABLE_HARMING_ARROW.get()){
            ItemStack itemStack = recipeInputInventory.getStack(1 + recipeInputInventory.getWidth());
            if (itemStack.isOf(Items.LINGERING_POTION)) {
                Potion pot = PotionUtil.getPotion(itemStack);
                if(pot == Potions.STRONG_HARMING) {
                    cir.setReturnValue(ItemStack.EMPTY);
                } else {
                    ItemStack itemStack2 = new ItemStack(Items.TIPPED_ARROW, 8);
                    PotionUtil.setPotion(itemStack2, PotionUtil.getPotion(itemStack));
                    PotionUtil.setCustomPotionEffects(itemStack2, PotionUtil.getCustomPotionEffects(itemStack));
                    cir.setReturnValue(itemStack2);
                }
            }
            cir.cancel();
        }

    }
}
