package main.core;

import main.managers.GameManager;

public class ThreadDinamicObject extends Thread{
	private GameManager GM;
	//private MyPanel panel;
	
	public ThreadDinamicObject(GameManager g/*, MyPanel panel*/) {
		GM=g;
		//this.panel=panel;
	}
	@Override
	public void run() {
		while(!GM.gameOver()) {
			synchronized (this){
			GM.getPlayer().update();
			GM.getPlayer().setPlayerLastDir(GM.getPlayer().getDirection());
			
			GM.getEai().injuring(GM.getPlayer().getProiettile());
			GM.getEai().update();
			
			for(int i=0; i<GM.getEnemy().length; i++)
			{
				if(GM.getEnemy()[i]!= null) {
					GM.getEnemy()[i].injuring(GM.getPlayer().getProiettile());
					GM.getEnemy()[i].update();
				}
				
				//gestire collisione player con nemico
				//gestire collisione proiettile con nemico
			}
			}
			//panel.repaint();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
