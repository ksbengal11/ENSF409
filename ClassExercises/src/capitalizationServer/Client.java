package capitalizationServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private BufferedReader stdIn;
	private Socket aSocket;
	
	public Client(String serverName, int portNumber){
		try {
			aSocket = new Socket(serverName, portNumber);
			socketOut = new PrintWriter(aSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void communicate(){
		String line = "";
		String response = "";
		while(true){
			System.out.println("Please enter a word");
			try{
				line = stdIn.readLine();
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
			
			if(line.equalsIgnoreCase("QUIT")) {
				System.out.println("Goodbye ... ");
				socketOut.println("QUIT");
				break;
			}
			
			try{
				socketOut.println(line);
				response = socketIn.readLine();
				System.out.println(response);
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
		try{
			socketOut.close();
			socketIn.close();
			stdIn.close();
			aSocket.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		Client aClient = new Client("localhost", 9090);
		aClient.communicate();
		
	}

}
