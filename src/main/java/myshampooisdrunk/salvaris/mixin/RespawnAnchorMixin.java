package myshampooisdrunk.salvaris.mixin;

import myshampooisdrunk.salvaris.config.Config;
import myshampooisdrunk.salvaris.world.WorldUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static myshampooisdrunk.salvaris.mixin.RespawnAnchorBlockAccessor.callHasStillWater;

@Mixin(RespawnAnchorBlock.class)
public abstract class RespawnAnchorMixin {

    @Shadow @Final public static IntProperty CHARGES;

    @Inject(method="explode",at=@At("HEAD"),cancellable = true)
    private void explode(BlockState state, World world, BlockPos explodedPos, CallbackInfo ci) {
        int level = state.get(CHARGES);
        world.removeBlock(explodedPos, false);
        Stream<Direction> horizontalDirections = Direction.Type.HORIZONTAL.stream();
        horizontalDirections
                .map(explodedPos::offset)
                .anyMatch(pos -> callHasStillWater(pos, world));
        final boolean bl2 = world.getFluidState(explodedPos.up()).isIn(FluidTags.WATER);
        ExplosionBehavior explosionBehavior = new ExplosionBehavior() {
            public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
                return pos.equals(explodedPos) && bl2 ? Optional.of(Blocks.WATER.getBlastResistance()) : super.getBlastResistance(explosion, world, pos, blockState, fluidState);
            }
        };
        Vec3d vec3d = explodedPos.toCenterPos();
        //2.5 -> 5; 1 -> 3.5  2 -> 4.5, 3 -> 5.5, 4 -> 6.5; 2.5 + (n)
        if(Config.ANCHOR_LEVEL_AFFECTS_STRENGTH.get()){
            WorldUtils.createExplosion(world,null, world.getDamageSources().badRespawnPoint(vec3d), explosionBehavior,
                    vec3d, (2.5f + level) * Config.ANCHOR_EXPLOSION_SIZE_MULTIPLIER.get().floatValue(), true,
                    World.ExplosionSourceType.BLOCK,(level+1.5f)/4f * Config.ANCHOR_EXPLOSION_DAMAGE_MULTIPLIER.get().floatValue());
        }else{
            WorldUtils.createExplosion(world,null, world.getDamageSources().badRespawnPoint(vec3d), null,
                    vec3d, 5f * Config.ANCHOR_EXPLOSION_SIZE_MULTIPLIER.get().floatValue(), true,
                    World.ExplosionSourceType.BLOCK, Config.ANCHOR_EXPLOSION_DAMAGE_MULTIPLIER.get().floatValue());
        }
        ci.cancel();
        world.createExplosion(null, world.getDamageSources().badRespawnPoint(vec3d), explosionBehavior, vec3d, 0f, false, World.ExplosionSourceType.BLOCK);
    }


}
