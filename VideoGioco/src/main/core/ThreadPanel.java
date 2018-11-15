package main.core;

import java.awt.Image;

import main.core.interfaces.Directions;
import main.managers.GameManager;

public class ThreadPanel extends Thread{
	private MyPanel p;
	private GameManager GM;
	public ThreadPanel(MyPanel p,GameManager GM) {
		this.p=p;
		this.GM=GM;
	}
	public void run() {
		while(true) {
			if(GM.connected==true||GM.getLevels()!=4) {
				p.repaint();
			}
			try {
				sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

