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
	
	public void communicate() throws IOException{
		StringBuffer read = null;
		while(true){
			read = new StringBuffer(in.readLine());
			if(read.toString().equals("QUIT")) {
				break;
			}
			if(isPalindrome(read.toString())) {
				out.println(read.toString() + " is a palindrome.");
			}
			else {
				out.println(read.toString() + " is not a palindrome.");
			}
		}
		out.println("Closing connection now, Goodbye!");
		in.close();
		out.close();
	}
	
	public static void main (String [] args) throws IOException {
		Server s = new Server();
		s.communicate();
	}
}