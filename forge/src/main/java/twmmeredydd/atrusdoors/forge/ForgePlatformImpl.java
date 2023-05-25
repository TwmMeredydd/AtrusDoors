package twmmeredydd.atrusdoors.forge;

import net.minecraftforge.fml.ModList;
import twmmeredydd.atrusdoors.services.platform.IPlatformImpl;

public class ForgePlatformImpl implements IPlatformImpl {
    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}
