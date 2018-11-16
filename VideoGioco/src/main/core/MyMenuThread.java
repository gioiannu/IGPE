package main.core;

import main.core.images.MyMenuPanel;

public class MyMenuThread extends Thread {
	
	MyMenuPanel mmp;
	
	public MyMenuThread(MyMenuPanel mmp) {
		this.mmp=mmp;
	}
	
	public void run() {
		while(true) {
		mmp.repaint();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
}