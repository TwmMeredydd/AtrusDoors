package twmmeredydd.atrusdoors.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.impl.IRegisterProvider;

import java.util.LinkedHashMap;
import java.util.Map;

public class FabricRegisterProviderImpl implements IRegisterProvider {
    @Override
    public <T> Register<T> create(ResourceKey<? extends Registry<T>> registryKey, String modid) {
        return new RegisterImpl<>(registryKey, modid);
    }

    private class RegisterImpl<T> extends Register<T> {
        private Registry<T> registry;
        private final String modid;
        private final Map<ResourceLocation, T> contents;

        protected RegisterImpl(ResourceKey<? extends Registry<T>> registryKey, String modid) {
            this.registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location());
            this.modid = modid;

            contents = new LinkedHashMap<>();
        }

        @Override
        public <I extends T> I register(String name, I entry) {
            if (contents.putIfAbsent(new ResourceLocation(modid, name), entry) != null) {
                throw new IllegalArgumentException("Duplicate registration " + name);
            }
            return entry;
        }

        @Override
        public void registerAll() {
            contents.forEach((resourceLocation, t) -> Registry.register(registry, resourceLocation, t));
        }
    }
}
