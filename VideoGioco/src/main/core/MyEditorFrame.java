package main.core;

import javax.swing.JFrame;

public class MyEditorFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyEditorPanel me = new MyEditorPanel();
	

	
	public MyEditorFrame() {
		super();
		this.setTitle("Editor");
		this.setSize(1024, 514);
		this.setContentPane(me);
		me.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.setResizable(false);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
	
	public static void main(final String[] args) {
		MyEditorFrame menu = new MyEditorFrame();

	}
}
