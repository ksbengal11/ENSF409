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

/**
 * @author Karan Bengali
 * @author Naveed Kawsar
 * @version 1.0
 * @since March 15, 2017
 * 
 * Class responsible for communication between client and tick-tac-toe
 * game server. Contains a subclass communication which runs threads 
 * that open streams for communication and handle requests.
 *
 */
public class Server implements Constants {
    /**
     * A server socket
     */
    private ServerSocket serverSocket;
    /**
     * Connection instance for player one
     */
    protected Connection p1 = null;    
    /**
     * Connection instance for player one
     */
    protected Connection p2 = null;
    /**
     * @author Karan Bengali
     * @author Naveed Kawsar
     * 
     * Class that handles communication between the server and client.
     * Includes a run module that start a new game once two clients
     * are connected to the server. 
     *
     */
    private class Connection implements Runnable {
        /**
         * Variable to write in the output stream
         */
        private PrintWriter socketOut;
        /**
         * Variable to read the input stream
         */
        private BufferedReader socketIn;
        /**
         * Socket to communicate with the client
         */
        private Socket aSock;
        /**
         * Variable to identify player
         */
        private int player_id;
        
        /**
         * Default constructor for class connection. Initializes input
         * and output communication streams.
         * @param s a socket to communicate with the client
         * @param new_player_id player identification
         * @throws IOException
         */
        Connection (Socket s, int new_player_id) throws IOException {
            aSock = s;
            socketIn = new BufferedReader(new InputStreamReader(aSock.getInputStream()));
            socketOut = new PrintWriter(aSock.getOutputStream(), true);
            player_id = new_player_id;
        }
        /**
         * Run method for runnable threads which start a new game when
         * two clients are connected to the server.
         */
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
    /**
     * Closes socket communication streams if a client disconnects 
     * unexpectedly from the server. 
     * @param id player identification
     * @throws IOException
     */
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
    /**
     * Default constructor for the class Server. Initializes the server
     * socket on the port 9090.
     */
    public Server () {
        try {
            serverSocket = new ServerSocket(9090);
            System.out.println("Tick-Tac-Toe server running on localhost:9090 ....");
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    /**
     * Method communicate which creates a new instance of class communication
     * if variables for player 1 and 2 are null. Runs each instance in a 
     * thread. Rejects connection if more than two players try to join.
     */
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
    /**
     * Entry point for the class Server
     * @param argv command line input
     */
    public static void main (String[] argv) {
        Server server = new Server();
        server.communicate();
    }

}

