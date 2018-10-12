package main.core;

import javax.swing.JFrame;

public class MyFrame extends JFrame{

	MyPanel panel=new MyPanel();
	
	public MyFrame() {
		super();
		init();
	}
	public void init() {
		this.setSize(1920, 1080);
		this.setContentPane(panel);
		this.setVisible(true);	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		panel.setFocusable(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public static void main(final String[] args){
		MyFrame f=new MyFrame();
	}
	

}
