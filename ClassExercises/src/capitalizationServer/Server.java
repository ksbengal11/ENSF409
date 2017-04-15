package capitalizationServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private Socket aSocket;
	private BufferedReader in;
	private PrintWriter out;
	
	public Server(){
		try{
			serverSocket = new ServerSocket(9090);
			aSocket = serverSocket.accept();
			in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			out = new PrintWriter(aSocket.getOutputStream(), true);
			System.out.println("Server running on localhost:9090 ....");
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public void communicate(){
		String line = "";
		String response = "";
		while(true){
			try {
				line = in.readLine();
			} catch (IOException e) {
				System.out.print(e.getMessage());
			}
			if(line.equalsIgnoreCase("QUIT")) break;
			else{
				response = line.toUpperCase();
				out.println(response);
			}
		}
		try{
			System.out.println("Server session closing. Goodbye ... ");
			out.close();
			in.close();
			aSocket.close();
			serverSocket.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		Server aServer = new Server();
		aServer.communicate();

	}

}
