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
import java.net.URL;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import jdk.internal.util.xml.impl.Pair;
import main.core.interfaces.Directions;


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
	//Player pl;
	
	private World myWorld;
	Editor editor;
	
	String [][] matrix;

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
	
	int x = 898*width/1920;  ;  
	int y = 16*height/1080;	;	
	int altezza = 900*height/1080;;
	int larghezza = 900*width/1920;;
	
	private Vector<Point> points = new Vector<Point>();
	
	
	
	public MyEditorPanel() {
		super();
		
		this.setEnabled(true);
		
		//pl=new Player(myWorld, 11,18,Directions.STOP,0);
		myWorld = new World(20*fattore,23*fattore);
		editor = new Editor();
		
		position = new int [4];
		position[0]=1; position[1]=0; position[2]=0; position[3]=0;
		matrix= new String [20][23];
		for(int i=0;i<20; i++)
			for(int j=0;j<23; j++)
				matrix[i][j]= " ";
		
		matrix[11][18] = "player";
		matrix[1][9] = "enemyAI";
		
		
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
		URL urlPressX = this.getClass().getResource("PRESS_X");
		URL urlEraser = this.getClass().getResource("ERASER");
		
		blocco_editor = tk.getImage(urlBlocco).getScaledInstance(width*45/1920,height*45/1080, 1);
		scala_editor = tk.getImage(urlScala).getScaledInstance(width*45/1920,height*45/1080, 1);
		enemy_editor = tk.getImage(urlEnemy).getScaledInstance(width*45/1920,height*45/1080, 1);
		movable_editor = tk.getImage(urlMovable).getScaledInstance(width*45/1920,height*45/1080, 1);
		elementoEvidenziato = tk.getImage(urlEvidenzia).getScaledInstance(width*45/1920,height*45/1080, 1);
		mappa_editor= tk.getImage(urlMappa).getScaledInstance(width*900/1920,height*900/1080, 1);
		press_x = tk.getImage(urlPressX).getScaledInstance(width*406/1920, height*40/1080, 1);
		eraser = tk.getImage(urlEraser).getScaledInstance(width*325/1920, height*48/1080, 1);
		
		mt.start();
	}
	
	private Point clickToGrid(int i, int j)
	{
		int px=i;
		int py=j;
		
		px=(px-x)/45;
		py=(py-y)/45;
		
		Point p= new Point (px,py);
		return p;
	}
	
	

	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(editorBackground, 0, 0, this);

		
		g.drawImage(mappa_editor,x,y,this);
		
		if(position[0]==1)
		g.drawImage(elementoEvidenziato, 343, 272, this);
		if(position[1]==1)
		g.drawImage(elementoEvidenziato, 343, 415, this);
		if(position[2]==1)
		g.drawImage(elementoEvidenziato, 267, 565, this);
		if(position[3]==1)
		g.drawImage(elementoEvidenziato, 416, 565, this);
		
		if(elementi[0] ==1)
			g.drawImage(press_x, 200, 600, this);
		if(elementi[1] ==1)
			g.drawImage(eraser, 200, 600, this);


		
		for(int i=0; i< points.size(); i++)
		{
			Point tmp= points.get(i);
			for(int l=0;l<20; l++)
				for(int t=0;t<23; t++)
				{
					if( tmp.x == l && tmp.y==t)
					{
						if(matrix[l][t] == "blocco")
							g.drawImage(blocco_editor, x+(l*45), y+(t*45), this);
						else if(matrix[l][t] == "sfera")
							g.drawImage(movable_editor, x+(l*45), y+(t*45), this);
						else if(matrix[l][t] == "nemico")
							g.drawImage(enemy_editor, x+(l*45), y+(t*45), this);
						else if(matrix[l][t] == "scala")
							g.drawImage(scala_editor, x+(l*45), y+(t*45), this);
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
					position[0]=1;
					position[1]=0;
					position[2]=0;
					position[3]=0;
					elementi[0]=1;
					elementi[1]=0;
				}
					
				if(e.getKeyCode()==50) {
					position[0]=0;
					position[1]=1;
					position[2]=0;
					position[3]=0;
					elementi[0]=1;
					elementi[1]=0;
				}
				if(e.getKeyCode()==51) {
					position[2]=1;
					position[0]=0;
					position[1]=0;
					position[3]=0;
					elementi[0]=1;
					elementi[1]=0;
				}
				if(e.getKeyCode()==52) {
					position[3]=1;
					position[0]=0;
					position[1]=0;
					position[2]=0;
					elementi[0]=1;
					elementi[1]=0;
					
				}
				if(e.getKeyCode()==88) {
					position[0]=0;
					position[1]=0;
					position[2]=0;
					position[3]=0;
					elementi[0]=0;
					elementi[1]=1;
					
				}
				
				else if(e.getKeyCode()==10) {
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
									if(matrix[(int)(k-x)/45][(int)(h-y)/45] == " " &&  (matrix[(int)(k-x)/45][(int)(h-y)/45] != "player" || matrix[(int)(k-x)/45][(int)(h-y)/45] != "enemyAI"))
									{
										matrix[(int)(k-x)/45][(int)(h-y)/45]="blocco";
										for(int i=0;i<10;i++) {
											SolidBrick s= new SolidBrick(myWorld,(int)((k-x)/45)*10+i,(int)((h-y)/45)*10);
										
										
											//editor.addElement(((int)(k-x)/45)*10+i , ((int)(h-y)/45)*10+i ,s);
										
											editor.addSolidBrick(s);
										}
									}
								}

								else if(position[1]==1)
								{
									if(matrix[(int)(k-x)/45][(int)(h-y)/45] == " " &&  (matrix[(int)(k-x)/45][(int)(h-y)/45] != "player" || matrix[(int)(k-x)/45][(int)(h-y)/45] != "enemyAI"))
									{
										matrix[(int)(k-x)/45][(int)(h-y)/45]="sfera";
										MovableObject m= new MovableObject(myWorld,((int)(k-x)/45)*10,((int)(h-y)/45)*10+10-1,Directions.STOP, 0);
										//editor.addElement(((int)(k-x)/45)*10+i , ((int)(h-y)/45)*10+i,m);
										editor.addMovableObject(m);
									}
									
								}

								else if(position[2]==1)
								{
									if(matrix[(int)(k-x)/45][(int)(h-y)/45] == " " &&  (matrix[(int)(k-x)/45][(int)(h-y)/45] != "player" || matrix[(int)(k-x)/45][(int)(h-y)/45] != "enemyAI"))
									{
										matrix[(int)(k-x)/45][(int)(h-y)/45]="nemico";
										Enemy en= new Enemy(myWorld,(int)(k-x)/45*10,(int)(h-y)/45*10+10-1,Directions.STOP, 0);
										//editor.addElement(((int)(k-x)/45)* 10+i , ((int)(h-y)/45)* 10+i,en);
										editor.addEnemy(en);
										
									}
								}

								else if(position[3]==1)
								{
									if(matrix[(int)(k-x)/45][(int)(h-y)/45] == " " &&  (matrix[(int)(k-x)/45][(int)(h-y)/45] != "player" || matrix[(int)(k-x)/45][(int)(h-y)/45] != "enemyAI"))
									{
										matrix[(int)(k-x)/45][(int)(h-y)/45]="scala";
										for(int i=0;i<10;i++) {
										Stairs st= new Stairs(myWorld,(int)(k-x)/45*10,(int)(h-y)/45*10+i);
										//editor.addElement(((int)(k-x)/45)* 10+i , ((int)(h-y)/45)* 10+i,st);
										editor.addStair(st);
										}
									}
								}
								
							points.add(p);
								
							
						}
						
						if(elementi[1]==1)
						{
							if(matrix[(int)(k-x)/45][(int)(h-y)/45]=="blocco")
								matrix[(int)(k-x)/45][(int)(h-y)/45]= " ";
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


	
	
