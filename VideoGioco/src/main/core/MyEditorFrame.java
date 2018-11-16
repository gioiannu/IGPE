package main.core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.core.images.MyEditorPanel;

public class MyEditorFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int height=(int) screenSize.getHeight();
	public static int width=(int) screenSize.getWidth();
	
	MyEditorPanel me ;
	

	
	public MyEditorFrame() {
		super();
		me= new MyEditorPanel();
		this.setTitle("Editor");
		this.setSize(width, height);
		this.setContentPane(me);
		me.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.setResizable(false);	
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
	
	/*public static void main(final String[] args) {
		MyEditorFrame menu = new MyEditorFrame();

	}*/
}
