package com.kushi.niobium.mixin;

import com.kushi.niobium.item.ModItems;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

    @Inject(method = "attackBlock", at = @At("HEAD"))
    private void onBlockHit(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null) {
            ItemStack heldItem = client.player.getMainHandStack();

            // Check if the player is holding the mace
            if (heldItem.getItem() == Items.MACE) {
                World world = client.player.getWorld();

                // Play a custom sound (replace with your own if needed)
                world.playSound(
                        client.player, // Player who hears the sound
                        pos,           // Position of the block hit
                        SoundEvents.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, // Example sound (change if needed)
                        SoundCategory.PLAYERS,
                        0.8f, // Volume
                        0.8f  // Pitch
                );
            }
        }
    }
}
