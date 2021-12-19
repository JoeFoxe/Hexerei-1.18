package net.joefoxe.hexerei.util;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.model.WitchArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import net.joefoxe.hexerei.client.renderer.entity.ModEntityTypes;
import net.joefoxe.hexerei.client.renderer.entity.model.BroomModel;
import net.joefoxe.hexerei.client.renderer.entity.render.BroomRenderer;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.joefoxe.hexerei.tileentity.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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


    @SubscribeEvent
    public static void setup(EntityRenderersEvent.RegisterRenderers e){
        e.registerBlockEntityRenderer(ModTileEntities.MIXING_CAULDRON_TILE.get(), context -> new MixingCauldronRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.COFFER_TILE.get(), context -> new CofferRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.HERB_JAR_TILE.get(), context -> new HerbJarRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.CRYSTAL_BALL_TILE.get(), context -> new CrystalBallRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.BOOK_OF_SHADOWS_ALTAR_TILE.get(), context -> new BookOfShadowsAltarRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.CANDLE_TILE.get(), context -> new CandleRenderer());
        e.registerBlockEntityRenderer(ModTileEntities.CANDLE_DIPPER_TILE.get(), context -> new CandleDipperRenderer());
        e.registerEntityRenderer(ModEntityTypes.BROOM.get(), BroomRenderer::new);
    }

    @SubscribeEvent
    public static void layerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BroomModel.LAYER_LOCATION, BroomModel::createBodyLayer);
        event.registerLayerDefinition(ClientProxy.WITCH_ARMOR_LAYER, WitchArmorModel::createBodyLayer);

    }

}