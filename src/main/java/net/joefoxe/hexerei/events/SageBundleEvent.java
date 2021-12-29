package net.joefoxe.hexerei.events;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.block.custom.Candle;
import net.joefoxe.hexerei.tileentity.ModTileEntities;
import net.joefoxe.hexerei.util.HexereiUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.List;

@EventBusSubscriber
public class SageBundleEvent {

//    @SubscribeEvent
//    public void onEntityJoin(LivingSpawnEvent.CheckSpawn e) {
//        Level world = e.getWorld().isClientSide() ? null : e.getWorld() instanceof Level ? (Level)e.getWorld() : null;
//
//        if (world == null) {
//            return;
//        }
//
//        Entity entity = e.getEntity();
//
//        if (entity.getTags().contains(Hexerei.MOD_ID + ".checked" )) {
//
//            return;
//        }
//        entity.addTag(Hexerei.MOD_ID + ".checked");
//
//        if (!HexereiUtil.entityIsHostile(entity)) {
//            return;
//        }
//
//        List<BlockPos> nearbySageBundles = HexereiUtil.getAllTileEntityPositionsNearby(ModTileEntities.CANDLE_TILE.get(), 48, world, entity);
//        if (nearbySageBundles.size() == 0) {
//            return;
//        }
//
//        BlockPos bundle_plate = null;
//        for (BlockPos nearbySageBundle : nearbySageBundles) {
//            BlockState bundle_platestate = world.getBlockState(nearbySageBundle);
//            Block block = bundle_platestate.getBlock();
//            if (!(block instanceof Candle)) {
//                continue;
//            }
////            if (block.equals(ModBlocks.CANDLE)) {
////                continue;
////            }
//
//            bundle_plate = nearbySageBundle.immutable();
//            break;
//        }
//
//        if (bundle_plate == null) {
//            return;
//        }
//
//        List<Entity> passengers = entity.getPassengers();
//        if (passengers.size() > 0) {
//            for (Entity passenger : passengers) {
//                passenger.remove(RemovalReason.DISCARDED);
//            }
//        }
//
//        e.setResult(Result.DENY);
//    }
}