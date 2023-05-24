package twmmeredydd.atrusdoors.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;
import twmmeredydd.atrusdoors.item.BaseItem;

import java.util.Map;

public class AtrusDoorsFabric implements ModInitializer {
    public static final CreativeModeTab TAB = FabricItemGroup.builder(AtrusDoors.id("main"))
            .icon(() -> ItemStack.EMPTY)
            .displayItems((itemDisplayParameters, output) -> {
                AtrusDoorsItems.ITEMS.values().forEach(t -> {
                    if (((BaseItem)t).isInGroup()) {
                        output.accept(t);
                    }
                });
            })
            .build();

    @Override
    public void onInitialize() {
        register(BuiltInRegistries.ITEM, AtrusDoorsItems.ITEMS);
    }

    public static <T> void register(Registry<T> registry, Map<ResourceLocation, T> map) {
        map.forEach((r, t) -> Registry.register(registry, r, t));
    }
}
