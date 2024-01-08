package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThrowablePotionItem.class)
public abstract class ThrowablePotionItemMixin extends Item {
    public ThrowablePotionItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method="use",at=@At("HEAD"), cancellable = true)
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        ItemStack itemStack = user.getStackInHand(hand);
        if(Config.SPLASH_POTION_COOLDOWN.get().intValue() >= 0){
            if(Config.SPLASH_POTION_COOLDOWN.get().intValue() > 0){
                user.getItemCooldownManager().set(this,Config.SPLASH_POTION_COOLDOWN.get().intValue());
            }
        }else{
            cir.setReturnValue(TypedActionResult.pass(itemStack));
        }

    }
}
