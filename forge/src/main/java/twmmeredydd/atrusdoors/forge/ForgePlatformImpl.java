package twmmeredydd.atrusdoors.forge;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.fml.ModList;
import twmmeredydd.atrusdoors.forge.registry.ForgeRegisterImpl;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.impl.IPlatform;

public class ForgePlatformImpl implements IPlatform {
    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    @Override
    public <T> Register<T> createRegister(ResourceKey<Registry<T>> registryKey) {
        return new ForgeRegisterImpl<>(registryKey);
    }
}
