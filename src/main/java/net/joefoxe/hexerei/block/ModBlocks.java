package net.joefoxe.hexerei.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.custom.*;
import net.joefoxe.hexerei.block.custom.trees.MahoganyTree;
import net.joefoxe.hexerei.block.custom.trees.WillowTree;
import net.joefoxe.hexerei.item.ModItemGroup;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.tileentity.HerbJarTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModBlocks {


    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Hexerei.MOD_ID);

    public static final DeferredRegister<Item> ITEMS = ModItems.ITEMS;

//    public static final RegistryObject<Block> SCRAP_BLOCK = registerBlock("scrap_block",
//            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2).setRequiresTool().explosionResistance(5f)));

    public static final RegistryObject<MixingCauldron> MIXING_CAULDRON = registerBlock("mixing_cauldron",
            () -> new MixingCauldron(BlockBehaviour.Properties.of(Material.METAL).explosionResistance(4f).requiresCorrectToolForDrops().strength(3).lightLevel(state -> 12)));

    public static final RegistryObject<CandleDipper> CANDLE_DIPPER = registerBlock("candle_dipper",
            () -> new CandleDipper(BlockBehaviour.Properties.of(Material.METAL).noCollission().noOcclusion().strength(3).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER = registerBlock("coffer",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_BLACK = registerBlock("coffer_black",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_BLUE = registerBlock("coffer_blue",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_CYAN = registerBlock("coffer_cyan",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_GRAY = registerBlock("coffer_gray",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_GREEN = registerBlock("coffer_green",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_LIGHT_BLUE = registerBlock("coffer_light_blue",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_LIGHT_GRAY = registerBlock("coffer_light_gray",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_LIME = registerBlock("coffer_lime",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_MAGENTA = registerBlock("coffer_magenta",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_ORANGE = registerBlock("coffer_orange",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_PINK = registerBlock("coffer_pink",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_PURPLE = registerBlock("coffer_purple",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_RED = registerBlock("coffer_red",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_WHITE = registerBlock("coffer_white",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Coffer> COFFER_YELLOW = registerBlock("coffer_yellow",
            () -> new Coffer(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).requiresCorrectToolForDrops().explosionResistance(8f)));

    public static final RegistryObject<Altar> BOOK_OF_SHADOWS_ALTAR = registerBlock("book_of_shadows_altar",
            () -> new Altar(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).explosionResistance(2f)));

//    public static final RegistryObject<Block> SAGE_BUNDLE_PLATE = registerBlock("sage_bundle_plate",
//            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2).explosionResistance(2f)));

    public static final RegistryObject<SageBlock> SAGE = BLOCKS.register("sage_crop",
            () -> new SageBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<PestleAndMortar> PESTLE_AND_MORTAR = registerBlock("pestle_and_mortar",
            () -> new PestleAndMortar(BlockBehaviour.Properties.of(Material.STONE).strength(2).explosionResistance(2f)));

    public static final RegistryObject<SageBurningPlate> SAGE_BURNING_PLATE = registerBlock("sage_burning_plate",
            () -> new SageBurningPlate(BlockBehaviour.Properties.of(Material.METAL).strength(1).explosionResistance(2f)));

    public static final RegistryObject<CrystalBall> CRYSTAL_BALL = registerBlock("crystal_ball",
            () -> new CrystalBall(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(2f).lightLevel(state -> 9)));

    public static final RegistryObject<HerbJar> HERB_JAR = registerBlock("herb_jar",
            () -> new HerbJar(BlockBehaviour.Properties.of(Material.GLASS).strength(1).explosionResistance(0.5f)));

    public static final RegistryObject<HerbDryingRackFull> HERB_DRYING_RACK_FULL = registerBlock("herb_drying_rack_full",
            () -> new HerbDryingRackFull(BlockBehaviour.Properties.of(Material.WOOD).strength(1).explosionResistance(2f)));

    public static final RegistryObject<HerbDryingRack> HERB_DRYING_RACK = registerBlock("herb_drying_rack",
            () -> new HerbDryingRack(BlockBehaviour.Properties.of(Material.WOOD).strength(1).explosionResistance(2f)));

    public static final RegistryObject<Candelabra> CANDELABRA = registerBlock("candelabra",
            () -> new Candelabra(BlockBehaviour.Properties.of(Material.METAL).strength(1).explosionResistance(2f).lightLevel(state -> state.getValue(Candelabra.LIT) ? 15 : 0)));

    public static final RegistryObject<Candle> CANDLE = registerBlock("candle",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_BLUE = registerBlock("candle_blue",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_BLACK = registerBlock("candle_black",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_LIME = registerBlock("candle_lime",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_ORANGE = registerBlock("candle_orange",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_PINK = registerBlock("candle_pink",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_PURPLE = registerBlock("candle_purple",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_RED = registerBlock("candle_red",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_CYAN = registerBlock("candle_cyan",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));

    public static final RegistryObject<Candle> CANDLE_YELLOW = registerBlock("candle_yellow",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).noCollission().noOcclusion().strength(1).explosionResistance(0.5f).lightLevel(state -> Math.min(state.getValue(Candle.CANDLES_LIT) * 12, 15))));



    public static final RegistryObject<MahoganyLog> MAHOGANY_LOG = registerBlock("mahogany_log",
            () -> new MahoganyLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<MahoganyWood> MAHOGANY_WOOD = registerBlock("mahogany_wood",
            () -> new MahoganyWood(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_MAHOGANY_LOG = registerBlock("stripped_mahogany_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_MAHOGANY_WOOD = registerBlock("stripped_mahogany_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> MAHOGANY_PLANKS = registerBlock("mahogany_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<StairBlock> MAHOGANY_STAIRS = registerBlock("mahogany_stairs",
            () -> new StairBlock(() -> MAHOGANY_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<FenceBlock> MAHOGANY_FENCE = registerBlock("mahogany_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<FenceGateBlock> MAHOGANY_FENCE_GATE = registerBlock("mahogany_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<SlabBlock> MAHOGANY_SLAB = registerBlock("mahogany_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<WoodButtonBlock> MAHOGANY_BUTTON = registerBlock("mahogany_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noCollission()));

    public static final RegistryObject<PressurePlateBlock> MAHOGANY_PRESSURE_PLATE = registerBlock("mahogany_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING ,BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noCollission()));

    public static final RegistryObject<DoorBlock> MAHOGANY_DOOR = registerBlock("mahogany_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));

    public static final RegistryObject<TrapDoorBlock> MAHOGANY_TRAPDOOR = registerBlock("mahogany_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));

    public static final RegistryObject<LeavesBlock> MAHOGANY_LEAVES = registerBlock("mahogany_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isSuffocating(Properties::never).isViewBlocking(Properties::never)));

    public static final RegistryObject<SaplingBlock> MAHOGANY_SAPLING = registerBlock("mahogany_sapling",
            () -> new SaplingBlock(new MahoganyTree(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));



    public static final RegistryObject<WillowLog> WILLOW_LOG = registerBlock("willow_log",
            () -> new WillowLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<WillowWood> WILLOW_WOOD = registerBlock("willow_wood",
            () -> new WillowWood(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_WILLOW_LOG = registerBlock("stripped_willow_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_WILLOW_WOOD = registerBlock("stripped_willow_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> WILLOW_PLANKS = registerBlock("willow_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<StairBlock> WILLOW_STAIRS = registerBlock("willow_stairs",
            () -> new StairBlock(() -> WILLOW_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> WILLOW_VINES = registerBlock("willow_vines",
            () -> new WillowVinesBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.WEEPING_VINES)));

    public static final RegistryObject<Block> WILLOW_VINES_PLANT = registerBlockNoItem("willow_vines_plant",
            () -> new WillowVinesPlantBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.WEEPING_VINES)));

    public static final RegistryObject<FenceBlock> WILLOW_FENCE = registerBlock("willow_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<FenceGateBlock> WILLOW_FENCE_GATE = registerBlock("willow_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<SlabBlock> WILLOW_SLAB = registerBlock("willow_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<WoodButtonBlock> WILLOW_BUTTON = registerBlock("willow_button",
            () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noCollission()));

    public static final RegistryObject<PressurePlateBlock> WILLOW_PRESSURE_PLATE = registerBlock("willow_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING ,BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noCollission()));

    public static final RegistryObject<DoorBlock> WILLOW_DOOR = registerBlock("willow_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));

    public static final RegistryObject<TrapDoorBlock> WILLOW_TRAPDOOR = registerBlock("willow_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion()));

    public static final RegistryObject<LeavesBlock> WILLOW_LEAVES = registerBlock("willow_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.AZALEA_LEAVES).noOcclusion().isSuffocating(Properties::never).isViewBlocking(Properties::never)));

    public static final RegistryObject<SaplingBlock> WILLOW_SAPLING = registerBlock("willow_sapling",
            () -> new SaplingBlock(new WillowTree(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<FloweringLilyPadBlock> LILY_PAD_BLOCK = registerBlockNoItem("flowering_lily_pad",
            () -> new FloweringLilyPadBlock(BlockBehaviour.Properties.of(Material.VEGETABLE).instabreak().sound(SoundType.LILY_PAD).noOcclusion()));

    public static final RegistryObject<PickableFlower> MANDRAKE_FLOWER = registerBlock("mandrake_flower",
            () -> new PickableFlower(MobEffects.REGENERATION, 3, BlockBehaviour.Properties.copy(Blocks.DANDELION), ModItems.MANDRAKE_FLOWERS, 2, ModItems.MANDRAKE_ROOT, 1){


                @Override
                public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

                    tooltip.add(new TranslatableComponent("tooltip.hexerei.found_in_swamp").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                    super.appendHoverText(stack, world, tooltip, flagIn);
                }
            });

    public static final RegistryObject<PickableFlower> BELLADONNA_FLOWER = registerBlock("belladonna_flower",
            () -> new PickableFlower(MobEffects.POISON, 3, BlockBehaviour.Properties.copy(Blocks.DANDELION), ModItems.BELLADONNA_FLOWERS, 2, ModItems.BELLADONNA_BERRIES, 6){


                @Override
                public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

                    tooltip.add(new TranslatableComponent("tooltip.hexerei.found_in_swamp").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                    super.appendHoverText(stack, world, tooltip, flagIn);
                }
            });

//            () -> new PickableFlower(MobEffects.POISON, 3, BlockBehaviour.Properties.copy(Blocks.DANDELION), new ItemStack(ModItems.BELLADONNA_FLOWERS.get(), 4),  new ItemStack(ModItems.BELLADONNA_BERRIES.get(), 5), 5));

    public static final RegistryObject<PickableDoubleFlower> MUGWORT_BUSH = registerBlock("mugwort_bush",
            () -> new PickableDoubleFlower(MobEffects.GLOWING, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.AZALEA), ModItems.MUGWORT_LEAVES, 4, ModItems.MUGWORT_FLOWERS, 3){


                @Override
                public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

                    tooltip.add(new TranslatableComponent("tooltip.hexerei.found_in_swamp").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                    super.appendHoverText(stack, world, tooltip, flagIn);
                }
            });

    public static final RegistryObject<PickableDoubleFlower> YELLOW_DOCK_BUSH = registerBlock("yellow_dock_bush",
            () -> new PickableDoubleFlower(MobEffects.GLOWING, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.AZALEA), ModItems.YELLOW_DOCK_LEAVES, 4, ModItems.YELLOW_DOCK_FLOWERS, 3){


                @Override
                public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flagIn) {

                    tooltip.add(new TranslatableComponent("tooltip.hexerei.found_in_swamp").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                    super.appendHoverText(stack, world, tooltip, flagIn);
                }
            });




    public static final RegistryObject<AmethystBlock> SELENITE_BLOCK = registerBlock("selenite_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.TERRACOTTA_WHITE).strength(0.5F).sound(SoundType.AMETHYST).noOcclusion()));

    public static final RegistryObject<AmethystBlock> BUDDING_SELENITE = registerBlock("budding_selenite",
            () -> new BuddingSelenite(BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().strength(1.0F).sound(SoundType.AMETHYST).noOcclusion()));

    public static final RegistryObject<AmethystBlock> SELENITE_CLUSTER = registerBlock("selenite_cluster",
            () -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST).noOcclusion().strength(0.5F).randomTicks().sound(SoundType.AMETHYST_CLUSTER).noOcclusion().lightLevel((p_152632_) -> {
                return 5;
            })));

    public static final RegistryObject<AmethystBlock> LARGE_SELENITE_BUD = registerBlock("large_selenite_bud",
            () -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.MEDIUM_AMETHYST_BUD).noOcclusion().lightLevel((p_152629_) -> {
                return 4;
            })));

    public static final RegistryObject<AmethystBlock> MEDIUM_SELENITE_BUD = registerBlock("medium_selenite_bud",
            () -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.LARGE_AMETHYST_BUD).noOcclusion().lightLevel((p_152617_) -> {
                return 2;
            })));

    public static final RegistryObject<AmethystBlock> SMALL_SELENITE_BUD = registerBlock("small_selenite_bud",
            () -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(Blocks.AMETHYST_CLUSTER).sound(SoundType.SMALL_AMETHYST_BUD).noOcclusion().lightLevel((p_187409_) -> {
                return 1;
            })));

    // SIGILS
    public static final RegistryObject<Block> BLOOD_SIGIL = registerBlockNoItem("blood_sigil",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2).requiresCorrectToolForDrops().explosionResistance(5f)));

    // BLOCK PARTIALS

    public static final RegistryObject<Block> CANDLE_DIPPER_WICK_BASE = registerBlockNoItem("candle_dipper_wick_base",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_DIPPER_WICK = registerBlockNoItem("candle_dipper_wick",
            () -> new Block(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_DIPPER_CANDLE_1 = registerBlockNoItem("candle_dipper_candle_1",
            () -> new Block(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_DIPPER_CANDLE_2 = registerBlockNoItem("candle_dipper_candle_2",
            () -> new Block(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_DIPPER_CANDLE_3 = registerBlockNoItem("candle_dipper_candle_3",
            () -> new Block(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_0_OF_7 = registerBlockNoItem("candle_0_of_7",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_1_OF_7 = registerBlockNoItem("candle_1_of_7",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_2_OF_7 = registerBlockNoItem("candle_2_of_7",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_3_OF_7 = registerBlockNoItem("candle_3_of_7",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_4_OF_7 = registerBlockNoItem("candle_4_of_7",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_5_OF_7 = registerBlockNoItem("candle_5_of_7",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_6_OF_7 = registerBlockNoItem("candle_6_of_7",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CANDLE_7_OF_7 = registerBlockNoItem("candle_7_of_7",
            () -> new Candle(BlockBehaviour.Properties.of(Material.VEGETABLE).strength(0).noCollission().explosionResistance(1f)));

    public static final RegistryObject<Block> CRYSTAL_BALL_ORB = registerBlockNoItem("crystal_ball_orb",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).explosionResistance(2f)));

    public static final RegistryObject<Block> CRYSTAL_BALL_LARGE_RING = registerBlockNoItem("crystal_ball_large_ring",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).explosionResistance(2f)));

    public static final RegistryObject<Block> CRYSTAL_BALL_SMALL_RING = registerBlockNoItem("crystal_ball_small_ring",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).explosionResistance(2f)));

    public static final RegistryObject<Block> CRYSTAL_BALL_STAND = registerBlockNoItem("crystal_ball_stand",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).explosionResistance(2f)));

    public static final RegistryObject<Block> BOOK_OF_SHADOWS_COVER = registerBlockNoItem("book_of_shadows_cover",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).explosionResistance(2f)));

    public static final RegistryObject<Block> BOOK_OF_SHADOWS_BACK = registerBlockNoItem("book_of_shadows_back",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).explosionResistance(2f)));

    public static final RegistryObject<Block> BOOK_OF_SHADOWS_BINDING = registerBlockNoItem("book_of_shadows_binding",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).explosionResistance(2f)));

    public static final RegistryObject<Block> BOOK_OF_SHADOWS_PAGE = registerBlockNoItem("book_of_shadows_page_blank",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(0).explosionResistance(2f)));

    public static final RegistryObject<Block> COFFER_LID = registerBlockNoItem("coffer_lid",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_BLACK = registerBlockNoItem("coffer_lid_black",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_BLUE = registerBlockNoItem("coffer_lid_blue",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_CYAN = registerBlockNoItem("coffer_lid_cyan",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_GRAY = registerBlockNoItem("coffer_lid_gray",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_GREEN = registerBlockNoItem("coffer_lid_green",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_LIGHT_BLUE = registerBlockNoItem("coffer_lid_light_blue",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_LIGHT_GRAY = registerBlockNoItem("coffer_lid_light_gray",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_LIME = registerBlockNoItem("coffer_lid_lime",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_MAGENTA = registerBlockNoItem("coffer_lid_magenta",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_ORANGE = registerBlockNoItem("coffer_lid_orange",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_PINK = registerBlockNoItem("coffer_lid_pink",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_PURPLE = registerBlockNoItem("coffer_lid_purple",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_RED = registerBlockNoItem("coffer_lid_red",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_WHITE = registerBlockNoItem("coffer_lid_white",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_LID_YELLOW = registerBlockNoItem("coffer_lid_yellow",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(3).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> COFFER_CONTAINER = registerBlockNoItem("coffer_container",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> COFFER_HINGE = registerBlockNoItem("coffer_hinge",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_JAR_GENERIC = registerBlockNoItem("herb_jar_generic",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_JAR_BELLADONNA = registerBlockNoItem("herb_jar_belladonna",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_JAR_MANDRAKE_FLOWER = registerBlockNoItem("herb_jar_mandrake_flower",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_JAR_MANDRAKE_ROOT = registerBlockNoItem("herb_jar_mandrake_root",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_JAR_MUGWORT = registerBlockNoItem("herb_jar_mugwort",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_JAR_YELLOW_DOCK = registerBlockNoItem("herb_jar_yellow_dock",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_DRYING_RACK_BROWN_MUSHROOM_1 = registerBlockNoItem("herb_drying_rack_brown_mushroom_1",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_DRYING_RACK_BROWN_MUSHROOM_2 = registerBlockNoItem("herb_drying_rack_brown_mushroom_2",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_DRYING_RACK_RED_MUSHROOM_1 = registerBlockNoItem("herb_drying_rack_red_mushroom_1",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> HERB_DRYING_RACK_RED_MUSHROOM_2 = registerBlockNoItem("herb_drying_rack_red_mushroom_2",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> PESTLE_AND_MORTAR_PESTLE = registerBlockNoItem("pestle_and_mortar_pestle",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_5 = registerBlockNoItem("dried_sage_bundle_plate_5",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_4 = registerBlockNoItem("dried_sage_bundle_plate_4",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_3 = registerBlockNoItem("dried_sage_bundle_plate_3",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_2 = registerBlockNoItem("dried_sage_bundle_plate_2",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_1 = registerBlockNoItem("dried_sage_bundle_plate_1",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_5_LIT = registerBlockNoItem("dried_sage_bundle_plate_5_lit",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_4_LIT = registerBlockNoItem("dried_sage_bundle_plate_4_lit",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_3_LIT = registerBlockNoItem("dried_sage_bundle_plate_3_lit",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_2_LIT = registerBlockNoItem("dried_sage_bundle_plate_2_lit",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static final RegistryObject<Block> DRIED_SAGE_BUNDLE_PLATE_1_LIT = registerBlockNoItem("dried_sage_bundle_plate_1_lit",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2).explosionResistance(8f)));

    public static Block registerBlock(String name, CreativeModeTab itemGroup, Block block) {
        Item.Properties itemProperties = new Item.Properties().tab(itemGroup);
        BlockItem itemBlock;
        if (block instanceof HerbJar) {
            itemBlock = new BlockItem(block, itemProperties) {
                @Override
                public void initializeClient(@Nonnull Consumer<IItemRenderProperties> consumer) {
                    consumer.accept(new IItemRenderProperties() {
                        final BlockEntityWithoutLevelRenderer myRenderer = new BlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()) {
                            private HerbJarTile blockEntity;

                            @Override
                            public void renderByItem(@Nonnull ItemStack stack, @Nonnull ItemTransforms.TransformType transformType, @Nonnull PoseStack matrix, @Nonnull MultiBufferSource buffer, int x, int y) {
                                if (blockEntity == null) {
                                    blockEntity = new HerbJarTile(BlockPos.ZERO, ModBlocks.HERB_JAR.get().defaultBlockState());
                                }
                                Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(blockEntity, matrix, buffer, x, y);
                            }
                        };

                        @Override
                        public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                            return myRenderer;
                        }
                    });
                }
            };
        } else {
            itemBlock = new BlockItem(block, itemProperties);
        }
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }


    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<T> registerBlockNoItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new BlockItem.Properties().tab(ModItemGroup.HEXEREI_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static class Properties {

        public static boolean always(BlockState state, BlockGetter reader, BlockPos pos, EntityType<?> entity) {
            return true;
        }

        public static boolean hasPostProcess(BlockState state, BlockGetter reader, BlockPos pos) {
            return true;
        }

        public static boolean never(BlockState state, BlockGetter reader, BlockPos pos) {
            return false;
        }
    }





//    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup) {
//        return register(name, sup, block -> item(block));
//    }
//
//    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, Function<RegistryObject<T>, Supplier<? extends Item>> itemCreator) {
//        RegistryObject<T> ret = registerNoItem(name, sup);
//        ITEMS.register(name, itemCreator.apply(ret));
//        return ret;
//    }
//
//    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> sup) {
//        return BLOCKS.register(name, sup);
//    }
//
//    private static Supplier<BlockItem> item(final RegistryObject<? extends Block> block) {
//        return () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP));
//    }


}
