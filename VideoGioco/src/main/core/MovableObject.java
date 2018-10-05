package main.core;

import main.core.interfaces.Directions;

public class MovableObject extends AbstractDynamicObject {

	private int fattore=10;
	
	public MovableObject(World world, int x, int y, Directions dir, int speed) {
		super(world, x, y, dir, speed);
		
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
            	if(getY()>=21*fattore) {
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
        			world.remove(getX(), getY());
            	}
            	break;
                
            default:
                break;
        }
	}

	
}
