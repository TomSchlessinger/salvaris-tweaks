package myshampooisdrunk.salvaris.mixin;

import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin {
    @Overwrite
    public int getMaxLevel(){
        return 3;
    }
}
