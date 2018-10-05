package main.core;

import main.managers.GameManager;

public class ThreadGame extends Thread {
	private GameManager GM;
	//private MyPanel panel;
	
	public ThreadGame(GameManager g/*, MyPanel panel*/) {
		GM=g;
	//	this.panel=panel;
	}
	@Override
	public void run() {
		while(!GM.gameOver()) {
			GM.update();
			GM.printWorld();
			//panel.repaint();
			try {
				sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
