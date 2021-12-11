package net.joefoxe.hexerei.event;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.ModEntityTypes;
import net.joefoxe.hexerei.events.AnimalFatAdditionModifier;
import net.joefoxe.hexerei.item.custom.ModSpawnEggItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Hexerei.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
//        event.put(ModEntityTypes.BUFF_ZOMBIE.get(), BuffZombieEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
//        ModSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new AnimalFatAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Hexerei.MOD_ID,"animal_fat_from_cow")),
                new AnimalFatAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Hexerei.MOD_ID,"animal_fat_from_sheep")),
                new AnimalFatAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(Hexerei.MOD_ID,"animal_fat_from_pig"))
        );
    }
}