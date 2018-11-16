package main.managers;

import main.core.interfaces.Directions;
import net.Client;
import main.core.SolidBrick;
import main.core.Editor;
import main.core.Enemy;
import main.core.EnemyAI;
import main.core.Player;
import main.core.MovableObject;
import main.core.images.MyEditorPanel;
import main.core.Stairs;
import main.core.World;


public class GameManager {
	
	private World world;
	
	public boolean conteggio1=false;
	public boolean conteggio2=false;
	public boolean pausa= false;
	int pcont=0;
	int pcont2=0;
	
	
	private int fattore=10;

    private Stairs[] stairs;

    private SolidBrick[] solidBricks;

    private MovableObject[] movableObjects;
    
    private Enemy[] enemies;
    
    private EnemyAI eai;

    public Player player;
    public Player player2;
    
    private int levels=1;
    
    private Directions playerLastDir= Directions.STOP;
    private Directions playerLastDir2= Directions.STOP;
    
    MyEditorPanel me;
    
    Editor editor;
    boolean ed=false;
    boolean victory;
    int playerLives;
    int playerLives2;
    public boolean connected=false;
    public Client c;
    
    public boolean shooting2=false;
    
    
    
    
	
	public GameManager()
	{
		playerLives=3;
		editor= new Editor();
		playerLives2=3;
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
	
	
	public void setMatrix(Object[][] m)
	{
		world = new World (20*fattore, 23*fattore, m);
	}
	
	
	
	
	public boolean gameOver()
	{
		if(getLevels()!=4)
			return player.getLives() <=0;
		else {
			if(player2.ball==2)
				return true;
			else
				return false;
		}
	}
	
	public boolean win()
	{
		if(getLevels()!=4)
			return world.getWinnerCounter()==0;
		else {
			if(player.ball==2)
				return true;
			else
				return false;
		}
	}
	
	public void startGame(int levels,int lives)
	{
		this.levels=levels;
		
		if(levels==2) {
			world= new World (20*fattore,23*fattore);
			
			
			player= new Player (world,11*fattore,18*fattore+fattore-1, Directions.STOP,1,lives);
			solidBricks=new SolidBrick[66*fattore];
			stairs=new Stairs[23*fattore];
			movableObjects =new MovableObject[4];
			enemies=new Enemy[1];
			enemies[0]=new Enemy(world,15*fattore,9*fattore+fattore-1,Directions.STOP,1);
			enemies[0].insertP(player);
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
			for(int i=16*fattore,j=62*fattore;i<20*fattore;i++,j++)
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
		else if(levels==3) {
			world= new World (20*fattore,23*fattore);
			
			
			player= new Player (world,11*fattore,18*fattore+fattore-1, Directions.STOP,1,lives);
			solidBricks=new SolidBrick[57*fattore];
			stairs=new Stairs[33*fattore];
			movableObjects =new MovableObject[5];
			enemies=new Enemy[2];
			enemies[0]=new Enemy(world,1*fattore,4*fattore+fattore-1,Directions.STOP,1);
			enemies[1]=new Enemy(world,15*fattore,8*fattore+fattore-1,Directions.STOP,1);
			enemies[0].insertP(player);
			enemies[1].insertP(player);
			eai=new EnemyAI(world,11*fattore,8*fattore+fattore-1,Directions.STOP,1,player);
		
			for(int i=0;i<20*fattore;i++)
				solidBricks[i]=new SolidBrick(world,i,19*fattore);
			
			for(int i=0,j=20*fattore ;i<3*fattore; i++,j++)
				solidBricks[j]=new SolidBrick(world,i,14*fattore);
				
			for(int i=8*fattore,j=23*fattore;i<14*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,14*fattore);
				
			for(int i=7*fattore,j=29*fattore;i<13*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,9*fattore);
				
			for(int i=0,j=35*fattore;i<3*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,9*fattore);
				
			for(int i=13*fattore,j=38*fattore;i<20*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,9*fattore);
			
			for(int i=0*fattore,j=45*fattore;i<12*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,5*fattore);
			
			
			for(int i=5*fattore,j=0;i<9*fattore;i++,j++)//al posto di 19 ho messo 9
				stairs[j]=new Stairs(world,1*fattore,i);
				
			for(int i=5*fattore,j=4*fattore;i<9*fattore;i++,j++)//aggiunto
				stairs[j]=new Stairs(world,10*fattore,i);//aggiunto
				
			for(int i=9*fattore,j=8*fattore;i<14*fattore;i++,j++)//aggiunto
				stairs[j]=new Stairs(world,2*fattore,i);//aggiunto
			
				
			for(int i=9*fattore,j=13*fattore;i<14*fattore;i++,j++)
				stairs[j]=new Stairs(world,13*fattore,i);
			
			for(int i=9*fattore,j=18*fattore;i<19*fattore;i++,j++)
				stairs[j]=new Stairs(world,18*fattore,i);
			
			for(int i=14*fattore,j=28*fattore;i<19*fattore;i++,j++)
				stairs[j]=new Stairs(world,9*fattore,i);
			
			
				
			int cont=0;
			movableObjects[cont++]=new MovableObject(world,1*fattore,13*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,17*fattore,8*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,1*fattore,8*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,0*fattore,4*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,8*fattore,4*fattore+fattore-1,Directions.STOP,0);
			
			world.update(player, enemies, movableObjects, solidBricks, stairs, eai);
			
			
		}
		else if(levels==1){
			world= new World (20*fattore,23*fattore);
			
			
			player= new Player (world,11*fattore,18*fattore+fattore-1, Directions.STOP,1,lives);
			solidBricks=new SolidBrick[80*fattore];
			stairs=new Stairs[16*fattore];
			movableObjects =new MovableObject[4];
			enemies=new Enemy[1];
			enemies[0]=new Enemy(world,10*fattore,14*fattore+fattore-1,Directions.STOP,1);
			enemies[0].insertP(player);
			eai=new EnemyAI(world,6*fattore,6*fattore+fattore-1,Directions.STOP,1,player);
		
			for(int i=0;i<20*fattore;i++)
				solidBricks[i]=new SolidBrick(world,i,19*fattore);
			for(int i=0,j=20*fattore ;i<20*fattore; i++,j++)
				solidBricks[j]=new SolidBrick(world,i,15*fattore);
			for(int i=0,j=40*fattore;i<20*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,11*fattore);
			for(int i=0*fattore,j=60*fattore;i<20*fattore;i++,j++)
				solidBricks[j]=new SolidBrick(world,i,7*fattore);
			
			for(int i=7*fattore,j=0;i<11*fattore;i++,j++)//al posto di 19 ho messo 9
				stairs[j]=new Stairs(world,9*fattore,i);
			for(int i=11*fattore,j=4*fattore;i<15*fattore;i++,j++)//aggiunto
				stairs[j]=new Stairs(world,2*fattore,i);//aggiunto
			for(int i=15*fattore,j=8*fattore;i<19*fattore;i++,j++)//aggiunto
				stairs[j]=new Stairs(world,9*fattore,i);//aggiunto
			for(int i=11*fattore,j=12*fattore;i<15*fattore;i++,j++)
				stairs[j]=new Stairs(world,17*fattore,i);
			
			
			int cont=0;
			movableObjects[cont++]=new MovableObject(world,6*fattore,6*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,10*fattore,14*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,14*fattore,6*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,18*fattore,14*fattore+fattore-1,Directions.STOP,0);
			
			world.update(player, enemies, movableObjects, solidBricks, stairs, eai);
		}
		else {
			world= new World (20*fattore,23*fattore);
			
			
			player= new Player (world,3*fattore,18*fattore+fattore-1, Directions.STOP,1,lives);
			solidBricks=new SolidBrick[62*fattore];
			stairs=new Stairs[28*fattore];
			movableObjects =new MovableObject[4];
			//enemies=new Enemy[1];
			//enemies[0]=new Enemy(world,1*fattore,6*fattore+fattore-1,Directions.STOP,1);
			//enemies[0].insertP(player);
			player2=new Player(world,17*fattore,18*fattore+fattore-1,Directions.STOP,1,lives);
		
			for(int i=0;i<20*fattore;i++)
				solidBricks[i]=new SolidBrick(world,i,19*fattore);
			
			for(int i=0,j=20*fattore ;i<8*fattore; i++,j++)
				solidBricks[j]=new SolidBrick(world,i,15*fattore);
			for(int i=12*fattore,j=28*fattore ;i<20*fattore; i++,j++)
				solidBricks[j]=new SolidBrick(world,i,15*fattore);
			
			for(int i=0,j=36*fattore ;i<20*fattore; i++,j++)
				solidBricks[j]=new SolidBrick(world,i,10*fattore);
			
			for(int i=0,j=56*fattore ;i<3*fattore; i++,j++)
				solidBricks[j]=new SolidBrick(world,i,5*fattore);
			for(int i=17*fattore,j=59*fattore ;i<20*fattore; i++,j++)
				solidBricks[j]=new SolidBrick(world,i,5*fattore);
			
			
			
			for(int i=5*fattore,j=0;i<10*fattore;i++,j++)
				stairs[j]=new Stairs(world,2*fattore,i);
			for(int i=5*fattore,j=5*fattore;i<10*fattore;i++,j++)
				stairs[j]=new Stairs(world,18*fattore,i);
			
			for(int i=10*fattore,j=10*fattore;i<15*fattore;i++,j++)
				stairs[j]=new Stairs(world,4*fattore,i);
			for(int i=10*fattore,j=15*fattore;i<15*fattore;i++,j++)
				stairs[j]=new Stairs(world,16*fattore,i);
			
			for(int i=15*fattore,j=20*fattore;i<19*fattore;i++,j++)
				stairs[j]=new Stairs(world,7*fattore,i);
			for(int i=15*fattore,j=24*fattore;i<19*fattore;i++,j++)
				stairs[j]=new Stairs(world,13*fattore,i);
			
			
			
			int cont=0;
			movableObjects[cont++]=new MovableObject(world,1*fattore,4*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,19*fattore,4*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,9*fattore,9*fattore+fattore-1,Directions.STOP,0);
			movableObjects[cont++]=new MovableObject(world,10*fattore,9*fattore+fattore-1,Directions.STOP,0);

			
			
			world.update(player, movableObjects, solidBricks, stairs, player2);
			
		}
		
		
		
		
		
		
		
		
				
		
		
	}
	
	public boolean getED() {
		return ed;
	}
	public Editor getEDITOR() {
		return editor;
	}
	public void startGame(int levels,Editor editor,int lives)
	{
		this.editor=editor;
		ed=true;
		world= new World (20*fattore,23*fattore);
		
		player= new Player (world,11*fattore,18*fattore+fattore-1, Directions.STOP,1,lives);
		eai=new EnemyAI(world,1*fattore,9*fattore+fattore-1,Directions.STOP,1,player);
		solidBricks= new SolidBrick[editor.getSolidBricks().size()];
		movableObjects = new MovableObject[editor.getMovableObjects().size()];
		enemies=new Enemy[editor.getEnemies().size()];
		stairs=new Stairs[editor.getStairs().size()];
		
		
		
		for(int i=0; i<editor.getSolidBricks().size(); i++)
		{
			solidBricks[i]= editor.getSolidBricks().get(i);
		}
		
		for(int i=0; i<editor.getMovableObjects().size(); i++)
		{
			movableObjects[i]= editor.getMovableObjects().get(i);
		}
		
		for(int i=0; i<editor.getEnemies().size(); i++)
		{
			enemies[i]= new Enemy(world,editor.getEnemies().get(i).getX(),editor.getEnemies().get(i).getY(),Directions.STOP,1);
		//	enemies[i]= editor.getEnemies().get(i);
			enemies[i].insertP(player);
		}
		
		for(int i=0; i<editor.getStairs().size(); i++)
		{
			stairs[i]= editor.getStairs().get(i);
		}

			
		
			
			world.update(player, enemies, movableObjects, solidBricks, stairs, eai);
			
			
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
	
	public boolean getCollision()
	{
		if(levels!=4)
			return eai.collisionep(player);
		else
			return player.collision(player2);
	}



	public void setConnected(boolean b) {
		connected=b;
		
	}



	public Directions getPlayerLastDir2() {
		return player2.lastDirection;
	}



	public void cgo() {
		c=new Client(this);
		c.start();
		
	}
	public void setplayer2(String s) {
		if(s.equals("up"))
			player2.setDirection(Directions.UP);
		else if(s.equals("down"))
			player2.setDirection(Directions.DOWN);
		else if(s.equals("left"))
			player2.setDirection(Directions.LEFT);
		else if(s.equals("right"))
			player2.setDirection(Directions.RIGHT);
		else if(s.equals("stop"))
			player2.setDirection(Directions.STOP);
		else if(s.equals("shoot")) {
			if(player2.getProiettile().isVisible()==false) {
				player2.getProiettile().setVisible(true);
				if(player2.bullet>0)
					player2.shoot();
					shooting2=true;
			}
		}
		else if(s.equals("shootof")) {
			shooting2=false;
		}
	}



	public void collisioneproiettili() {
		if(player.getProiettile().isVisible()&&player2.getProiettile().isVisible()) {
			for(int i=player.getProiettile().getX()-fattore/2;i<player.getProiettile().getX()+fattore/2;i++)
				for(int j=player2.getProiettile().getX()-fattore/2;j<player2.getProiettile().getX()+fattore/2;j++)
					if(player.getProiettile().getY()==player2.getProiettile().getY()&&i==j)
					{
						player.getProiettile().setVisible(false);
						player2.getProiettile().setVisible(false);
						player.getProiettile().setX(player.getX());
						player.getProiettile().setY(player.getY());
						player2.getProiettile().setX(player2.getX());
						player2.getProiettile().setY(player2.getY());
					}
			/*if(player.getProiettile().collision(player2.getProiettile())&&player2.getProiettile().collision(player.getProiettile())) {
				player.getProiettile().setVisible(false);
				player2.getProiettile().setVisible(false);
				player.getProiettile().setX(player.getX());
				player.getProiettile().setY(player.getY());
				player2.getProiettile().setX(player2.getX());
				player2.getProiettile().setY(player2.getY());
			}*/
		}
		
		if(player.getProiettile().isVisible())
			for(int i=player.getProiettile().getX()-fattore/2;i<player.getProiettile().getX()+fattore/2;i++)
				for(int j=player2.getX()-fattore/2;j<player2.getX()+fattore/2;j++)
					if(player.getProiettile().getY()==player2.getY()&&i==j)
					{
						player2.setSpeed(0);
						player2.setDirection(Directions.STOP);
						player.getProiettile().setVisible(false);
						player.getProiettile().setX(player.getX());
						player.getProiettile().setY(player.getY());
						conteggio1=true;
					}
		
		if(player2.getProiettile().isVisible())
			for(int i=player2.getProiettile().getX()-fattore/2;i<player2.getProiettile().getX()+fattore/2;i++)
				for(int j=player.getX()-fattore/2;j<player.getX()+fattore/2;j++)
					if(player2.getProiettile().getY()==player.getY()&&i==j)
					{
						player.setSpeed(0);
						player.setDirection(Directions.STOP);
						player2.getProiettile().setVisible(false);
						player2.getProiettile().setX(player2.getX());
						player2.getProiettile().setY(player2.getY());
						conteggio2=true;
					}
		if(conteggio1==true) {
			if(pcont>=100) {
				conteggio1=false;
				player2.setSpeed(1);
				pcont=0;
				
			}
			else
				pcont++;
		}
		if(conteggio2==true) {
			if(pcont2>=100) {
				conteggio2=false;
				player.setSpeed(1);
				pcont2=0;
				
			}
			else
				pcont2++;
		}
	}



	public void setposition(String s) {
		player2.setX(Integer.parseInt(s));
		
	}
		
}