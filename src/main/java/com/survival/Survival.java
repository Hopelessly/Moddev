package com.survival;

import com.survival.items.ModItems;
import com.survival.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import com.survival.block.ModBlocks;

@Mod(modid = Survival.MODID, version = Survival.VERSION, name = Survival.NAME)
public class Survival
{
    public static final String MODID = "thirst-test";
    public static final String NAME = "Survival";
    public static final String VERSION = "1.0";
    public static final float SLEEP_MAX = 1000F;
    public static final float THIRST_MAX = 1000F;

    @SidedProxy(clientSide = "com.survival.proxy.CommonProxy", serverSide = "com.survival.proxy.CommonProxy")
    
    public static CommonProxy proxy;

	@EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	ModItems.init();
    	ModBlocks.init();
    }
}
