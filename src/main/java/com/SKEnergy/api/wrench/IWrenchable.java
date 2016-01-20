package com.SKEnergy.api.wrench;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Used to set whether a block can be wrenched
 *
 * Created by Adam on 20/01/2016.
 */
public interface IWrenchable
{
    /**
     * Dismantles the block. If returnDrops is TRUE, the drop(s) should be automatically place into player inventory
     */
    ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, BlockPos pos, boolean returnDrops);

    /**
     * Return TRUE if the block can be dismantled using a wrench. This criteria is entirely on the Block side
     */
    boolean canDismantle(EntityPlayer player, World world, BlockPos pos);
}
