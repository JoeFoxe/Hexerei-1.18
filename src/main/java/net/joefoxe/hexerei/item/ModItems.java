package net.joefoxe.hexerei.item;

import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.joefoxe.hexerei.fluid.ModFluids;
import net.joefoxe.hexerei.item.custom.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Hexerei.MOD_ID);

    public static final RegistryObject<Item> MAHOGANY_BROOM = ITEMS.register("mahogany_broom",
            () -> new BroomItem(BroomEntity.Type.MAHOGANY, new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP).fireResistant()));

    public static final RegistryObject<Item> WILLOW_BROOM = ITEMS.register("willow_broom",
            () -> new BroomItem(BroomEntity.Type.WILLOW, new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> FIRE_TABLET = ITEMS.register("fire_tablet",
            () -> new FireTabletItem(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> SMALL_SATCHEL = ITEMS.register("small_satchel",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> MEDIUM_SATCHEL = ITEMS.register("medium_satchel",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> LARGE_SATCHEL = ITEMS.register("large_satchel",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> GOLD_RINGS = ITEMS.register("gold_rings",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> WET_BROOM_BRUSH = ITEMS.register("wet_broom_brush",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> BROOM_BRUSH = ITEMS.register("broom_brush",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP).durability(100)));

    public static final RegistryObject<Item> WARHAMMER = ITEMS.register("warhammer",
            () -> new SwordItem(ModItemTier.ARMOR_SCRAP, 3, -2.4F,
                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> ARMOR_SCRAP = ITEMS.register("armor_scrap",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> BLOOD_BUCKET = ITEMS.register("blood_bucket",
            () -> new BucketItem(() -> ModFluids.BLOOD_FLUID.get(), new Item.Properties().stacksTo(1).tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> TALLOW_BUCKET = ITEMS.register("tallow_bucket",
            () -> new BucketItem(() -> ModFluids.TALLOW_FLUID.get(), new Item.Properties().stacksTo(1).tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> QUICKSILVER_BUCKET = ITEMS.register("quicksilver_bucket",
            () -> new BucketItem(() -> ModFluids.QUICKSILVER_FLUID.get(), new Item.Properties().stacksTo(1).tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> QUICKSILVER_BOTTLE = ITEMS.register("quicksilver_bottle",
            () -> new BottleQuicksilverItem(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> BLOOD_BOTTLE = ITEMS.register("blood_bottle",
            () -> new BottleBloodtem(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> TALLOW_BOTTLE = ITEMS.register("tallow_bottle",
            () -> new BottleTallowItem(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> LAVA_BOTTLE = ITEMS.register("lava_bottle",
            () -> new BottleLavaItem(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP).durability(100)));

    public static final RegistryObject<Item> MILK_BOTTLE = ITEMS.register("milk_bottle",
            () -> new BottleMilkItem(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> BLOOD_SIGIL = ITEMS.register("blood_sigil",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> ANIMAL_FAT = ITEMS.register("animal_fat",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> TALLOW_IMPURITY = ITEMS.register("tallow_impurity",
            () -> new TallowImpurityItem(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));


    public static final RegistryObject<Item> INFUSED_FABRIC = ITEMS.register("infused_fabric",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> MANDRAKE_ROOT = ITEMS.register("mandrake_root",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> SELENITE_SHARD = ITEMS.register("selenite_shard",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> SAGE = ITEMS.register("sage",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> SAGE_SEED = ITEMS.register("sage_seed",
            () -> new BlockItem(ModBlocks.SAGE.get(), new Item.Properties()
                    //.food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1f).fastToEat().build())
                    .tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> SAGE_BUNDLE = ITEMS.register("sage_bundle",
            () -> new Item(new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> LILY_PAD_ITEM = ITEMS.register("flowering_lily_pad",
            () -> new FloweringLilyPadItem(ModBlocks.LILY_PAD_BLOCK.get(),(new Item.Properties()).tab(ModItemGroup.HEXEREI_GROUP))); // ModBlocks.LILY_PAD_BLOCK.get(),

    // EGG ITEMS

//    public static final RegistryObject<ModSpawnEggItem> BUFF_ZOMBIE_SPAWN_EGG = ITEMS.register("buff_zombie_spawn_egg",
//            () -> new ModSpawnEggItem(ModEntityTypes.BUFF_ZOMBIE, 0x464F56, 0x1D6336,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));
//
//    public static final RegistryObject<ModSpawnEggItem> PIGEON_SPAWN_EGG = ITEMS.register("pigeon_spawn_egg",
//            () -> new ModSpawnEggItem(ModEntityTypes.PIGEON, 0x879995, 0x576ABC,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));


    // ARMOR ITEMS
//    public static final RegistryObject<Item> ORC_HELMET = ITEMS.register("orc_helmet",
//            () -> new OrcArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.HEAD,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));
//
//    public static final RegistryObject<Item> ORC_CHESTPLATE = ITEMS.register("orc_chestplate",
//            () -> new OrcArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.CHEST,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));
//
//    public static final RegistryObject<Item> ORC_LEGGINGS = ITEMS.register("orc_leggings",
//            () -> new OrcArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.LEGS,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));
//
//    public static final RegistryObject<Item> ORC_BOOTS = ITEMS.register("orc_boots",
//            () -> new OrcArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.FEET,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

//    public static final RegistryObject<Item> DRUID_HELMET = ITEMS.register("druid_helmet",
//            () -> new DruidArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.HEAD,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));
//
//    public static final RegistryObject<Item> DRUID_CHESTPLATE = ITEMS.register("druid_chestplate",
//            () -> new DruidArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.CHEST,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));
//
//    public static final RegistryObject<Item> DRUID_LEGGINGS = ITEMS.register("druid_leggings",
//            () -> new DruidArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.LEGS,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));
//
//    public static final RegistryObject<Item> DRUID_BOOTS = ITEMS.register("druid_boots",
//            () -> new DruidArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.FEET,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> WITCH_HELMET = ITEMS.register("witch_helmet",
            () -> new WitchArmorItem(ModArmorMaterial.INFUSED_FABRIC, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> WITCH_CHESTPLATE = ITEMS.register("witch_chestplate",
            () -> new WitchArmorItem(ModArmorMaterial.INFUSED_FABRIC, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

//    public static final RegistryObject<Item> WITCH_LEGGINGS = ITEMS.register("witch_leggings",
//            () -> new WitchArmorItem(ModArmorMaterial.ARMOR_SCRAP, EquipmentSlot.LEGS,
//                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));

    public static final RegistryObject<Item> WITCH_BOOTS = ITEMS.register("witch_boots",
            () -> new WitchArmorItem(ModArmorMaterial.INFUSED_FABRIC, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModItemGroup.HEXEREI_GROUP)));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
