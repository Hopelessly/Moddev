package com.survival;

import com.survival.capabilities.IThirst;
import com.survival.capabilities.Thirst;
import com.survival.capabilities.ThirstProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandler {
	@SubscribeEvent
    public void onPlayerLogsIn(PlayerLoggedInEvent event)
    {
        EntityPlayer player = event.player;
        IThirst thirst = player.getCapability(ThirstProvider.Thirst_CAP, null);

        String message = String.format("Hello there, you have §7%d§r thirst left.", (int) thirst.getThirst());
        player.addChatMessage(new TextComponentString(message));
    }

    @SubscribeEvent
    public void onPlayerSleep(PlayerSleepInBedEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();

        if (player.worldObj.isRemote) return;

        IThirst thirst = player.getCapability(ThirstProvider.Thirst_CAP, null);

        thirst.fill(50);

        String message = String.format("You refreshed yourself in the bed. You received 50 Thirst, you have §7%d§r Thirst left.", (int) thirst.getThirst());
        player.addChatMessage(new TextComponentString(message));
    }

//    @SubscribeEvent
//    public void onPlayerFalls(LivingFallEvent event)
//    {
//        Entity entity = event.getEntity();
//
//        if (entity.worldObj.isRemote || !(entity instanceof EntityPlayerMP) || event.getDistance() < 3) return;
//
//        EntityPlayer player = (EntityPlayer) entity;
//        IThirst thirst = player.getCapability(ThirstProvider.Thirst_CAP, null);
//
//        float points = thirst.getThirst();
//        float cost = event.getDistance() * 2;
//
//        if (points > cost)
//        {
//            thirst.consume(cost);
//
//            String message = String.format("You absorbed fall damage. It costed §7%d§r mana, you have §7%d§r mana left.", (int) cost, (int) thirst.Thirst());
//            player.addChatMessage(new TextComponentString(message));
//
//            event.setCanceled(true);
//        }
//    }
}
