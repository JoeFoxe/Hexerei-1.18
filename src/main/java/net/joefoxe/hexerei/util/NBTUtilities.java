package net.joefoxe.hexerei.util;

import java.util.UUID;
import javax.annotation.Nullable;

import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class NBTUtilities {
    public static void putUniqueId(CompoundTag compound, String key, @Nullable UUID uuid) {
        if (uuid != null) {
            compound.putUUID(key, uuid);
        }
    }
    @Nullable
    public static UUID getUniqueId(CompoundTag compound, String key) {
        if (compound.hasUUID(key)) {
            return compound.getUUID(key);
        } else if (NBTUtilities.hasOldUniqueId(compound, key)) {
            return NBTUtilities.getOldUniqueId(compound, key);
        }
        return null;
    }
    public static UUID getOldUniqueId(CompoundTag compound, String key) {return new UUID(compound.getLong(key + "Most"), compound.getLong(key + "Least"));}
    public static boolean hasOldUniqueId(CompoundTag compound, String key) {return compound.contains(key + "Most", Tag.TAG_ANY_NUMERIC) && compound.contains(key + "Least", Tag.TAG_ANY_NUMERIC);}
    public static void removeOldUniqueId(CompoundTag compound, String key) {
        compound.remove(key + "Most");
        compound.remove(key + "Least");
    }
    public static void putResourceLocation(CompoundTag compound, String key, @Nullable ResourceLocation rl) {
        if (rl != null) {compound.putString(key, rl.toString());}
    }

}