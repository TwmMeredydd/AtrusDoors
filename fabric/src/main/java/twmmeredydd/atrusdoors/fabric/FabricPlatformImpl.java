package twmmeredydd.atrusdoors.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import twmmeredydd.atrusdoors.fabric.registry.FabricRegisterImpl;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.impl.IPlatform;

public class FabricPlatformImpl implements IPlatform {
    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    @Override
    public <T> Register<T> createRegister(ResourceKey<Registry<T>> registryKey) {
        return new FabricRegisterImpl<>(registryKey);
    }
}
