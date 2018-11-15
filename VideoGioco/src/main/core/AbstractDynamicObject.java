package main.core;

import main.core.interfaces.Directions;
import main.core.interfaces.DynamicObject;

public abstract class AbstractDynamicObject extends AbstractGameObject implements DynamicObject{
	
	private Directions dir;
	int speed;
	
	public AbstractDynamicObject (final World world, int x, int y,Directions dir, int speed)
	{
		super(world,x,y);
		this.dir=dir;
		this.speed=speed;
	}
	
	@Override
	public Directions getDirection()
	{
		return dir;
	}
	
	@Override
	public int getSpeed()
	{
		return speed;
	}
	
	@Override
	public void setSpeed(int speed) {
		this.speed=speed;
		
	}

	public void setX(int x)
	{
		if( x < 0 )
		{
			this.x=0;
		}
		else if( x > world.getWidth() )
		{
			this.x=world.getWidth();
		}
		else
		{
			this.x=x;
		}
	}
	
	public void setY(int y)
	{
		if(y < 0)
		{
			this.y=0;
		}
		else if(y > world.getHeight())
		{
			this.y=world.getHeight();
		}
		else
		{
			this.y=y;
		}
	}
	
	
	public void setDirection(final Directions dir)
    {
        this.dir = dir;
    }
	
	
	@Override
	public void update()
	{
		switch (getDirection())
        {
            case UP:
                setY(getY() - getSpeed());
                break;
            case DOWN:
                setY(getY() + getSpeed());
                break;
            case LEFT:
                setX(getX() - getSpeed());
                break;
            case RIGHT:
                setX(getX() + getSpeed());
                break;

            default:
                break;
        }
	}
	
	

}
