package main.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import jdk.internal.util.xml.impl.Pair;
import main.core.interfaces.Directions;
import main.managers.audio.AudioManager;


public class MyEditorPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image elementoEvidenziato;
	Image blocco_editor;
	Image scala_editor;
	Image movable_editor;
	Image enemy_editor;
	Image mappa_editor;
	Image press_x;
	Image eraser;
	int fattore= 10;
	AudioManager audio_manager;
	//Player pl;
	
	private World myWorld;
	Editor editor;
	
	ContenutoMatriceEditor [][] matrix;

	//mediante il mondo aggiungi alle liste i nuovi oggetti creati nelle posizioni previste 

	
	Toolkit tk = Toolkit.getDefaultToolkit();
	int [] position;
	int [] elementi;
	
	MyEditorThread mt = new MyEditorThread(this);
	MyFrame mf;
	URL urlEditorBackground = this.getClass().getResource("SCHERMATA_EDITOR(2).png");
	Image editorBG = tk.getImage(urlEditorBackground);
	
	Dimension screenSize = tk.getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	Image editorBackground = editorBG.getScaledInstance(width, height,1);
	
	int x = 898*width/1920;  
	int y = 16*height/1080;	
	int altezza = 900*height/1080;
	int larghezza = 900*width/1920;
	
	private Vector<Point> points = new Vector<Point>();
	
	
	
	public MyEditorPanel() {
		super();
		
		this.setEnabled(true);
		
		//pl=new Player(myWorld, 11,18,Directions.STOP,0);
		myWorld = new World(20*fattore,23*fattore);
		editor = new Editor();
		
		position = new int [4];
		position[0]=1; position[1]=0; position[2]=0; position[3]=0;
		matrix= new ContenutoMatriceEditor [20][23];
		for(int i=0;i<20; i++)
			for(int j=0;j<23; j++)
				matrix[i][j]= new ContenutoMatriceEditor("",0,0);
		
		matrix[11][18] = new ContenutoMatriceEditor("player",11,18);
		matrix[1][9] = new ContenutoMatriceEditor("enemyAI",1,9);
		
		audio_manager=new AudioManager();
		
		
		elementi = new int [2];
		elementi[0]=1; elementi [1] =0;
		initEDITOR();
		initListener();

	   
	}
	
	
	public void initEDITOR() {
		URL urlBlocco = this.getClass().getResource("BLOCCO_EDITOR.png");
		URL urlScala = this.getClass().getResource("SCALA_EDITOR.png");
		URL urlEnemy = this.getClass().getResource("ENEMY_EDITOR.png");
		URL urlMovable = this.getClass().getResource("MOVABLE_EDITOR.png");
		URL urlMappa = this.getClass().getResource("mappa_editor.png");
		
		URL urlEvidenzia = this.getClass().getResource("EVIDENZIA.png");
		URL urlPressX = this.getClass().getResource("PRESS_X.png");
		URL urlEraser = this.getClass().getResource("ERASER.png");
		
		blocco_editor = tk.getImage(urlBlocco).getScaledInstance(width*45/1920,height*45/1080, 1);
		scala_editor = tk.getImage(urlScala).getScaledInstance(width*45/1920,height*45/1080, 1);
		enemy_editor = tk.getImage(urlEnemy).getScaledInstance(width*45/1920,height*45/1080, 1);
		movable_editor = tk.getImage(urlMovable).getScaledInstance(width*45/1920,height*45/1080, 1);
		elementoEvidenziato = tk.getImage(urlEvidenzia).getScaledInstance(width*45/1920,height*45/1080, 1);
		mappa_editor= tk.getImage(urlMappa).getScaledInstance(width*900/1920,height*1035/1080, 1);
		press_x = tk.getImage(urlPressX).getScaledInstance(width*406/1920, height*40/1080, 1);
		eraser = tk.getImage(urlEraser).getScaledInstance(width*325/1920, height*48/1080, 1);
		
		
		try {
			
			audio_manager.initMusic();
		
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		mt.start();
	}
	
	private Point clickToGrid(int i, int j)
	{
		int px=i;
		int py=j;
		
		px=(px-x)/(45*width/1920);
		py=(py-y)/(45*height/1080);
		
		Point p= new Point (px,py);
		return p;
	}
	
	

	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(editorBackground, 0, 0, this);

		
		g.drawImage(mappa_editor,x,y,this);
		
		if(position[0]==1)
		g.drawImage(elementoEvidenziato, 243*width/1920, 292*height/1080, this);
		if(position[1]==1)
		g.drawImage(elementoEvidenziato, 243*width/1920, 381*height/1080, this);
		if(position[2]==1)
		g.drawImage(elementoEvidenziato, 243*width/1920, 470*height/1080, this);
		if(position[3]==1)
		g.drawImage(elementoEvidenziato, 243*width/1920, 552*height/1080, this);
		
		if(elementi[0] ==1)
			g.drawImage(press_x, 54*width/1920, 630*height/1080, this);
		if(elementi[1] ==1)
			g.drawImage(eraser, 70*width/1920, 625*height/1080, this);
		
		//BISOGNA RIADATTARE LE DIMENSIONI DI QUESTE ULTIME IMMAGINI
		
		for(int i=0; i< points.size(); i++)
		{
			Point tmp= points.get(i);
			for(int l=0;l<20; l++)
				for(int t=0;t<23; t++)
				{
					if( tmp.x == l && tmp.y==t)
					{
						if(matrix[l][t].getS() == "blocco")
							g.drawImage(blocco_editor, x+l*(45*width/1920), y+t*(45*height/1080), this);
						else if(matrix[l][t].getS() == "sfera")
							g.drawImage(movable_editor, x+l*(45*width/1920), y+t*(45*height/1080), this);
						else if(matrix[l][t].getS() == "nemico")
							g.drawImage(enemy_editor, x+l*(45*width/1920), y+t*(45*height/1080), this);
						else if(matrix[l][t].getS() == "scala")
							g.drawImage(scala_editor, x+l*(45*width/1920), y+t*(45*height/1080), this);
						else if(matrix[l][t].getS() == "bloccoScala")
							g.drawImage(scala_editor, x+l*(45*width/1920), y+t*(45*height/1080), this);
					}
				}
		}
		
		
		}
	
	
	
	
public Editor getEditor()
{
	return editor;
}
	
	
	
public void initListener() {
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==49) {
					audio_manager.playButton19();
					position[0]=1;
					position[1]=0;
					position[2]=0;
					position[3]=0;
					elementi[0]=1;
					elementi[1]=0;
				}
					
				if(e.getKeyCode()==50) {
					audio_manager.playButton19();
					position[0]=0;
					position[1]=1;
					position[2]=0;
					position[3]=0;
					elementi[0]=1;
					elementi[1]=0;
				}
				if(e.getKeyCode()==51) {
					audio_manager.playButton19();
					position[2]=1;
					position[0]=0;
					position[1]=0;
					position[3]=0;
					elementi[0]=1;
					elementi[1]=0;
				}
				if(e.getKeyCode()==52) {
					audio_manager.playButton19();
					position[3]=1;
					position[0]=0;
					position[1]=0;
					position[2]=0;
					elementi[0]=1;
					elementi[1]=0;
					
				}
				if(e.getKeyCode()==88) {
					audio_manager.playButton19();
					position[0]=0;
					position[1]=0;
					position[2]=0;
					position[3]=0;
					elementi[0]=0;
					elementi[1]=1;
					
				}
				
				else if(e.getKeyCode()==10) {
					audio_manager.playButton3();
					
					for(int i=0; i<20; i++)
					{
						for(int j=0; j<23; j++)
						{
							if(matrix[i][j].getS()=="blocco")
							{
								for(int t=0;t<10;t++) 
									editor.addSolidBrick(new SolidBrick(myWorld,matrix[i][j].getCoordinataX()*10+t,matrix[i][j].getCoordinataY()*10));
							}
							if(matrix[i][j].getS()=="scala")
							{
								for(int t=0;t<10;t++) 
									editor.addStair(new Stairs(myWorld,matrix[i][j].getCoordinataX()*10,matrix[i][j].getCoordinataY()*10+t));
							}
							if(matrix[i][j].getS()=="sfera")
							{
								editor.addMovableObject(new MovableObject(myWorld,matrix[i][j].getCoordinataX()*10,matrix[i][j].getCoordinataY()*10+10-1,Directions.STOP, 0));
							}
							if(matrix[i][j].getS()=="nemico")
							{
								editor.addEnemy(new Enemy(myWorld,matrix[i][j].getCoordinataX()*10,matrix[i][j].getCoordinataY()*10+10-1,Directions.STOP, 1));
							}
							if(matrix[i][j].getS()=="bloccoScala")
							{
								for(int t=0;t<10;t++) 
									editor.addSolidBrick(new SolidBrick(myWorld,matrix[i][j].getCoordinataX()*10+t,matrix[i][j].getCoordinataY()*10));
								for(int t=0;t<10;t++) 
									editor.addStair(new Stairs(myWorld,matrix[i][j].getCoordinataX()*10,matrix[i][j].getCoordinataY()*10+t));	
							}
								
						}
						
					}
					
					mf = new MyFrame(editor);
					 JComponent comp = (JComponent) e.getSource();
					  Window win = SwingUtilities.getWindowAncestor(comp);
					  win.dispose();
				}	
						
			}
				
				
		});	
		
		
		
		
		
		
		
			this.addMouseListener(new MouseListener() {
				
				//71 lunghezza
				//35 altezza

				
					
				

				@Override
				public void mouseClicked(MouseEvent e) {
					audio_manager.playButton19();
					int k= e.getX();
					int h= e.getY();
					Point p= clickToGrid(k,h);
					
					
					
					if(k >=x && k <= x+larghezza && h >= y && h <= y+altezza )
					{
						
						System.out.println(k);
						System.out.println(h);
						if (e.getButton() == MouseEvent.BUTTON1)
						{
								if(position[0]==1)
								{
									if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() == "" &&  (matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() != "player" || matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() != "enemyAI"))
									{
										matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)]=new ContenutoMatriceEditor("blocco",(int)(k-x)/(45*width/1920),(int)(h-y)/(45*height/1080));
									}
									
									if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() == "scala")
									{
										matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)]=new ContenutoMatriceEditor("bloccoScala",(int)(k-x)/(45*width/1920),(int)(h-y)/(45*height/1080));
									}
								}

								else if(position[1]==1)
								{
									if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() == "" &&  (matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() != "player" || matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() != "enemyAI"))
									{
										matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)]=new ContenutoMatriceEditor("sfera",(int)(k-x)/(45*width/1920),(int)(h-y)/(45*height/1080));
									}
									
								}

								else if(position[2]==1)
								{
									if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() == "" &&  (matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() != "player" || matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() != "enemyAI"))
									{
										matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)]=new ContenutoMatriceEditor("nemico",(int)(k-x)/(45*width/1920),(int)(h-y)/(45*height/1080));
										
									}
								}

								else if(position[3]==1)
								{
									if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() == "" &&  (matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() != "player" || matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() != "enemyAI"))
									{
										matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)]=new ContenutoMatriceEditor("scala",(int)(k-x)/(45*width/1920),(int)(h-y)/(45*height/1080));
										//for(int i=0;i<10;i++) {
										//}
									}
									if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS() == "blocco")
										matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)]=new ContenutoMatriceEditor("bloccoScala",(int)(k-x)/(45*width/1920),(int)(h-y)/(45*height/1080));
								}
								
							points.add(p);
								
							
						}
						
						if(elementi[1]==1)
						{
							if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS()=="blocco")
							{
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setS("");
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setCoordinataX(0);
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setCoordinataY(0);

							}
							if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS()=="scala")
							{
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setS("");
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setCoordinataX(0);
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setCoordinataY(0);

							}
							if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS()=="sfera")
							{
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setS("");
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setCoordinataX(0);
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setCoordinataY(0);

							}
							if(matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].getS()=="nemico")
							{
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setS("");
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setCoordinataX(0);
								matrix[(int)(k-x)/(45*width/1920)][(int)(h-y)/(45*height/1080)].setCoordinataY(0);

							}
							
						}
						
						System.out.println(p.x);
						System.out.println(p.y);
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
		
			});
		
		
		
		
	}





public World getMyWorld() {
	return myWorld;
}


public void setMyWorld(World myWorld) {
	this.myWorld = myWorld;
}



	
	
}


	
	
