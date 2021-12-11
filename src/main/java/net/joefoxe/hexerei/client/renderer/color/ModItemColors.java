package net.joefoxe.hexerei.client.renderer.color;

import net.joefoxe.hexerei.block.ModBlocks;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItemColors {
    private ModItemColors() {}
    // FORGE: Use RegistryDelegates as non-Vanilla item ids are not constant

    @SubscribeEvent
    public static void initItemColors(ColorHandlerEvent.Item event) {

        event.getItemColors().register((stack, color) -> {
            Block block = Block.byItem(stack.getItem());
            if(block instanceof WaterlilyBlock) {
                return GrassColor.get(0.0D, 0.5D);
            }
            return 0;
        }, ModBlocks.LILY_PAD_BLOCK.get());

    }


}