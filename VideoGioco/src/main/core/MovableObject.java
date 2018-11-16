package main.core;

import main.core.interfaces.Directions;

public class MovableObject extends AbstractDynamicObject {

	private int fattore=10;
	public boolean removed;
	
	public MovableObject(World world, int x, int y, Directions dir, int speed) {
		super(world, x, y, dir, speed);
		removed=false;
		
	}
	public void falling(){
		setDirection(Directions.DOWN);
		setSpeed(1);
		setY(getY()+1);
	}
	@Override
	public void update() {
		switch (getDirection())
        {
            case DOWN:
            	if(getY()>=20*fattore) {
            		world.remove(getX(), getY());
            		world.removeOneCounter();
            		setSpeed(0);
        			setDirection(Directions.STOP);
        			break;
            	}
            	if(world.getObject(getX(), getY()+1) instanceof SolidBrick) {
        			setSpeed(0);
        			setDirection(Directions.STOP);
        		}
            	setY(getY() + getSpeed());
            	break;
                
            case STOP:
            	if(getY()>=21*fattore) {
        			world.remove(getY(), getX());
        			world.removeOneCounter();
            	}
            	break;
                
            default:
                break;
        }
	}

	
}
