package net.joefoxe.hexerei.util;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.model.*;
import net.joefoxe.hexerei.config.ModKeyBindings;
import net.joefoxe.hexerei.item.ModItemProperties;
import net.joefoxe.hexerei.item.ModItems;
import net.joefoxe.hexerei.item.custom.BroomItemStackRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.joefoxe.hexerei.client.renderer.entity.ModEntityTypes;
import net.joefoxe.hexerei.client.renderer.entity.render.BroomRenderer;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.joefoxe.hexerei.tileentity.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.BiFunction;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientProxy implements SidedProxy {

    public static final ModelLayerLocation WITCH_ARMOR_LAYER = new ModelLayerLocation(new ResourceLocation(Hexerei.MOD_ID, "witch_armor"), "main");
    public static WitchArmorModel WITCH_ARMOR_MODEL = null;


    @Override
    public Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    @Override
    public Level getLevel() {
        return Minecraft.getInstance().level;
    }

    @Override
    public void init() {
    }

    @Override
    public void openCodexGui() {

    }

    public static void registerISTER(Consumer<IItemRenderProperties> consumer, BiFunction<BlockEntityRenderDispatcher, EntityModelSet, BlockEntityWithoutLevelRenderer> factory) {
        consumer.accept(new IItemRenderProperties() {
            final NonNullLazy<BlockEntityWithoutLevelRenderer> renderer = NonNullLazy.of(
                    () -> factory.apply(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
                            Minecraft.getInstance().getEntityModels()));

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer.get();
            }
        });
    }


    @SubscribeEvent
    public static void setup(EntityRenderersEvent.RegisterRenderers e){
        e.registerBlockEntityRenderer(ModTileEntities.MIXING_CAULDRON_TILE.get(), context -> new MixingCauldronRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.COFFER_TILE.get(), context -> new CofferRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.HERB_JAR_TILE.get(), context -> new HerbJarRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.CRYSTAL_BALL_TILE.get(), context -> new CrystalBallRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.BOOK_OF_SHADOWS_ALTAR_TILE.get(), context -> new BookOfShadowsAltarRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.CANDLE_TILE.get(), context -> new CandleRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.CANDLE_DIPPER_TILE.get(), context -> new CandleDipperRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.DRYING_RACK_TILE.get(), context -> new DryingRackRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.PESTLE_AND_MORTAR_TILE.get(), context -> new PestleAndMortarRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.SAGE_BURNING_PLATE_TILE.get(), context -> new SageBurningPlateRenderer());
        e.registerEntityRenderer(ModEntityTypes.BROOM.get(), BroomRenderer::new);
        ModItemProperties.makeDowsingRod(ModItems.DOWSING_ROD.get());
    }

    @SubscribeEvent
    public static void layerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BroomModel.LAYER_LOCATION, BroomModel::createBodyLayer);
        event.registerLayerDefinition(BroomBrushBaseModel.LAYER_LOCATION, BroomBrushBaseModel::createBodyLayer);
        event.registerLayerDefinition(BroomStickBaseModel.LAYER_LOCATION, BroomStickBaseModel::createBodyLayer);
        event.registerLayerDefinition(BroomRingsModel.LAYER_LOCATION, BroomRingsModel::createBodyLayer);
        event.registerLayerDefinition(BroomRingsModel.LAYER_LOCATION, BroomRingsModel::createBodyLayer);
        event.registerLayerDefinition(BroomSmallSatchelModel.LAYER_LOCATION, BroomSmallSatchelModel::createBodyLayer);
        event.registerLayerDefinition(BroomMediumSatchelModel.LAYER_LOCATION, BroomMediumSatchelModel::createBodyLayer);
        event.registerLayerDefinition(BroomLargeSatchelModel.LAYER_LOCATION, BroomLargeSatchelModel::createBodyLayer);
        event.registerLayerDefinition(BroomKeychainModel.LAYER_LOCATION, BroomKeychainModel::createBodyLayer);
        event.registerLayerDefinition(BroomKeychainChainModel.LAYER_LOCATION, BroomKeychainChainModel::createBodyLayer);
        event.registerLayerDefinition(BroomNetheriteTipModel.LAYER_LOCATION, BroomNetheriteTipModel::createBodyLayer);
        event.registerLayerDefinition(BroomWaterproofTipModel.LAYER_LOCATION, BroomWaterproofTipModel::createBodyLayer);
        event.registerLayerDefinition(ClientProxy.WITCH_ARMOR_LAYER, WitchArmorModel::createBodyLayer);

    }

}