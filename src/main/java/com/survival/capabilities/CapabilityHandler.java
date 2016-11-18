package com.survival.capabilities;

import com.survival.Survival;
import com.survival.capabilities.Sleep.SleepProvider;
import com.survival.capabilities.Thirst.ThirstProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Capability handler
 * 
 * This class is responsible for attaching our capabilities
 */
public class CapabilityHandler
{
    public static final ResourceLocation THIRST_CAP = new ResourceLocation(Survival.MODID, "thirst");
    public static final ResourceLocation SLEEP_CAP = new ResourceLocation(Survival.MODID, "sleep");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent.Entity event)
    {
        if (!(event.getEntity() instanceof EntityPlayer)) 
        	return;
        event.addCapability(THIRST_CAP, new ThirstProvider());
        event.addCapability(SLEEP_CAP, new SleepProvider());
    }
}