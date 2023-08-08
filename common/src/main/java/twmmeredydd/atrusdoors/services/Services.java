package twmmeredydd.atrusdoors.services;

import twmmeredydd.atrusdoors.services.impl.IPlatformImpl;

import java.util.ServiceLoader;

public class Services {
    public static final IPlatformImpl PLATFORM = load(IPlatformImpl.class);

    public static <T> T load(Class<T> clazz) {
        final T platform = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("No avaliable service for " + clazz.getName()));
        return platform;
    }
}
