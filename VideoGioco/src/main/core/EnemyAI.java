package main.core;

import java.util.Random;

import main.core.interfaces.Directions;

public class EnemyAI extends Enemy{
	public EnemyAI(World world, int x, int y, Directions dir, int speed,Player player) {
		super(world, x, y, dir, speed);
		this.player=player;
		
	}
	@Override
	public void update() {
		//Random random=new Random();
				//int movimento= random.nextInt(2);
				if(speed==0 && player.cont2>=200)
					this.setSpeed(1);
		
				if(player.getY()>this.getY()) {
					if(world.getObject(getX(), getY()+1) instanceof Stairs) {
						this.setDirection(Directions.DOWN);
					}
						
					else {
						this.setDirection(dir);
						/*if(movimento==0)
							this.setDirection(Directions.RIGHT);
						else
							this.setDirection(Directions.LEFT);*/
					}
						
				}
				else if(player.getY()<this.getY()){
					if(world.getObject(getX(), getY()-1) instanceof Stairs||(world.getObject(getX(), getY()) instanceof Stairs)) {
						this.setDirection(Directions.UP);
					}
					else {
						/*if(movimento==0)
							this.setDirection(Directions.RIGHT);
						else
							this.setDirection(Directions.LEFT);*/
						this.setDirection(dir);
					}
				}
				else if(player.getX()<this.getX()) {
					if(world.getObject(getX()-1, getY()+1) instanceof Stairs||world.getObject(getX()-1, getY()+1) instanceof SolidBrick ) {
						this.setDirection(Directions.LEFT);
						dir=Directions.LEFT;
					}
				}
				else if(player.getX()>this.getX()) {
					if(world.getObject(getX()+1, getY()+1) instanceof Stairs||world.getObject(getX()+1, getY()+1) instanceof SolidBrick ) {
						this.setDirection(Directions.RIGHT);
						dir=Directions.RIGHT;
					}
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
		            	}
		            	else if(world.getObject(getX()-1, getY()+1) instanceof Stairs||world.getObject(getX()-1, getY()+1) instanceof SolidBrick)
		            		setX(getX() - getSpeed());
		                break;
		            case RIGHT:
		            	if(getX()==world.getWidth()-1||world.getObject(getX()+1, getY()+1) instanceof EmptyObject)
		            	{
		            		this.setDirection(Directions.LEFT);
		            		dir=Directions.LEFT;
		            	}
		            	else if(world.getObject(getX()+1, getY()+1) instanceof Stairs||world.getObject(getX()+1, getY()+1) instanceof SolidBrick)
		            		setX(getX() + getSpeed());
		                break;

		            default:
		                break;
		        }
		
	}
	@Override
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
						player.conteggio2=true;
						return true;
					}
			
		
		}
		return false;
	}
	

}
