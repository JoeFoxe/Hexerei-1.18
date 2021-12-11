package net.joefoxe.hexerei.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.joefoxe.hexerei.block.ModBlocks;
import net.joefoxe.hexerei.state.properties.LiquidType;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.Locale;

public class MixingCauldronRecipe implements IMixingCauldronRecipe{

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final LiquidType liquid;
    private final LiquidType liquidOutput;
    private final int fluidLevelsConsumed;


    public MixingCauldronRecipe(ResourceLocation id, ItemStack output,
                                NonNullList<Ingredient> recipeItems, LiquidType liquid, LiquidType liquidOutput, int fluidLevelsConsumed) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.liquid = liquid;
        this.liquidOutput = liquidOutput;
        this.fluidLevelsConsumed = fluidLevelsConsumed;
    }


    @Override
    public boolean matches(Container inv, Level worldIn) {
        if(recipeItems.get(0).test(inv.getItem(0)) &&
            recipeItems.get(1).test(inv.getItem(1)) &&
            recipeItems.get(2).test(inv.getItem(2)) &&
            recipeItems.get(3).test(inv.getItem(3)) &&
            recipeItems.get(4).test(inv.getItem(4)) &&
            recipeItems.get(5).test(inv.getItem(5)) &&
            recipeItems.get(6).test(inv.getItem(6)) &&
            recipeItems.get(7).test(inv.getItem(7)))
        {
            return true;
        }
        return false;

    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(Container inv) {
        return output;
    }

    @Override
    public ItemStack getResultItem() {

        return output.copy();
    }

    public LiquidType getLiquid() { return this.liquid; }

    public LiquidType getLiquidOutput() { return this.liquidOutput; }

    public int getFluidLevelsConsumed() { return this.fluidLevelsConsumed; }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.MIXING_CAULDRON.get());
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.MIXING_SERIALIZER.get();
    }

    public static class MixingCauldronRecipeType implements RecipeType<MixingCauldronRecipe> {
        @Override
        public String toString() {
            return MixingCauldronRecipe.TYPE_ID.toString();
        }
    }


    // for Serializing the recipe into/from a json
    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>>
            implements RecipeSerializer<MixingCauldronRecipe> {

        @Override
        public MixingCauldronRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            String liquid = GsonHelper.getAsString(json, "liquid");
            String liquidOutput = GsonHelper.getAsString(json, "liquidOutput");
            int fluidLevelsConsumed = GsonHelper.getAsInt(json, "fluidLevelsConsumed");

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(8, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new MixingCauldronRecipe(recipeId, output,
                    inputs, LiquidType.valueOf(liquid.toUpperCase(Locale.ROOT)), LiquidType.valueOf(liquidOutput.toUpperCase(Locale.ROOT)), fluidLevelsConsumed);
        }

        @Nullable
        @Override
        public MixingCauldronRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new MixingCauldronRecipe(recipeId, output,
                    inputs, buffer.readEnum(LiquidType.class), buffer.readEnum(LiquidType.class), buffer.readInt());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MixingCauldronRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeEnum(recipe.getLiquid());
            buffer.writeEnum(recipe.getLiquidOutput());
            buffer.writeInt(recipe.getFluidLevelsConsumed());
        }

    }
}
