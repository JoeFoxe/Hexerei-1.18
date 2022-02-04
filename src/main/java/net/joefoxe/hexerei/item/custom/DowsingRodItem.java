package net.joefoxe.hexerei.item.custom;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.joefoxe.hexerei.particle.ModParticleTypes;
import net.joefoxe.hexerei.util.HexereiPacketHandler;
import net.joefoxe.hexerei.util.message.DowsingRodUpdatePositionPacket;
import net.joefoxe.hexerei.util.message.EmitExtinguishParticlesPacket;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class DowsingRodItem extends Item {

    public BlockPos nearestPos = null;
    public boolean swampMode = true;

    public DowsingRodItem(Properties properties) {
        super(properties);
    }


    public static double angleDifference( double angle1, double angle2 )
    {
        double diff = ( angle2 - angle1 + 180 ) % 360 - 180;
        return diff < -180 ? diff + 360 : diff;
    }


    @Override
    public void inventoryTick(ItemStack p_41404_, Level world, Entity entity, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, world, entity, p_41407_, p_41408_);

        if(entity instanceof Player){
            if(this.nearestPos== null && ((Player) entity).getMainHandItem() == p_41404_ || ((Player) entity).getOffhandItem() == p_41404_) {
                if (this.swampMode)
                    findSwamp(world, entity);
                else
                    findJungle(world, entity);
            }
        }

    }


    public static final DynamicCommandExceptionType ERROR_INVALID_BIOME = new DynamicCommandExceptionType((p_137850_) -> {
        return new TranslatableComponent("commands.locatebiome.invalid", p_137850_);
    });

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        HitResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.ANY);


        if(playerIn.isSecondaryUseActive()) {
            if(!worldIn.isClientSide){
                playerIn.getCooldowns().addCooldown(this, 20);
                this.swampMode = !this.swampMode;

                String s = "display.hexerei.dowsing_rod_swamp";
                if (!this.swampMode)
                    s = "display.hexerei.dowsing_rod_jungle";

                if (this.swampMode)
                    findSwamp(worldIn, playerIn);
                else
                    findJungle(worldIn, playerIn);


                playerIn.displayClientMessage(new TranslatableComponent(s), true);
            }
        }
        else
        {
            if (this.swampMode)
                findSwamp(worldIn, playerIn);
            else
                findJungle(worldIn, playerIn);

        }
        if(!worldIn.isClientSide)
            HexereiPacketHandler.instance.send(PacketDistributor.TRACKING_CHUNK.with(() -> worldIn.getChunkAt(playerIn.blockPosition())), new DowsingRodUpdatePositionPacket(itemstack, this.nearestPos, this.swampMode));

        return InteractionResultHolder.pass(itemstack);
    }

    public void findSwamp(Level worldIn, Entity entity)
    {
        if(!worldIn.isClientSide){
            Biome biome = null;
            try {
                biome = entity.getServer().registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getOptional(Biomes.SWAMP).orElseThrow(() -> {
                    return ERROR_INVALID_BIOME.create(Biomes.SWAMP);
                });
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }


            this.nearestPos = ((ServerLevel) worldIn).findNearestBiome(biome, entity.blockPosition(), 6400, 8);

        }
    }



    public void findJungle(Level worldIn, Entity entity)
    {
        if(!worldIn.isClientSide){
            Biome biome = null;
            try {
                biome = entity.getServer().registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getOptional(Biomes.JUNGLE).orElseThrow(() -> {
                    return ERROR_INVALID_BIOME.create(Biomes.SWAMP);
                });
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }


            this.nearestPos = ((ServerLevel) worldIn).findNearestBiome(biome, entity.blockPosition(), 6400, 8);

        }
    }

    //BlockPos blockpos1 = p_137843_.getLevel().findNearestBiome(biome, blockpos, 6400, 8);
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flagIn) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableComponent("<%s>", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAA6600)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.dowsing_rod_2").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.dowsing_rod_3").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.dowsing_rod_4").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.dowsing_rod_5").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
        } else {
            tooltip.add(new TranslatableComponent("[%s]", new TranslatableComponent("tooltip.hexerei.shift").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xAAAA00)))).withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
            tooltip.add(new TranslatableComponent("tooltip.hexerei.dowsing_rod").withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x999999))));
        }


        super.appendHoverText(stack, world, tooltip, flagIn);
    }
}