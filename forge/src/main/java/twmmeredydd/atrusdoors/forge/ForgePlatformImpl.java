package twmmeredydd.atrusdoors.forge;

import net.minecraftforge.fml.ModList;
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
}
