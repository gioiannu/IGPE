package main.core;

import javax.swing.JFrame;

public class MyLivMultFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyLivMultPanel mlmp = new MyLivMultPanel();
	

	
	public MyLivMultFrame() {
		super();
		this.setTitle("LivMult");
		this.setSize(1024, 514);
		this.setContentPane(mlmp);
		mlmp.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.setResizable(false);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
	
	public static void main(final String[] args) {
		MyLivMultFrame livMult = new MyLivMultFrame();

	}
}
