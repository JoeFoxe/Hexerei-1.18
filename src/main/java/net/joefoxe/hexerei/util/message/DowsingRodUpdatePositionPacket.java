package net.joefoxe.hexerei.util.message;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.joefoxe.hexerei.item.custom.DowsingRodItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DowsingRodUpdatePositionPacket {
    ItemStack itemStack;
    BlockPos blockPos;
    Boolean swampMode;

    public DowsingRodUpdatePositionPacket(ItemStack itemStack, BlockPos blockPos, Boolean swampMode) {
        this.itemStack = itemStack;
        this.blockPos = blockPos;
        this.swampMode = swampMode;
    }
    public DowsingRodUpdatePositionPacket(FriendlyByteBuf buf) {
        this.itemStack = buf.readItem();
        this.blockPos = buf.readBlockPos();
        this.swampMode = buf.readBoolean();

    }

    public static void encode(DowsingRodUpdatePositionPacket object, FriendlyByteBuf buffer) {
        buffer.writeItem(object.itemStack);
        buffer.writeBlockPos(object.blockPos);
        buffer.writeBoolean(object.swampMode);

    }

    public static DowsingRodUpdatePositionPacket decode(FriendlyByteBuf buffer) {
        return new DowsingRodUpdatePositionPacket(buffer);
    }

    public static void consume(DowsingRodUpdatePositionPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Level world;
            if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT) {
                world = Hexerei.proxy.getLevel();
            }
            else {
                if (ctx.get().getSender() == null) return;
                world = ctx.get().getSender().level;
            }

            ((DowsingRodItem)packet.itemStack.getItem()).nearestPos = packet.blockPos;
            ((DowsingRodItem)packet.itemStack.getItem()).swampMode = packet.swampMode;



//            ((BroomEntity)world.getEntity(packet.itemStack)).damageBrush();
        });
        ctx.get().setPacketHandled(true);
    }
}