package twmmeredydd.atrusdoors.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import twmmeredydd.atrusdoors.block.BookStandBlock;
import twmmeredydd.atrusdoors.block.entity.BookstandBlockEntity;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;
import twmmeredydd.atrusdoors.entity.LinkingBookEntity;

public class BookstandRenderer implements BlockEntityRenderer<BookstandBlockEntity> {
    private final EntityRenderDispatcher entityRenderer;

    public BookstandRenderer(BlockEntityRendererProvider.Context context) {
        this.entityRenderer = context.getEntityRenderer();
    }

    @Override
    public void render(BookstandBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        BlockState state = blockEntity.getBlockState();
        poseStack.pushPose();
        if (state.getValue(BookStandBlock.HAS_BOOK)) {
            LinkingBookEntity entity = AtrusDoorsEntityTypes.LINKING_BOOK.create(blockEntity.getLevel());

            poseStack.mulPose(Axis.XP.rotationDegrees(-45));
            poseStack.translate(0.5f, -0.03125f, 0.73125f);
            this.entityRenderer.render(entity, 0.0f, 0.0f, 0.0f, 0.0f, f, poseStack, multiBufferSource, i);
        }
        poseStack.popPose();
    }
}
