package net.joefoxe.hexerei.data.recipes;


import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SpecialRecipeBuilder;

import java.util.function.Consumer;

public class HexereiRecipeProvider extends RecipeProvider {


    public HexereiRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        SpecialRecipeBuilder.special(CofferDyeingRecipe.SERIALIZER).save(consumer, "hexerei:coffer_dyeing");
        SpecialRecipeBuilder.special(KeychainRecipe.SERIALIZER).save(consumer, "hexerei:keychain_apply");
    }
}
