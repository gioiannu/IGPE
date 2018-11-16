package main.core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.core.images.MyHTPPanel;

public class MyHTPFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int height=(int) screenSize.getHeight();
	public static int width=(int) screenSize.getWidth();
	
	MyHTPPanel mhtp ;
	

	
	public MyHTPFrame() {
		super();
		mhtp= new MyHTPPanel();
		this.setTitle("HTP");
		this.setSize(width, height);
		this.setContentPane(mhtp);
		mhtp.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.setResizable(false);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
	

}
