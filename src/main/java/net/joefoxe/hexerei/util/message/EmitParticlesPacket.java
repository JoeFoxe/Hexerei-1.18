package net.joefoxe.hexerei.util.message;

import io.netty.buffer.ByteBuf;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.tileentity.HerbJarTile;
import net.joefoxe.hexerei.tileentity.MixingCauldronTile;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EmitParticlesPacket
{
    BlockPos pos;
    int emitParticles;
    boolean spout;

    public EmitParticlesPacket(BlockPos pos, int emitParticles, boolean spout) {
        this.pos = pos;
        this.emitParticles = emitParticles;
        this.spout = spout;
    }

    public static void encode(EmitParticlesPacket object, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(object.pos);
        buffer.writeInt(object.emitParticles);
        buffer.writeBoolean(object.spout);
    }

    public static EmitParticlesPacket decode(FriendlyByteBuf buffer) {
        return new EmitParticlesPacket(buffer.readBlockPos(), buffer.readInt(), buffer.readBoolean());
    }

    public static void handle(EmitParticlesPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Level world;
            if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                world = Hexerei.proxy.getLevel();
            }
            else {
                if (ctx.get().getSender() == null) return;
                world = ctx.get().getSender().level;
            }

            if(world.getBlockEntity(packet.pos) instanceof MixingCauldronTile) {
                ((MixingCauldronTile) world.getBlockEntity(packet.pos)).emitParticles = packet.emitParticles;
                ((MixingCauldronTile) world.getBlockEntity(packet.pos)).emitParticleSpout = packet.spout;
                world.getBlockEntity(packet.pos).setChanged();
            }
        });
        ctx.get().setPacketHandled(true);
    }

}