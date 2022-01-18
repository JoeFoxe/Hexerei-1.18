package net.joefoxe.hexerei.data.recipes;

import net.joefoxe.hexerei.Hexerei;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {
    public static DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Hexerei.MOD_ID);


    public static final RegistryObject<MixingCauldronRecipe.Serializer> MIXING_SERIALIZER = RECIPE_SERIALIZER.register("mixingcauldron", MixingCauldronRecipe.Serializer::new);
    public static RecipeType<MixingCauldronRecipe> MIXING_CAULDRON_RECIPE = new MixingCauldronRecipe.MixingCauldronRecipeType();


    public static final RegistryObject<DipperRecipe.Serializer> DIPPER_SERIALIZER = RECIPE_SERIALIZER.register("dipper", DipperRecipe.Serializer::new);
    public static RecipeType<DipperRecipe> DIPPER_RECIPE = new DipperRecipe.DipperRecipeType();


    public static final RegistryObject<DryingRackRecipe.Serializer> DRYING_RACK_SERIALIZER = RECIPE_SERIALIZER.register("drying_rack", DryingRackRecipe.Serializer::new);
    public static RecipeType<DryingRackRecipe> DRYING_RACK_RECIPE = new DryingRackRecipe.DryingRackRecipeType();


    public static final RegistryObject<PestleAndMortarRecipe.Serializer> PESTLE_AND_MORTAR_SERIALIZER = RECIPE_SERIALIZER.register("pestle_and_mortar", PestleAndMortarRecipe.Serializer::new);
    public static RecipeType<PestleAndMortarRecipe> PESTLE_AND_MORTAR_RECIPE = new PestleAndMortarRecipe.PestleAndMortarRecipeType();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);

        Registry.register(Registry.RECIPE_TYPE, MixingCauldronRecipe.TYPE_ID, MIXING_CAULDRON_RECIPE);

        Registry.register(Registry.RECIPE_TYPE, DipperRecipe.TYPE_ID, DIPPER_RECIPE);

        Registry.register(Registry.RECIPE_TYPE, DryingRackRecipe.TYPE_ID, DRYING_RACK_RECIPE);

        Registry.register(Registry.RECIPE_TYPE, PestleAndMortarRecipe.TYPE_ID, PESTLE_AND_MORTAR_RECIPE);

    }



}
