package twmmeredydd.atrusdoors.forge;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;

import java.util.Map;

@Mod(AtrusDoors.MOD_ID)
public class AtrusDoorsForge {
    private final IEventBus bus;

    public AtrusDoorsForge() {
        bus = FMLJavaModLoadingContext.get().getModEventBus();

        register(Registries.ITEM, AtrusDoorsItems.ITEMS);
    }

    public <T> void register(ResourceKey<Registry<T>> registry, Map<ResourceLocation, T> map) {
        bus.addListener((RegisterEvent event) -> map.forEach((r, t) -> event.register(registry, r, () -> t)));
    }
}
