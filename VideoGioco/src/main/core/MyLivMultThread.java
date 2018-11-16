package main.core;

import main.core.images.MyLivMultPanel;

public class MyLivMultThread extends Thread{
	MyLivMultPanel mlmp;
	public MyLivMultThread(MyLivMultPanel mlmp) {
		this.mlmp=mlmp;
	}
	
	public void run() {
		while(true) {
		mlmp.repaint();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}