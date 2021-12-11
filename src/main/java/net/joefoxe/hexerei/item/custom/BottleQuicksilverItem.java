package net.joefoxe.hexerei.item.custom;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BottleQuicksilverItem extends Item {

    public static FoodProperties FOOD = new FoodProperties.Builder().saturationMod(1).nutrition(1).alwaysEat().effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.8F).effect(new MobEffectInstance(MobEffects.POISON, 300, 0), 0.8F).build();

    public BottleQuicksilverItem(Properties properties) {
        super(properties.food(FOOD));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving) {
        if (!world.isClientSide && entityLiving instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) entityLiving;
            ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
            ItemStack itemstack = ((ServerPlayer) entityLiving).getItemInHand(InteractionHand.MAIN_HAND);
            if (itemstack.isEmpty()) {
                player.setItemInHand(InteractionHand.MAIN_HAND, itemstack3);
            } else if (!player.inventory.add(itemstack3)) {
                player.drop(itemstack3, false);
            } else if (player instanceof ServerPlayer) {
                player.initMenu(player.containerMenu);
            }
        }

        return super.finishUsingItem(stack, world, entityLiving);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.bottle_quicksilver_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.bottle_quicksilver"));
        }


        super.appendHoverText(stack, world, tooltip, flagIn);
    }

}
