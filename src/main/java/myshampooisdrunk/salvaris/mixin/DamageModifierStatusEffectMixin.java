package myshampooisdrunk.salvaris.mixin;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.DamageModifierStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.effect.StatusEffects;

@Mixin(DamageModifierStatusEffect.class)
public class DamageModifierStatusEffectMixin extends StatusEffect {
    protected DamageModifierStatusEffectMixin(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Inject(method = "adjustModifierAmount", at = @At("RETURN"),cancellable = true)
    private void adjustModifierAmount(int amplifier, EntityAttributeModifier modifier, CallbackInfoReturnable<Double> cir){
        if (this.equals(StatusEffects.STRENGTH)) {
            cir.setReturnValue(cir.getReturnValueD() * 2/3F);
        }
        else if (this.equals(StatusEffects.WEAKNESS)) {
            cir.setReturnValue(cir.getReturnValueD() * 3/4F);
        }
    }
}
