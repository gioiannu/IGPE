package main.core;

import java.util.Random;

import main.core.interfaces.Directions;

public class Enemy extends AbstractDynamicObject{
	
	Player player;
	Directions dir=Directions.RIGHT;
	int movimento;
	public Enemy(World world, int x, int y, Directions dir, int speed) {
		super(world, x, y, dir, speed);
		Random random=new Random();
		movimento= random.nextInt(2);
		
	}
	@Override
	public void update() {
		if(movimento==1) {
			if(world.getObject(getX(), getY()+1) instanceof Stairs) 
				this.setDirection(Directions.DOWN);
			else
				this.setDirection(dir);
		}
		else {
			if(world.getObject(getX(), getY()-1) instanceof Stairs||world.getObject(getX(), getY()) instanceof Stairs)
				this.setDirection(Directions.UP);	
			else
				this.setDirection(dir);
		}
		switch (getDirection())
        {
            case UP:
            	if(world.getObject(getX(), getY()) instanceof Stairs)
            		setY(getY() - getSpeed());
                break;
            case DOWN:
            	if(world.getObject(getX(), getY()+1) instanceof Stairs)
            		setY(getY() + getSpeed());
                break;
            case LEFT:
            	if(getX()==0||world.getObject(getX()-1, getY()+1) instanceof EmptyObject)
            	{
            		this.setDirection(Directions.RIGHT);
            		dir=Directions.RIGHT;
            		if(movimento==1)
            			movimento=2;
            		else
            			movimento=1;
            	}
            	else if(world.getObject(getX()-1, getY()+1) instanceof Stairs||world.getObject(getX()-1, getY()+1) instanceof SolidBrick)
            		setX(getX() - getSpeed());
                break;
            case RIGHT:
            	if(getX()==world.getWidth()-1||world.getObject(getX()+1, getY()+1) instanceof EmptyObject)
            	{
            		this.setDirection(Directions.LEFT);
            		dir=Directions.LEFT;
            		if(movimento==1)
            			movimento=2;
            		else
            			movimento=1;
            	}
            	else if(world.getObject(getX()+1, getY()+1) instanceof Stairs||world.getObject(getX()+1, getY()+1) instanceof SolidBrick)
            		setX(getX() + getSpeed());
                break;

            default:
                break;
        }
		
	}
	

	
	

}
