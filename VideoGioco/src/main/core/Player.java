package main.core;

import main.core.interfaces.CanShoot;
import main.core.interfaces.Directions;
import main.core.interfaces.GameObject;


public class Player extends AbstractDynamicObject implements CanShoot {
	
	int lives;
	
	private int fattore=10;
	
	private final Proiettile proiettile;
	
	public Directions lastDirection=Directions.STOP;
	
	boolean conteggio1=false;
	boolean conteggio2=false;
	int cont=0;
	int cont2=0;

	
	
	
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
		proiettile.setVisible(true);
		proiettile.setSpeed(1);
		proiettile.setX(getX());
		proiettile.setY(getY());
		/*if(getDirection()==Directions.RIGHT||getDirection()==Directions.LEFT) {
			proiettile.setVisible(true);
			proiettile.setDirection(getDirection());
			proiettile.setSpeed(1);
			proiettile.setX(getX());
			proiettile.setY(getY());
			
		}
		else if(getDirection()==Directions.STOP&&lastDirection!=Directions.UP&&lastDirection!=Directions.DOWN) {
			proiettile.setVisible(true);	
			proiettile.setDirection(lastDirection);
			proiettile.setSpeed(1);
			proiettile.setX(getX());
			proiettile.setY(getY());
		}*/
	}
	
	public void removeOneLife()
	{
		lives--;
	}

	@Override
	public void update()
	{
		if(conteggio1==true) {
			if(cont>200) {
			cont=0;
			conteggio1=false;
			}
			else 
				cont++;
		}
		
		if(conteggio2==true) {
			if(cont2>200) {
			cont2=0;
			conteggio2=false;
			}
			else 
				cont2++;
		}
		switch (getDirection())
        {
            case UP:
            	for(int i=getX()-fattore/2;i<getX()+fattore/2;i++) {
            	if(world.getObject(i, getY()) instanceof Stairs||world.getObject(i, getY()-1) instanceof Stairs)
            			setY(getY() - getSpeed());
            	}            	
            	lastDirection=Directions.UP;
                break;
            case DOWN:
            	for(int i=getX()-fattore;i<getX()+fattore/2;i++) {
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
            	if(proiettile.isVisible()==false)
            		proiettile.setDirection(Directions.LEFT);
                break;
            case RIGHT:
            		if(getX()==world.getWidth()-1&&((world.getObject(0, getY()+1) instanceof Stairs||world.getObject(0, getY()+1) instanceof SolidBrick)))
            			setX(0);
            		else if(world.getObject(getX()+1, getY()+1) instanceof Stairs||world.getObject(getX()+1, getY()+1) instanceof SolidBrick)
            			setX(getX() + getSpeed());
            	lastDirection=Directions.RIGHT;
            	if(proiettile.isVisible()==false)
            		proiettile.setDirection(Directions.RIGHT);
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
