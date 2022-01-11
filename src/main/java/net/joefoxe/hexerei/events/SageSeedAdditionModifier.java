package net.joefoxe.hexerei.events;


import com.google.gson.JsonObject;
import net.joefoxe.hexerei.Hexerei;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;


public class SageSeedAdditionModifier extends LootModifier {
    private final Item addition;
    private final int count;

    protected SageSeedAdditionModifier(LootItemCondition[] conditionsIn, Item addition, int count) {
        super(conditionsIn);
        this.addition = addition;
        this.count = count;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        // generatedLoot is the loot that would be dropped, if we wouldn't add or replace
        // anything!
        generatedLoot.add(new ItemStack(addition, count));

        return generatedLoot;
    }

    @SubscribeEvent
    public static void registerModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> registryEvent) {
        registryEvent.getRegistry().register(new SageSeedAdditionModifier.Serializer().setRegistryName(Hexerei.MOD_ID, "global_loot_modifiers"));
    }

    public static class Serializer extends GlobalLootModifierSerializer<SageSeedAdditionModifier> {

        @Override
        public SageSeedAdditionModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            int count = GsonHelper.getAsInt(object, "count", 1);
            return new SageSeedAdditionModifier(conditionsIn, addition, count);
        }

        @Override
        public JsonObject write(SageSeedAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            json.addProperty("count", instance.count);
            return json;
        }
    }

}