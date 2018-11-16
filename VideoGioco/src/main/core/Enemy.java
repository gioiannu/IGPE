package main.core;

import java.util.Random;

import javax.swing.Timer;

import main.core.interfaces.Directions;
import main.core.interfaces.GameObject;

public class Enemy extends AbstractDynamicObject{
	Player player;
	Directions dir=Directions.RIGHT;
	int movimento;
	int fattore=10;
	public Enemy(World world, int x, int y, Directions dir, int speed) {
		super(world, x, y, dir, speed);
		Random random=new Random();
		movimento= random.nextInt(2);
		
	}
	public void insertP(Player p) {
		player=p;
	}
	@Override
	public void update() {
		if(speed==0 && player.cont>=200) {
			this.setDirection(Directions.RIGHT);
			this.setSpeed(1);
		}
			
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
	public boolean collisione(Proiettile g) {
		
		
		if(g.isVisible())
		{
			for(int i=g.getX()-fattore/2;i<g.getX()+fattore/2;i++)
				for(int j=this.getX()-fattore/2;j<this.getX()+fattore/2;j++)
					if(g.getY()==this.getY()&&i==j)
					{
						this.setSpeed(0);
						this.setDirection(Directions.STOP);
						g.setVisible(false);
						g.setX(player.getX());
						g.setY(player.getY());
						player.conteggio1=true;
						return true;
					}
		}	
		return false;
	}
	public boolean collisionep(Player g) {
		for(int i=g.getX()-fattore/2;i<g.getX()+fattore/2;i++)
			for(int j=this.getX()-fattore/2;j<this.getX()+fattore/2;j++)
				if(g.getY()==this.getY()&&i==j)
				{
					
					return true;
				}
	
		return false;
	}
	

	
	

}
