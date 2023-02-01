package com.tompkins_development.forge.abundance;


import com.tompkins_development.forge.abundance.blocks.ModBlocks;
import com.tompkins_development.forge.abundance.blocks.entity.ModBlockEntities;
import com.tompkins_development.forge.abundance.items.ModItems;
import com.tompkins_development.forge.abundance.recipe.ModRecipes;
import com.tompkins_development.forge.abundance.screen.ModMenuTypes;
import com.tompkins_development.forge.abundance.screen.PotScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AbundanceMod.MOD_ID)
public class AbundanceMod {

    public static final String MOD_ID = "abundance";

    public AbundanceMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
        eventBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CUTTINGBOARD.get(), RenderType.solid());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POT.get(), RenderType.solid());
        MenuScreens.register(ModMenuTypes.POT_MENU.get(), PotScreen::new);
    }
}
