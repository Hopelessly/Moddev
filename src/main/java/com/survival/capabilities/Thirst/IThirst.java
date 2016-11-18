package com.survival.capabilities.Thirst;

public interface  IThirst {
	
	public void consume(float points);
	
	public void fill(float points);
	
	public void set(float points);
	
	public float getThirst();

}