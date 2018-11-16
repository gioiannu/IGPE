package main.core;

import main.core.images.MyHTPPanel;

public class MyHTPThread extends Thread{
	MyHTPPanel mhtp;
	public MyHTPThread(MyHTPPanel mhtp) {
		this.mhtp=mhtp;
	}
	
	public void run() {
		while(true) {
		mhtp.repaint();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
