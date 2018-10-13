package main.core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyFrame extends JFrame{

	MyPanel panel;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int height=(int) screenSize.getHeight();
	public static int width=(int) screenSize.getWidth();
	
	public MyFrame() {
		super();
		init();
	}
	public void init() {
		panel=new MyPanel();
		this.setSize(width,height);
		this.setContentPane(panel);
		this.setVisible(true);	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		panel.setFocusable(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	/*public static void main(final String[] args){
		MyFrame f=new MyFrame();
	}*/
	

}
