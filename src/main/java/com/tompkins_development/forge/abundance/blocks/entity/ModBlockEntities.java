package com.tompkins_development.forge.abundance.blocks.entity;

import com.tompkins_development.forge.abundance.AbundanceMod;
import com.tompkins_development.forge.abundance.blocks.ModBlocks;
import com.tompkins_development.forge.abundance.blocks.entity.custom.CuttingBoardBlockEntity;
import com.tompkins_development.forge.abundance.blocks.entity.custom.PotBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, AbundanceMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<CuttingBoardBlockEntity>> CUTTINGBOARD_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("cuttingboard_entity", () ->
                    BlockEntityType.Builder.of(CuttingBoardBlockEntity::new,
                            ModBlocks.CUTTINGBOARD.get()).build(null));

    public static final RegistryObject<BlockEntityType<PotBlockEntity>> POT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("pot_entity", () ->
                    BlockEntityType.Builder.of(PotBlockEntity::new,
                            ModBlocks.POT.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
