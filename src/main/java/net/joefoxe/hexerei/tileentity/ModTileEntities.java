package net.joefoxe.hexerei.tileentity;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModTileEntities {

    public static DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Hexerei.MOD_ID);


    public static final RegistryObject<BlockEntityType<MixingCauldronTile>> MIXING_CAULDRON_TILE = TILE_ENTITIES.register(
            "mixing_cauldron_entity", () -> BlockEntityType.Builder.of(MixingCauldronTile::new, ModBlocks.MIXING_CAULDRON.get()).build(null));

    public static final RegistryObject<BlockEntityType<CofferTile>> COFFER_TILE = TILE_ENTITIES.register(
            "coffer_entity", () -> BlockEntityType.Builder.of(CofferTile::new, ModBlocks.COFFER.get()).build(null));


    public static final RegistryObject<BlockEntityType<HerbJarTile>> HERB_JAR_TILE = TILE_ENTITIES.register(
            "herb_jar_entity", () -> BlockEntityType.Builder.of(HerbJarTile::new, ModBlocks.HERB_JAR.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrystalBallTile>> CRYSTAL_BALL_TILE = TILE_ENTITIES.register(
            "crystal_ball_entity", () -> BlockEntityType.Builder.of(CrystalBallTile::new, ModBlocks.CRYSTAL_BALL.get()).build(null));

    public static final RegistryObject<BlockEntityType<BookOfShadowsAltarTile>> BOOK_OF_SHADOWS_ALTAR_TILE = TILE_ENTITIES.register(
            "book_of_shadows_altar_entity", () -> BlockEntityType.Builder.of(BookOfShadowsAltarTile::new, ModBlocks.BOOK_OF_SHADOWS_ALTAR.get()).build(null));

    public static final RegistryObject<BlockEntityType<CandleTile>> CANDLE_TILE = TILE_ENTITIES.register(
            "candle_entity", () -> BlockEntityType.Builder.of(CandleTile::new, ModBlocks.CANDLE.get(), ModBlocks.CANDLE_BLUE.get(), ModBlocks.CANDLE_BLACK.get(), ModBlocks.CANDLE_LIME.get(), ModBlocks.CANDLE_ORANGE.get(), ModBlocks.CANDLE_PINK.get(), ModBlocks.CANDLE_PURPLE.get(), ModBlocks.CANDLE_RED.get(), ModBlocks.CANDLE_CYAN.get(), ModBlocks.CANDLE_YELLOW.get()).build(null));

    public static final RegistryObject<BlockEntityType<CandleDipperTile>> CANDLE_DIPPER_TILE = TILE_ENTITIES.register(
            "candle_dipper_entity", () -> BlockEntityType.Builder.of(CandleDipperTile::new, ModBlocks.CANDLE_DIPPER.get()).build(null));

    public static final RegistryObject<BlockEntityType<DryingRackTile>> DRYING_RACK_TILE = TILE_ENTITIES.register(
            "drying_rack_entity", () -> BlockEntityType.Builder.of(DryingRackTile::new, ModBlocks.HERB_DRYING_RACK.get()).build(null));


    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
