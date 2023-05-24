package twmmeredydd.atrusdoors.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;

import java.util.Map;

public class AtrusDoorsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        register(BuiltInRegistries.ITEM, AtrusDoorsItems.ITEMS);
    }

    public static <T> void register(Registry<T> registry, Map<ResourceLocation, T> map) {
        map.forEach((r, t) -> Registry.register(registry, r, t));
    }
}
