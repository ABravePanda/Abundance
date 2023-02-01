package com.tompkins_development.forge.abundance.blocks.custom;

import com.tompkins_development.forge.abundance.blocks.entity.ModBlockEntities;
import com.tompkins_development.forge.abundance.blocks.entity.custom.CuttingBoardBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CuttingBoardBlock extends BaseEntityBlock {

    private static final VoxelShape SHAPE = Block.box(1,0,2,15,.5,14);

    public CuttingBoardBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CuttingBoardBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, ModBlockEntities.CUTTINGBOARD_BLOCK_ENTITY.get(),
                CuttingBoardBlockEntity::tick);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pLevel.isClientSide()) return InteractionResult.FAIL;
        if(pHand != InteractionHand.MAIN_HAND) return InteractionResult.FAIL;
        CuttingBoardBlockEntity cuttingBoardBlockEntity = (CuttingBoardBlockEntity) pLevel.getBlockEntity(pPos);
        if(cuttingBoardBlockEntity == null) return InteractionResult.FAIL;
        this.setItem(cuttingBoardBlockEntity, pPlayer.getMainHandItem());
        return InteractionResult.CONSUME;
    }

    @Override
    public void destroy(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        if(pLevel.isClientSide()) return;
        CuttingBoardBlockEntity cuttingBoardBlockEntity = (CuttingBoardBlockEntity) pLevel.getBlockEntity(pPos);
        if(cuttingBoardBlockEntity == null) return;
        cuttingBoardBlockEntity.drops();
    }


    public void setItem(CuttingBoardBlockEntity entity, ItemStack item) {
        //If item is air, replace current item and drop other item
        if(item.getItem() == Items.AIR) {
            if(entity.getItem().getItem() != Items.AIR) entity.drops();
            entity.setItem(new ItemStack(Items.AIR));
            return;
        }
        //If item is not food, return
        if(item.getItem().getFoodProperties() == null && item.getItem() != Items.CAKE) return;

        //If inventory is not full of air then drop item and set new item
        if(entity.getItem().getItem() != Items.AIR) entity.drops();
        ItemStack newItemStack = item.copy();
        newItemStack.setCount(1);
        item.shrink(1);
        entity.setItem(newItemStack);
    }
}
