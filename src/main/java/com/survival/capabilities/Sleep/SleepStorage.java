package com.survival.capabilities.Sleep;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class SleepStorage implements IStorage<ISleep>{

	@Override
	public NBTBase writeNBT(Capability<ISleep> capability, ISleep instance, EnumFacing side) 
	{
		return new NBTTagFloat(instance.getSleep());
	}

	@Override
	public void readNBT(Capability<ISleep> capability, ISleep instance, EnumFacing side, NBTBase nbt) 
	{
		instance.set(((NBTTagFloat) nbt).getFloat());		
	}

}
