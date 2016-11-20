package com.survival;

import com.survival.capabilities.Sleep.ISleep;
import com.survival.capabilities.Sleep.SleepProvider;
import com.survival.capabilities.Thirst.IThirst;
import com.survival.capabilities.Thirst.ThirstProvider;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandler {

	@SubscribeEvent
	public void onPlayerLogsIn(PlayerLoggedInEvent event) {
		EntityPlayer player = event.player;
		ISleep sleep = player.getCapability(SleepProvider.SLEEP_CAP, null);
		IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);

		String message = String.format("Hello there, you have " + (int) thirst.getThirst()) + " thirst left, "
				+ (int) sleep.getSleep() + " sleep left.";
		player.addChatMessage(new TextComponentString(message));
	}

	private int thirstTimer;
	private int sleepTimer;
	private int day;
	private boolean msgSent = false;

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		Entity entity = event.getEntity();
		if (entity.worldObj.isRemote || !(entity instanceof EntityPlayerMP))
			return;

		EntityPlayer player = (EntityPlayer) entity;

		ISleep sleep = player.getCapability(SleepProvider.SLEEP_CAP, null);
		IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
		EnumDifficulty enumdifficulty = player.worldObj.getDifficulty();
		
		if (enumdifficulty == EnumDifficulty.NORMAL) {
			++this.sleepTimer;
			if (this.sleepTimer >= 24000) {
				++this.day;
				String message = String.format("A day passes without sleep, you suffer as a result.");
				player.addChatMessage(new TextComponentString(message));
				if (day >= 3)
					day = 3;
				this.sleepTimer = 0;
			}		
		} else if (enumdifficulty == EnumDifficulty.HARD) {
			++this.sleepTimer;
			if (this.sleepTimer >= 100) {
				if(sleep.getSleep() >= 5.5F)
					sleep.consume(5.5F);
				else 
					sleep.set(0F);
				if (sleep.getSleep() <= 0F) {
					sleep.set(0F);
					if (this.msgSent == false) {
						String message = String.format("You are exhausted. Get to a bed and go to sleep fast!");
						player.addChatMessage(new TextComponentString(message));
						this.msgSent = true;
					}
				}
				this.sleepTimer = 0;
			}
		}
		if (thirst.getThirst() <= 0F) {
			++this.thirstTimer;
			if (this.thirstTimer >= 80) {
				if (player.getHealth() > 1.0F) {
					player.attackEntityFrom(DamageSource.starve, 1.0F);
				}
				this.thirstTimer = 0;
			}
		}
	}

	/*
	 * Variables: EntityLivingBase entity, DamageSource source, float amount
	 */
	@SubscribeEvent
	public void AttackEvent(LivingAttackEvent event) {

	}

	/*
	 * Variables: EntityLivingBase entity, DamageSource source, float amount
	 */
	@SubscribeEvent
	public void HurtEvent(LivingHurtEvent event) {

	}

	@SubscribeEvent
	public void onHealEvent(LivingHealEvent event) {
		Entity entity = event.getEntity();
		if (entity.worldObj.isRemote || !(entity instanceof EntityPlayerMP))
			return;

		EntityPlayer player = (EntityPlayer) entity;

		if (this.day >= 1)
		{
			event.setCanceled(true);
		}

	}

	@SubscribeEvent
	public void onWakeUp(PlayerWakeUpEvent event) {
		EntityPlayer player = event.getEntityPlayer();
		IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
		ISleep sleep = player.getCapability(SleepProvider.SLEEP_CAP, null);
		// TODO: Add food when implemented.

		if (player.worldObj.isRemote)
			return;
		if (player.getBedLocation() != null) {

			sleep.set(Survival.SLEEP_MAX);
			this.day = 0;
			this.msgSent = false;

			if (thirst.getThirst() < 400F)
				thirst.consume(400F);
			else
				thirst.set(0F);

			String message;
			if (thirst.getThirst() == 0F)
				message = String.format("You are now fully rested. You're dying of thirst!");
			else
				message = String.format("You are now fully rested. You wake up feeling famished.");
			player.addChatMessage(new TextComponentString(message));
		}
	}
}