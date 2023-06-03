package twmmeredydd.atrusdoors.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import twmmeredydd.atrusdoors.client.model.entity.LinkingBookModel;
import twmmeredydd.atrusdoors.client.renderer.entity.LinkingBookRenderer;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;

public class AtrusDoorsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(AtrusDoorsEntityTypes.LINKING_BOOK, LinkingBookRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(LinkingBookModel.LAYER, LinkingBookModel::createBodyLayer);
    }
}
