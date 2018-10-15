package main.core;

import main.core.interfaces.Directions;


public class Proiettile extends AbstractDynamicObject{
	
	private boolean visible = false;
	
	public Proiettile(final World world, int x, int y, Directions dir,int speed)
	{
		super(world,x,y,Directions.RIGHT,speed);
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible=visible;
	}
	
	@Override
	public void update()
	{
		switch (getDirection())
        {
            case LEFT:
            	if(getX()==0) {
            		setVisible(false);
            	}
            	else 
            		setX(getX() - getSpeed());
                break;
            case RIGHT:
            	if(getX()==world.getWidth()-1) {
            		setVisible(false);
            	}
            	else 
            		setX(getX() + getSpeed());
                break;

            default:
            	setVisible(false);
                break;
        }
	}

	/*@Override
	public boolean collision(GameObject g) {
		// TODO Auto-generated method stub
		return false;
	}*/

}
