package net.joefoxe.hexerei.config;


import com.mojang.blaze3d.platform.InputConstants;
import com.sun.java.accessibility.util.Translator;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.resources.language.I18n;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public final class ModKeyBindings {
    public static final KeyMapping broomDescend;
    private static final String recipeCategoryName = I18n.get("hexerei.key.category.broom");

    private static final List<KeyMapping> allBindings;

    static InputConstants.Key getKey(int key) {
        return InputConstants.Type.KEYSYM.getOrCreate(key);
    }

    static {

        allBindings = List.of(
                // Overlay

                // Recipes
                broomDescend = new KeyMapping("key.hexerei.broomDescend", KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_LEFT_SHIFT), recipeCategoryName)
        );

    }

    private ModKeyBindings() {
    }

    public static void init() {
        for (KeyMapping binding : allBindings) {
            ClientRegistry.registerKeyBinding(binding);
        }
    }

}

