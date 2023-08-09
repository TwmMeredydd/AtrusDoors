package twmmeredydd.atrusdoors.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import twmmeredydd.atrusdoors.AtrusDoors;

public class AtrusDoorsFabric implements ModInitializer {
    public static final CreativeModeTab TAB = AtrusDoors.buildItemGroup(FabricItemGroup.builder(AtrusDoors.id("main")))
            .build();

    @Override
    public void onInitialize() {
        AtrusDoors.init();
    }
}
