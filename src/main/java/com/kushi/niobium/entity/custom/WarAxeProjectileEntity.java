package com.kushi.niobium.entity.custom;

import com.kushi.niobium.entity.ModEntities;
import com.kushi.niobium.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.World;

public class WarAxeProjectileEntity extends PersistentProjectileEntity {
    private float rotation;
    public Vec2f groundedOffset;

    public WarAxeProjectileEntity(World world, PlayerEntity player) {
        super(ModEntities.WAR_AXE, player, world, new ItemStack(ModItems.WAR_AXE), null);
    }

    public WarAxeProjectileEntity(net.minecraft.entity.EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.WAR_AXE);
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if (rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    public boolean isGrounded() {
        return inGround;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        if (this.getOwner() instanceof PlayerEntity player && entity instanceof LivingEntity target) {
            if (player.getMainHandStack().getItem() == ModItems.WAR_AXE) {
                float damage = 11.0f;

                double attackDamage = player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                damage += (float) attackDamage;

                boolean isCriticalHit = player.getRandom().nextFloat() < 0.2f;
                if (isCriticalHit) {
                    damage *= 1.5f;
                }

                target.damage(player.getDamageSources().playerAttack(player), damage);

                player.getWorld().playSound(null, target.getX(), target.getY(), target.getZ(),
                        SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 1.0f, 1.0f);

                this.discard();
            }
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);

        Direction side = result.getSide();

        if (side == Direction.NORTH) {
            groundedOffset = new Vec2f(215f, 180f);
        } else if (side == Direction.SOUTH) {
            groundedOffset = new Vec2f(215f, 0f);
        } else if (side == Direction.WEST) {
            groundedOffset = new Vec2f(215f, -90f);
        } else if (side == Direction.EAST) {
            groundedOffset = new Vec2f(215f, 90f);
        } else if (side == Direction.UP) {
            groundedOffset = new Vec2f(115f, 180f);
        } else if (side == Direction.DOWN) {
            groundedOffset = new Vec2f(285f, 180f);
        }
    }
}
