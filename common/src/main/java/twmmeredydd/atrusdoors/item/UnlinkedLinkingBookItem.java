package twmmeredydd.atrusdoors.item;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;

public class UnlinkedLinkingBookItem extends Item {
    public UnlinkedLinkingBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stackInHand = player.getItemInHand(interactionHand);
        if (level.isClientSide) {
            return InteractionResultHolder.success(stackInHand);
        } else  if (!player.getAbilities().instabuild) {
            stackInHand.shrink(1);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        ItemStack linkedBook = new ItemStack(AtrusDoorsItems.LINKING_BOOK);
        LinkingBookData.fromEntity(player).serializeNBT(linkedBook.getOrCreateTag());

        if (stackInHand.isEmpty()) {
            return InteractionResultHolder.consume(linkedBook);
        } else if (!player.getInventory().add(linkedBook.copy())) {
            player.drop(linkedBook, false);
        }

        return InteractionResultHolder.consume(stackInHand);
    }
}
