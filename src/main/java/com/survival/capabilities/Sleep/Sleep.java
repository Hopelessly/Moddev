package com.survival.capabilities.Sleep;

public class Sleep implements ISleep 
{
	private float sleep = 250.0F;

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
