package myshampooisdrunk.salvaris.mixin;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface StackChanger {
    @Accessor("maxCount")
    void setMaxCount(int count);
}
