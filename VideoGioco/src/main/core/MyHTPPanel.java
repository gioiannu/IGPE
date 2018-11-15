package main.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.managers.audio.AudioManager;

public class MyHTPPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	AudioManager audio_manager;
	MyHTPThread mt = new MyHTPThread(this);
	MyLevelsFrame lv;
	MyMenuFrame mmf;
	URL BG= this.getClass().getResource("HOWTOPLAY.png");
	
	Dimension screenSize = tk.getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	Image background= tk.getImage(BG).getScaledInstance(width,height,1);
	Image HTPBackground = background.getScaledInstance(width, height,1);
	
	public MyHTPPanel()
	{
		super();
		
		this.setEnabled(true);
		audio_manager= new AudioManager();
		initBG();
		initListener();
	}
	
	public void initBG()
	{
		try {
		
		audio_manager.initMusic();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mt.start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
	}
	
	public void initListener()
	{
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				
				if(e.getKeyCode()==10) 
				{	
					audio_manager.playButton3();
							lv = new MyLevelsFrame();
							 JComponent comp = (JComponent) e.getSource();
							  Window win = SwingUtilities.getWindowAncestor(comp);
							  win.dispose();
				}
				else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
				{
					audio_manager.playButton3();
					mmf= new MyMenuFrame();
					JComponent comp = (JComponent) e.getSource();
					  Window win = SwingUtilities.getWindowAncestor(comp);
					  win.dispose();
				}
			}
					
		});		
	}

}
