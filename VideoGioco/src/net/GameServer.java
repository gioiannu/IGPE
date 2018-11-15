package net;



import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
	
	public static void main(String[] args) {
		GameServer server = new GameServer();
		server.initServer();
	}
	ServerSocket s;
	Socket incoming;
	ArrayList<Socket> socketList;
	int i =0;
	public void initServer()
	{
		try{
			socketList= new ArrayList<Socket>();
			s=new ServerSocket(8118);
			while(socketList.size()<2)
			{
				i++;
				incoming=s.accept();
				socketList.add(incoming);
				
				ClientHandler ch = new ClientHandler(socketList,incoming,i);
				ch.start();
				//System.out.println("CONNESSO");
				
				
				
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
}
