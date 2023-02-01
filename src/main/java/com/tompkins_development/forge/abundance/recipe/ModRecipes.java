package com.tompkins_development.forge.abundance.recipe;

import com.tompkins_development.forge.abundance.AbundanceMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AbundanceMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PotRecipe>> POT_SERIALIZER =
            SERIALIZERS.register("pot", () -> PotRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
