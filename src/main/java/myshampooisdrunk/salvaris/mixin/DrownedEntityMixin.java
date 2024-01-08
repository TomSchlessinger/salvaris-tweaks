package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DrownedEntity.class)
abstract class DrownedEntityMixin{
    @Inject(method = {"initEquipment"},
            at = {@At("HEAD")},
            cancellable=true
    )
    protected void initEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        if(Config.DISABLE_TRIDENTS.get()){
            if ((double)random.nextFloat() > 0.9) {
                int i = random.nextInt(16);
                if (i >= 10) {
                    ((DrownedEntity)(Object)this).equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.FISHING_ROD));
                }
            }
            ci.cancel();
        }

    }
}
