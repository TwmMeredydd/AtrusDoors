package twmmeredydd.atrusdoors.services.impl;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import twmmeredydd.atrusdoors.registry.Register;

public interface IRegisterProvider {
   <T> Register<T> create(ResourceKey<? extends Registry<T>> registryKey, String modid);
}
