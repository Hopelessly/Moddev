package com.survival.capabilities.Sleep;

import com.survival.Survival;

public class Sleep implements ISleep 
{
	private float sleep = Survival.SLEEP_MAX;

	@Override
	public void consume(float points) 
	{
		this.sleep -= points;
		if (this.sleep < 0.0F) this.sleep = 0.0F;
		
	}

	@Override
	public void fill(float points) 
	{
		this.sleep += points;
		
	}

	@Override
	public void set(float points) 
	{
		this.sleep = points;
		
	}

	@Override
	public float getSleep() 
	{
		return this.sleep;
	}

}
