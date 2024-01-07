package myshampooisdrunk.salvaris.mixin;

import net.minecraft.enchantment.PowerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(PowerEnchantment.class)
public class PowerEnchantmentMixin {
    @Overwrite
    public int getMaxLevel(){return 4;}
}
