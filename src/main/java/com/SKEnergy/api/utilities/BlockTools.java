package com.SKEnergy.api.utilities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Adam on 20/01/2016.
 */
public class BlockTools
{
    private static final Random rnd = new Random();

    //Use these if you want to support single redstone signals in 3 bits for orientation it can emit from
    public static final int FACE_ORIENTATION = 0x7;
    public static final int FACE_REDSTONE = 0x8;

    //Use these if you want to support redstone input and output signals in 2 bits for orientation
    public static final int FACE_ORIENTATION_HORIZONTAL = 0x3;                  //2 bit orientation
    public static final int FACE_REDSTONE_IN = 0x8;                             //Redstone signal input
    public static final int FACE_REDSTONE_OUT = 0x4;                            //Redstone signal output
    public static final int FACE_STATE = 0xc;                                   //Redstone use disabled: state

    public static EnumFacing getOrientation(int metadata)
    {
        return EnumFacing.VALUES[(metadata & FACE_ORIENTATION)];
    }


    //Reads the meta value of a block and reorients the world direction to match the internal block direction.
    //This will always make the front side of the block refer to SOUTH.
    public static EnumFacing reorient(EnumFacing side, int meta)
    {
        return reorient(side, getOrientation(meta));
    }

    //As above but horizontal.
    public static EnumFacing reorientHoriz(EnumFacing side, int meta)
    {
        return reorient(side, getOrientationHoriz(meta));
    }

    public static EnumFacing reorient(EnumFacing side, EnumFacing blockDir)
    {
        switch (blockDir)
        {
            case DOWN:
                switch (side)
                {
                    case DOWN: return EnumFacing.SOUTH;
                    case UP: return EnumFacing.NORTH;
                    case NORTH: return EnumFacing.UP;
                    case SOUTH: return EnumFacing.DOWN;
                    case WEST: return EnumFacing.EAST;
                    case EAST: return EnumFacing.WEST;
                    default: return side;
                }
            case UP:
                switch (side)
                {
                    case DOWN: return EnumFacing.NORTH;
                    case UP: return EnumFacing.SOUTH;
                    case NORTH: return EnumFacing.SOUTH;
                    case SOUTH: return EnumFacing.DOWN;
                    case WEST: return EnumFacing.WEST;
                    case EAST: return EnumFacing.EAST;
                }
            case NORTH:
                if(side == EnumFacing.DOWN || side == EnumFacing.UP)
                {
                    return side;
                }
                return side.getOpposite();
            case SOUTH:
                return side;
            case WEST:
                if(side == EnumFacing.DOWN || side == EnumFacing.UP)
                {
                    return side;
                }
                else if(side == EnumFacing.WEST)
                {
                    return EnumFacing.SOUTH;
                }
                else if(side == EnumFacing.NORTH)
                {
                    return EnumFacing.WEST;
                }
                else if(side == EnumFacing.EAST)
                {
                    return EnumFacing.NORTH;
                }
                else
                {
                    return EnumFacing.EAST;
                }
            case EAST:
                if(side == EnumFacing.DOWN || side == EnumFacing.UP)
                {
                    return side;
                }
                else if(side == EnumFacing.WEST)
                {
                    return EnumFacing.NORTH;
                }
                else if(side == EnumFacing.NORTH)
                {
                    return EnumFacing.EAST;
                }
                else if(side == EnumFacing.EAST)
                {
                    return EnumFacing.SOUTH;
                }
                else
                {
                    return EnumFacing.WEST;
                }
            default:
                return side;
        }
        //return side
    }

    public static EnumFacing getTopDirection(EnumFacing dir)
    {
        switch(dir)
        {
            case DOWN:
                return EnumFacing.SOUTH;
            case UP:
                return EnumFacing.NORTH;
            default:
                return EnumFacing.UP;
        }
    }

    public static EnumFacing getBottomDirection(EnumFacing dir)
    {
        switch(dir)
        {
            case DOWN:
                return EnumFacing.NORTH;
            case UP:
                return EnumFacing.SOUTH;
            default:
                return EnumFacing.DOWN;
        }
    }

    public static int setOrientation(int metadata, EnumFacing orientation)
    {
        return (metadata & ~FACE_ORIENTATION) | orientation.ordinal();
    }

    public static EnumFacing getOrientationHoriz(int metadata)
    {
        return EnumFacing.VALUES[(metadata & FACE_ORIENTATION_HORIZONTAL)+2];
    }

    public static int setOrientationHoriz(int metadata, EnumFacing orientation)
    {
        return (metadata & ~FACE_ORIENTATION_HORIZONTAL) | (orientation.ordinal()-2);
    }

    public static int setState(int metadata, int val)
    {
        return (metadata & ~FACE_STATE) >> 2;
    }

    public static EnumFacing determineOrientation(BlockPos pos, EntityLivingBase entity)
    {
        return determineOrientation(pos.getX(), pos.getY(), pos.getZ(), entity);
    }

    public static EnumFacing determineOrientation(int x, int y, int z, EntityLivingBase entity)
    {
        if (MathHelper.abs((float) entity.posX - x) < 2.0F && MathHelper.abs((float) entity.posZ - z) < 2.0F)
        {
            double d0 = entity.posY + 1.82D - entity.getYOffset();

            if(d0 - y > 2.0D)
            {
                return EnumFacing.UP;
            }

            if(y - d0 > 0.0D)
            {
                return EnumFacing.DOWN;
            }
        }
        int l = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? EnumFacing.NORTH : (l == 1 ? EnumFacing.EAST : (l == 2 ? EnumFacing.SOUTH : (l == 3 ? EnumFacing.WEST : EnumFacing.DOWN)));
    }

    public static EnumFacing determineOrientationHoriz(EntityLivingBase entity)
    {
        int l = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? EnumFacing.NORTH : (l == 1 ? EnumFacing.EAST : (l == 2 ? EnumFacing.SOUTH : (l == 3 ? EnumFacing.WEST : EnumFacing.DOWN)));
    }

    public static void emptyInventoryInWorld(World world, int x, int y, int z, Block block, IInventory inv)
    {
        for(int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack stack = inv.getStackInSlot(i);
            spawnItemStack(world, x, y, z, stack);
            inv.setInventorySlotContents(i, null);
        }
    }

    public static void spawnItemStack(World world, int x, int y, int z, ItemStack stack)
    {
        if(stack != null)
        {
            float f = rnd.nextFloat() * 0.8F + 0.1F;
            float f1 =rnd.nextFloat() * 0.8F + 0.1F;
            EntityItem item;

            float f2 = rnd.nextFloat() * 0.8F + 0.1F;
            while(stack.stackSize > 0)
            {
                int j = rnd.nextInt(21) + 10;

                if(j > stack.stackSize)
                {
                    j = stack.stackSize;
                }

                stack.stackSize -= j;
                item = new EntityItem(world, (x + f), (y + f1), (z + f2), new ItemStack(stack.getItem(), j, stack.getItemDamage()));
                float f3 = 0.05f;
                item.motionX = ((float)rnd.nextGaussian() * f3);
                item.motionY = ((float)rnd.nextGaussian() * f3 + 0.2F);
                item.motionZ = ((float)rnd.nextGaussian() * f3);

                if(stack.hasTagCompound())
                {
                    item.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
                }
                world.spawnEntityInWorld(item);
            }
        }
    }

    public static Block getBlock(ItemStack stack)
    {
        if(stack.getItem() instanceof ItemBlock)
        {
            return ((ItemBlock) stack.getItem()).getBlock();
        }
        else
        {
            return null;
        }
    }
}
