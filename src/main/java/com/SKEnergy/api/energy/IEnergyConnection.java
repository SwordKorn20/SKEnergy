package com.SKEnergy.api.energy;

import com.SKEnergy.api.RotationalDirection;

/**
 * Used for implementation of energy storage/transmission to approriate TileEntity energy transportation blocks
 *
 * eg. Cable, power transmission capable blocks
 *
 * Created by Adam on 19/01/2016.
 */
public interface IEnergyConnection
{
    /**
     * Returns true if the TileEntity can connect on a given side
     */
    boolean canConnectEnergy(RotationalDirection from);
}
