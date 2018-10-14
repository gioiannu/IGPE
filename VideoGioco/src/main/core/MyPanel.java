package main.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import main.core.interfaces.Directions;
import main.managers.GameManager;

public class MyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7756238667816638729L;
	GameManager gameManager=new GameManager();
	Toolkit tk=Toolkit.getDefaultToolkit();
	
	Image []player=new Image[15];
	
	Image background;
	Image scaledBackground;
	
	Image brick;
	
	Image stair;
	
	Image []enemyAI= new Image[11];
	
	Image object7;
	
	Image lastImageP;
	Image lastImageE;
	
	Image [] bullet=new Image[2];
	
	boolean shooting=false;
	
	private int fattore=10;
	
	Dimension screenSize = tk.getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();

	
	public MyPanel() 
	{
		super();
		initGUI();
		initEH();
		gameManager.startGame(1);
		ThreadDinamicObject te=new ThreadDinamicObject(gameManager/*,this*/);
		ThreadMovableObject tm=new ThreadMovableObject(gameManager/*,this*/);
		ThreadPanel tp= new ThreadPanel(this,gameManager);
		te.start();
		tp.start();
		tm.start();
	}
	
	private void initGUI() {
		this.setFocusable(true);
		background=tk.getImage(this.getClass().getResource("SFONDO.png")).getScaledInstance(width,height,1);
		//scaledBackground = background.getScaledInstance(width,height,1);
		
		brick=tk.getImage(this.getClass().getResource("BLOCCO.png")).getScaledInstance(width*130/1920,height*80/1080, 1);
		
		stair=tk.getImage(this.getClass().getResource("SCALA.png")).getScaledInstance(width*130/1920,height*80/1080, 1);
		
		object7=tk.getImage(this.getClass().getResource("MOVABLE.png")).getScaledInstance(width*58/1920,height*58/1080, 1);
		
		player[0]=tk.getImage(this.getClass().getResource("GOKUF1DX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[1]=tk.getImage(this.getClass().getResource("GOKUF1DX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[2]=tk.getImage(this.getClass().getResource("GOKUDX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[3]=tk.getImage(this.getClass().getResource("GOKUF1SX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[4]=tk.getImage(this.getClass().getResource("GOKUF1SX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[5]=tk.getImage(this.getClass().getResource("GOKUSX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[6]=tk.getImage(this.getClass().getResource("enemyy.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[7]=tk.getImage(this.getClass().getResource("GOKUJUMPDX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[8]=tk.getImage(this.getClass().getResource("GOKUJUMPSX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[9]=tk.getImage(this.getClass().getResource("GOKUDOWNSX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[10]=tk.getImage(this.getClass().getResource("GOKUDOWNDX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[11]=tk.getImage(this.getClass().getResource("GOKUSHOOTDX1.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[12]=tk.getImage(this.getClass().getResource("GOKUSHOOTDX2.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[13]=tk.getImage(this.getClass().getResource("GOKUSHOOTSX1.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		player[14]=tk.getImage(this.getClass().getResource("GOKUSHOOTSX2.png")).getScaledInstance(width*46/1920,height*58/1080, 1);	
		
		
		lastImageP=player[0];
		
		enemyAI[0]=tk.getImage(this.getClass().getResource("ENEMYF1DX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[1]=tk.getImage(this.getClass().getResource("ENEMYF1DX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[2]=tk.getImage(this.getClass().getResource("ENEMYDX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[3]=tk.getImage(this.getClass().getResource("ENEMYF1SX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[4]=tk.getImage(this.getClass().getResource("ENEMYF2SX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[5]=tk.getImage(this.getClass().getResource("ENEMYSX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[6]=tk.getImage(this.getClass().getResource("ENEMYTEL.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[7]=tk.getImage(this.getClass().getResource("ENEMYJUMPDX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[8]=tk.getImage(this.getClass().getResource("ENEMYJUMPSX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[9]=tk.getImage(this.getClass().getResource("ENEMYDOWNSX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		enemyAI[10]=tk.getImage(this.getClass().getResource("ENEMYDOWNDX.png")).getScaledInstance(width*46/1920,height*58/1080, 1);
		
		
		lastImageE=enemyAI[0];
		
		bullet[0]=tk.getImage(this.getClass().getResource("PROIETTILEDX.png")).getScaledInstance(width*50/1920,height*30/1080, 1);
		bullet[1]=tk.getImage(this.getClass().getResource("PROIETTILESX.png")).getScaledInstance(width*50/1920,height*30/1080, 1);
		
		
		
	}
	private void initEH() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_W)
					gameManager.getPlayer().setDirection(Directions.UP);
				
				else if(e.getKeyCode()==KeyEvent.VK_S)
					gameManager.getPlayer().setDirection(Directions.DOWN);
				
				else if(e.getKeyCode()==KeyEvent.VK_A)
					gameManager.getPlayer().setDirection(Directions.LEFT);
				
				else if(e.getKeyCode()==KeyEvent.VK_D)
					gameManager.getPlayer().setDirection(Directions.RIGHT);
				
				else if(e.getKeyCode()==KeyEvent.VK_F)
				{
					if(!gameManager.getPlayer().getProiettile().isVisible()) {
						gameManager.getPlayer().shoot();
						shooting=true;
						
					}
				}
				
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_W)
					gameManager.getPlayer().setDirection(Directions.STOP);
				
				else if(e.getKeyCode()==KeyEvent.VK_S)
					gameManager.getPlayer().setDirection(Directions.STOP);
				
				else if(e.getKeyCode()==KeyEvent.VK_A)
					gameManager.getPlayer().setDirection(Directions.STOP);
				
				else if(e.getKeyCode()==KeyEvent.VK_D)
					gameManager.getPlayer().setDirection(Directions.STOP);
				else if(e.getKeyCode()==KeyEvent.VK_F)
					shooting=false;
				
				
				
				
			}
			
		});
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,0,0,this);
		for(int i=0;i<gameManager.getSolidBrick().length/fattore;i++)
		{
			g.drawImage(brick,convertiX(gameManager.getSolidBrick()[i*fattore].getX()),convertiY(gameManager.getSolidBrick()[i*fattore].getY()), this);
			
		}
		for(int i=0;i<gameManager.getStair().length/fattore;i++)
		{
			g.drawImage(stair,convertiX(gameManager.getStair()[i*fattore].getX()),convertiY(gameManager.getStair()[i*fattore].getY()), this);
		}
		for(int i=0;i<gameManager.getMovableObject().length;i++)
		{
			if(21*fattore>gameManager.getMovableObject()[i].getY())
				g.drawImage(object7,convertiX(gameManager.getMovableObject()[i].getX()),convertiY(gameManager.getMovableObject()[i].getY()-fattore), this);
		}
		g.drawImage(playerMovement(gameManager.getPlayer()), convertiX(gameManager.getPlayer().getX()), convertiY(gameManager.getPlayer().getY()-fattore), this);
		g.drawImage(enemyMovement(gameManager.getEai()), convertiX(gameManager.getEai().getX()), convertiY(gameManager.getEai().getY()-fattore), this);
		g.drawImage(player[6],convertiX(gameManager.getEnemy()[0].getX()),convertiY(gameManager.getEnemy()[0].getY()-fattore), this);
		if(gameManager.getPlayer().getProiettile().isVisible()==true&&gameManager.getPlayer().getProiettile().getDirection()==Directions.RIGHT)
			g.drawImage(bullet[0], convertiX(gameManager.getPlayer().getProiettile().getX()), convertiY(gameManager.getPlayer().getProiettile().getY()-fattore), this);
		else if(gameManager.getPlayer().getProiettile().isVisible()==true&&gameManager.getPlayer().getProiettile().getDirection()==Directions.LEFT)
			g.drawImage(bullet[1], convertiX(gameManager.getPlayer().getProiettile().getX()), convertiY(gameManager.getPlayer().getProiettile().getY()-fattore), this);
	}
	
	private int convertiX(int x) {
		
		return (/*94*/(94*width/1920)*x)/fattore;
	}

	private int convertiY(int y) {
		return (/*48*/(48*height/1080)*y)/fattore;
	}

	private Image playerMovement(Player p)
	{
		if(gameManager.getPlayerLastDir().equals(Directions.STOP))
		{
			if(lastImageP.equals(player[0]))
			{
				lastImageP=player[1];
				return player[1];
			}
			else if(lastImageP.equals(player[1]))
			{
				lastImageP=player[0];
				return player[0];
			}
			else if(lastImageP.equals(player[2]))
			{
				lastImageP=player[0];
				return player[0];
			}
					
			else if(lastImageP.equals(player[3]))
			{
				lastImageP=player[4];
				return player[4];
			}
			else if(lastImageP.equals(player[4]))
			{
				lastImageP=player[3];
				return player[3];
			}
			else if(lastImageP.equals(player[5]))
			{
				lastImageP=player[3];
				return player[3];
			}
			else if(lastImageP.equals(player[7])) 
			{
				lastImageP=player[0];
				return player[0];
			}
			else if(lastImageP.equals(player[8])) 
			{
				lastImageP=player[3];
				return player[3];
			}
			else if(lastImageP.equals(player[9])) 
			{
				lastImageP=player[3];
				return player[3];
			}
			else if(lastImageP.equals(player[10])) 
			{
				lastImageP=player[0];
				return player[0];
			}
		}
		
		else if(gameManager.getPlayerLastDir().equals(Directions.RIGHT))
		{
			lastImageP=player[2];
			return player[2];
		}
		else if(gameManager.getPlayerLastDir().equals(Directions.LEFT))
		{
			lastImageP=player[5];
			return player[5];
		}
		
		else if(gameManager.getPlayerLastDir().equals(Directions.UP))
		{
			if(lastImageP.equals(player[3]) || lastImageP.equals(player[4]))
			{
				lastImageP=player[8];
				return player[8];
			}
			else if(lastImageP.equals(player[0]) || lastImageP.equals(player[1]))
			{
				lastImageP=player[7];
				return player[7];
			}
			else if(lastImageP.equals(player[9]))
			{
				lastImageP=player[8];
				return player[8];
			}
			else if(lastImageP.equals(player[10]))
			{
				lastImageP=player[7];
				return player[7];
			}
		}
		
		else if(gameManager.getPlayerLastDir().equals(Directions.DOWN))
		{
			if(lastImageP.equals(player[3]) || lastImageP.equals(player[4]))
			{
				lastImageP=player[9];
				return player[9];
			}
			else if(lastImageP.equals(player[0]) || lastImageP.equals(player[1]))
			{
				lastImageP=player[10];
				return player[10];
			}
			else if(lastImageP.equals(player[7]))
			{
				lastImageP=player[10];
				return player[10];
			}
			else if(lastImageP.equals(player[8]))
			{
				lastImageP=player[9];
				return player[9];
			}
		}
		
		return lastImageP;
		
		
	}
	private Image enemyMovement(EnemyAI e)
	{
		if(e.getDirection().equals(Directions.STOP))
		{
			if(lastImageE.equals(enemyAI[0]))
			{
				lastImageE=enemyAI[1];
				return enemyAI[1];
			}
			else if(lastImageE.equals(enemyAI[1]))
			{
				lastImageE=enemyAI[0];
				return enemyAI[0];
			}
			else if(lastImageE.equals(enemyAI[2]))
			{
				lastImageE=enemyAI[0];
				return enemyAI[0];
			}
					
			else if(lastImageE.equals(enemyAI[3]))
			{
				lastImageE=enemyAI[4];
				return enemyAI[4];
			}
			else if(lastImageE.equals(enemyAI[4]))
			{
				lastImageE=enemyAI[3];
				return enemyAI[3];
			}
			else if(lastImageE.equals(enemyAI[5]))
			{
				lastImageE=enemyAI[3];
				return enemyAI[3];
			}
			else if(lastImageE.equals(enemyAI[7])) 
			{
				lastImageE=enemyAI[0];
				return enemyAI[0];
			}
			else if(lastImageE.equals(enemyAI[8])) 
			{
				lastImageE=enemyAI[3];
				return enemyAI[3];
			}
			else if(lastImageE.equals(enemyAI[9])) 
			{
				lastImageE=enemyAI[3];
				return enemyAI[3];
			}
			else if(lastImageE.equals(enemyAI[10])) 
			{
				lastImageE=enemyAI[0];
				return enemyAI[0];
			}
		}
		
		else if(e.getDirection().equals(Directions.RIGHT))
		{
			lastImageE=enemyAI[2];
			return enemyAI[2];
		}
		else if(e.getDirection().equals(Directions.LEFT))
		{
			lastImageE=enemyAI[5];
			return enemyAI[5];
		}
		
		else if(e.getDirection().equals(Directions.UP))
		{
			if(lastImageE.equals(enemyAI[3]) || lastImageE.equals(enemyAI[4]))
			{
				lastImageE=enemyAI[8];
				return enemyAI[8];
			}
			else if(lastImageE.equals(enemyAI[0]) || lastImageE.equals(enemyAI[1])||lastImageE.equals(enemyAI[2]))
			{
				lastImageE=enemyAI[7];
				return enemyAI[7];
			}
			else if(lastImageE.equals(enemyAI[9])||lastImageE.equals(enemyAI[5]))
			{
				lastImageE=enemyAI[8];
				return enemyAI[8];
			}
			else if(lastImageE.equals(enemyAI[10]))
			{
				lastImageE=enemyAI[7];
				return enemyAI[7];
			}
			
		}
		
		else if(e.getDirection().equals(Directions.DOWN))
		{
			if(lastImageE.equals(enemyAI[3]) || lastImageE.equals(enemyAI[4]))
			{
				lastImageE=enemyAI[9];
				return enemyAI[9];
			}
			else if(lastImageE.equals(enemyAI[0]) || lastImageE.equals(enemyAI[1]))
			{
				lastImageE=enemyAI[10];
				return enemyAI[10];
			}
			else if(lastImageE.equals(enemyAI[7])||lastImageE.equals(enemyAI[2]))
			{
				lastImageE=enemyAI[10];
				return enemyAI[10];
			}
			else if(lastImageE.equals(enemyAI[8])||lastImageE.equals(enemyAI[5]))
			{
				lastImageE=enemyAI[9];
				return enemyAI[9];
			}
			}
		
		return lastImageE;
	}

}
