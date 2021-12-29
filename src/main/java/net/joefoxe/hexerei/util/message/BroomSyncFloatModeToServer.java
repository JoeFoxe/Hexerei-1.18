package net.joefoxe.hexerei.util.message;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BroomSyncFloatModeToServer {
    int sourceId;
    boolean mode;

    public BroomSyncFloatModeToServer(Entity entity, boolean tag) {
        this.sourceId = entity.getId();
        this.mode = tag;
    }
    public BroomSyncFloatModeToServer(FriendlyByteBuf buf) {
        this.sourceId = buf.readInt();
        this.mode = buf.readBoolean();

    }

    public static void encode(BroomSyncFloatModeToServer object, FriendlyByteBuf buffer) {
        buffer.writeInt(object.sourceId);
        buffer.writeBoolean(object.mode);
    }

    public static BroomSyncFloatModeToServer decode(FriendlyByteBuf buffer) {
        return new BroomSyncFloatModeToServer(buffer);
    }

    public static void consume(BroomSyncFloatModeToServer packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Level world;
            if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                world = Hexerei.proxy.getLevel();
            }
            else {
                if (ctx.get().getSender() == null) return;
                world = ctx.get().getSender().level;
            }

            ((BroomEntity)world.getEntity(packet.sourceId)).setFloatMode(packet.mode);
        });
        ctx.get().setPacketHandled(true);
    }
}