package com.survival.capabilities.Thirst;

public class Thirst implements IThirst 
{
	
	private float thirst = 250.0F;

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