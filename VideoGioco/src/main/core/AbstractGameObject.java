package main.core;

import main.core.interfaces.GameObject;

public abstract class AbstractGameObject implements GameObject{
	
	int x;
	int y;
	
	protected final World world;
	
	public AbstractGameObject(final World world, int x, int y)
	{
		this.world=world;
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
	
	@Override
	public boolean collision(GameObject g)
	{
		if(getX()==g.getX() && getY()==g.getY())
			return true;
		return false;
		
		
	}
	
	
	
	

}
