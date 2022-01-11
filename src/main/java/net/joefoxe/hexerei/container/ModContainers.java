package net.joefoxe.hexerei.container;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {

    public static DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Hexerei.MOD_ID);

    public static final RegistryObject<MenuType<MixingCauldronContainer>> MIXING_CAULDRON_CONTAINER
            = CONTAINERS.register("mixing_cauldron_container",
            () -> IForgeMenuType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getLevel();
                return new MixingCauldronContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<MenuType<CofferContainer>> COFFER_CONTAINER
            = CONTAINERS.register("coffer_container",
            () -> IForgeMenuType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getLevel();
                return new CofferContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<MenuType<HerbJarContainer>> HERB_JAR_CONTAINER
            = CONTAINERS.register("herb_jar_container",
            () -> IForgeMenuType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getLevel();
                return new HerbJarContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<MenuType<DipperContainer>> DIPPER_CONTAINER
            = CONTAINERS.register("dipper_container",
            () -> IForgeMenuType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getLevel();
                return new DipperContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<MenuType<DipperContainer>> DRYING_RACK_CONTAINER
            = CONTAINERS.register("drying_rack_container",
            () -> IForgeMenuType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getLevel();
                return new DipperContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<MenuType<PestleAndMortarContainer>> PESTLE_AND_MORTAR_CONTAINER
            = CONTAINERS.register("pestle_and_mortar_container",
            () -> IForgeMenuType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getLevel();
                return new PestleAndMortarContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<MenuType<BroomContainer>> BROOM_CONTAINER
            = CONTAINERS.register("broom_container",
            () -> IForgeMenuType.create(((windowId, inv, data) -> {

                Level world = inv.player.getLevel();//new BroomEntity(world, pos.getX(), pos.getY(), pos.getZ())
                int id = data.readInt();
                if(world.getEntity(id) != null)
                    return new BroomContainer(windowId,(BroomEntity)world.getEntity(id), inv, inv.player);
                else
                    return new BroomContainer(windowId,new BroomEntity(world, 0, 0, 0), inv, inv.player);

            })));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
