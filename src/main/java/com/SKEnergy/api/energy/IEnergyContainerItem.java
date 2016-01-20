package com.SKEnergy.api.energy;

import net.minecraft.item.ItemStack;

/**
 * Implement this interface on Item classes that support external manipulation of their internal energy tanks
 *
 * eg. power tools, portable battery
 * Created by Adam on 19/01/2016.
 */
public interface IEnergyContainerItem
{
    /**
     * Adds energy to a container item. Returns the quantity of energy that was accepted.
     * NOTE: This should always return 0 if the item can't be externally charged
     *
     * @param container
     *  ItemStack to be charged
     * @param maxReceive
     *  Maximum amount of energy to be sent into the item
     * @param simulate
     *  If TRUE, the charge will only be simulated
     * @return Amount of energy that was (or would have been, if simulated) received by the item
     */
    int receiveEnergy(ItemStack container, int maxReceive, boolean simulate);

    /**
     * Removes energy to a container item. Returns the quantity of energy that was accepted.
     * NOTE: This should always return 0 if the item can't be externally charged
     *
     * @param container
     *  ItemStack to be discharged
     * @param maxExtract
     *  Maximum amount of energy to be extracted from the item
     * @param simulate
     *  If TRUE, the discharge will only be simulated
     * @return Amount of energy that was (or would have been, if simulated) extracted from the item
     */
    int extractEnergy(ItemStack container, int maxExtract, boolean simulate);

    /**
     * Get the amount of energy currently stored in the container item
     */
    int getEnergyStored(ItemStack container);

    /**
     * Get the max amount of energy that can be stored in the container item
     */
    int getMaxEnergyStored(ItemStack container);
}
