package twmmeredydd.atrusdoors.helper;

import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;

import java.util.Arrays;
import java.util.function.Function;

public class NBTHelper {

    public static <T> ListTag listTagFromArray(Function<T, ? extends Tag> function, T... entries) {
        ListTag tag = new ListTag();
        Arrays.stream(entries).map(function).forEach(tag::add);
        return tag;
    }
}
