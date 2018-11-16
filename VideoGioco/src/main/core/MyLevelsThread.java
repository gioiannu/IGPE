package main.core;

import main.core.images.MyLevelsPanel;

public class MyLevelsThread extends Thread{
	MyLevelsPanel mlp;
	public MyLevelsThread(MyLevelsPanel mlp) {
		this.mlp=mlp;
	}
	
	public void run() {
		while(true) {
		mlp.repaint();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
