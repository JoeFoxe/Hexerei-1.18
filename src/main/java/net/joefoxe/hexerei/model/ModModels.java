package net.joefoxe.hexerei.model;

import net.joefoxe.hexerei.block.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

public class ModModels {

    // for rendering the transparency for the mixing cauldron when containing water
    public static void setupRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MIXING_CAULDRON.get(), ModModels::isMixingCauldronValidLayer);
    }

    public static boolean isMixingCauldronValidLayer(RenderType layerToCheck) {
        return layerToCheck == RenderType.cutout() || layerToCheck == RenderType.translucent();
    }

}
