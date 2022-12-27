package server;


import client.Conversation;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server extends Thread
{
	private boolean isActive=true;
	private int nombreClient=0;
	private int goldNumber;
	
	
	public static void main(String[] args) 
	{
		new Server().start();
	}
	
	@Override
	public void run() 
	{
		try 
		{
			ServerSocket serverSocket=new ServerSocket(1122);
			goldNumber=new Random().nextInt(1000);
			while(isActive)
			{
				System.out.println("le serveur est connecter ");
				Socket socket=serverSocket.accept();
				
				
				new Conversation(socket,++nombreClient,goldNumber).start();
				
				serverSocket.close();
			}
		} catch (IOException e) 
		{
			
		}
	}
}
