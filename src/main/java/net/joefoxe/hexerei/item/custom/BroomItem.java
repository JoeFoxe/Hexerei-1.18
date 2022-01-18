package net.joefoxe.hexerei.item.custom;

import java.util.List;
import java.util.function.Predicate;

import net.joefoxe.hexerei.client.renderer.entity.custom.BroomEntity;
import net.joefoxe.hexerei.config.ModKeyBindings;
import net.joefoxe.hexerei.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class BroomItem extends Item {
    private static final Predicate<Entity> field_219989_a = EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith);
    private final BroomEntity.Type type;

    public BroomItem(BroomEntity.Type broomType, Item.Properties properties) {
        super(properties);
        this.type = broomType;
    }

//    @Override
//    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
//        consumer.accept(new IItemRenderProperties() {
//            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(
//                    () -> new BroomItemStackRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels()));
//
//            @Override
//            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
//                return ister.get();
//            }
//        });
//    }


    @Override
    public Object getRenderPropertiesInternal() {
        return new BroomRenderProperties();
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #use}.
     */
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        HitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.ANY);
        if (raytraceresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vector3d = playerIn.getLookAngle();
            double d0 = 5.0D;
            List<Entity> list = worldIn.getEntities(playerIn, playerIn.getBoundingBox().expandTowards(vector3d.scale(5.0D)).inflate(1.0D), field_219989_a);
            if (!list.isEmpty()) {
                Vec3 vector3d1 = playerIn.getEyePosition(1.0F);

                for(Entity entity : list) {
                    AABB axisalignedbb = entity.getBoundingBox().inflate((double)entity.getPickRadius());
                    if (axisalignedbb.contains(vector3d1)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (raytraceresult.getType() == HitResult.Type.BLOCK) {
                BroomEntity boatentity = new BroomEntity(worldIn, raytraceresult.getLocation().x, raytraceresult.getLocation().y, raytraceresult.getLocation().z);
                boatentity.setBroomType(this.type);
                boatentity.setYRot(playerIn.getYRot());
                boatentity.itemHandler.deserializeNBT(itemstack.getOrCreateTag().getCompound("Inventory"));
                if(!itemstack.getOrCreateTag().contains("floatMode")) {
                    boatentity.itemHandler.setStackInSlot(2, new ItemStack(ModItems.BROOM_BRUSH.get()));
                    boatentity.sync();
                }
                boatentity.floatMode = (itemstack.getOrCreateTag().getBoolean("floatMode"));

                if (!worldIn.noCollision(boatentity, boatentity.getBoundingBox().inflate(-0.1D))) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!worldIn.isClientSide) {

                        worldIn.addFreshEntity(boatentity);
                        if (!playerIn.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                    }

                    playerIn.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        CompoundTag inv = stack.getOrCreateTag().getCompound("Inventory");
        ListTag tagList = inv.getList("Items", Tag.TAG_COMPOUND);

        if(Screen.hasShiftDown()) {

            tooltip.add(new TranslatableComponent("<%s>", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA6600)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.broom_shift_2", new TranslatableComponent(ModKeyBindings.broomDescend.getKey().getName()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xCCCC00)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.broom_shift_3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.broom_shift_4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            if(stack.is(ModItems.MAHOGANY_BROOM.get())) {
                tooltip.add(new TranslatableComponent(""));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.mahogany_broom_shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.mahogany_broom_shift_2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            }
            else {
                tooltip.add(new TranslatableComponent(""));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.willow_broom_shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.willow_broom_shift_2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            }
            if(stack.hasTag()) {
                tooltip.add(new TranslatableComponent(""));
                CompoundTag brushTags = null;
                CompoundTag miscTags = null;
                CompoundTag satchelTags = null;

                for(int i = 0; i < 3; i++){
                    if (tagList.getCompound(i).getInt("Slot") == 0 && miscTags == null)
                        miscTags = tagList.getCompound(i);
                    if (tagList.getCompound(i).getInt("Slot") == 1 && satchelTags == null)
                        satchelTags = tagList.getCompound(i);
                    if (tagList.getCompound(i).getInt("Slot") == 2 && brushTags == null)
                        brushTags = tagList.getCompound(i);
                }
                ItemStack miscItem = miscTags == null ? ItemStack.EMPTY : ItemStack.of(miscTags);
                ItemStack brushItem = brushTags == null ? ItemStack.EMPTY : ItemStack.of(brushTags);

                if(tagList.size() > (miscItem.isEmpty() ? 0 : 1) + (brushItem.isEmpty() ? 0 : 1)) {

                    tooltip.add(new TranslatableComponent("tooltip.hexerei.broom_5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                    for (int i = 0; i < tagList.size(); i++)
                    {
                        CompoundTag itemTags = tagList.getCompound(i);

                        TranslatableComponent itemText = (TranslatableComponent) new TranslatableComponent(ItemStack.of(itemTags).getDescriptionId()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x998800)));
                        TranslatableComponent itemText2 = (TranslatableComponent) new TranslatableComponent(" - %s", itemText).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999)));
                        int countText = ItemStack.of(itemTags).getCount();
                        itemText2.append(" x" + countText);

                        if(itemTags.getInt("Slot") != 0 && itemTags.getInt("Slot") != 1 && itemTags.getInt("Slot") != 2)
                            tooltip.add(itemText2);
                    }
                }
            }
        } else {
//            tooltip.add(new TranslatableComponent("tooltip.hexerei.broom"));
            tooltip.add(new TranslatableComponent("[%s]", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAA00)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            if(stack.hasTag())
            {
                CompoundTag brushTags = null;
                CompoundTag miscTags = null;
                CompoundTag satchelTags = null;

                for(int i = 0; i < 3; i++){
                    if (tagList.getCompound(i).getInt("Slot") == 0 && miscTags == null)
                        miscTags = tagList.getCompound(i);
                    if (tagList.getCompound(i).getInt("Slot") == 1 && satchelTags == null)
                        satchelTags = tagList.getCompound(i);
                    if (tagList.getCompound(i).getInt("Slot") == 2 && brushTags == null)
                        brushTags = tagList.getCompound(i);
                }
                ItemStack miscItem = miscTags == null ? ItemStack.EMPTY : ItemStack.of(miscTags);
                ItemStack brushItem = brushTags == null ? ItemStack.EMPTY : ItemStack.of(brushTags);


                TranslatableComponent miscText = (TranslatableComponent) new TranslatableComponent(miscTags == null ? "" : ItemStack.of(miscTags).isEmpty() ? "" : ((ItemStack.of(miscTags).getDescriptionId()))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA9900)));

                TranslatableComponent satchelText = (TranslatableComponent) new TranslatableComponent((satchelTags == null ? "" : ItemStack.of(satchelTags).isEmpty() ? "" : ((ItemStack.of(satchelTags).getDescriptionId())))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA9900)));

                TranslatableComponent brushText = (TranslatableComponent) new TranslatableComponent((brushTags == null ? "" : brushItem.isEmpty() ? "" : ((brushItem.getDescriptionId())))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA9900)));

                if(!brushItem.isEmpty())
                    brushText.append(new TranslatableComponent(" - %s/%s", brushItem.getMaxDamage() - brushItem.getDamageValue(), brushItem.getMaxDamage()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                if(miscItem.is(ModItems.BROOM_KEYCHAIN.get()))
                {
                    CompoundTag inv2 = miscItem.getOrCreateTag();
                    ListTag tagList2 = inv2.getList("Items", Tag.TAG_COMPOUND);
                    CompoundTag compoundtag = tagList2.getCompound(0);
                    CompoundTag itemTags = tagList2.getCompound(0);

                    TranslatableComponent itemText = (TranslatableComponent) new TranslatableComponent(ItemStack.of(compoundtag).getDescriptionId()).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x998800)));

                    miscText.append(new TranslatableComponent(" - %s", itemText).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));

                }

                tooltip.add(new TranslatableComponent("tooltip.hexerei.broom_2", miscText).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.broom_3", satchelText).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
                tooltip.add(new TranslatableComponent("tooltip.hexerei.broom_4", brushText).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));


            }
        }


        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}