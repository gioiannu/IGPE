package main.core;

import javax.swing.JFrame;

public class MyLevelsFrame  extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyLevelsPanel ml = new MyLevelsPanel();

	
	public MyLevelsFrame() {
		super();
		this.setTitle("Levels");
		this.setSize(1024, 524);
		this.setContentPane(ml);
		ml.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.setResizable(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
	
	public static void main(final String[] args) {
		MyLevelsFrame lvs = new MyLevelsFrame();
	}
}
