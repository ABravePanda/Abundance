package com.tompkins_development.forge.abundance.blocks.entity.custom;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tompkins_development.forge.abundance.blocks.entity.ModBlockEntities;
import com.tompkins_development.forge.abundance.blocks.renderer.CuttingBoardRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jline.utils.InfoCmp;

import javax.annotation.Nonnull;

public class CuttingBoardBlockEntity extends BlockEntity {

    private final ItemStackHandler itemHandler;

    public CuttingBoardBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.CUTTINGBOARD_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.itemHandler = createInventory();
    }

    public void setItem(ItemStack item) {
        ItemStack copy = item.copy();
        //item.shrink(1);
        itemHandler.setStackInSlot(0, copy);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(1);
        inventory.addItem(getItem());
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public ItemStack getItem() {
        //System.out.println(this.itemHandler.getStackInSlot(0));
        return itemHandler.getStackInSlot(0);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, CuttingBoardBlockEntity pBlockEntity) {
    }

    private ItemStackHandler createInventory() {
        return new ItemStackHandler(1) {
            @Override
            public void setStackInSlot(int slot, @NotNull ItemStack stack) {
                super.setStackInSlot(slot, stack);
                CuttingBoardBlockEntity.this.update();
            }
        };
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        ClientboundBlockEntityDataPacket packet = ClientboundBlockEntityDataPacket.create(this);
        return packet;
    }

    @Override
    public CompoundTag getUpdateTag() {
        return serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }


    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void update() {
        requestModelDataUpdate();
        setChanged();
        assert this.level != null;
        this.level.setBlockAndUpdate(worldPosition, getBlockState());
        this.level.markAndNotifyBlock(this.worldPosition, this.level.getChunkAt(this.worldPosition), this.getBlockState(), this.getBlockState(), 2, 0);
    }
}
