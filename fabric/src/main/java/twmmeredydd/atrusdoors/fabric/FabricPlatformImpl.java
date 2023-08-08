package twmmeredydd.atrusdoors.fabric;

import net.fabricmc.loader.api.FabricLoader;
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
}
