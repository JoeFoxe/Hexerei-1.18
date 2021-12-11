package net.joefoxe.hexerei.util;

import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class WorldUtil {
    @SuppressWarnings("unchecked")
    @Nullable
    public static <T extends BlockEntity> T getBlockEntity(BlockGetter worldIn, BlockPos posIn, Class<T> type) {
        BlockEntity tileEntity = worldIn.getBlockEntity(posIn);
        if (tileEntity != null && tileEntity.getClass().isAssignableFrom(type)) {
            return (T) tileEntity;
        }
        return null;
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Nullable
    public static <T extends Entity> T getCachedEntity(@Nullable Level worldIn, Class<T> type, @Nullable T cached, @Nullable UUID uuid) {
        if ((cached == null || cached.isRemoved()) && uuid != null && worldIn instanceof ServerLevel) {
            Entity entity = ((ServerLevel) worldIn).getPlayerByUUID(uuid);
            if (entity != null && entity.getClass().isAssignableFrom(type)) {
                return (T) entity;
            } else {
                return null;
            }
        }
        return cached;
    }
    public static Optional<BlockPos> immutable(BlockPos pos) {return pos != null ? Optional.of(pos.immutable()) : Optional.empty();}
    public static Optional<BlockPos> immutable(Optional<BlockPos> pos) {
        return pos.map(BlockPos::immutable);
    }
}