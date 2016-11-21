package com.survival;

/**
 * @author Hopelessly, CmKiller
 */

import com.survival.block.ModBlocks;
import com.survival.items.ModItems;
import com.survival.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Survival.MODID, version = Survival.VERSION, name = Survival.NAME)

public class Survival
{
	public static final String MODID = "survival";
	public static final String NAME = "Survival Mod";
	public static final String VERSION = "1.0.0";
	public static final float SLEEP_MAX = 1000F;
	public static final float THIRST_MAX = 1000F;

	@SidedProxy(clientSide = "com.survival.proxy.ClientProxy", serverSide = "com.survival.proxy.CommonProxy")

	public static CommonProxy proxy;

	@Mod.Instance(MODID)
	public static Survival instance;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ModItems.init();
		ModBlocks.init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
