package com.survival.capabilities.Sleep;

import com.survival.capabilities.Thirst.IThirst;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SleepProvider implements ICapabilitySerializable<NBTBase> 
{

	@CapabilityInject(ISleep.class)
    public static final Capability<ISleep> SLEEP_CAP = null;

    private ISleep instance = SLEEP_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == SLEEP_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == SLEEP_CAP ? SLEEP_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return SLEEP_CAP.getStorage().writeNBT(SLEEP_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        SLEEP_CAP.getStorage().readNBT(SLEEP_CAP, this.instance, null, nbt);
    }

}
