package twmmeredydd.atrusdoors.item.data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Set;

import static twmmeredydd.atrusdoors.helper.NBTHelper.listTagFromArray;

public class LinkingBookData {
    public ResourceKey<Level> dimension;
    public double x, y, z;
    public float xRotation, yRotation;

    public static LinkingBookData fromEntity(LivingEntity entity) {
        LinkingBookData data = new LinkingBookData();
        data.dimension = entity.level.dimension();
        data.x = entity.getX();
        data.y = entity.getY();
        data.z = entity.getZ();
        data.xRotation = entity.getXRot();
        data.yRotation = entity.getYHeadRot();
        return data;
    }

    public static LinkingBookData deserializeNBT(CompoundTag tag) {
        LinkingBookData data = new LinkingBookData();

        CompoundTag dataTag = tag.getCompound("LinkDestination");

        data.dimension = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(dataTag.getString("Dimension")));

        ListTag pos = dataTag.getList("Pos", 6);
        data.x = pos.getDouble(0);
        data.y = pos.getDouble(1);
        data.z = pos.getDouble(2);

        ListTag rotation = dataTag.getList("Rotation", 5);
        data.xRotation = rotation.getFloat(0);
        data.yRotation = rotation.getFloat(1);

        return data;
    }

    public CompoundTag serializeNBT(CompoundTag tag) {
        CompoundTag dataTag = new CompoundTag();
        dataTag.putString("Dimension", this.dimension.location().toString());
        dataTag.put("Pos", listTagFromArray(DoubleTag::valueOf, this.x, this.y, this.z));
        dataTag.put("Rotation", listTagFromArray(FloatTag::valueOf, this.xRotation, this.yRotation));
        tag.put("LinkDestination", dataTag);
        return tag;
    }

    public boolean isValid(Level level) {
        Level toLevel = level.getServer().getLevel(dimension);
        if (toLevel == null) return false;
        return toLevel.isInWorldBounds(new BlockPos((int) x, (int) y, (int) z));
    }

    public void linkEntity(LivingEntity entity) {
        entity.teleportTo(entity.getServer().getLevel(dimension), x, y, z, Set.of(), yRotation, xRotation);
    }
}
