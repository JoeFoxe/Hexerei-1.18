package net.joefoxe.hexerei.item.custom;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BottleLavaItem extends Item {

    public static FoodProperties FOOD = new FoodProperties.Builder().saturationMod(0).nutrition(0).alwaysEat().build();

    public BottleLavaItem(Properties properties) {
        super(properties.food(FOOD));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entityLiving) {
        if (!world.isClientSide && entityLiving instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) entityLiving;
            ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
            ItemStack itemstack = ((ServerPlayer) entityLiving).getItemInHand(InteractionHand.MAIN_HAND);
            entityLiving.setSecondsOnFire(10);
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
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        super.inventoryTick(stack, world, entity, itemSlot, isSelected);

        Random rand = new Random();

        if(rand.nextDouble() > 0.5d) {
            stack.hurtAndBreak(1, (Player) entity, player -> player.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.bottle_lava_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.bottle_lava"));
        }


        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}

