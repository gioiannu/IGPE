package main.managers;

import main.core.interfaces.Directions;
import main.core.SolidBrick;
import main.core.Editor;
import main.core.Enemy;
import main.core.EnemyAI;
import main.core.Player;
import main.core.MovableObject;
import main.core.MyEditorPanel;
import main.core.Stairs;
import main.core.World;


public class GameManager {
	
	private World world;
	
	private MyEditorPanel me;
	
	private Editor editor;
	
	private int fattore=10;

    private Stairs[] stairs;

    private SolidBrick[] solidBricks;

    private MovableObject[] movableObjects;
    
    private Enemy[] enemies;
    
    private EnemyAI eai;

    private Player player;
    
    private int levels=1;
    
    private Directions playerLastDir= Directions.STOP;
    
    
    
    
    
    
	
	public GameManager()
	{
		editor= new Editor();
		me=new MyEditorPanel();
	}
	
	
	
	//GETS=====================================
	public Player getPlayer()
	{
		return player;
	}
	
	public Enemy[] getEnemy()
	{
		return enemies;
	}
	
	public MovableObject[] getMovableObject()
	{
		return movableObjects;
	}
	
	public Stairs[] getStair()
	{
		return stairs;
	}
	
	public SolidBrick[] getSolidBrick()
	{
		return solidBricks;
	}
	
	
	
	
	//SETS============================================
	public void setPlayer(final Player player_1)
	{
		this.player=player_1;
	}
	
	public void setStairs (final Stairs[] stairs)
	{
		this.stairs = stairs;
	}
	
	public void setMovableObject (final MovableObject[] movableObjects)
	{
		this.movableObjects=movableObjects;
	}
	
	public void setEnemy (final Enemy[] enemies)
	{
		this.enemies=enemies;
	}
	
	public void setSolidBrick (final SolidBrick[] solidBricks)
	{
		this.solidBricks=solidBricks;
	}
	
	
	
	
	
	public boolean gameOver()
	{
		return getPlayer().getLives() <=0;
	}
	
	public void startGame(int levels)
	{
		
		if(levels==1) {
			world= new World (20*fattore,23*fattore);
			
			
			player= new Player (world,11*fattore,18*fattore+fattore-1, Directions.STOP,1);
			solidBricks=new SolidBrick[65*fattore];
			stairs=new Stairs[23*fattore];
			movableObjects =new MovableObject[4];
			enemies=new Enemy[1];
			enemies[0]=new Enemy(world,1*fattore,9*fattore+fattore-1,Directions.STOP,1);
			eai=new EnemyAI(world,1*fattore,9*fattore+fattore-1,Directions.STOP,1,player);
		
			for(int i=0;i<20*fattore;i++)
				solidBricks[i]=new SolidBrick(world,i,19*fattore);
			for(int i=0,j=20*fattore ;i<20*fattore; i++,j++)
				solidBricks[j]=new SolidBrick(world,i,10*fattore);
			for(int i=0,j=40*fattore;i<6*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,14*fattore);
			for(int i=12*fattore,j=46*fattore;i<20*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,14*fattore);
			for(int i=0,j=54*fattore;i<8*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,5*fattore);
			for(int i=17*fattore,j=62*fattore;i<20*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,5*fattore);
			
			for(int i=5*fattore,j=0;i<10*fattore;i++,j++)//al posto di 19 ho messo 9
				stairs[j]=new Stairs(world,4*fattore,i);
			for(int i=10*fattore,j=5*fattore;i<14*fattore;i++,j++)//aggiunto
				stairs[j]=new Stairs(world,1*fattore,i);//aggiunto
			for(int i=14*fattore,j=9*fattore;i<19*fattore;i++,j++)//aggiunto
				stairs[j]=new Stairs(world,4*fattore,i);//aggiunto
			
			for(int i=5*fattore,j=14*fattore;i<10*fattore;i++,j++)
				stairs[j]=new Stairs(world,17*fattore,i);
			for(int i=10*fattore,j=19*fattore;i<14*fattore;i++,j++)
				stairs[j]=new Stairs(world,13*fattore,i);
			int cont=0;
			movableObjects[cont++]=new MovableObject(world,1*fattore,4*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,18*fattore,4*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,18*fattore,13*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,10*fattore,9*fattore+fattore-1,Directions.STOP,0);
			
			world.update(player, enemies, movableObjects, solidBricks, stairs, eai);
			
			
		}
		
		else if(levels==2)
		{
			world = new World(20*fattore,23*fattore,me.getEditor().getMatrix());
			
			
			solidBricks= new SolidBrick[me.getEditor().getSolidBricks().size()*fattore];
			for(int i=0,k=0; i<me.getEditor().getSolidBricks().size(); i++)
			{
				
					solidBricks[k++]= new SolidBrick (world,me.getEditor().getSolidBricks().get(i).getX()*fattore,me.getEditor().getSolidBricks().get(i).getY()*fattore) ;
					for(int j=0; j<20; j++)
						solidBricks[k++]= new SolidBrick (world,me.getEditor().getSolidBricks().get(i).getX()*fattore+j,me.getEditor().getSolidBricks().get(i).getY()*fattore+j) ;

			}
			
			movableObjects = new MovableObject[me.getEditor().getMovableObjects().size()];
			
			for(int i=0; i<me.getEditor().getMovableObjects().size(); i++)
			{
				
				movableObjects[i]= new MovableObject(world,me.getEditor().getMovableObjects().get(i).getX()*fattore,me.getEditor().getMovableObjects().get(i).getY()*fattore+fattore-1,Directions.STOP,0 );
			}
			
			enemies=new Enemy[me.getEditor().getEnemies().size()];
			for(int i=0; i<me.getEditor().getEnemies().size(); i++)
			{
				enemies[i]=new Enemy(world,me.getEditor().getEnemies().get(i).getX()*fattore,me.getEditor().getEnemies().get(i).getY()*fattore+fattore-1,Directions.STOP,1);

			}
			
			stairs=new Stairs[me.getEditor().getStairs().size()*fattore];
			for(int i=0,k=0; i<me.getEditor().getStairs().size(); i++)
			{
				
					stairs[k++]= new Stairs (world,me.getEditor().getStairs().get(i).getX()*fattore,me.getEditor().getStairs().get(i).getY()*fattore) ;
					for(int j=0; j<20; j++)
						stairs[k++]= new Stairs (world,me.getEditor().getStairs().get(i).getX()*fattore+j,me.getEditor().getStairs().get(i).getY()*fattore+j) ;

			}

			
			player= new Player (world,11*fattore,18*fattore+fattore-1, Directions.STOP,1);
			eai=new EnemyAI(world,1*fattore,9*fattore+fattore-1,Directions.STOP,1,player);
			
			world.update(player, enemies, movableObjects, solidBricks, stairs, eai);
			
			
			
		}
		
		
				
		
		
	}
	public void setPlayerLastDir(Directions d)
	{
		playerLastDir=d;
	}
	/*public void update()
	{
		//player.update();
		//setPlayerLastDir(player.getDirection());
		for(int i=0; i<enemies.length; i++)
		{
			if(enemies[i]!= null)
				enemies[i].update();
			
			//gestire collisione player con nemico
			//gestire collisione proiettile con nemico
		}
		
			
			//eai.update();
				
			
			//gestire collisione player con oggetti da prendere
		//}
		//player.proiettile_update();
		
		world.update(player, enemies, movableObjects, solidBricks, stairs,eai);
		
	}*/
	
	public void printWorld()
	{
		System.out.println(world.toString());
		
	}



	public int getLevels() {
		return levels;
	}



	public void setLevels(int levels) {
		this.levels = levels;
	}



	public EnemyAI getEai() {
		return eai;
	}

	public Directions getPlayerLastDir() {
		return player.lastDirection;
	}
	public World getWorld() {
		return world;
	}

}
	
	
	
	

