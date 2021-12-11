package net.joefoxe.hexerei.block.custom.trees;


import net.joefoxe.hexerei.world.gen.ModConfiguredFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;

import javax.annotation.Nullable;
import java.util.Random;

public class MahoganyTree extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random p_60014_, boolean p_60015_) {
        return ModConfiguredFeatures.MAHOGANY;
    }
}
