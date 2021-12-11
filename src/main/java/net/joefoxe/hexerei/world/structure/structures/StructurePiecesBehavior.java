//package net.joefoxe.hexerei.world.structure.structures;
//
//
//import net.minecraft.resources.ResourceLocation;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class StructurePiecesBehavior {
//
//    // No need for Lazy in this class because it is init in FMLCommonSetupEvent where config values now exists
//    public static void init() {
//    }
//
//    public static class RequiredPieceNeeds {
//        private final int maxLimit;
//        private final Integer minDistanceFromCenter;
//        public RequiredPieceNeeds(int maxLimit, Integer minDistanceFromCenter) {
//            this.maxLimit = maxLimit;
//            this.minDistanceFromCenter = minDistanceFromCenter;
//        }
//
//        public int getRequiredAmount(){
//            return maxLimit;
//        }
//
//        public int getMinDistanceFromCenter(){
//            return minDistanceFromCenter;
//        }
//    }
//
//    public static HashMap<ResourceLocation, Map<ResourceLocation, RequiredPieceNeeds>> REQUIRED_PIECES_COUNT = new HashMap<>();
//
//    public static HashMap<ResourceLocation, Integer> PIECES_COUNT = new HashMap<>();
//}