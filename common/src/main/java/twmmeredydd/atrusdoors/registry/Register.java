package twmmeredydd.atrusdoors.registry;

import java.util.function.Supplier;

public abstract class Register<T> {
    public abstract <I extends T> Supplier<I> register(String name, Supplier<I> supplier);

    public abstract void registerAll();
}
