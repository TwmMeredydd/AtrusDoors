package twmmeredydd.atrusdoors.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import twmmeredydd.atrusdoors.block.BookstandBlock;
import twmmeredydd.atrusdoors.block.entity.BookstandBlockEntity;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;
import twmmeredydd.atrusdoors.entity.LinkingBookEntity;

public class BookstandRenderer implements BlockEntityRenderer<BookstandBlockEntity> {
    private final EntityRenderDispatcher entityRenderer;
    private final BlockRenderDispatcher blockRenderer;

    public BookstandRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderer = context.getEntityRenderer();
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(BookstandBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        BlockState state = blockEntity.getBlockState();
        poseStack.pushPose();
        poseStack.rotateAround(Axis.YP.rotationDegrees(RotationSegment.convertToDegrees(-state.getValue(BookstandBlock.ROTATION))), 0.5f, 0.0f, 0.5f);
        this.blockRenderer.getModelRenderer().renderModel(poseStack.last(), multiBufferSource.getBuffer(Sheets.solidBlockSheet()), state, this.blockRenderer.getBlockModel(state), 1, 1, 1, i, OverlayTexture.NO_OVERLAY);

        if (state.getValue(BookstandBlock.HAS_BOOK)) {
            LinkingBookEntity entity = AtrusDoorsEntityTypes.LINKING_BOOK.get().create(blockEntity.getLevel());

            entity.lastTickAnimProgress = blockEntity.lastTickAnimProgress;
            entity.animProgress = blockEntity.animProgress;

            poseStack.mulPose(Axis.XP.rotationDegrees(-45));
            poseStack.translate(0.5f, -0.03125f, 0.73125f);
            this.entityRenderer.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, f, poseStack, multiBufferSource, i);
        }

        poseStack.popPose();
    }
}
