package main.core;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

public class MyLevelsPanel extends JPanel{
	
	Image liv1;
	Image liv2;
	Image liv3;
	Toolkit tk = Toolkit.getDefaultToolkit();
	int [] position;
	MyLevelsThread mt = new MyLevelsThread(this);
	MyFrame game;
	URL urlLevelsBackground = this.getClass().getResource("LEVELS.png");
	Image levelsBG = tk.getImage(urlLevelsBackground);
	
	Dimension screenSize = tk.getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	Image levelsBackground = levelsBG.getScaledInstance(width, height,Image.SCALE_DEFAULT);
	
	
	public MyLevelsPanel() {
		super();
		
		this.setEnabled(true);
		
		position = new int [3];
		position[0]=1; position[1]=0; position[2]=0;
		initLEVELS();
		initListener();
		
	}
	
	
	public void initLEVELS() {
		URL urlLiv1 = this.getClass().getResource("LIV1.png");
		URL urlLiv2 = this.getClass().getResource("LIV2.png");
		URL urlLiv3 = this.getClass().getResource("LIV3.png");
		liv1 = tk.getImage(urlLiv1);
		liv2 = tk.getImage(urlLiv2);
		liv3 = tk.getImage(urlLiv3);
		mt.start();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(levelsBackground, 0, 0, this);
		
		if(position[0]==1)
		{
			g.drawImage(liv2, 313, 226, this);
			g.drawImage(liv3, 651, 101, this);
		}
		if(position[1]==1)
		{
			g.drawImage(liv1, 1230, 335, this);
			g.drawImage(liv3, 651, 101, this);
		}
		if(position[2]==1)
		{
			g.drawImage(liv1, 1230, 335, this);
			g.drawImage(liv2, 313, 226, this);
		}
		
	}
	
	
public void initListener() {
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==40) {
					if(position[0]==1) {
						position[0]=0;
						position[1]=1;
						System.out.println("A");
					}
					else if(position[1]==1) {
						position[1]=0;
						position[2]=1;
						System.out.println("B");
					}
				}
				else if(e.getKeyCode()==38) {
					
					if(position[2]==1) {
						position[2]=0;
						position[1]=1;
					}
					else if(position[1]==1) {
						position[1]=0;
						position[0]=1;
					}
				}
				else if(e.getKeyCode()==10) {
					if(position[0]==1) {
						game = new MyFrame();
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					
						
				}
			}	
		});		
}
}
