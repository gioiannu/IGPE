package main.core;
import main.core.interfaces.Directions;
import main.managers.GameManager;

public class ThreadPlayer extends Thread{
	private GameManager GM;
	//private MyPanel panel;
	
	public ThreadPlayer(GameManager g/*, MyPanel panel*/) {
		GM=g;
		//this.panel=panel;
	}
	@Override
	public void run() {
		while(!GM.gameOver()) {
			GM.getPlayer().update();
			GM.getPlayer().setPlayerLastDir(GM.getPlayer().getDirection());
			//GM.getPlayer().setDirection(Directions.STOP);

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
