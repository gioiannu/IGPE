package main.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.core.interfaces.Directions;
import main.managers.GameManager;
import main.managers.audio.AudioManager;
import net.Client;

public class MyPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7756238667816638729L;
	GameManager gameManager=new GameManager();
	AudioManager audio_manager;
	Toolkit tk=Toolkit.getDefaultToolkit();
	
	Image []player=new Image[15];
	
	Image background;
	Image scaledBackground;
	
	Image brick;
	
	Image stair;
	
	Image []enemyAI= new Image[11];
	
	Image object7;
	
	Image lastImageP;
	Image lastImageP2;
	Image lastImageE;
	Image [] gameOver= new Image[3];
	Image [] vittoria= new Image[3];
	int [] pos2;
	int [] position;
	Image life;
	Image onda;
	
	Image [] bullet=new Image[2];
	MyEditorPanel me= new MyEditorPanel();
	MyMenuFrame mmp;
	MyFrame mf;
	Editor startEditor;
	Client c;
	
	boolean shooting=false;
	
	boolean fire= false;
	boolean creatoreDiLivelli=false;
	
	private int fattore=10;
	
	Dimension screenSize = tk.getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();

	public MyPanel(int l) 
	{
		super();
		position= new int [2];
		pos2= new int [2];
		position[0]=1; position [1]=0;	
		pos2[0]=1; pos2[1]=0;
		
		gameManager.startGame(l,3);
		if(l==4) {
			c=new Client(gameManager);
			c.start();
		}
		audio_manager = new AudioManager(this.gameManager);
		initGUI();
		initEH();
		initListener();
		
		ThreadDinamicObject te=new ThreadDinamicObject(gameManager,audio_manager);
		ThreadMovableObject tm=new ThreadMovableObject(gameManager,audio_manager);
		ThreadPanel tp= new ThreadPanel(this,gameManager);
		te.start();
		tp.start();
		tm.start();
		
	}
	public MyPanel( Editor editor) 
	{
		super();
		startEditor=editor;
		creatoreDiLivelli=true;
		position= new int [2];
		pos2= new int [2];
		position[0]=1; position [1]=0;	
		pos2[0]=1; pos2[1]=0;
		gameManager.startGame(4,editor,3);
		audio_manager = new AudioManager(this.gameManager);
		initGUI();
		initEH();
		initListener();
		ThreadDinamicObject te=new ThreadDinamicObject(gameManager,audio_manager);
		ThreadMovableObject tm=new ThreadMovableObject(gameManager,audio_manager);
		ThreadPanel tp= new ThreadPanel(this,gameManager);
		te.start();
		tp.start();
		tm.start();
	}
	
	private void initGUI() {
		this.setFocusable(true);
		if(gameManager.getCollision())
			audio_manager.playHit();
		
		try {
				
				audio_manager.initMusic();
				audio_manager.playMusic();
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(gameManager.win())
		{
			audio_manager.stopMusic();
			audio_manager.playYouWin();
		}
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
		lastImageP2=player[0];
		
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
		
		bullet[0]=tk.getImage(this.getClass().getResource("PROIETTILEDX.png")).getScaledInstance(width*80/1920,height*40/1080, 1);
		bullet[1]=tk.getImage(this.getClass().getResource("PROIETTILESX.png")).getScaledInstance(width*80/1920,height*40/1080, 1);
		
		gameOver[0]= tk.getImage(this.getClass().getResource("GAMEOVER.png")).getScaledInstance(width*1000/1920,height*800/1080, 1);
		gameOver[1]= tk.getImage(this.getClass().getResource("MENUGMPS.png")).getScaledInstance(width*362/1920,height*101/1080, 1);
		gameOver[2]= tk.getImage(this.getClass().getResource("RESTART.png")).getScaledInstance(width*426/1920,height*100/1080, 1);
		
		vittoria[0]= tk.getImage(this.getClass().getResource("VITTORIA.png")).getScaledInstance(width*1000/1920,height*800/1080, 1);
		vittoria[1]= tk.getImage(this.getClass().getResource("MENUVIT.png")).getScaledInstance(width*362/1920,height*101/1080, 1);
		vittoria[2]= tk.getImage(this.getClass().getResource("LIVELLO_SUCCESSIVO.png")).getScaledInstance(width*467/1920,height*165/1080, 1);
		
		
		life= tk.getImage(this.getClass().getResource("LIVES.png")).getScaledInstance(width*50/1920,height*50/1080, 1);
		onda= tk.getImage(this.getClass().getResource("ondazza.png")).getScaledInstance(width*50/1920,height*50/1080, 1);
		
	}
	
	private boolean pressed=false;
	private void initEH() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_W)
				{
					gameManager.getPlayer().setDirection(Directions.UP);
					if(gameManager.getLevels()==4)
						c.sendDirection("up");
					
					 if (!pressed&& !gameManager.gameOver()&&!gameManager.win()) {
						 audio_manager.playFly();
				            pressed = true;
				        }
					
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_S)
				{
					gameManager.getPlayer().setDirection(Directions.DOWN);
					if(gameManager.getLevels()==4)
						c.sendDirection("down");
					if (!pressed&& !gameManager.gameOver()&&!gameManager.win()) {
						 audio_manager.playFly();
				            pressed = true;
				        }
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_A)
				{
					gameManager.getPlayer().setDirection(Directions.LEFT);
					if(gameManager.getLevels()==4)
						c.sendDirection("right");
					if (!pressed&& !gameManager.gameOver()&&!gameManager.win()) {
						 audio_manager.playFly();
				            pressed = true;
				        }
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_D)
				{
					gameManager.getPlayer().setDirection(Directions.RIGHT);
					if(gameManager.getLevels()==4)
						c.sendDirection("left");
					if (!pressed&& !gameManager.gameOver()&&!gameManager.win()) {
						 audio_manager.playFly();
				            pressed = true;
				        }
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_F)
				{
					if(!gameManager.getPlayer().getProiettile().isVisible()) {
						if(gameManager.getPlayer().bullet>0) {
							gameManager.getPlayer().shoot();
							shooting=true;
							if(gameManager.getLevels()==4)
								c.sendShoot();
							if (!pressed && !gameManager.gameOver()&&!gameManager.win()) {
								 audio_manager.playShoot();
						            pressed = true;
						        }
							/*if(gameManager.getPlayerLastDir().equals(Directions.RIGHT)||lastImageP==player[0]||lastImageP==player[1]||lastImageP==player[2]){
									lastImageP=player[11];
							}
							else if(gameManager.getPlayerLastDir().equals(Directions.LEFT)||lastImageP==player[3]||lastImageP==player[4]||lastImageP==player[5]){
									lastImageP=player[13];	
							}*/
						}
					}
				
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_W)
				{
					gameManager.getPlayer().setDirection(Directions.STOP);
					if(gameManager.getLevels()==4)
						c.sendDirection("stop");
					pressed=false;
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_S)
				{
					gameManager.getPlayer().setDirection(Directions.STOP);
					if(gameManager.getLevels()==4)
						c.sendDirection("stop");
					pressed=false;
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_A)
				{
					gameManager.getPlayer().setDirection(Directions.STOP);
					if(gameManager.getLevels()==4)
						c.sendDirection("stop");
					pressed=false;
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_D)
				{
					gameManager.getPlayer().setDirection(Directions.STOP);
					if(gameManager.getLevels()==4)
						c.sendDirection("stop");
					pressed=false;
				}
				else if(e.getKeyCode()==KeyEvent.VK_F) {
					shooting=false;
					pressed=false;
				}
				
				
				
				
			}
			
		});
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	try {
		if(gameManager.connected==true||gameManager.getLevels()!=4) {
		g.drawImage(background,0,0,this);
		
		for(int i=0;i<gameManager.getPlayer().getLives(); i++)
			g.drawImage(life, convertiX(i*6+3),convertiY(5), this);
		
		for(int i=0;i<gameManager.getPlayer().bullet; i++)
			g.drawImage(onda, convertiX(i*6+3),convertiY(16), this);
		
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
		if(gameManager.getLevels()!=4)
			for(int i=0;i<gameManager.getEnemy().length;i++)
			{
				g.drawImage(player[6],convertiX(gameManager.getEnemy()[i].getX()),convertiY(gameManager.getEnemy()[i].getY()-fattore), this);
			}
		g.drawImage(playerMovement(gameManager.getPlayer()), convertiX(gameManager.getPlayer().getX()), convertiY(gameManager.getPlayer().getY()-fattore), this);
		if(gameManager.getLevels()!=4)
			g.drawImage(enemyMovement(gameManager.getEai()), convertiX(gameManager.getEai().getX()), convertiY(gameManager.getEai().getY()-fattore), this);
		else
			g.drawImage(playerMovement2(gameManager.player2), convertiX(gameManager.player2.getX()), convertiY(gameManager.player2.getY()-fattore), this);
			
		
		if(gameManager.getPlayer().getProiettile().isVisible()==true&&gameManager.getPlayer().getProiettile().getDirection()==Directions.RIGHT)
			g.drawImage(bullet[0], convertiX(gameManager.getPlayer().getProiettile().getX()), convertiY(gameManager.getPlayer().getProiettile().getY()-fattore), this);
		else if(gameManager.getPlayer().getProiettile().isVisible()==true&&gameManager.getPlayer().getProiettile().getDirection()==Directions.LEFT)
			g.drawImage(bullet[1], convertiX(gameManager.getPlayer().getProiettile().getX()), convertiY(gameManager.getPlayer().getProiettile().getY()-fattore), this);
		if(gameManager.getLevels()==4) {
			if(gameManager.player2.getProiettile().isVisible()==true&&gameManager.player2.getProiettile().getDirection()==Directions.RIGHT)
				g.drawImage(bullet[0], convertiX(gameManager.player2.getProiettile().getX()), convertiY(gameManager.player2.getProiettile().getY()-fattore), this);
			else if(gameManager.player2.getProiettile().isVisible()==true&&gameManager.player2.getProiettile().getDirection()==Directions.LEFT)
				g.drawImage(bullet[1], convertiX(gameManager.player2.getProiettile().getX()), convertiY(gameManager.player2.getProiettile().getY()-fattore), this);
		}
		}
	}
	catch(NullPointerException n){
		//System.out.println("");
	}
	
		if(gameManager.gameOver())
		{
			g.drawImage(gameOver[0], 400, 100, this);
			if(position[0] ==1)
				g.drawImage(gameOver[1], 466, 436, this);
			else
				g.drawImage(gameOver[2], 942, 436, this);
		}
		if(gameManager.win())
		{
			g.drawImage(vittoria[0], 400, 100, this);
			if(pos2[0] ==1)
				g.drawImage(vittoria[1], 466, 436, this);
			else
				g.drawImage(vittoria[2], 930, 399, this);
		}
	
	}
	
	private int convertiX(int x) {
		
		return (/*94*/(94*width/1920)*x)/fattore;
	}

	private int convertiY(int y) {
		return (/*48*/(48*height/1080)*y)/fattore;
	}

	private Image playerMovement(Player p)
	{
		if(shooting) {
			if(gameManager.getPlayerLastDir().equals(Directions.RIGHT)||lastImageP==player[0]||lastImageP==player[1]||lastImageP==player[2]){
				lastImageP=player[12];
			}
			else if(gameManager.getPlayerLastDir().equals(Directions.LEFT)||lastImageP==player[3]||lastImageP==player[4]||lastImageP==player[5]){
					lastImageP=player[14];	
			}
		}
		else {
			if(lastImageP==player[12]) {
				lastImageP=player[2];
			}
			if(lastImageP==player[14]) {
				lastImageP=player[5];
			}
		
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
		
		}
		return lastImageP;
		
	}
	private Image playerMovement2(Player p)
	{
		if(gameManager.shooting2) {
			if(gameManager.getPlayerLastDir2().equals(Directions.RIGHT)||lastImageP2==player[0]||lastImageP2==player[1]||lastImageP2==player[2]){
				lastImageP2=player[12];
			}
			else if(gameManager.getPlayerLastDir2().equals(Directions.LEFT)||lastImageP2==player[3]||lastImageP2==player[4]||lastImageP2==player[5]){
					lastImageP2=player[14];	
			}
		}
		else {
			if(lastImageP2==player[12]) {
				lastImageP2=player[2];
			}
			if(lastImageP2==player[14]) {
				lastImageP2=player[5];
			}
		
			if(gameManager.getPlayerLastDir2().equals(Directions.STOP))
			{
				if(lastImageP2.equals(player[0]))
				{
					lastImageP2=player[1];
					return player[1];
				}
				else if(lastImageP2.equals(player[1]))
				{
					lastImageP2=player[0];
					return player[0];
				}
				else if(lastImageP2.equals(player[2]))
				{
					lastImageP2=player[0];
					return player[0];
				}
						
				else if(lastImageP2.equals(player[3]))
				{
					lastImageP2=player[4];
					return player[4];
				}
				else if(lastImageP2.equals(player[4]))
				{
					lastImageP2=player[3];
					return player[3];
				}
				else if(lastImageP2.equals(player[5]))
				{
					lastImageP2=player[3];
					return player[3];
				}
				else if(lastImageP2.equals(player[7])) 
				{
					lastImageP2=player[0];
					return player[0];
				}
				else if(lastImageP2.equals(player[8])) 
				{
					lastImageP2=player[3];
					return player[3];
				}
				else if(lastImageP2.equals(player[9])) 
				{
					lastImageP2=player[3];
					return player[3];
				}
				else if(lastImageP2.equals(player[10])) 
				{
					lastImageP2=player[0];
					return player[0];
				}
			}
			
			else if(gameManager.getPlayerLastDir2().equals(Directions.RIGHT))
			{
				lastImageP2=player[2];
				return player[2];
			}
			else if(gameManager.getPlayerLastDir2().equals(Directions.LEFT))
			{
				lastImageP2=player[5];
				return player[5];
			}
			
			else if(gameManager.getPlayerLastDir2().equals(Directions.UP))
			{
				if(lastImageP2.equals(player[3]) || lastImageP2.equals(player[4]))
				{
					lastImageP2=player[8];
					return player[8];
				}
				else if(lastImageP2.equals(player[0]) || lastImageP2.equals(player[1]))
				{
					lastImageP2=player[7];
					return player[7];
				}
				else if(lastImageP2.equals(player[9]))
				{
					lastImageP2=player[8];
					return player[8];
				}
				else if(lastImageP2.equals(player[10]))
				{
					lastImageP2=player[7];
					return player[7];
				}
				
			}
			
			else if(gameManager.getPlayerLastDir2().equals(Directions.DOWN))
			{
				if(lastImageP2.equals(player[3]) || lastImageP2.equals(player[4]))
				{
					lastImageP2=player[9];
					return player[9];
				}
				else if(lastImageP2.equals(player[0]) || lastImageP2.equals(player[1]))
				{
					lastImageP2=player[10];
					return player[10];
				}
				else if(lastImageP2.equals(player[7]))
				{
					lastImageP2=player[10];
					return player[10];
				}
				else if(lastImageP2.equals(player[8]))
				{
					lastImageP2=player[9];
					return player[9];
				}
			}
		
		}
		return lastImageP2;
		
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
	
public void initListener() {
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==39&& (gameManager.gameOver() || gameManager.win())) {
					audio_manager.playButton19();
					if(position[0]==1&& gameManager.gameOver()) {
						position[0]=0;
						position[1]=1;
						System.out.println("A");
					}
					else if(pos2[0]==1&& gameManager.win()) {
						pos2[0]=0;
						pos2[1]=1;
						System.out.println("A");
					}
				}
				else if(e.getKeyCode()==37&&(gameManager.gameOver() || gameManager.win())) {
					audio_manager.playButton19();
					if(position[1]==1&& gameManager.gameOver()) {
						position[1]=0;
						position[0]=1;
						System.out.println("B");
					}
					else if(pos2[1]==1&& gameManager.win()) {
						pos2[1]=0;
						pos2[0]=1;
						System.out.println("B");
					}
				}
				else if(e.getKeyCode()==10 && gameManager.gameOver()) {
					audio_manager.playButton3();
					if(position[0]==1) {
						audio_manager.stopMusic();
						mmp = new MyMenuFrame();
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					else if(position[1]==1 && gameManager.gameOver())
					{
						if(creatoreDiLivelli)
						{
							audio_manager.stopMusic();
							mf = new MyFrame(startEditor);
							JComponent comp = (JComponent) e.getSource();
							  Window win = SwingUtilities.getWindowAncestor(comp);
							  win.dispose();
						}
						else
						{
							audio_manager.stopMusic();
							mf = new MyFrame(gameManager.getLevels());
							 JComponent comp = (JComponent) e.getSource();
							  Window win = SwingUtilities.getWindowAncestor(comp);
							  win.dispose();
						}
					}			
						
				}
				else if(e.getKeyCode()==10 && gameManager.win()) {
					audio_manager.playButton3();
					if(pos2[0]==1) {
						audio_manager.stopMusic();
						mmp = new MyMenuFrame();
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					else if(pos2[1]==1 && gameManager.win())
					{
						if(creatoreDiLivelli)
						{
							audio_manager.stopMusic();
							mf = new MyFrame(startEditor);
							JComponent comp = (JComponent) e.getSource();
							  Window win = SwingUtilities.getWindowAncestor(comp);
							  win.dispose();
						}
						else
						{
							if(gameManager.getLevels()==3)
							{
								audio_manager.stopMusic();
								mf = new MyFrame(gameManager.getLevels()-2);
								JComponent comp = (JComponent) e.getSource();
								  Window win = SwingUtilities.getWindowAncestor(comp);
								  
								  win.dispose();
							}
							else
							{
								audio_manager.stopMusic();
								mf = new MyFrame(gameManager.getLevels()+1);
								 JComponent comp = (JComponent) e.getSource();
								  Window win = SwingUtilities.getWindowAncestor(comp);
								  win.dispose();
							}
						}
					}
					
				}
				
				
			}	
		});
		
	}

}
