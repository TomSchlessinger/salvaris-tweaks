package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.enchantment.RiptideEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RiptideEnchantment.class)
public class RiptideEnchantmentMixin {
    /**
     * @author SHAMPOO
     * @reason A
     */
    @Overwrite
    public int getMaxLevel(){
        return Config.MAX_RIPTIDE_LEVEL.get().intValue();
    }
}
