package twmmeredydd.atrusdoors.item;

import net.minecraft.world.item.Item;

public class BaseItem extends Item {
    private final boolean isInGroup;
    public BaseItem(Properties properties, boolean isInGroup) {
        super(properties);
        this.isInGroup = isInGroup;
    }

    public boolean isInGroup() {
        return isInGroup;
    }
}
