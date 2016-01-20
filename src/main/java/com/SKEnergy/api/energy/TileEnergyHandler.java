package com.SKEnergy.api.energy;

import com.SKEnergy.api.RotationalDirection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Example implementation of {@link IEnergyHandler}
 *
 * Use/extend this or implement your own
 *
 * Created by Adam on 19/01/2016.
 */
public class TileEnergyHandler extends TileEntity implements IEnergyHandler
{

    protected EnergyStorage storage = new EnergyStorage(32000);

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
    }

    /* IEnergyConnection */
    @Override
    public boolean canConnectEnergy(RotationalDirection from) {

        return true;
    }

    /* IEnergyReceiver */
    @Override
    public int receiveEnergy(RotationalDirection from, int maxReceive, boolean simulate) {

        return storage.receiveEnergy(maxReceive, simulate);
    }

    /* IEnergyProvider */
    @Override
    public int extractEnergy(RotationalDirection from, int maxExtract, boolean simulate) {

        return storage.extractEnergy(maxExtract, simulate);
    }

    /* IEnergyReceiver and IEnergyProvider */
    @Override
    public int getEnergyStored(RotationalDirection from) {

        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(RotationalDirection from) {

        return storage.getMaxEnergyStored();
    }
}
