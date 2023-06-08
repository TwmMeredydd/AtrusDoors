package twmmeredydd.atrusdoors.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.client.model.entity.LinkingBookModel;
import twmmeredydd.atrusdoors.entity.LinkingBookEntity;

public class LinkingBookRenderer extends EntityRenderer<LinkingBookEntity> {
    private final LinkingBookModel model;

    public LinkingBookRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new LinkingBookModel(context.bakeLayer(LinkingBookModel.LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(LinkingBookEntity entity) {
        return AtrusDoors.id("textures/entity/linking_book/linking_book.png");
    }

    @Override
    public void render(LinkingBookEntity entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        super.render(entity, f, g, poseStack, multiBufferSource, i);
        poseStack.pushPose();
        poseStack.scale(-1.0f, -1.0f, 1.0f);
        poseStack.translate(0, -1.501, 0);
        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot()));
        this.model.setupAnim(entity, Mth.lerp(g, entity.lastTickAnimProgress, entity.animProgress), 0,0,0,0);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.model.renderType(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        poseStack.popPose();
    }
}
