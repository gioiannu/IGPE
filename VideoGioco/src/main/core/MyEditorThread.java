package main.core;

public class MyEditorThread extends Thread{
	MyEditorPanel me;
	public MyEditorThread(MyEditorPanel me) {
		this.me=me;
	}
	
	public void run() {
		while(true) {
		me.repaint();
		try {
			sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
