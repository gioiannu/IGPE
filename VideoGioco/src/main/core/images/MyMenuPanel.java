package main.core.images;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import main.core.MyEditorFrame;
import main.core.MyHTPFrame;
import main.core.MyLivMultFrame;
import main.core.MyMenuThread;
import main.managers.audio.AudioManager;

public class MyMenuPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image gioca;
	Image editor;
	Image how;
	Image esci;
	AudioManager audio_manager;
	Toolkit tk = Toolkit.getDefaultToolkit();
	int [] position;
	MyMenuThread mt ;
	MyLivMultFrame lvMult;
	MyEditorFrame myEdit;
	MyHTPFrame mhtp;
	URL urlMenuBackground = this.getClass().getResource("MENU.png");
	Image menuBG = tk.getImage(urlMenuBackground);
	
	Dimension screenSize = tk.getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	Image menuBackground = menuBG.getScaledInstance(width, height,1);
	
	
	
	
	public MyMenuPanel() {
		super();
		
		this.setEnabled(true);
		
		position = new int [4];
		position[0]=1; position[1]=0; position[2]=0; position[3]=0;
		audio_manager= new AudioManager();
		mt = new MyMenuThread(this);
		initMENU();
		initListener();
		
		
		
	}
	
	public void initMENU() {
		URL urlGioca = this.getClass().getResource("GIOCA.png");
		URL urlEditor = this.getClass().getResource("EDITOR .png");
		URL urlHow = this.getClass().getResource("COME_GIOCARE.png");
		URL urlEsci = this.getClass().getResource("ESCI_DAL_GIOCO.png");
		gioca = tk.getImage(urlGioca);
		editor = tk.getImage(urlEditor);
		how = tk.getImage(urlHow);
		esci = tk.getImage(urlEsci);
		gioca= gioca.getScaledInstance(width*410/1920,height*195/1080, 1);
		editor= editor.getScaledInstance(width*413/1920,height*186/1080, 1);
		how= how.getScaledInstance(width*697/1920,height*188/1080, 1);
		esci= esci.getScaledInstance(width*293/1920,height*175/1080, 1);
		
		try {
			
			audio_manager.initMusic();
			audio_manager.playMenu();
		
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		mt.start();
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(menuBackground, 0, 0, this);
		if(position[0]==1)
		g.drawImage(gioca, (923*width/1920), (373*height/1080), this);
		if(position[1]==1)
		g.drawImage(editor, (922*width/1920), (546*height/1080), this);
		if(position[2]==1)
		g.drawImage(how, (781*width/1920), (710*height/1080), this);
		if(position[3]==1)
		g.drawImage(esci, (987*width/1920), (868*height/1080), this);
		
		
		
		}
	
	
public void initListener() {
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==40) {
					audio_manager.playButton19();
					if(position[0]==1) {
						position[0]=0;
						position[1]=1;
					}
					else if(position[1]==1) {
						position[1]=0;
						position[2]=1;
					}
					else if(position[2]==1) {
						position[2]=0;
						position[3]=1;
					}
				}
				else if(e.getKeyCode()==38) {
					audio_manager.playButton19();
					if(position[3]==1) {
						position[3]=0;
						position[2]=1;
					}
					else if(position[2]==1) {
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
						lvMult = new MyLivMultFrame();
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					else if(position[1]==1)
					{
						myEdit = new MyEditorFrame();
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					else if(position[2]==1)
					{
						mhtp = new MyHTPFrame();
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					
					else if(position[3]==1)
					{
						
						 JComponent comp = (JComponent) e.getSource();
						  Window win = SwingUtilities.getWindowAncestor(comp);
						  win.dispose();
					}
					
						
				}
			}	
		});		
	}
}
