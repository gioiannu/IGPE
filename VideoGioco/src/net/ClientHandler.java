package net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import main.managers.GameManager;

public class ClientHandler extends Thread{

	ArrayList<Socket>socketList;
	Socket c;
	BufferedReader in;
	PrintWriter out;
	boolean connected;
	int i;
	
	public ClientHandler(ArrayList<Socket>socketList,Socket c, int i)
	{
		this.socketList=socketList;
		this.c=c;
		connected=false;
		this.i=i;
	}
	
	@Override
	public void run()
	{
		try{
			while(true)
			{
			  	if(connected==false){
					if(socketList.size()==2)
					{
						for(Socket e : socketList)
						{
							
							 out = new PrintWriter(e.getOutputStream());
							
							 if(!e.equals(c)){
									out.println("connected");
									out.flush();
								}
							
								
						}
						connected=true;
						
						}
					}
					
				
				
			  	
				in =new BufferedReader(new InputStreamReader(c.getInputStream()));
				
				String s = in.readLine().trim();
				for(Socket e : socketList)
				{
					PrintWriter outp = new PrintWriter(e.getOutputStream());
					if(!e.equals(c)){
						outp.println(s);
						outp.flush();
					}
						
				}
			}
			
				
			}
		catch(Exception e)
		{
			
			
			try {
				c.close();
				in.close();
				out.close();
				
				for(Socket g: socketList)
				{
					g.close();
				}
			} catch (IOException e1) {
				
				
			}
			
		}
		
		finally{
			
			
			try {
				c.close();
				in.close();
				out.close();
				
			} catch (IOException e) {
				
			}
		}
	}
}
