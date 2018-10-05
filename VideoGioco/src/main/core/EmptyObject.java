package main.core;

import main.core.interfaces.GameObject;

public class EmptyObject implements GameObject{
	
	private final int x; 
	private final int y;
	
	public EmptyObject(final int x, final int y)
	{
		this.x=x;
		this.y=y;
	}
	
	@Override
	public int getX()
	{
		return x;
	}
	
	@Override
	public int getY()
	{
		return y;
	}
	
	public boolean collision(final GameObject g)
	{
		return false;
	}

}
