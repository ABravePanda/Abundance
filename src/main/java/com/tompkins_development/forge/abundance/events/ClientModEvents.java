package com.tompkins_development.forge.abundance.events;

import com.tompkins_development.forge.abundance.AbundanceMod;
import com.tompkins_development.forge.abundance.blocks.entity.ModBlockEntities;
import com.tompkins_development.forge.abundance.blocks.entity.custom.CuttingBoardBlockEntity;
import com.tompkins_development.forge.abundance.blocks.renderer.CuttingBoardRenderer;
import com.tompkins_development.forge.abundance.recipe.PotRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AbundanceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.CUTTINGBOARD_BLOCK_ENTITY.get(), CuttingBoardRenderer::new);
    }

}
