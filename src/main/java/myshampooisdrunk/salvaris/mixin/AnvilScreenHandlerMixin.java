package myshampooisdrunk.salvaris.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicInteger;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {
    @Unique
    private final AtomicInteger resultCount = new AtomicInteger();

    @Inject(method = "updateResult", at = @At("RETURN"))
    private void afterUpdateResult(CallbackInfo info) {
        resultCount.set(((AnvilScreenHandler) (Object) this).getSlot(2).getStack().getCount());
    }

    @Redirect(method = "onTakeOutput", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/inventory/Inventory;setStack(ILnet/minecraft/item/ItemStack;)V", ordinal = 0))
    private void removeItemsFromFirstInputSlot(Inventory instance, int slot, ItemStack itemStack, @Share("resultCountSnapshot") LocalIntRef resultCountSnapshot) {
        resultCountSnapshot.set(resultCount.get());
        decrementOrRemove(instance, slot, resultCountSnapshot.get());
    }

    @Redirect(method = "onTakeOutput", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/inventory/Inventory;setStack(ILnet/minecraft/item/ItemStack;)V", ordinal = 3))
    private void removeItemsFromSecondInputSlot(Inventory instance, int slot, ItemStack itemStack, @Share("resultCountSnapshot") LocalIntRef resultCountSnapshot) {
        decrementOrRemove(instance, slot, resultCountSnapshot.get());
    }

    @Unique
    private void decrementOrRemove(
            Inventory inventory, int slot, int amount) {
        ItemStack currentStack = inventory.getStack(slot);

        if (currentStack.getCount() > amount) {
            ItemStack copy = currentStack.copy();
            copy.decrement(amount);
            inventory.setStack(slot, copy);
            return;
        }

        inventory.setStack(slot, ItemStack.EMPTY);
    }

    @ModifyExpressionValue(method = "updateResult", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/inventory/Inventory;getStack(I)Lnet/minecraft/item/ItemStack;", ordinal = 0))
    private ItemStack tweakFirstInputStack(ItemStack stack) {
        if (stack.getCount() > 1 && stack.isOf(Items.ENCHANTED_BOOK)) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            return copy;
        }

        return stack;
    }

    @ModifyExpressionValue(method = "updateResult", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/inventory/Inventory;getStack(I)Lnet/minecraft/item/ItemStack;", ordinal = 1))
    private ItemStack tweakSecondInputStack(ItemStack stack) {
        if (stack.getCount() > 1 && stack.isOf(Items.ENCHANTED_BOOK)) {
            ItemStack copy = stack.copy();
            copy.setCount(1);
            return copy;
        }

        return stack;
    }
}
