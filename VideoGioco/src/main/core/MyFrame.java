package main.core;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import main.core.images.MyPanel;

public class MyFrame extends JFrame{

	MyPanel panel;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int height=(int) screenSize.getHeight();
	public static int width=(int) screenSize.getWidth();
	Editor editor;
	
	public MyFrame(int l) {
		super();
		init(l);
	}
	public MyFrame(Editor editor) {
		super();
		this.editor=editor;
		init2();
			
	}
	public void init(int l) {
		panel=new MyPanel(l);
		this.setSize(width,height);
		this.setContentPane(panel);
		this.setVisible(true);	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		panel.setFocusable(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void init2() {
		panel=new MyPanel(editor);
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
