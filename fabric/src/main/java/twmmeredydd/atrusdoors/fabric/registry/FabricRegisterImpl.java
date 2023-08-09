package twmmeredydd.atrusdoors.fabric.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.registry.Register;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FabricRegisterImpl<T> extends Register<T> {
    private Registry<T> registry;
    private final Map<ResourceLocation, Supplier<? extends T>> contents;

    public FabricRegisterImpl(ResourceKey<? extends Registry<T>> registryKey) {
        this.registry = (Registry<T>) BuiltInRegistries.REGISTRY.get(registryKey.location());

        contents = new LinkedHashMap<>();
    }

    @Override
    public <I extends T> Supplier<I> register(String name, Supplier<I> entry) {
        I object = entry.get();
        if (contents.putIfAbsent(AtrusDoors.id(name), () -> object) != null) {
            throw new IllegalArgumentException("Duplicate registration " + name);
        }
        return () -> object;
    }

    @Override
    public void registerAll() {
        contents.forEach((resourceLocation, t) -> Registry.register(registry, resourceLocation, t.get()));
    }
}
