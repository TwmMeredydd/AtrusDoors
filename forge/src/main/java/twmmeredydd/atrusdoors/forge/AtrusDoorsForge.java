package twmmeredydd.atrusdoors.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import twmmeredydd.atrusdoors.AtrusDoors;

@Mod(AtrusDoors.MOD_ID)
public class AtrusDoorsForge {

    public AtrusDoorsForge() {
        AtrusDoors.init();
        registerItemGroup();

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static IEventBus getBus() {
        return FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static void registerItemGroup() {
        getBus().addListener((CreativeModeTabEvent.Register event) -> event.registerCreativeModeTab(AtrusDoors.id("main"), AtrusDoors::buildItemGroup));
    }
}
