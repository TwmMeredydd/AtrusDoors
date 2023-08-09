package twmmeredydd.atrusdoors.forge.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.impl.IRegisterProvider;

import java.util.function.Supplier;

public class ForgeRegisterProviderImpl implements IRegisterProvider {

    @Override
    public <T> Register<T> create(ResourceKey<? extends Registry<T>> registryKey, String modid) {
        return new RegisterImpl<>(registryKey, modid);
    }

    private class RegisterImpl<T> extends Register<T> {
        private final DeferredRegister<T> contents;

        protected RegisterImpl(ResourceKey<? extends Registry<T>> registryKey, String modid) {
            contents = DeferredRegister.create(registryKey, modid);
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
}
