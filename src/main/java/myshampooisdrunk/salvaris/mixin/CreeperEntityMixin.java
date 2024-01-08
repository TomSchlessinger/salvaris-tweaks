package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import myshampooisdrunk.salvaris.world.WorldUtils;
import net.minecraft.client.render.entity.feature.SkinOverlayOwner;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity implements SkinOverlayOwner {

    @Shadow private int explosionRadius;

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method="explode",at=@At("HEAD"), cancellable = true)
    private void explode(CallbackInfo ci){
        float d = this.shouldRenderOverlay() ? 2 * Config.CHARGED_CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.get().floatValue() : Config.CREEPER_EXPLOSION_DAMAGE_MULTIPLIER.get().floatValue();
        float s = this.shouldRenderOverlay() ? 2 * Config.CHARGED_CREEPER_EXPLOSION_SIZE_MULTIPLIER.get().floatValue() : Config.CREEPER_EXPLOSION_SIZE_MULTIPLIER.get().floatValue();
        this.dead = true;
        WorldUtils.createExplosion(getWorld(),(CreeperEntity)(Object)this,
                getX(),getY(),getZ(), this.explosionRadius * s, false, World.ExplosionSourceType.MOB,d);
        this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 0, World.ExplosionSourceType.MOB);
        this.discard();
        this.spawnEffectsCloud();
        ci.cancel();
    }

    private void spawnEffectsCloud() {
        Collection<StatusEffectInstance> collection = this.getStatusEffects();
        if (!collection.isEmpty()) {
            AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
            areaEffectCloudEntity.setRadius(2.5f);
            areaEffectCloudEntity.setRadiusOnUse(-0.5f);
            areaEffectCloudEntity.setWaitTime(10);
            areaEffectCloudEntity.setDuration(areaEffectCloudEntity.getDuration() / 2);
            areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float)areaEffectCloudEntity.getDuration());
            for (StatusEffectInstance statusEffectInstance : collection) {
                areaEffectCloudEntity.addEffect(new StatusEffectInstance(statusEffectInstance));
            }
            this.getWorld().spawnEntity(areaEffectCloudEntity);
        }
    }
}
