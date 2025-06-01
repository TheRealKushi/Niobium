package com.kushi.niobium.mixin;

import com.kushi.niobium.util.ModDamageTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class MixinStonecutterDamage {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onPlayerTick(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        World world = player.getWorld();

        // Get player's exact Y position
        double playerFeetY = player.getY() - 0.1;

        // Get four corner positions of the player's feet
        BlockPos[] checkPositions = new BlockPos[]{
                new BlockPos(MathHelper.floor(player.getX()), MathHelper.floor(playerFeetY), MathHelper.floor(player.getZ())),
                new BlockPos(MathHelper.floor(player.getX() + 0.3), MathHelper.floor(playerFeetY), MathHelper.floor(player.getZ())),
                new BlockPos(MathHelper.floor(player.getX() - 0.3), MathHelper.floor(playerFeetY), MathHelper.floor(player.getZ())),
                new BlockPos(MathHelper.floor(player.getX()), MathHelper.floor(playerFeetY), MathHelper.floor(player.getZ() + 0.3)),
                new BlockPos(MathHelper.floor(player.getX()), MathHelper.floor(playerFeetY), MathHelper.floor(player.getZ() - 0.3))
        };

        // Check if any of the positions are a stonecutter
        for (BlockPos pos : checkPositions) {
            BlockState blockState = world.getBlockState(pos);
            if (blockState.getBlock() == Blocks.STONECUTTER) {
                applyDamage(player);
                break;
            }
        }
    }

    @Unique
    private void applyDamage(ServerPlayerEntity player) {
        World world = player.getWorld();

        // Get custom damage type for the stonecutter
        RegistryEntry<DamageType> damageTypeEntry = world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(ModDamageTypes.STONECUTTER);

        // Create the damage source with the custom type
        DamageSource damageSource = new DamageSource(damageTypeEntry);

        // Apply damage
        player.damage(damageSource, 1.0f);

        // Play custom sound
        world.playSound(null, player.getBlockPos(), SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.PLAYERS, 0.8f, 0.8f);
    }
}