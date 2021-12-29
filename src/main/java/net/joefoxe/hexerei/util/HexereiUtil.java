package net.joefoxe.hexerei.util;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IRegistryDelegate;
import net.minecraftforge.registries.RegistryObject;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class HexereiUtil {

    private static final DecimalFormat dfShort = new DecimalFormat("0.0");
    private static final DecimalFormat dfShortDouble = new DecimalFormat("0.00");

    public static String format1DP(double value) {
        return HexereiUtil.dfShort.format(value);
    }

    public static String format2DP(double value) {
        return HexereiUtil.dfShortDouble.format(value);
    }

    public static boolean isPointInRegion(int x, int y, int width, int height, double mouseX, double mouseY) {
        return mouseX >= x - 1 && mouseX < x + width + 1 && mouseY >= y - 1 && mouseY < y + height + 1;
    }

    public static float[] rgbIntToFloatArray(int rgbInt) {
        int r = (rgbInt >> 16) & 255;
        int g = (rgbInt >> 8) & 255;
        int b = (rgbInt >> 0) & 255;

        return new float[] {r / 255F, g / 255F, b / 255F};
    }

    public static int[] rgbIntToIntArray(int rgbInt) {
        int r = (rgbInt >> 16) & 255;
        int g = (rgbInt >> 8) & 255;
        int b = (rgbInt >> 0) & 255;

        return new int[] {r, g, b};
    }

    public static boolean entityIsHostile(Entity entity) {
        if (entity.getType().getCategory().equals(MobCategory.MONSTER)) {
            return true;
        }

        return false;
    }

    public static List<BlockPos> getAllTileEntityPositionsNearby(BlockEntityType<?> te, Integer radius, Level world, Entity entity) {
        BlockPos entitypos = entity.blockPosition();

        List<BlockPos> nearby = new ArrayList<BlockPos>();
        List<BlockEntity> tiles = getTileEntitiesAroundPosition(world, entitypos, radius);

        for (BlockEntity tile : tiles) {
            BlockEntityType<?> tileType = tile.getType();
            if (tileType == null) {
                continue;
            }

            if (tileType.equals(te)) {
                BlockPos tilePos = tile.getBlockPos();
                if (tilePos.closerThan(entity.position(), radius)) {
                    nearby.add(tile.getBlockPos());
                }
            }
        }

        return nearby;
    }

    private static List<BlockEntity> getTileEntitiesAroundPosition(Level world, BlockPos pos, Integer radius) {
        List<BlockEntity> blockentities = new ArrayList<BlockEntity>();

        int chunkradius = (int)Math.ceil(radius/16.0);
        int chunkPosX = pos.getX() >> 4;
        int chunkPosZ = pos.getZ() >> 4;

        for (int x = chunkPosX - chunkradius; x < chunkPosX + chunkradius; x++) {
            for (int z = chunkPosZ - chunkradius; z < chunkPosZ + chunkradius; z++) {
                Iterator<BlockEntity> iterator = world.getChunk(x, z).getBlockEntities().values().iterator();
                while (iterator.hasNext()) {
                    BlockEntity be = iterator.next();
                    if (!blockentities.contains(be)) {
                        blockentities.add(be);
                    }
                }
            }
        }

        return blockentities;
    }

    public static ResourceLocation getResource(String name) {
        return getResource(HexereiConstants.MOD_ID, name);
    }

    public static ResourceLocation getResource(String modId, String name) {
        return new ResourceLocation(modId, name);
    }

    public static String getResourcePath(String name) {
        return getResourcePath(HexereiConstants.MOD_ID, name);
    }

    public static String getResourcePath(String modId, String name) {
        return getResource(modId, name).toString();
    }

    public static FriendlyByteBuf createBuf() {
        return new FriendlyByteBuf(Unpooled.buffer());
    }

    public static <T> T make(Supplier<T> supplier) {
        return supplier.get();
    }

    public static <T> T make(T object, Consumer<T> consumer) {
        consumer.accept(object);
        return object;
    }


    public static BlockState getBlockStateWithExistingProperties(BlockState oldState, BlockState newState) {
        BlockState finalState = newState;
        for (Property<?> property : oldState.getProperties()) {
            if (newState.hasProperty(property)) {
                finalState = newStateWithOldProperty(oldState, finalState, property);
            }
        }
        return finalState;
    }

    public static BlockState setBlockStateWithExistingProperties(Level level, BlockPos pos, BlockState newState, int flags) {
        BlockState oldState = level.getBlockState(pos);
        BlockState finalState = getBlockStateWithExistingProperties(oldState, newState);
        level.sendBlockUpdated(pos, oldState, finalState, flags);
        level.setBlock(pos, finalState, flags);
        return finalState;
    }

    public static <T extends Comparable<T>> BlockState newStateWithOldProperty(BlockState oldState, BlockState newState, Property<T> property) {
        return newState.setValue(property, oldState.getValue(property));
    }

    public static <T extends IForgeRegistryEntry<? super T>> RegistryObject<T> acceptOrElse(RegistryObject<T> opt, Consumer<T> consumer, Runnable orElse) {
        if (opt.isPresent()) {
            consumer.accept(opt.get());
        } else {
            orElse.run();
        }
        return opt;
    }

    public static <T> Optional<T> acceptOrElse(Optional<T> opt, Consumer<T> consumer, Runnable orElse) {
        if (opt.isPresent()) {
            consumer.accept(opt.get());
        } else {
            orElse.run();
        }
        return opt;
    }

    public static <T> boolean allMatch(Iterable<T> input, Predicate<T> matcher) {
        Objects.requireNonNull(matcher);
        for (T e : input) {
            if (!matcher.test(e)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean anyMatch(Iterable<T> input, Predicate<T> matcher) {
        Objects.requireNonNull(matcher);
        for (T e : input) {
            if (matcher.test(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Takes various registry related objects and returns the
     * registry id of the object it is representing
     */
    public static ResourceLocation getRegistryId(Object obj) {
        if (obj instanceof ResourceLocation) {
            return (ResourceLocation) obj;
        }
        if (obj instanceof String) {
            // Returns null when namespace or path contain invalid charcters
            return ResourceLocation.tryParse((String) obj);
        }
        if (obj instanceof IForgeRegistryEntry) {
            return ((IForgeRegistryEntry) obj).getRegistryName();
        }
        if (obj instanceof IRegistryDelegate) {
            return ((IRegistryDelegate) obj).name();
        }
        if (obj instanceof RegistryObject) {
            return ((RegistryObject) obj).getId();
        }
        return null;
    }
}