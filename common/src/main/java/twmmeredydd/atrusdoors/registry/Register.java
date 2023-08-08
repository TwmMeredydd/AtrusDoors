package twmmeredydd.atrusdoors.registry;

public abstract class Register<T> {
    public abstract <I extends T> I register(String name, I entry);

    public abstract void registerAll();
}
