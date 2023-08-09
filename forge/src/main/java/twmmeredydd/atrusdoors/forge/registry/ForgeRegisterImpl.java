package twmmeredydd.atrusdoors.forge.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.registry.Register;

import java.util.function.Supplier;

public class ForgeRegisterImpl<T> extends Register<T> {
    private final DeferredRegister<T> contents;

    public ForgeRegisterImpl(ResourceKey<? extends Registry<T>> registryKey) {
        contents = DeferredRegister.create(registryKey, AtrusDoors.MOD_ID);
    }

    @Override
    public <I extends T> Supplier<I> register(String name, Supplier<I> entry) {
        return contents.register(name, entry);
    }

    @Override
    public void registerAll() {
        contents.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
