package net.joefoxe.hexerei.util.message;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.tileentity.MixingCauldronTile;
import net.joefoxe.hexerei.tileentity.SageBurningPlateTile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EmitExtinguishParticlesPacket
{
    BlockPos pos;

    public EmitExtinguishParticlesPacket(BlockPos pos) {
        this.pos = pos;
    }

    public static void encode(EmitExtinguishParticlesPacket object, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(object.pos);
    }

    public static EmitExtinguishParticlesPacket decode(FriendlyByteBuf buffer) {
        return new EmitExtinguishParticlesPacket(buffer.readBlockPos());
    }

    public static void handle(EmitExtinguishParticlesPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Level world;
            if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                world = Hexerei.proxy.getLevel();
            }
            else {
                if (ctx.get().getSender() == null) return;
                world = ctx.get().getSender().level;
            }

            if(world.getBlockEntity(packet.pos) instanceof SageBurningPlateTile) {
                ((SageBurningPlateTile) world.getBlockEntity(packet.pos)).extinguishParticles();
            }
        });
        ctx.get().setPacketHandled(true);
    }

}