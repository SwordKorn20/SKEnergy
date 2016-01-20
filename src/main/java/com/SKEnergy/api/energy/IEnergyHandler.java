package com.SKEnergy.api.energy;

import com.SKEnergy.api.RotationalDirection;

/**
 * Implement this interface on TileEntities which should handle energy, generally storing it in one or more internal tank
 * For more information, refer to {@link IEnergyStorage}
 *
 * Created by Adam on 19/01/2016.
 */
public interface IEnergyHandler extends IEnergyProvider, IEnergyReceiver
{
    /**
     * Add energy to an IEnergyReceiver, internal distribution is left entirely to the IEnergyReceiver.
     *
     * @param from
     *            Orientation the energy is received from.
     * @param maxReceive
     *            Maximum amount of energy to receive.
     * @param simulate
     *            If TRUE, the charge will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) received.
     */
    @Override
    int receiveEnergy(RotationalDirection from, int maxReceive, boolean simulate);

    /**
     * Remove energy from an IEnergyProvider, internal distribution is left entirely to the IEnergyProvider.
     *
     * @param from
     *            Orientation the energy is extracted from.
     * @param maxExtract
     *            Maximum amount of energy to extract.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @return Amount of energy that was (or would have been, if simulated) extracted.
     */
    @Override
    int extractEnergy(RotationalDirection from, int maxExtract, boolean simulate);

    /**
     * Returns the amount of energy currently stored.
     */
    @Override
    int getEnergyStored(RotationalDirection from);

    /**
     * Returns the maximum amount of energy that can be stored.
     */
    @Override
    int getMaxEnergyStored(RotationalDirection from);
}
