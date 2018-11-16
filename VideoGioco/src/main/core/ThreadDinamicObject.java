package main.core;

import main.core.interfaces.Directions;
import main.managers.GameManager;
import main.managers.audio.AudioManager;

public class ThreadDinamicObject extends Thread{
	private GameManager GM;
	private AudioManager AM;
	//private MyPanel panel;
	

	public ThreadDinamicObject(GameManager g,AudioManager a) {
		GM=g;
		AM=a;
		//this.panel=panel;
	}
	@Override
	public void run() {
		while(!GM.gameOver()&& !GM.win()) {
			synchronized (this){
				
			if(!GM.pausa)
			{
					if(GM.connected==true||GM.getLevels()!=4) {
					GM.getPlayer().update();
					GM.getPlayer().setPlayerLastDir(GM.getPlayer().getDirection());
					if(GM.getLevels()!=4) {
						if(GM.getEai().collisione(GM.getPlayer().getProiettile()))
							AM.playHit();
						if(GM.getEai().collisionep(GM.getPlayer())&&(GM.getEai().getSpeed()!=0)) {
							if(GM.getPlayer().getLives()!=1)
								AM.playHit();
							else
							{
								AM.playGameOver();
								AM.stopMusic();
							}
							if(GM.getED())
								GM.startGame(GM.getLevels(),GM.getEDITOR(),GM.getPlayer().getLives()-1);
							else	
								GM.startGame(GM.getLevels(),GM.getPlayer().getLives()-1);
							continue;
						}
					}
						
					if(GM.getLevels()!=4) {
						GM.getEai().update();
						
						for(int i=0; i<GM.getEnemy().length; i++)
						{
							if(GM.getEnemy()[i]!= null) {
								if(GM.getEnemy()[i].collisione(GM.getPlayer().getProiettile()))
									if(GM.getPlayer().getLives()!=1)
										AM.playHit();
									else
									{
										AM.playGameOver();
										AM.stopMusic();
									}
								if(GM.getEnemy()[i].collisionep(GM.getPlayer())&&(GM.getEnemy()[i].getSpeed()!=0)) {
									AM.playHit();
									if(GM.getED())
										GM.startGame(GM.getLevels(),GM.getEDITOR(),GM.getPlayer().getLives()-1);
									else	
										GM.startGame(GM.getLevels(),GM.getPlayer().getLives()-1);
									break;
								}
								GM.getEnemy()[i].update();
							}
							
							//gestire collisione player con nemico
							//gestire collisione proiettile con nemico
						}
					}
					else {
						GM.player2.update();
						GM.player2.setPlayerLastDir(GM.player2.getDirection());
						GM.collisioneproiettili();
						
					}
				}
			
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
