package main.core;
import main.managers.GameManager;

public class ThreadMovableObject extends Thread{
	private GameManager GM;
	//private MyPanel panel;
	private int fattore=10;
	
	public ThreadMovableObject(GameManager g/*, MyPanel panel*/) {
		GM=g;
		//this.panel=panel;
	}
	@Override
	public void run() {
		while(!GM.gameOver()) {
			for(int i=0;i<GM.getMovableObject().length; i++)
			{
				if(GM.getMovableObject()[i]!= null)
				{
					if(GM.getPlayer().collision(GM.getMovableObject()[i]))
						GM.getMovableObject()[i].falling();
					for(int j=0;j<GM.getMovableObject().length; j++)
					{
						if(j!=i && GM.getMovableObject()[i].collision(GM.getMovableObject()[j]))
						{
							GM.getMovableObject()[j].falling();
							//if(movableObjects[i].getY()<=21)
								
						}
					}
					if(GM.getMovableObject()[i].getY()<21*fattore)
						GM.getMovableObject()[i].update();
					try {
						sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

						
				}
			}
		}
			
			
			//panel.repaint();
			
	}
}
