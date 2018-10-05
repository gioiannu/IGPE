package main.core;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame{
	public static void main(final String[] args){
		MyFrame f=new MyFrame();
		f.setVisible(true);
	}
	
	public MyFrame() {
		super();
		MyPanel panel=new MyPanel();
		this.setSize(1000, 700);
		this.setContentPane(panel);
		
	}
	

}
