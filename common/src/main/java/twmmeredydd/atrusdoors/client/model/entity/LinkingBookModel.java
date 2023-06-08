package twmmeredydd.atrusdoors.client.model.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.entity.LinkingBookEntity;

public class LinkingBookModel extends EntityModel<LinkingBookEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(AtrusDoors.id("linking_book"), "main");

    private final ModelPart root;
    private final ModelPart pages;
    private final ModelPart topCover;
    private final ModelPart bottomCover;
    private final ModelPart spine;

    public LinkingBookModel(ModelPart root) {
        this.root = root;
        this.topCover = root.getChild("top_cover");
        this.bottomCover = root.getChild("bottom_cover");
        this.spine = root.getChild("spine");
        this.pages = root.getChild("pages");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("top_cover", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -0.01F, -4.5F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(12, 0).addBox(0.0F, -0.005F, -4.5F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));
        partDefinition.addOrReplaceChild("bottom_cover", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, 0.0F, -4.5F, 6.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -2.01F, -4.5F, 0.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("pages", CubeListBuilder.create().texOffs(14, 11).addBox(0.005F, -2.005F, -3.5F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }

    @Override
    public void setupAnim(LinkingBookEntity entity, float f, float g, float h, float i, float j) {
        //this.topCover.zRot = -Mth.sin(f * Mth.PI / 2) * Mth.PI;
        this.topCover.zRot = -f * Mth.PI;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        ImmutableList.of(this.root).forEach(modelPart -> modelPart.render(poseStack, vertexConsumer, i, j, f, g, h, k));
    }
}
