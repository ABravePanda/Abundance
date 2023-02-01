package com.tompkins_development.forge.abundance.items;

import com.tompkins_development.forge.abundance.AbundanceMod;
import com.tompkins_development.forge.abundance.tabs.ModCreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AbundanceMod.MOD_ID);

    public static final RegistryObject<Item> SEED_BAG = ITEMS.register("fried_egg", () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.SEED_TAB)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
