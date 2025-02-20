package net.earlyalpha.aristysa.datagen.tags;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> LEAD_ORE =
                commonTag("lead_ores");
        public static final TagKey<Block> ALUMINUM_ORE =
                commonTag("aluminum_ores");
        public static final TagKey<Block> LEAD_BLOCKS =
                commonTag("lead_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Aristysa.MOD_ID, name));
        }
        private static TagKey<Block> commonTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> LEAD_INGOTS =
                commonTag("lead_ingots");
        public static final TagKey<Item> ALUMINUM_INGOTS =
                commonTag("aluminum_ingots");
        public static final TagKey<Item> ALUMINUM_PLATES =
                commonTag("aluminum_plates");
        public static final TagKey<Item> LEAD_PLATES =
                commonTag("lead_plates");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Aristysa.MOD_ID, name));
        }
        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
