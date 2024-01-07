package myshampooisdrunk.salvaris.mixin;

import net.minecraft.enchantment.DamageEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
    @Shadow @Final public int typeIndex;

    @Overwrite
    public int getMaxLevel(){
        if (((DamageEnchantment)(Object)this).typeIndex==0){
            return 3;
        }
        return 5;
    }
}
