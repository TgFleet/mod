package com.anubhav.steamcraft.entity;

import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkHooks;

public class SteamTrainEntity extends BoatEntity {
    private int tickCounter = 0;

    public SteamTrainEntity(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        tickCounter++;

        if (!this.world.isRemote && this.canMoveForward()) {
            this.setMotion(this.getLookVec().scale(0.1));
        }

        if (world.isRemote && tickCounter % 10 == 0) {
            world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, 
                this.getPosX(), this.getPosY() + 1.5, this.getPosZ(), 0, 0.05, 0);
        }

        if (!world.isRemote && tickCounter % 100 == 0) {
            world.playSound(null, this.getPosition(), SoundEvents.BLOCK_NOTE_BLOCK_BIT, 
                this.getSoundCategory(), 3.0F, 1.0F);
        }
    }

    private boolean canMoveForward() {
        BlockPos ahead = this.getPosition().add(this.getLookVec().x, -1, this.getLookVec().z);
        return this.world.getBlockState(ahead).getBlock().getRegistryName().getPath().contains("rail");
    }

    @Override
    public boolean processInitialInteract(PlayerEntity player, net.minecraft.util.Hand hand) {
        if (!this.world.isRemote) {
            player.startRiding(this);
        }
        return true;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}