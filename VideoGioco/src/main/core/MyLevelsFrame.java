package main.core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyLevelsFrame  extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int height=(int) screenSize.getHeight();
	public static int width=(int) screenSize.getWidth();
	
	MyLevelsPanel ml;

	
	public MyLevelsFrame() {
		super();
		ml = new MyLevelsPanel();
		this.setTitle("Levels");
		this.setSize(width, height);
		this.setContentPane(ml);
		ml.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.setResizable(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
	
	/*public static void main(final String[] args) {
		MyLevelsFrame lvs = new MyLevelsFrame();
	}*/
}
