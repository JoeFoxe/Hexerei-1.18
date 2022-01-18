package net.joefoxe.hexerei.item.custom;

import net.joefoxe.hexerei.Hexerei;
//import net.joefoxe.hexerei.client.renderer.entity.model.DruidArmorModel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class DruidArmorItem extends ArmorItem {

    public DruidArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
    }

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlot armorSlot, A _default) {
//
//        ModModels.GearModel gearModel = ModModels.GearModel.REGISTRY.get(0);
//
//        if (gearModel == null) {
//            return null;
//        }
//
//        return (A) gearModel.forSlotType(armorSlot);
//    }

//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlot armorSlot, A _default) {
//        return DruidArmorModel.getModel(armorSlot, entityLiving);
//    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return Hexerei.MOD_ID + ":textures/models/armor/druid_armor_layer1.png";
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {

            tooltip.add(new TranslatableComponent("tooltip.hexerei.druid_armor_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.druid_armor"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}