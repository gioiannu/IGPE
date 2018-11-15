package net;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import main.core.interfaces.Directions;
import main.managers.GameManager;


public class Client extends Thread {
	private GameManager game_manager;
	public Socket c;
	BufferedReader in;
	PrintWriter out;
	
	public Client(GameManager game_manager)
	{
		try
		{
			c=new Socket("192.168.137.45",8118);//ale 192.168.137.45 127.0.0.1
			in=new BufferedReader(new InputStreamReader(c.getInputStream()));
			out=new PrintWriter(c.getOutputStream());
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.game_manager=game_manager;
	}
	
	@Override
	public void run()
	{
		try{
			while(true)
			{
				String s=in.readLine().trim();
				
				if(s.equals("up"))
					game_manager.setplayer2(s);
				else if(s.equals("down"))
					game_manager.setplayer2(s);
				else if(s.equals("left"))
					game_manager.setplayer2(s);
				else if(s.equals("right"))
					game_manager.setplayer2(s);
				else if(s.equals("stop"))
					game_manager.setplayer2(s);
				else if(s.equals("shoot")) {
					game_manager.setplayer2(s);
							//shooting2????
				}
				else if(s.equals("shootof")) {
					game_manager.setplayer2(s);
							//shooting2????
				}
				else if(s.equals("connected")){
					game_manager.setConnected(true);
					PrintWriter pout = new PrintWriter(c.getOutputStream());
					pout.println(s);
					pout.flush();
				}

				sleep(10);
			}
		}
		
		catch(Exception e){
			
			try {
				c.close();
				in.close();
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		finally{
			try {
				c.close();
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public void sendDirection(String direction)
	{
		out.println(direction);
		out.flush();
	}
	
	public void sendShoot()
	{
		out.println("shoot");
		out.flush();
	}

	public void sendPosition(String string) {
		
		
		out.println(string);
		out.flush();
		
	}

	public void sendShootof() {
		out.println("shootof");
		out.flush();
		
	}
}
