package com.tompkins_development.forge.abundance.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.tompkins_development.forge.abundance.blocks.ModBlocks;
import com.tompkins_development.forge.abundance.blocks.entity.custom.CuttingBoardBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.data.EmptyModelData;


public class CuttingBoardRenderer implements BlockEntityRenderer<CuttingBoardBlockEntity> {
    ItemRenderer itemRenderer;
    private final BlockEntityRendererProvider.Context context;
    public CuttingBoardRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(CuttingBoardBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemStack item = pBlockEntity.getItem();
        if(item.isEmpty()) return;
        pPoseStack.pushPose();
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90f));
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180f));
        pPoseStack.translate(-0.5, 0.5, 0.1);
        pPoseStack.scale(0.5f, 0.5f, 0.5f);
        itemRenderer.renderStatic(item, ItemTransforms.TransformType.FIXED, pPackedLight, pPackedOverlay, pPoseStack, pBufferSource, pPackedOverlay);
        pPoseStack.popPose();
    }
}