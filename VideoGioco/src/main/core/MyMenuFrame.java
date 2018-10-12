package main.core;

import javax.swing.JFrame;

public class MyMenuFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyMenuPanel mf = new MyMenuPanel();
	

	
	public MyMenuFrame() {
		super();
		this.setTitle("Menu");
		this.setSize(1024, 514);
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
