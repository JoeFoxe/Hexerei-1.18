package net.joefoxe.hexerei.item.custom;

import net.minecraft.world.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

public class FloweringLilyPadItem extends BlockItem {
    public FloweringLilyPadItem(Block p_43436_, Item.Properties p_43437_) {
        super(p_43436_, p_43437_);
    }

//    public FloweringLilyPadItem(Block blockIn, Item.Properties builder) {
//        super(blockIn, builder);
//    }


//    public InteractionResult useOn(UseOnContext context) {
//        return InteractionResult.PASS;
//    }
//
//    /**
//     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
//     * {@link #useOn}.
//     */
//    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
//        BlockHitResult blockraytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
//        BlockHitResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos().above());
////        if(worldIn.getFluidState(blockraytraceresult.getPos()).getType() != Fluids.WATER)
////            return new InteractionResultHolder<>(InteractionResult.PASS, playerIn.getItemInHand(handIn));
//        InteractionResult actionresulttype = super.useOn(new UseOnContext(playerIn, handIn, blockraytraceresult1));
//        return new InteractionResultHolder<>(actionresulttype, playerIn.getItemInHand(handIn));
//    }



    public InteractionResult useOn(UseOnContext p_43439_) {
        return InteractionResult.PASS;
    }

    public InteractionResultHolder<ItemStack> use(Level p_43441_, Player p_43442_, InteractionHand p_43443_) {
        BlockHitResult blockhitresult = getPlayerPOVHitResult(p_43441_, p_43442_, ClipContext.Fluid.SOURCE_ONLY);
        BlockHitResult blockhitresult1 = blockhitresult.withPosition(blockhitresult.getBlockPos().above());
        InteractionResult interactionresult = super.useOn(new UseOnContext(p_43442_, p_43443_, blockhitresult1));
        return new InteractionResultHolder<>(interactionresult, p_43442_.getItemInHand(p_43443_));
    }
}
