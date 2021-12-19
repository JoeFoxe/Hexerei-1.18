package net.joefoxe.hexerei.util;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.util.message.EmitParticlesPacket;
import net.joefoxe.hexerei.util.message.IMessage;
import net.joefoxe.hexerei.util.message.MessageCountUpdate;
import net.joefoxe.hexerei.util.message.TESyncPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class HexereiPacketHandler {
    public static final String PROTOCOL_VERSION = "1";

    public static SimpleChannel instance;
    private static int nextId = 0;
    static int id = 0;

    public static void register()
    {
        instance = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Hexerei.MOD_ID, "network"))
                .networkProtocolVersion(() -> PROTOCOL_VERSION)
                .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                .simpleChannel();

        instance.registerMessage(++ id, MessageCountUpdate.class, MessageCountUpdate::encode, MessageCountUpdate::decode, MessageCountUpdate::handle);

        instance.registerMessage(++ id, EmitParticlesPacket.class, EmitParticlesPacket::encode, EmitParticlesPacket::decode, EmitParticlesPacket::handle);

        instance.registerMessage(
                ++ id,
                TESyncPacket.class,
                TESyncPacket::encode,
                TESyncPacket::decode,
                TESyncPacket::consume
        );
    }

    private static <T> void register(Class<T> clazz, IMessage<T> message)
    {
        instance.registerMessage(nextId++, clazz, message::encode, message::decode, message::handle);
    }
}