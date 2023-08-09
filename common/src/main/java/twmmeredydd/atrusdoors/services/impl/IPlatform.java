package twmmeredydd.atrusdoors.services.impl;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import twmmeredydd.atrusdoors.registry.Register;

public interface IPlatform {
    String getPlatformName();

    boolean isModLoaded(String id);

    <T> Register<T> createRegister(ResourceKey<Registry<T>> registryKey);
}
