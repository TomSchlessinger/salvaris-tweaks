package myshampooisdrunk.salvaris.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.StewItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StewItem.class)
public class StewItemMixin extends Item {
    public StewItemMixin(Settings settings){
        super(settings);
    }
    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    private void use(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
        ItemStack item = super.finishUsing(stack,world,user);
        cir.setReturnValue(new ItemStack(Items.BOWL));
        if(!stack.isEmpty() && user instanceof PlayerEntity p){
            if(!p.getAbilities().creativeMode){
                p.getInventory().offerOrDrop(new ItemStack(Items.BOWL));
            }
            cir.setReturnValue(item);
        }
        cir.cancel();
    }
}
