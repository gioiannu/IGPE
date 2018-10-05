package main.core;

import main.managers.GameManager;

public class ThreadEnemy extends Thread{
	private GameManager GM;
	//private MyPanel panel;
	
	public ThreadEnemy(GameManager g/*, MyPanel panel*/) {
		GM=g;
		//this.panel=panel;
	}
	@Override
	public void run() {
		while(!GM.gameOver()) {
			GM.getEai().update();
			
			for(int i=0; i<GM.getEnemy().length; i++)
			{
				if(GM.getEnemy()[i]!= null)
					GM.getEnemy()[i].update();
				
				//gestire collisione player con nemico
				//gestire collisione proiettile con nemico
			}
			//panel.repaint();
			try {
				sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
