package main.core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyMenuFrame extends JFrame {
	
	/**
	 * 
	 */
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int height=(int) screenSize.getHeight();
	public static int width=(int) screenSize.getWidth();
	
	private static final long serialVersionUID = 1L;
	MyMenuPanel mf ;
	

	
	public MyMenuFrame() {
		super();
		mf = new MyMenuPanel();
		this.setTitle("Menu");
		this.setSize((width), (height));
		this.setContentPane(mf);
		mf.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.setResizable(false);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
	
	public static void main(final String[] args) {
		MyMenuFrame menu = new MyMenuFrame();

	}
}
