package twmmeredydd.atrusdoors.forge;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(AtrusDoors.MOD_ID)
public class AtrusDoorsForge {

    public AtrusDoorsForge() {
        register(Registries.ITEM, resourceLocationBiConsumer -> AtrusDoorsItems.consume(resourceLocationBiConsumer));
        registerItemGroup();
    }

    public static IEventBus getBus() {
        return FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static <T> void register(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<ResourceLocation, ? super T>> consumer) {
        getBus().addListener((RegisterEvent event) -> event.register(registry, helper -> consumer.accept(helper::register)));
    }

    public static void registerItemGroup() {
        getBus().addListener((CreativeModeTabEvent.Register event) -> event.registerCreativeModeTab(AtrusDoors.id("main"), AtrusDoors::buildItemGroup));
    }
}
