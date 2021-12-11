package net.joefoxe.hexerei.util;
import net.minecraft.resources.ResourceLocation;

public class HexereiConstants {

    public static final String MOD_ID = "hexerei";
    public static final String MOD_NAME = "hexerei";

    public static final String VANILLA_ID = "minecraft";
    public static final String VANILLA_NAME = "Minecraft";

    // Network
    public static final ResourceLocation CHANNEL_NAME = HexereiUtil.getResource("channel");
    public static final String PROTOCOL_VERSION = Integer.toString(1);

    // Storage
    public static final String STORAGE_PIGEON_RESPAWN = MOD_ID + "PigeonGraveyard";
    public static final String STORAGE_PIGEON_LOCATION = "pigeon_locations";

    public static class EntityState {
        public static final byte DEATH = 3;
        public static final byte PIGEON_SMOKE = 6;
        public static final byte PIGEON_HEARTS = 7;
    }
}