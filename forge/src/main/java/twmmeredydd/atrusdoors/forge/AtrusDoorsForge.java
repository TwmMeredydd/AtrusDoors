package twmmeredydd.atrusdoors.forge;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;
import twmmeredydd.atrusdoors.item.BaseItem;

import java.util.Map;

@Mod(AtrusDoors.MOD_ID)
public class AtrusDoorsForge {
    private final IEventBus bus;

    public AtrusDoorsForge() {
        bus = FMLJavaModLoadingContext.get().getModEventBus();

        register(Registries.ITEM, AtrusDoorsItems.ITEMS);
        registerItemGroup();
    }

    public <T> void register(ResourceKey<Registry<T>> registry, Map<ResourceLocation, T> map) {
        bus.addListener((RegisterEvent event) -> map.forEach((r, t) -> event.register(registry, r, () -> t)));
    }

    //Replace with custom class provider which other registries can easily be added to or something.

    public void registerItemGroup() {
        bus.addListener((CreativeModeTabEvent.Register event) -> {
            event.registerCreativeModeTab(AtrusDoors.id("main"), builder -> {
                builder.title(Component.translatable("itemGroup." + AtrusDoors.MOD_ID + ".main"));
                builder.icon(() -> ItemStack.EMPTY);
                builder.displayItems((itemDisplayParameters, output) -> {
                    AtrusDoorsItems.ITEMS.values().forEach(t -> {
                        if (((BaseItem)t).isInGroup()) {
                            output.accept(t);
                        }
                    });
                });
            });
        });
    }
}
