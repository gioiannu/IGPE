package main.core;

import main.core.interfaces.CanShoot;
import main.core.interfaces.Directions;
import main.core.interfaces.GameObject;


public class Player extends AbstractDynamicObject implements CanShoot {
	
	int lives;
	
	private int fattore=10;
	
	private final Proiettile proiettile;
	
	public Directions lastDirection=Directions.RIGHT;

	
	
	
	public Player(final World world, int x, int y,Directions dir, final int speed)
	{
		super(world,x,y,dir,speed);
		
		proiettile= new Proiettile(world,x,y,Directions.STOP, 1);
		
		lives=3;
	}
	@Override
	public boolean collision(GameObject g) {
		for(int i=g.getX()-fattore/2;i<g.getX()+fattore/2;i++)
			for(int j=this.getX()-fattore/2;j<this.getX()+fattore/2;j++)
				if(g.getY()==this.getY()&&i==j)
					return true;
		return false;
		
	}
	public Proiettile getProiettile()
	{
		return proiettile;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	@Override
    public void setDirection(final Directions direction)
    {
        super.setDirection(direction);

    }
	
	
	@Override
	public void shoot()
	{
		if(getDirection()==Directions.RIGHT||getDirection()==Directions.LEFT) {
			proiettile.setVisible(true);
			proiettile.setDirection(getDirection());
			proiettile.setSpeed(1);
			proiettile.setX(getX());
			proiettile.setY(getY());
			
		}
		else if(getDirection()==Directions.STOP&&lastDirection==Directions.RIGHT) {
			proiettile.setVisible(true);	
			proiettile.setDirection(Directions.RIGHT);
			proiettile.setSpeed(1);
			proiettile.setX(getX());
			proiettile.setY(getY());
		}
		else if(getDirection()==Directions.STOP&&lastDirection==Directions.LEFT) {
			proiettile.setVisible(true);	
			proiettile.setDirection(Directions.LEFT);
			proiettile.setSpeed(1);
			proiettile.setX(getX());
			proiettile.setY(getY());
		}
	}
	
	public void removeOneLife()
	{
		lives--;
	}

	@Override
	public void update()
	{
		switch (getDirection())
        {
			
            case UP:
            	for(int i=getX()-10;i<getX()+10;i++) {
            	if(world.getObject(i, getY()) instanceof Stairs||world.getObject(i, getY()-1) instanceof Stairs)
            			setY(getY() - getSpeed());
            	}            	lastDirection=Directions.UP;
                break;
            case DOWN:
            	for(int i=getX()-10;i<getX()+10;i++) {
            	if(world.getObject(i, getY()+1) instanceof Stairs)
            		setY(getY() + getSpeed());
            	}
            	lastDirection=Directions.DOWN;
                break;
            case LEFT:
            		if(getX()==0&&(world.getObject(world.getWidth()-1, getY()+1) instanceof Stairs||world.getObject(world.getWidth()-1, getY()+1) instanceof SolidBrick))
            			setX(world.getWidth()-1);
            		else if(world.getObject(getX()-1, getY()+1) instanceof Stairs||world.getObject(getX()-1, getY()+1) instanceof SolidBrick)
            			setX(getX() - getSpeed());
            	lastDirection=Directions.LEFT;
                break;
            case RIGHT:
            		if(getX()==world.getWidth()-1&&((world.getObject(0, getY()+1) instanceof Stairs||world.getObject(0, getY()+1) instanceof SolidBrick)))
            			setX(0);
            		else if(world.getObject(getX()+1, getY()+1) instanceof Stairs||world.getObject(getX()+1, getY()+1) instanceof SolidBrick)
            			setX(getX() + getSpeed());
            	lastDirection=Directions.RIGHT;
                break;

            default:
                break;
        }
		
		
			proiettile.update();
		

	}
	public void setPlayerLastDir(Directions direction) {
		lastDirection=direction;
		
	}
	
	
	

	
	
	
	
	

}
