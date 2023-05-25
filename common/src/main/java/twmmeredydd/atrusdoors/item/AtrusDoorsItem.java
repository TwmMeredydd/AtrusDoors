package twmmeredydd.atrusdoors.item;

import net.minecraft.world.item.Item;

public class AtrusDoorsItem extends Item implements CreativeTabMember {
    private final boolean isInTab;
    public AtrusDoorsItem(Properties properties, boolean isInTab) {
        super(properties);
        this.isInTab = isInTab;
    }

    @Override
    public boolean isInTab() {
        return isInTab;
    }
}
