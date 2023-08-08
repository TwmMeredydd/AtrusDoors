package twmmeredydd.atrusdoors.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface IRegister {
    <T> IRegister create(ResourceKey<? extends Registry<T>> registryKey, String modid);
}
