package twmmeredydd.atrusdoors.fabric;

import net.fabricmc.loader.api.FabricLoader;
import twmmeredydd.atrusdoors.services.platform.IPlatformImpl;

public class FabricPlatformImpl implements IPlatformImpl {
    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }
}
