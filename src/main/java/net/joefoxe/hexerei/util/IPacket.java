package net.joefoxe.hexerei.util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface IPacket<D> {

    void encode(D data, FriendlyByteBuf buf);

    D decode(FriendlyByteBuf buf);

    void handle(D data, Supplier<NetworkEvent.Context> ctx);
}