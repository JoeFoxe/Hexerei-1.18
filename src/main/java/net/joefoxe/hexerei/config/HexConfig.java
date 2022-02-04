package net.joefoxe.hexerei.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.joefoxe.hexerei.Hexerei;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber
public class HexConfig {

    public static ForgeConfigSpec COMMON_CONFIG;

    public static ForgeConfigSpec.BooleanValue JARS_ONLY_HOLD_HERBS;

    public static ForgeConfigSpec.ConfigValue<Integer> SAGE_BURNING_PLATE_RANGE;

    public static ForgeConfigSpec.ConfigValue<Integer> SAGE_BUNDLE_DURATION;

    public static ForgeConfigSpec.ConfigValue<Integer> WITCH_HUT_SPACING;

    public static ForgeConfigSpec.ConfigValue<Integer> WITCH_HUT_SEPARATION;

    public static ForgeConfigSpec.ConfigValue<Integer> DARK_COVEN_SPACING;

    public static ForgeConfigSpec.ConfigValue<Integer> DARK_COVEN_SEPARATION;

    public static ForgeConfigSpec.ConfigValue<Integer> BROOM_BRUSH_DURABILITY;

    public static ForgeConfigSpec.ConfigValue<Integer> ENHANCED_BROOM_BRUSH_DURABILITY;

    public static ForgeConfigSpec.ConfigValue<Integer> BROOM_NETHERITE_TIP_DURABILITY;

    public static ForgeConfigSpec.ConfigValue<Integer> BROOM_WATERPROOF_TIP_DURABILITY;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        //_____________  S E R V E R     C O N F I G   _____________//

        builder.push("Settings");
        builder.pop();
        builder.push("Herb Jar Settings");
        JARS_ONLY_HOLD_HERBS = builder
                .comment("Disabling allows jars to hold any item")
                .translation("hexerei.config.jars_only_hold_herbs")
                .define("jars_only_hold_herbs", true);
        builder.pop();
        builder.push("Sage Burning Plate Settings");
        SAGE_BURNING_PLATE_RANGE = builder
                .comment("Range of the Sage Burning Plate, setting to 0 will disable completely")
                .translation("hexerei.config.spawn_disable_range")
                .define("spawn_disable_range", 48);
        builder.pop();
        builder.push("Sage Bundle Settings");
        SAGE_BUNDLE_DURATION = builder
                .comment("Duration of how long each bundle will last while burning")
                .translation("hexerei.config.sage_bundle_duration")
                .define("sage_bundle_duration_in_seconds", 3600);
        builder.pop();
        builder.push("");
        builder.pop();
        builder.push("Structures");
        builder.pop();
        builder.push("Witch Hut Spacing");
        WITCH_HUT_SPACING = builder
                .comment("spacing between witch huts, lower both spacing and separation to increase spawn rates")
                .translation("hexerei.config.witch_hut_spacing")
                .define("witch_hut_spacing", 20);
        builder.pop();
        builder.push("Witch Hut Separation");
        WITCH_HUT_SEPARATION = builder
                .comment("separation of the witch huts, lower both spacing and separation to increase spawn rates")
                .translation("hexerei.config.witch_hut_separation")
                .define("witch_hut_separation", 8);
        builder.pop();
        builder.push("Dark Coven Spacing");
        DARK_COVEN_SPACING = builder
                .comment("spacing between dark covens, lower both spacing and separation to increase spawn rates")
                .translation("hexerei.config.dark_coven_spacing")
                .define("dark_coven_spacing", 29);
        builder.pop();
        builder.push("Dark Coven Separation");
        DARK_COVEN_SEPARATION = builder
                .comment("separation of the dark covens, lower both spacing and separation to increase spawn rates")
                .translation("hexerei.config.dark_coven_separation")
                .define("dark_coven_separation", 11);
        builder.pop();
        builder.push("Broom Brush Durability");
        BROOM_BRUSH_DURABILITY = builder
                .comment("100 durability will be about 16 minutes of flight time")
                .translation("hexerei.config.broom_brush_durability")
                .define("broom_brush_durability", 100);
        builder.pop();
        builder.push("Enhanced Broom Brush Durability");
        ENHANCED_BROOM_BRUSH_DURABILITY = builder
                .comment("200 durability will be about 32 minutes of flight time")
                .translation("hexerei.config.enhanced_broom_brush_durability")
                .define("enhanced_broom_brush_durability", 200);
        builder.pop();
        builder.push("Broom Netherite Tip Durability");
        BROOM_NETHERITE_TIP_DURABILITY = builder
                .comment("1 second of active time per 1 durability")
                .translation("hexerei.config.broom_netherite_tip_durability")
                .define("broom_netherite_tip_durability", 200);
        builder.pop();
        builder.push("Broom Waterproof Tip Durability");
        BROOM_WATERPROOF_TIP_DURABILITY = builder
                .comment("1 second of active time per 1 durability")
                .translation("hexerei.config.broom_waterproof_tip_durability")
                .define("broom_waterproof_tip_durability", 800);
        builder.pop();

        COMMON_CONFIG = builder.build();


    }

    public static void refreshServer() {
        Hexerei.LOGGER.debug("Refreshing Server Config");
        HexConfig.JARS_ONLY_HOLD_HERBS.get();
        HexConfig.SAGE_BURNING_PLATE_RANGE.get();
        HexConfig.SAGE_BUNDLE_DURATION.get();
        HexConfig.WITCH_HUT_SPACING.get();
        HexConfig.WITCH_HUT_SEPARATION.get();
        HexConfig.DARK_COVEN_SPACING.get();
        HexConfig.DARK_COVEN_SEPARATION.get();
        HexConfig.BROOM_BRUSH_DURABILITY.get();
        HexConfig.ENHANCED_BROOM_BRUSH_DURABILITY.get();
        HexConfig.BROOM_NETHERITE_TIP_DURABILITY.get();
        HexConfig.BROOM_WATERPROOF_TIP_DURABILITY.get();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        Hexerei.LOGGER.debug("Loading config file {}", path);
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        Hexerei.LOGGER.debug("Built TOML config for {}", path.toString());
        configData.load();
        Hexerei.LOGGER.debug("Loaded TOML config file {}", path.toString());
        spec.setConfig(configData);
//        System.out.println("config check");
        HexConfig.JARS_ONLY_HOLD_HERBS.get();
        HexConfig.SAGE_BURNING_PLATE_RANGE.get();
        HexConfig.SAGE_BUNDLE_DURATION.get();
        HexConfig.WITCH_HUT_SPACING.get();
        HexConfig.WITCH_HUT_SEPARATION.get();
        HexConfig.DARK_COVEN_SPACING.get();
        HexConfig.DARK_COVEN_SEPARATION.get();
        HexConfig.BROOM_BRUSH_DURABILITY.get();
        HexConfig.ENHANCED_BROOM_BRUSH_DURABILITY.get();
        HexConfig.BROOM_NETHERITE_TIP_DURABILITY.get();
        HexConfig.BROOM_WATERPROOF_TIP_DURABILITY.get();
    }

//    @SubscribeEvent public static void onLoad(final ModConfig.Loading configEvent) {}
//    @SubscribeEvent public static void onReload(final ModConfig.Reloading configEvent) {HexConfig.refreshServer();}

}
