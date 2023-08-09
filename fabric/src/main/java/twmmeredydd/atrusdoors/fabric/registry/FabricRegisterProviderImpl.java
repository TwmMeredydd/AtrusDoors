package twmmeredydd.atrusdoors.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.impl.IRegisterProvider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FabricRegisterProviderImpl implements IRegisterProvider {
    @Override
    public <T> Register<T> create(ResourceKey<? extends Registry<T>> registryKey, String modid) {
        return new RegisterImpl<>(registryKey, modid);
    }

    private class RegisterImpl<T> extends Register<T> {
        private Registry<T> registry;
        private final String modid;
        private final Map<ResourceLocation, Supplier<? extends T>> contents;

        protected RegisterImpl(ResourceKey<? extends Registry<T>> registryKey, String modid) {
            this.registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location());
            this.modid = modid;

            contents = new LinkedHashMap<>();
        }

        @Override
        public <I extends T> Supplier<I> register(String name, Supplier<I> entry) {
            I object = entry.get();
            if (contents.putIfAbsent(new ResourceLocation(modid, name), () -> object) != null) {
                throw new IllegalArgumentException("Duplicate registration " + name);
            }
            return () -> object;
        }

        @Override
        public void registerAll() {
            contents.forEach((resourceLocation, t) -> Registry.register(registry, resourceLocation, t.get()));
        }
    }
}
