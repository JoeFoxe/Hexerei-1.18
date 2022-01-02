package net.joefoxe.hexerei.util.message;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BroomAskForSyncPacket {
    int sourceId;

    public BroomAskForSyncPacket(Entity entity) {
        this.sourceId = entity.getId();
    }
    public BroomAskForSyncPacket(FriendlyByteBuf buf) {
        this.sourceId = buf.readInt();

    }

    public static void encode(BroomAskForSyncPacket object, FriendlyByteBuf buffer) {
        buffer.writeInt(object.sourceId);
    }

    public static BroomAskForSyncPacket decode(FriendlyByteBuf buffer) {
        return new BroomAskForSyncPacket(buffer);
    }

    public static void consume(BroomAskForSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Level world;
            if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                world = Hexerei.proxy.getLevel();
            }
            else {
                if (ctx.get().getSender() == null) return;
                world = ctx.get().getSender().level;
            }

            ((BroomEntity)world.getEntity(packet.sourceId)).sync();
        });
        ctx.get().setPacketHandled(true);
    }
}