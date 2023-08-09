package twmmeredydd.atrusdoors.forge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.block.entity.AtrusDoorsBlockEntityTypes;
import twmmeredydd.atrusdoors.client.model.entity.LinkingBookModel;
import twmmeredydd.atrusdoors.client.renderer.block.BookstandRenderer;
import twmmeredydd.atrusdoors.client.renderer.entity.LinkingBookRenderer;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;

@Mod.EventBusSubscriber(modid = AtrusDoors.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AtrusDoorsForgeClient {

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AtrusDoorsEntityTypes.LINKING_BOOK.get(), LinkingBookRenderer::new);

        event.registerBlockEntityRenderer(AtrusDoorsBlockEntityTypes.BOOKSTAND, BookstandRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LinkingBookModel.LAYER, LinkingBookModel::createBodyLayer);
    }
}
