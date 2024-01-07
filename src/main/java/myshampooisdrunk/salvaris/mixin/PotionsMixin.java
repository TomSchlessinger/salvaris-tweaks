package myshampooisdrunk.salvaris.mixin;

import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Potions.class)
public class PotionsMixin {
    @ModifyConstant(method = "<clinit>",slice = @Slice(from = @At(value = "CONSTANT",args = "stringValue=strong_poison",ordinal = 0),to=@At("TAIL")),  constant = @Constant(intValue = 432))
    private static int modify(int val){
        return 324;
    }

}
