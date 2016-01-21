package com.SKEnergy.api.energy;

import net.minecraft.util.EnumFacing;

/**
 * Implemented on TileEntities which should provide energy
 *
 * eg. generator, machine, battery
 *
 * Created by Adam on 19/01/2016.
 */
public interface IEnergyProvider extends IEnergyConnection
{
    /**
     * Remove energy from an IEnergyProvider, internal distribution left to IEnergyProvider
     *
     * @param from
     *  Orientation the energy is extracted from
     * @param maxExtract
     *  Maximum amount of energy to extract
     * @param simulate
     *  If TRUE, the extraction will only be simulated
     * @return Amount of energy that was (or would have been if simulated) extracted
     */
    int extractEnergy(EnumFacing from, int maxExtract, boolean simulate);

    /**
     * Returns the amount of energy currently stored
     */
    int getEnergyStored(EnumFacing from);

    /**
     * Returns the maximum amount of energy that can be stored
     */
    int getMaxEnergyStored(EnumFacing from);
}
