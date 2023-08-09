package twmmeredydd.atrusdoors.forge;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.block.AtrusDoorsBlocks;
import twmmeredydd.atrusdoors.block.entity.AtrusDoorsBlockEntityTypes;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(AtrusDoors.MOD_ID)
public class AtrusDoorsForge {

    public AtrusDoorsForge() {
        // IDK why, but replacing the lambda with a map::forEach reference causes a frozen registry error
        AtrusDoorsBlocks.REGISTER.registerAll();
        AtrusDoorsItems.REGISTER.registerAll();
        register(Registries.ENTITY_TYPE, consumer -> AtrusDoorsEntityTypes.ENTITY_TYPES.forEach(consumer));
        register(Registries.BLOCK_ENTITY_TYPE, consumer -> AtrusDoorsBlockEntityTypes.BLOCK_ENTITY_TYPES.forEach(consumer));
        registerItemGroup();

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static IEventBus getBus() {
        return FMLJavaModLoadingContext.get().getModEventBus();
    }

    public <T> void register(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<ResourceLocation, T>> consumer) {
        getBus().addListener((RegisterEvent event) -> event.register(registry, helper -> consumer.accept(helper::register)));
    }

//    Also don't know why this causes a registry frozen error, but not when the map is extracted to a consumer function.
//    public <T> void register(ResourceKey<Registry<T>> registry, Map<ResourceLocation, T> map) {
//        getBus().addListener((RegisterEvent event) -> event.register(registry, helper -> map.forEach(helper::register)));
//    }

    public static void registerItemGroup() {
        getBus().addListener((CreativeModeTabEvent.Register event) -> event.registerCreativeModeTab(AtrusDoors.id("main"), AtrusDoors::buildItemGroup));
    }
}
