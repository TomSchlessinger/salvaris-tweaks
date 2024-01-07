package myshampooisdrunk.salvaris.mixin;

import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StatusEffect.class)
public class StatusEffectMixin{
    @ModifyArg(method = "applyInstantEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;heal(F)V"))
    private float weakerInstantHealth(float value) {
        return value * 3/4F;
    }

    @ModifyArg(method = "applyInstantEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float weakerInstantDamage(float value) {
        return value * 3/4F;
    }

    @ModifyArg(method = "applyUpdateEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;heal(F)V", ordinal = 1))
    private float weakerUpdateHealth(float value) {
        return value * 3/4F;
    }

    @ModifyArg(method = "applyUpdateEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", ordinal = 2))
    private float weakerUpdateDamage(float value) {
        return value * 3/4F;
    }
}
