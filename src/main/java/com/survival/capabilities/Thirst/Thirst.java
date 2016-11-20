package com.survival.capabilities.Thirst;

import com.survival.Survival;

public class Thirst implements IThirst 
{
	
	private float thirst = Survival.THIRST_MAX;

	@Override
	public void consume(float points) 
	{
		this.thirst -= points;
		
		if (this.thirst < 0.0F) this.thirst = 0.0F;
		
	}

	@Override
	public void fill(float points) 
	{
		this.thirst += points;
		
	}

	@Override
	public void set(float points) 
	{
		this.thirst = points;
		
	}

	@Override
	public float getThirst() 
	{
		return this.thirst;
	}

}