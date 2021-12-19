package net.joefoxe.hexerei.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.joefoxe.hexerei.Hexerei;
import net.joefoxe.hexerei.block.ModBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.TagParser;
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
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class DipperRecipe implements IDipperRecipe{

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final FluidStack liquid;
    private final int fluidLevelsConsumed;
    private final int dippingTime;
    private final int dryingTime;
    private final int numberOfDips;
    protected static final List<Boolean> itemMatchesSlot = new ArrayList<>();


    public DipperRecipe(ResourceLocation id, NonNullList<Ingredient> inputs,
                        ItemStack output, FluidStack liquid, int fluidLevelsConsumed, int dippingTime, int dryingTime, int numberOfDips) {
        this.id = id;
        this.output = output;
        this.recipeItems = inputs;
        this.liquid = liquid;
        this.fluidLevelsConsumed = fluidLevelsConsumed;
        this.dippingTime = dippingTime;
        this.dryingTime = dryingTime;
        this.numberOfDips = numberOfDips;

        for(int i = 0; i < 8; i++) {
            itemMatchesSlot.add(false);
        }

    }


    @Override
    public boolean matches(Container inv, Level worldIn) {
        if(recipeItems.get(0).test(inv.getItem(0) )||
                recipeItems.get(0).test(inv.getItem(1)) ||
                        recipeItems.get(0).test(inv.getItem(2)))
            return true;

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

    public FluidStack getLiquid() { return this.liquid; }

    public int getFluidLevelsConsumed() { return this.fluidLevelsConsumed; }

    public int getDippingTime() { return this.dippingTime; }

    public int getDryingTime() { return this.dryingTime; }

    public int getNumberOfDips() { return this.numberOfDips; }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.CANDLE_DIPPER.get());
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.DIPPER_SERIALIZER.get();
    }

    public static class DipperRecipeType implements RecipeType<DipperRecipe> {
        @Override
        public String toString() {
            return DipperRecipe.TYPE_ID.toString();
        }
    }


    // for Serializing the recipe into/from a json
    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>>
            implements RecipeSerializer<DipperRecipe> {

        @Override
        public DipperRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            FluidStack liquid = deserializeFluidStack(GsonHelper.getAsJsonObject(json, "liquid"));
            int fluidLevelsConsumed = GsonHelper.getAsInt(json, "fluidLevelsConsumed");
            int dippingTime = GsonHelper.getAsInt(json, "dippingTimeInTicks");
            int dryingTime = GsonHelper.getAsInt(json, "dryingTimeInTicks");
            int numberOfDips = GsonHelper.getAsInt(json, "numberOfDips");

            inputs.set(0, Ingredient.fromJson(ingredients.get(0)));

            return new DipperRecipe(recipeId, inputs,
                    output, liquid, fluidLevelsConsumed, dippingTime, dryingTime, numberOfDips);
        }

        @Nullable
        @Override
        public DipperRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            inputs.set(0, Ingredient.fromNetwork(buffer));

            ItemStack output = buffer.readItem();
            return new DipperRecipe(recipeId, inputs, output,
                    buffer.readFluidStack(), buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, DipperRecipe recipe) {

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
            buffer.writeFluidStack(recipe.getLiquid());
            buffer.writeInt(recipe.getFluidLevelsConsumed());
            buffer.writeInt(recipe.getDippingTime());
            buffer.writeInt(recipe.getDryingTime());
        }

        public static FluidStack deserializeFluidStack(JsonObject json) {
            ResourceLocation id = new ResourceLocation(GsonHelper.getAsString(json, "fluid"));
            Fluid fluid = ForgeRegistries.FLUIDS.getValue(id);
            if (fluid == null)
                throw new JsonSyntaxException("Unknown fluid '" + id + "'");
            FluidStack stack = new FluidStack(fluid, 1);

            if (!json.has("nbt"))
                return stack;

            try {
                JsonElement element = json.get("nbt");
                stack.setTag(TagParser.parseTag(
                        element.isJsonObject() ? Hexerei.GSON.toJson(element) : GsonHelper.convertToString(element, "nbt")));

            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }

            return stack;
        }

    }
}
