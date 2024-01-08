package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import myshampooisdrunk.salvaris.world.WorldUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntMinecartEntity.class)
public abstract class TntMinecartEntityMixin extends AbstractMinecartEntity {
    protected TntMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method="explode(Lnet/minecraft/entity/damage/DamageSource;D)V",at=@At("HEAD"), cancellable = true)
    private void explode(DamageSource damageSource, double power, CallbackInfo ci){
        TntMinecartEntity me = ((TntMinecartEntity)(Object)this);
        if (!me.getWorld().isClient) {
            double d = Math.sqrt(power);
            if (d > 5.0) {
                d = 5.0;
            }
            WorldUtils.createExplosion(getWorld(),me,damageSource,null,getX(),getY(),getZ(),
                    Config.TNT_CART_EXPLOSION_SIZE_MULTIPLIER.get().floatValue()*(float)(4f+this.random.nextDouble() * 1.5f * d),
                    false,World.ExplosionSourceType.TNT, Config.TNT_CART_EXPLOSION_DAMAGE_MULTIPLIER.get().floatValue());
            me.getWorld().createExplosion(me, damageSource, null, me.getX(), me.getY(), me.getZ(),
                    0f, false, World.ExplosionSourceType.TNT);
            me.discard();
        }
        ci.cancel();
    }
}
