package net.joefoxe.hexerei.data.recipes;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

public class HexereiDataGenerator {
    private HexereiDataGenerator() {}

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        gen.addProvider(new HexereiRecipeProvider(gen));


    }
}
