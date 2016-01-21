package com.SKEnergy.api.utilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;

/**
 * Created by Adam on 20/01/2016.
 */
public class BlockPosTools
{
    public static final BlockPos INVALID = new BlockPos(-1, -1, -1);

    public static int area(BlockPos c1, BlockPos c2)
    {
        return (c2.getX()-c1.getX()+1) * (c2.getY()-c1.getY()+1) * (c2.getZ()-c1.getZ()+1);
    }

    public static BlockPos center(BlockPos c1, BlockPos c2)
    {
        return new BlockPos((c1.getX()+c2.getX()) /2, (c1.getY()+c2.getY()) /2, (c1.getZ()+c2.getZ() /2));
    }

    public static BlockPos readFromNBT(NBTTagCompound nbt, String tag)
    {
        int[] array = nbt.getIntArray(tag);
        if(array.length == 0)
        {
            return null;
        }
        else
        {
            return new BlockPos(array[0], array[1], array[2]);
        }
    }

    public static void writeToNBT(NBTTagCompound tag, String name, BlockPos coord)
    {
        if(coord == null)
        {
            tag.setIntArray(name, new int[]{});
        }
        else
        {
            tag.setIntArray(name, new int[]{coord.getX(), coord.getY(), coord.getZ()});
        }
    }

    public static NBTTagCompound writeToNBT(BlockPos coord)
    {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag, "c", coord);
        return tag;
    }

    public static String toString(BlockPos pos)
    {
        return pos.getX() + "," + pos.getY() + "," + pos.getZ();
    }
}
