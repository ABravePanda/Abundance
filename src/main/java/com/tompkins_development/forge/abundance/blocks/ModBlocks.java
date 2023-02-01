package com.tompkins_development.forge.abundance.blocks;

import com.tompkins_development.forge.abundance.AbundanceMod;
import com.tompkins_development.forge.abundance.blocks.custom.CuttingBoardBlock;
import com.tompkins_development.forge.abundance.blocks.custom.PotBlock;
import com.tompkins_development.forge.abundance.items.ModItems;
import com.tompkins_development.forge.abundance.tabs.ModCreativeModeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AbundanceMod.MOD_ID);

    public static final RegistryObject<Block> CUTTINGBOARD = registerBlock("cuttingboard",
            () -> new CuttingBoardBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)), ModCreativeModeTabs.BLOCKS_TAB);
    public static final RegistryObject<Block> POT = registerBlock("pot",
            () -> new PotBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), ModCreativeModeTabs.BLOCKS_TAB);

    private static <T extends Block> RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
