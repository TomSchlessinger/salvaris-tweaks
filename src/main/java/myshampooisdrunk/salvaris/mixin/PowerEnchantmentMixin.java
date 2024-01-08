package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.enchantment.PowerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PowerEnchantment.class)
public class PowerEnchantmentMixin {
    /**
     * @author my shampoo is drunk
     * @reason b
     */
    @Overwrite
    public int getMaxLevel(){return Config.MAX_POWER_LEVEL.get().intValue();}
}
