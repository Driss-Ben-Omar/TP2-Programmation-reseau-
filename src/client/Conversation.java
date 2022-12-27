package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Conversation extends Thread
{
	private Socket socket;
	private int number;
	private int goldNumber;
	private boolean fin;
	private String gagnant;
	
	public Conversation(Socket socket,int number,int goldNumber)
	{
		this.number=number;
		this.socket=socket;
		this.goldNumber=goldNumber;
	}
	
	@Override
	public void run() 
	{
		try 
		{
			InputStream is=socket.getInputStream();
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			
			PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
			
			
			String ip=socket.getRemoteSocketAddress().toString();
			pw.println("welcome client "+ip);
			System.out.println("Client "+ip+" is connected");
			pw.println("Enter the gold Number");
			
			while(true)
			{
				String request=br.readLine();
				System.out.println("le client "+ip+" a envoi une reponse");
				number=Integer.parseInt(request);
				if(fin==false)
				{
					if(number>goldNumber)
					{
						pw.println("votre nomber est superiure ");
					}
					else if(number<goldNumber)
					{
						pw.println("votre nombre est inferieur");
					}
					else
					{
						pw.println("Felecitatio tu va gagner");
						System.out.println("Congrats "+ip);
						gagnant=ip;
						fin=true;
					}
				}
				else
				{
					pw.println("The game is finish ,the winner is "+gagnant);
				}
				
				
			}
		} catch (IOException e) 
		{
			
		}
	}
}
