package net.joefoxe.hexerei.fluid;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    public static final ResourceLocation QUICKSILVER_STILL_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/quicksilver_still");
    public static final ResourceLocation QUICKSILVER_FLOWING_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/quicksilver_flow");
    public static final ResourceLocation QUICKSILVER_OVERLAY_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/quicksilver_overlay");
    public static final ResourceLocation BLOOD_STILL_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/blood_still");
    public static final ResourceLocation BLOOD_FLOWING_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/blood_flow");
    public static final ResourceLocation BLOOD_OVERLAY_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/blood_overlay");
    public static final ResourceLocation TALLOW_STILL_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/tallow_still");
    public static final ResourceLocation TALLOW_FLOWING_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/tallow_flow");
    public static final ResourceLocation TALLOW_OVERLAY_RL = new ResourceLocation(Hexerei.MOD_ID + ":block/tallow_overlay");
    public static final Material BLOOD = (new Material.Builder(MaterialColor.WATER)).noCollider().nonSolid().replaceable().liquid().build();
    public static final Material TALLOW = (new Material.Builder(MaterialColor.WATER)).noCollider().nonSolid().replaceable().liquid().build();


    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Hexerei.MOD_ID);


    public static final RegistryObject<FlowingFluid> QUICKSILVER_FLUID = FLUIDS.register("quicksilver_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.QUICKSILVER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> QUICKSILVER_FLOWING = FLUIDS.register("quicksilver_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.QUICKSILVER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties QUICKSILVER_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> QUICKSILVER_FLUID.get(), () -> QUICKSILVER_FLOWING.get(), FluidAttributes.builder(QUICKSILVER_STILL_RL, QUICKSILVER_FLOWING_RL).density(15).luminosity(15).viscosity(15).sound(SoundEvents.BUCKET_EMPTY_LAVA).overlay(QUICKSILVER_OVERLAY_RL).color(0xF9FFFFFF).gaseous()).slopeFindDistance(2).levelDecreasePerBlock(2).block(() -> ModFluids.QUICKSILVER_BLOCK.get()).bucket(() -> ModItems.QUICKSILVER_BUCKET.get());

    public static final RegistryObject<LiquidBlock> QUICKSILVER_BLOCK = ModBlocks.BLOCKS.register("quicksilver", () -> new LiquidBlock(() -> ModFluids.QUICKSILVER_FLUID.get(),
            BlockBehaviour.Properties.of(Material.LAVA).noCollission().explosionResistance(100f).noDrops()));



    public static final RegistryObject<BloodFluid.Source> BLOOD_FLUID = FLUIDS.register("blood_fluid", () -> new BloodFluid.Source(ModFluids.BLOOD_PROPERTIES));

    public static final RegistryObject<BloodFluid.Flowing> BLOOD_FLOWING = FLUIDS.register("blood_flowing", () -> new BloodFluid.Flowing(ModFluids.BLOOD_PROPERTIES));

    public static final BloodFluid.Properties BLOOD_PROPERTIES = new BloodFluid.Properties(
            () -> BLOOD_FLUID.get(), () -> BLOOD_FLOWING.get(), FluidAttributes.builder(BLOOD_STILL_RL, BLOOD_FLOWING_RL)
            .density(1500)
            .luminosity(15)
            .viscosity(2000)
            .sound(SoundEvents.HONEY_DRINK)
            .overlay(BLOOD_OVERLAY_RL)
            .color(0xF9FFFFFF)
            .gaseous())
            .slopeFindDistance(2)
            .levelDecreasePerBlock(2)
            .block(() -> ModFluids.BLOOD_BLOCK.get())
            .bucket(() -> ModItems.BLOOD_BUCKET.get());

    public static final RegistryObject<LiquidBlock> BLOOD_BLOCK = ModBlocks.BLOCKS.register("blood", () -> new LiquidBlock(() -> ModFluids.BLOOD_FLUID.get(),
            BlockBehaviour.Properties.of(BLOOD).noCollission().explosionResistance(100f).noDrops()));



    public static final RegistryObject<TallowFluid.Source> TALLOW_FLUID = FLUIDS.register("tallow_fluid", () -> new TallowFluid.Source(ModFluids.TALLOW_PROPERTIES));

    public static final RegistryObject<TallowFluid.Flowing> TALLOW_FLOWING = FLUIDS.register("tallow_flowing", () -> new TallowFluid.Flowing(ModFluids.TALLOW_PROPERTIES));

    public static final TallowFluid.Properties TALLOW_PROPERTIES = new TallowFluid.Properties(
            () -> TALLOW_FLUID.get(), () -> TALLOW_FLOWING.get(), FluidAttributes.builder(TALLOW_STILL_RL, TALLOW_FLOWING_RL)
            .density(1500)
            .luminosity(15)
            .viscosity(2000)
            .sound(SoundEvents.HONEY_DRINK)
            .overlay(TALLOW_OVERLAY_RL)
            .color(0xF9FFFFFF)
            .gaseous())
            .slopeFindDistance(2)
            .levelDecreasePerBlock(3)
            .block(() -> ModFluids.TALLOW_BLOCK.get())
            .bucket(() -> ModItems.TALLOW_BUCKET.get());

    public static final RegistryObject<LiquidBlock> TALLOW_BLOCK = ModBlocks.BLOCKS.register("tallow", () -> new LiquidBlock(() -> ModFluids.TALLOW_FLUID.get(),
            BlockBehaviour.Properties.of(TALLOW).noCollission().explosionResistance(100f).noDrops()));


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }




}


