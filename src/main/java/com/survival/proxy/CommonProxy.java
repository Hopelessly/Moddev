package com.survival.proxy;

import com.survival.EventHandler;
import com.survival.capabilities.CapabilityHandler;
import com.survival.capabilities.IThirst;
import com.survival.capabilities.Thirst;
import com.survival.capabilities.ThirstStorage;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CommonProxy {
	
	public void init()
    {
        CapabilityManager.INSTANCE.register(IThirst.class, new ThirstStorage(), Thirst.class);

        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}
