package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "net.minecraft.entity.effect.InstantHealthOrDamageStatusEffect")
public class InstantHealthOrDamageStatusEffectMixin extends InstantStatusEffect {

    public InstantHealthOrDamageStatusEffectMixin(StatusEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);
    }

    @ModifyArg(method = "applyInstantEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;heal(F)V"))
    private float weakerInstantHealth(float value) {
        return value * Config.HEALING_EFFECT_MULTIPLIER.get().floatValue();
    }

    @ModifyArg(method = "applyInstantEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float weakerInstantDamage(float value) {
        return value * Config.HARMING_EFFECT_MULTIPLIER.get().floatValue();
    }

    @ModifyArg(method = "applyUpdateEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;heal(F)V"))
    private float weakerUpdateHealth(float value) {
        return value * Config.HEALING_EFFECT_MULTIPLIER.get().floatValue();
    }

    @ModifyArg(method = "applyUpdateEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float weakerUpdateDamage(float value) {
        return value * Config.HARMING_EFFECT_MULTIPLIER.get().floatValue();
    }
}
