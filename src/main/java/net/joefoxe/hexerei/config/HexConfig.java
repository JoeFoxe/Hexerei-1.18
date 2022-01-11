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
                .comment("Range of the Sage Burning Plate")
                .translation("hexerei.config.spawn_disable_range")
                .define("spawn_disable_range", 48);
        builder.pop();
        builder.push("Sage Bundle Settings");
        SAGE_BUNDLE_DURATION = builder
                .comment("Duration of how long each bundle will last while burning")
                .translation("hexerei.config.sage_bundle_duration")
                .define("sage_bundle_duration_in_seconds", 3600);
        builder.pop();

        COMMON_CONFIG = builder.build();


    }

    public static void refreshServer() {
        Hexerei.LOGGER.debug("Refreshing Server Config");
        HexConfig.JARS_ONLY_HOLD_HERBS.get();
        HexConfig.SAGE_BURNING_PLATE_RANGE.get();
        HexConfig.SAGE_BUNDLE_DURATION.get();
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
    }

//    @SubscribeEvent public static void onLoad(final ModConfig.Loading configEvent) {}
//    @SubscribeEvent public static void onReload(final ModConfig.Reloading configEvent) {HexConfig.refreshServer();}

}
