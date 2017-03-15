package inlab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	PrintWriter out;
	Socket aSocket;
	ServerSocket serverSocket;
	BufferedReader in;
	public Server() throws IOException {
		serverSocket = new ServerSocket(8099);
		System.out.println("Server is running ...");
		aSocket = serverSocket.accept();
		in = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
		out = new PrintWriter((aSocket.getOutputStream()), true);
	}
	public boolean isPalindrome(String s){
		int n = s.length();
		for(int i = 0; i < (n/2); ++i){
			if(s.charAt(i) != s.charAt(n-i-1)){
				return false;
			}
		}
		return true;
	}
	
	public void communicate(){
		String line = null;
		while (true){
			try{
				line = in.readLine();
				if(line.toUpperCase().equals("QUIT")) {
					line = "Good Bye!";
					out.println(line);
					break;
				}
				if(isPalindrome(line.toLowerCase())){
					line += " is a palindrome";
					out.println(line);
				}
				else {
					line += " is not a palindrome";
					out.println(line);
				}
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		try{
			in.close();
			out.close();
			serverSocket.close();
			aSocket.close();
		}catch(IOException e) {
			System.out.println("Unexpected exception: " + e.getMessage());
		}
	}
	
	public static void main (String [] args) throws IOException {
		Server s = new Server();
		s.communicate();
	}
}