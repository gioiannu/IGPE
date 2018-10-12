package main.core;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class MyMenuPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image gioca;
	Image editor;
	Image how;
	Image esci;
	Toolkit tk = Toolkit.getDefaultToolkit();
	int [] position;
	MyMenuThread mt = new MyMenuThread(this);
	MyLivMultFrame lvMult;
	MyEditorFrame myEdit;
	URL urlMenuBackground = this.getClass().getResource("MENU.png");
	Image menuBG = tk.getImage(urlMenuBackground);
	
	Dimension screenSize = tk.getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	Image menuBackground = menuBG.getScaledInstance(width, height,Image.SCALE_DEFAULT);
	
	
	
	
	public MyMenuPanel() {
		super();
		
		this.setEnabled(true);
		
		position = new int [4];
		position[0]=1; position[1]=0; position[2]=0; position[3]=0;
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
		mt.start();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(menuBackground, 0, 0, this);
		if(position[0]==1)
		g.drawImage(gioca, 923, 373, this);
		if(position[1]==1)
		g.drawImage(editor, 922, 546, this);
		if(position[2]==1)
		g.drawImage(how, 781, 710, this);
		if(position[3]==1)
		g.drawImage(esci, 987, 868, this);
		
		
		
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
					else if(position[2]==1) {
						position[2]=0;
						position[3]=1;
						System.out.println("C");
					}
				}
				else if(e.getKeyCode()==38) {
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
					
						
				}
			}	
		});		
	}
}
