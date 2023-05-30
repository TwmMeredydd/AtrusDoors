package twmmeredydd.atrusdoors.item;

import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;

public class LinkingBookItem extends Item {
    public LinkingBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack book = player.getItemInHand(interactionHand);
        if (level.isClientSide) {
            return InteractionResultHolder.success(book);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        LinkingBookData data = LinkingBookData.deserializeNBT(book.getTag());

        if (level.getServer().getLevel(data.dimension) == null) {
            player.displayClientMessage(Component.translatable("item.atrusdoors.linking_book.link_error"), true);
            return InteractionResultHolder.fail(book);
        }

        data.linkEntity(player);
        return InteractionResultHolder.success(book);
    }
}
