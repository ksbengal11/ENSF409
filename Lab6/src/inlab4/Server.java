/**
 * 
 */
package inlab4;

import java.io.IOException;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Server implements Constants {
    private ServerSocket serverSocket;
    protected Connection p1 = null;
    protected Connection p2 = null;
    
    private class Connection implements Runnable {
        private PrintWriter socketOut;
        private BufferedReader socketIn;
        private Socket aSock;
        private int player_id;
        
        Connection (Socket s, int new_player_id) throws IOException {
            aSock = s;
            socketIn = new BufferedReader(new InputStreamReader(aSock.getInputStream()));
            socketOut = new PrintWriter(aSock.getOutputStream(), true);
            player_id = new_player_id;
        }        
        public void run (){
            try {
                String line = "";
                do{
                	line = socketIn.readLine();
                	if(line == null) closeConnection(player_id);
                	socketOut.println("R");
                	if(player_id == 2) {
                		System.out.println("Starting new game");
                		Game newGame = new Game();
                		newGame.startGame(p1.socketIn, p1.socketOut, p2.socketIn, p2.socketOut);
                		break;
                	}
                }while(line == "R");
            }catch(IOException e){
            	e.printStackTrace();
            }
        }
    }
    public void closeConnection(int id) throws IOException{
    	System.out.printf("Player %d disconnected\n", id);
    	if(id == 1){
        	p1.socketIn.close();
        	p1.socketOut.close();
        	p1 = null;
    	}
    	else{
        	p2.socketIn.close();
        	p2.socketOut.close();
        	p2 = null;
    	}
    }   
    public Server () {
        try {
            serverSocket = new ServerSocket(9090);
            System.out.println("Tick-Tac-Toe server running on localhost:9090 ....");
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    public void communicate () {
        while (true) {
            try {
                Socket serverConnect = serverSocket.accept();
                if (p1 == null) {
                    p1 = new Connection(serverConnect, 1);
                    System.out.println("Player 1 connected");
                    Thread t = new Thread(p1);
                    t.start();
                } else if (p2 == null) {
                    p2 = new Connection(serverConnect, 2);
                    System.out.println("Player 2 connected");
                    Thread t = new Thread(p2);
                    t.start();
                } else {
                    try {
                        serverConnect.close();
                        System.out.println("Rejected a player");
                    } catch (IOException e) {
                    	e.printStackTrace();
                    }
                }
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
    }
    public static void main (String[] argv) {
        Server server = new Server();
        server.communicate();
    }

}

