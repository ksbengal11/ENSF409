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
            socketIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            socketOut = new PrintWriter(s.getOutputStream(), true);
            player_id = new_player_id;
        }
        
        public void closeConnection() throws IOException{
        	System.out.printf("Player %d disconnected\n", player_id);
        	socketIn.close();
        	socketOut.close();
        	if(player_id == 1) p1 = null;
        	else p2 = null;
        }
        
        public void run (){
            try {
                String line = "R";
                while (true){
                	socketIn.readLine();
                	if(line == null || line != "R") {
                		closeConnection();
                		break;
                	}          	
                	socketOut.println("R");
                	if(player_id == 1){
                        socketOut.println("Waiting for player 2...");
                        socketOut.append("P");
                	} else {
                		System.out.println("Starting the game");
                		Game newGame = new Game();
                		newGame.start(p1.socketIn, p1.socketOut, socketIn, socketOut);
                		break;
                	}
                }
            }catch(IOException e){
            	
            }
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
    public static void main (String[] argv) {
        Server server = new Server();
        server.listen();
    }
    public void listen () {
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
                        PrintWriter reject = new PrintWriter(serverConnect.getOutputStream(), true);
                        reject.println("Sorry, this server is full.");
                        serverConnect.close();
                        reject.close();
                        System.out.println("Rejected a player");
                    } catch (IOException e) {
                        System.err.println("Error rejecting connection");
                        System.err.println(e.getStackTrace());
                    }
                }
            } catch (IOException e) {
                System.err.println("Error establishing new client");
                System.err.println(e.getStackTrace());
            }
        }
    }
}

