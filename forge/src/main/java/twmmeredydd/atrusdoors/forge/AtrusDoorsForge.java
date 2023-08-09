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
        AtrusDoorsEntityTypes.ENTITY_TYPES.registerAll();
        AtrusDoorsBlockEntityTypes.BLOCK_ENTITY_TYPES.registerAll();
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
