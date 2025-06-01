package com.kushi.niobium.mixin;

import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.EntityType;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WardenEntity.class)
public abstract class WardenEntityMixin extends HostileEntity {

    @Unique
    private final ServerBossBar bossBar = new ServerBossBar(
            Text.translatable("The Warden"),
            BossBar.Color.BLUE,
            BossBar.Style.PROGRESS
    );

    @Unique
    private static final double RANGE = 50.0;

    protected WardenEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    private void onSpawnPacket(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CallbackInfoReturnable<EntityData> cir) {
        updateBossBarForPlayers();
    }


    @Inject(method = "mobTick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        WardenEntity warden = (WardenEntity) (Object) this;
        updateBossBarForPlayers();
        this.bossBar.setPercent(warden.getHealth() / warden.getMaxHealth());
    }

    @Inject(method = "getDeathSound", at = @At("RETURN"))
    private void onRemovedFromWorld(CallbackInfoReturnable<?> cir) {
        bossBar.clearPlayers();
    }

    @Unique
    private void updateBossBarForPlayers() {
        WardenEntity warden = (WardenEntity) (Object) this;
        if (warden.getWorld() instanceof ServerWorld world) {
            for (ServerPlayerEntity player : world.getPlayers()) {
                boolean inRange = player.squaredDistanceTo(warden) <= RANGE * RANGE;
                boolean alreadyHas = bossBar.getPlayers().contains(player);
                if (inRange && !alreadyHas) {
                    bossBar.addPlayer(player);
                } else if (!inRange && alreadyHas) {
                    bossBar.removePlayer(player);
                }
            }
        }
    }
}
