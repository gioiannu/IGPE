package main.core;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.managers.audio.AudioManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class MyLevelsPanel extends JPanel{
	
	Image liv1;
	Image liv2;
	Image liv3;
	Toolkit tk = Toolkit.getDefaultToolkit();
	AudioManager audio_manager;
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
		audio_manager= new AudioManager();
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
		
		liv1= liv1.getScaledInstance(width*412/1920,height*436/1080, 1);
		liv2= liv2.getScaledInstance(width*388/1920,height*390/1080, 1);
		liv3= liv3.getScaledInstance(width*382/1920,height*394/1080, 1);
		
		mt.start();
		
		try {
			
			audio_manager.initMusic();
			audio_manager.playMenu();
		
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(levelsBackground, 0, 0, this);
		
		if(position[0]==1)
		{
			g.drawImage(liv2, 313*width/1920, 226*height/1080, this);
			g.drawImage(liv3, 651*width/1920, 101*height/1080, this);
		}
		if(position[1]==1)
		{
			g.drawImage(liv1, 1230*width/1920, 335*height/1080, this);
			g.drawImage(liv3, 651*width/1920, 101*height/1080, this);
		}
		if(position[2]==1)
		{
			g.drawImage(liv1, 1230*width/1920, 335*height/1080, this);
			g.drawImage(liv2, 313*width/1920, 226*height/1080, this);
		}
		
	}
	
	
public void initListener() {
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==40) {
					audio_manager.playButton19();
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
					audio_manager.playButton19();
					
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
					audio_manager.playButton3();
					audio_manager.stopMenu();
					if(position[0]==1) {
						
						game = new MyFrame(1);
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					else if(position[1]==1) {
						
						game = new MyFrame(2);
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					else {
						
						game = new MyFrame(3);
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
						
						
					
						
				}
			}	
		});		
}
}
