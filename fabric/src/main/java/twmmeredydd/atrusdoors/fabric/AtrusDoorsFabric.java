package twmmeredydd.atrusdoors.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.block.AtrusDoorsBlocks;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;

import java.util.Map;

public class AtrusDoorsFabric implements ModInitializer {
    public static final CreativeModeTab TAB = AtrusDoors.buildItemGroup(FabricItemGroup.builder(AtrusDoors.id("main")))
            .build();

    @Override
    public void onInitialize() {
        register(BuiltInRegistries.BLOCK, AtrusDoorsBlocks.BLOCKS);
        register(BuiltInRegistries.ITEM, AtrusDoorsItems.ITEMS);
        register(BuiltInRegistries.ENTITY_TYPE, AtrusDoorsEntityTypes.ENTITY_TYPES);
    }

    public static <T> void register(Registry<T> registry, Map<ResourceLocation, T> map) {
        map.forEach((r, t) -> Registry.register(registry, r, t));
    }
}
