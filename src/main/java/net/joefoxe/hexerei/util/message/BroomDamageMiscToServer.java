package net.joefoxe.hexerei.util.message;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class BroomDamageMiscToServer {
    int sourceId;
    float rotation;

    public BroomDamageMiscToServer(Entity entity) {
        this.sourceId = entity.getId();
    }
    public BroomDamageMiscToServer(FriendlyByteBuf buf) {
        this.sourceId = buf.readInt();

    }

    public static void encode(BroomDamageMiscToServer object, FriendlyByteBuf buffer) {
        buffer.writeInt(object.sourceId);
    }

    public static BroomDamageMiscToServer decode(FriendlyByteBuf buffer) {
        return new BroomDamageMiscToServer(buffer);
    }

    public static void consume(BroomDamageMiscToServer packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Level world;
            if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                world = Hexerei.proxy.getLevel();
            }
            else {
                if (ctx.get().getSender() == null) return;
                world = ctx.get().getSender().level;
            }

            ((BroomEntity)world.getEntity(packet.sourceId)).damageMisc();
        });
        ctx.get().setPacketHandled(true);
    }
}