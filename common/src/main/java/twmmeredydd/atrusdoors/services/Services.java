package twmmeredydd.atrusdoors.services;

import twmmeredydd.atrusdoors.services.impl.IPlatform;
import twmmeredydd.atrusdoors.services.impl.IRegisterProvider;

import java.util.ServiceLoader;

public class Services {
    public static final IPlatform PLATFORM = load(IPlatform.class);
    public static final IRegisterProvider REGISTER_PROVIDER = load(IRegisterProvider.class);

    public static <T> T load(Class<T> clazz) {
        final T platform = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No avaliable service for " + clazz.getName()));
        return platform;
    }
}
