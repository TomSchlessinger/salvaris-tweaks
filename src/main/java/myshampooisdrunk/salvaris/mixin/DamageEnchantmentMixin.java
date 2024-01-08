package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.enchantment.DamageEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
    @Shadow @Final public int typeIndex;

    /**
     * @author MY SHAMPOO IS FUCKING DRUNK
     * @reason WHY THE FUCK DO I NEED TO SPECIFY A REASON???? I JUST WANT TO OVERRIDE THEIR FUCKING CODE
     */
    @Overwrite
    public int getMaxLevel(){
        return switch(((DamageEnchantment)(Object)this).typeIndex){
            case 0 -> Config.MAX_SHARPNESS_LEVEL.get().intValue();
            case 1 -> Config.MAX_SMITE_LEVEL.get().intValue();
            case 2 -> Config.MAX_BANE_LEVEL.get().intValue();
            default -> 5;
        };
    }
}
