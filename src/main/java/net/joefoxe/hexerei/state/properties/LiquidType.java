package net.joefoxe.hexerei.state.properties;

import net.minecraft.util.StringRepresentable;

// types of fluid inside the cauldron because im not sure how to implement a fluid tank yet. SOON will change the cauldron to hold any kind of fluid
public enum LiquidType implements StringRepresentable {
    WATER("water"),
    LAVA("lava"),
    EMPTY("empty"),
    QUICKSILVER("quicksilver"),
    BLOOD("blood"),
    TALLOW("tallow"),
    MILK("milk");

    private final String name;

    private LiquidType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getString() {
        return this.name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}