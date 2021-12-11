package net.joefoxe.hexerei.item.custom;

import net.joefoxe.hexerei.Hexerei;
//import net.joefoxe.hexerei.client.renderer.entity.model.OrcArmorModel;
//import net.joefoxe.hexerei.client.renderer.entity.model.WitchArmorModel;
import net.joefoxe.hexerei.client.renderer.entity.model.WitchArmorModel;
import net.joefoxe.hexerei.util.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Registry;
import net.minecraft.util.Mth;
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
import net.minecraftforge.client.IItemRenderProperties;

import javax.annotation.Nullable;
import java.util.List;

public class WitchArmorItem extends ArmorItem {

    public WitchArmorItem(ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
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

//    @Override TODO find what getArmorModel changed to
//    @OnlyIn(Dist.CLIENT)
//    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlot armorSlot, A _default) {
//        return WitchArmorModel.getModel(armorSlot, entityLiving);
//    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return Hexerei.MOD_ID + ":textures/models/armor/witch_armor_layer1.png";
    }


    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.witch_armor_shift"));
        } else {
            tooltip.add(new TranslatableComponent("tooltip.hexerei.witch_armor"));
        }
        super.appendHoverText(stack, world, tooltip, flagIn);
    }



    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public WitchArmorModel getArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel _default) {
                float pticks = Minecraft.getInstance().getFrameTime();
                float f = Mth.rotLerp(pticks, entity.yBodyRotO, entity.yBodyRot);
                float f1 = Mth.rotLerp(pticks, entity.yHeadRotO, entity.yHeadRot);
                float netHeadYaw = f1 - f;
                float netHeadPitch = Mth.lerp(pticks, entity.xRotO, entity.getXRot());
//                if(ClientProxy.WITCH_ARMOR_MODEL == null)
//                    ClientProxy.WITCH_ARMOR_MODEL = new WitchArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(ClientProxy.WITCH_ARMOR_LAYER));
                ClientProxy.WITCH_ARMOR_MODEL.slot = slot;
                ClientProxy.WITCH_ARMOR_MODEL.copyFromDefault(_default);
                ClientProxy.WITCH_ARMOR_MODEL.setupAnim(entity, entity.animationPosition, entity.animationSpeed, entity.tickCount + pticks, netHeadYaw, netHeadPitch);
                return ClientProxy.WITCH_ARMOR_MODEL;
            }
        });
    }

}