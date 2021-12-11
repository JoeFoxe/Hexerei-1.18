package net.joefoxe.hexerei.event;

import net.joefoxe.hexerei.model.ModModels;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//This is necessary for setting up the rendering for the armor models (I still need to move it to a centralized file. or move other registry events over here
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        ModModels.setupRenderLayers();
    }

}
