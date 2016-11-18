package com.survival;

import com.survival.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Survival.MODID, version = Survival.VERSION)
public class Survival {

	public static final String MODID = "capabilities-test";
	public static final String VERSION = "0.1";

	@SidedProxy(clientSide = "com.survival.proxy.CommonProxy", serverSide = "com.survival.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

}
