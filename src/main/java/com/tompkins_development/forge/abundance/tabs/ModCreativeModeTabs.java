package com.tompkins_development.forge.abundance.tabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class ModCreativeModeTabs {

    public static final CreativeModeTab SEED_TAB = new CreativeModeTab("seeds") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.WHEAT_SEEDS);
        }

    };

    public static final CreativeModeTab FOOD_TAB = new CreativeModeTab("food") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.BREAD);
        }

    };

    public static final CreativeModeTab BLOCKS_TAB = new CreativeModeTab("blocks") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.FURNACE);
        }

    };
}
