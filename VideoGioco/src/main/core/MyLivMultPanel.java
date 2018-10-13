package main.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyLivMultPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image liv;
	Image mult;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	int [] position;
	MyLivMultThread mt = new MyLivMultThread(this);
	MyLevelsFrame lv;
	URL urlLivMultBackground = this.getClass().getResource("LIVELLI_MULT.png");
	Image livMBG = tk.getImage(urlLivMultBackground);
	
	Dimension screenSize = tk.getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	Image livMBackground = livMBG.getScaledInstance(width, height,Image.SCALE_DEFAULT);
	
	
	public MyLivMultPanel() {
		super();
		
		this.setEnabled(true);
		
		position = new int [2];
		position[0]=1; position[1]=0;
		initLMB();
		initListener();
		
		
		
	}
	
	public void initLMB() {
		
		
		URL urlLiv = this.getClass().getResource("liv.png");
		URL urlMult = this.getClass().getResource("MULTIPLAYER.png");
		
		liv = tk.getImage(urlLiv);
		mult = tk.getImage(urlMult);
		
		liv=liv.getScaledInstance(width*538/1920,height*167/1080, 1);
		mult=mult.getScaledInstance(width*842/1920,height*186/1080, 1);
		
		mt.start();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(livMBackground, 0, 0, this);
		
		if(position[0]==1)
		g.drawImage(liv, 936*width/1920, 525*height/1080, this);
		
		if(position[1]==1)
		g.drawImage(mult, 934*width/1920, 683*height/1080, this);
		
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
				}
				else if(e.getKeyCode()==38) {
					if(position[1]==1) {
						position[1]=0;
						position[0]=1;
					}
				}
				else if(e.getKeyCode()==10) {
					if(position[0]==1) {
						lv = new MyLevelsFrame();
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					
						
				}
			}	
		});		
	}

}
