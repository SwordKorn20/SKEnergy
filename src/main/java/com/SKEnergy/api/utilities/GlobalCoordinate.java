package com.SKEnergy.api.utilities;

import net.minecraft.util.BlockPos;

/**
 * Created by Adam on 21/01/2016.
 */
public class GlobalCoordinate
{
    private final BlockPos coord;
    private final int dim;

    public GlobalCoordinate(BlockPos coord, int dim)
    {
        this.coord = coord;
        this.dim = dim;
    }

    public BlockPos getCoord()
    {
        return coord;
    }

    public int getDim()
    {
        return dim;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }

        GlobalCoordinate that = (GlobalCoordinate) o;

        if(dim != that.dim)
        {
            return false;
        }
        if(!coord.equals(that.coord))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = coord.hashCode();
        result = 31 * result + dim;
        return result;
    }
}
