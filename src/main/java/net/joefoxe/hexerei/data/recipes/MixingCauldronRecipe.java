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
import java.util.Locale;

public class MixingCauldronRecipe implements IMixingCauldronRecipe{

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final FluidStack liquid;
    private final FluidStack liquidOutput;
    private final int fluidLevelsConsumed;
    protected static final List<Boolean> itemMatchesSlot = new ArrayList<>();


    public MixingCauldronRecipe(ResourceLocation id, ItemStack output,
                                NonNullList<Ingredient> recipeItems, FluidStack liquid, FluidStack liquidOutput, int fluidLevelsConsumed) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.liquid = liquid;
        this.liquidOutput = liquidOutput;
        this.fluidLevelsConsumed = fluidLevelsConsumed;

        for(int i = 0; i < 8; i++) {
            itemMatchesSlot.add(false);
        }

    }


    @Override
    public boolean matches(Container inv, Level worldIn) {

        for(int i = 0; i < 8; i++)
            itemMatchesSlot.set(i, false);

        // the flag is to break out early in case nothing matches for that slot
        boolean flag = false;

        // cycle through each recipe slot
        for(int j = 0; j < 8; j++) {
            //cycle through each slot for each recipe slot
            for (int i = 0; i < 8; i++) {
                //if the recipe matches a slot
                if (recipeItems.get(j).test(inv.getItem(i))) {
                    // if the slot is not taken up
                    if (!itemMatchesSlot.get(i)) {
                        //mark the slot as taken up
                        itemMatchesSlot.set(i, true);
                        flag = true;
                        break;
                    }
                }
            }
            //this is where it breaks out early to stop the craft
            if(!flag)
                break;
            //reset the flag for the next iteration
            flag = false;
        }
        // checks if a slot is not taken up, if its not taken up then itll not craft
        for(int i = 0; i < 8; i++) {
            if (!itemMatchesSlot.get(i))
                return false;
        }
        //if it reaches here that means it has completed the shapeless craft and should craft it
        return true;


//        SHAPED CRAFTING - maybe bring this back as another config in the recipe to see if its shaped or shapeless
//        if(recipeItems.get(0).test(inv.getItem(0)) &&
//            recipeItems.get(1).test(inv.getItem(1)) &&
//            recipeItems.get(2).test(inv.getItem(2)) &&
//            recipeItems.get(3).test(inv.getItem(3)) &&
//            recipeItems.get(4).test(inv.getItem(4)) &&
//            recipeItems.get(5).test(inv.getItem(5)) &&
//            recipeItems.get(6).test(inv.getItem(6)) &&
//            recipeItems.get(7).test(inv.getItem(7)))
//        {
//            return true;
//        }
//        return false;

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

    public FluidStack getLiquidOutput() { return this.liquidOutput; }

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
            FluidStack liquid = deserializeFluidStack(GsonHelper.getAsJsonObject(json, "liquid"));
            FluidStack liquidOutput = deserializeFluidStack(GsonHelper.getAsJsonObject(json, "liquidOutput"));

            int fluidLevelsConsumed = GsonHelper.getAsInt(json, "fluidLevelsConsumed");

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(8, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new MixingCauldronRecipe(recipeId, output,
                    inputs, liquid, liquidOutput, fluidLevelsConsumed);
        }

        @Nullable
        @Override
        public MixingCauldronRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            return new MixingCauldronRecipe(recipeId, buffer.readItem(),
                    inputs, buffer.readFluidStack(), buffer.readFluidStack(), buffer.readInt());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MixingCauldronRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buffer);
            }
            buffer.writeItem(recipe.getResultItem());
            buffer.writeFluidStack(recipe.getLiquid());
            buffer.writeFluidStack(recipe.getLiquidOutput());
            buffer.writeInt(recipe.getFluidLevelsConsumed());
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
