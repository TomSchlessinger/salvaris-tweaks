package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method="tryUseTotem", at = @At("HEAD"),cancellable = true)
    private void tryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir){
        if(Config.TOTEM_COOLDOWN.get().intValue() < 0){
            cir.setReturnValue(false);
            return;
        }
        else if (Config.TOTEM_COOLDOWN.get().intValue() == 0){
            return;
        }
        if ((LivingEntity)(Object)this instanceof ServerPlayerEntity player){
            ItemCooldownManager manager = player.getItemCooldownManager();
            if(!manager.isCoolingDown(Items.TOTEM_OF_UNDYING)){
                manager.set(Items.TOTEM_OF_UNDYING,Config.TOTEM_COOLDOWN.get().intValue());//second number is amount of seconds
            } else{
                cir.setReturnValue(false);
            }
        }
    }
}