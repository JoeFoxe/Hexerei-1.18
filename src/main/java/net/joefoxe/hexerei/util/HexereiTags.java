package net.joefoxe.hexerei.util;

import net.joefoxe.hexerei.Hexerei;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public class HexereiTags {

    public static class Blocks {

        public static final Tags.IOptionalNamedTag<Block> HERB_BLOCK = createTag("herbs");

        private static Tags.IOptionalNamedTag<Block> createTag(String name) {
            return BlockTags.createOptional(new ResourceLocation(Hexerei.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class CrowScatter {

        public static final Tags.IOptionalNamedTag<EntityType<?>> CROW_SCATTER = createTag("herbs");

        private static Tags.IOptionalNamedTag<EntityType<?>> createTag(String name) {
            return EntityTypeTags.createOptional(new ResourceLocation(Hexerei.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<EntityType<?>> createForgeTag(String name) {
            return EntityTypeTags.createOptional(new ResourceLocation("forge", name));
        }
    }


    public static class Items {

        public static final Tags.IOptionalNamedTag<Item> SIGILS = createTag("sigils");
        public static final Tags.IOptionalNamedTag<Item> HERB_ITEM = createTag("herbs");
        public static final Tags.IOptionalNamedTag<Item> CANDLES = createTag("candles");

        private static Tags.IOptionalNamedTag<Item> createTag(String name) {
            return ItemTags.createOptional(new ResourceLocation(Hexerei.MOD_ID, name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
