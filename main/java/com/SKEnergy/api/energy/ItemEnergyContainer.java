package com.SKEnergy.api.energy;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Example implementation of {@link IEnergyContainerItem}
 *
 * Use/extend this to implement your own
 *
 * Created by Adam on 19/01/2016.
 */
public class ItemEnergyContainer extends Item implements IEnergyContainerItem
{
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public ItemEnergyContainer() {

    }

    public ItemEnergyContainer(int capacity) {

        this(capacity, capacity, capacity);
    }

    public ItemEnergyContainer(int capacity, int maxTransfer) {

        this(capacity, maxTransfer, maxTransfer);
    }

    public ItemEnergyContainer(int capacity, int maxReceive, int maxExtract) {

        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    public ItemEnergyContainer setCapacity(int capacity) {

        this.capacity = capacity;
        return this;
    }

    public void setMaxTransfer(int maxTransfer) {

        setMaxReceive(maxTransfer);
        setMaxExtract(maxTransfer);
    }

    public void setMaxReceive(int maxReceive) {

        this.maxReceive = maxReceive;
    }

    public void setMaxExtract(int maxExtract) {

        this.maxExtract = maxExtract;
    }

    /* IEnergyContainerItem */
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

        if (container.getTagCompound() == null) {
            container.setTagCompound(new NBTTagCompound());
        }
        int energy = container.getTagCompound().getInteger("Energy");
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

        if (!simulate) {
            energy += energyReceived;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

        if (container.getTagCompound() == null || !container.getTagCompound().hasKey("Energy")) {
            return 0;
        }
        int energy = container.getTagCompound().getInteger("Energy");
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

        if (!simulate) {
            energy -= energyExtracted;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {

        if (container.getTagCompound() == null || !container.getTagCompound().hasKey("Energy")) {
            return 0;
        }
        return container.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        return capacity;
    }
}
