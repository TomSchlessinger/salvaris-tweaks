package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin {
    @Shadow @Final public ProtectionEnchantment.Type protectionType;

    /**
     * @author myshampooisdrunk
     * @reason so this feature will fucking work
     */
    @Overwrite
    public int getMaxLevel(){
        return switch(this.protectionType){
            case ALL -> Config.MAX_PROTECTION_LEVEL.get().intValue();
            case FALL -> Config.MAX_FEATHER_FALLING_LEVEL.get().intValue();
            case PROJECTILE -> Config.MAX_PROJ_PROT_LEVEL.get().intValue();
            case EXPLOSION -> Config.MAX_BLAST_PROT_LEVEL.get().intValue();
            case FIRE -> Config.MAX_FIRE_PROT_LEVEL.get().intValue();
        };
    }
}
