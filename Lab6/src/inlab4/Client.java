/**
 * 
 */
package inlab4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class client which is responsible for handing client printing 
 * and input requests. It also maintains a communication stream with the
 * game server.
 * @author Karan Bengali
 * @author Naveed Kawsar
 * @version 1.0
 * @since March 15, 2017
 */
public class Client {
    /**
     * Variable to write in the output communication stream
     */
    private PrintWriter socketOutput;
    /**
     * A socket for communication
     */
    private Socket sock;
    /**
     * Variable to read the input communication stream
     */
    private BufferedReader socketIn;
    /**
     * Variable to read user input
     */
    private BufferedReader stdIn;
    /**
     * Default constructor for the class client. Initializes communication 
     * variables.
     * @param serverName name of the server
     * @param portNumber port number to communicate on
     */
    public Client (String serverName, int portNumber) {
        try {
            sock = new Socket(serverName, portNumber);
            socketIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            socketOutput = new PrintWriter(sock.getOutputStream(), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            socketOutput.println("R");
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    /**
     * Parse server for input and handle appropriate request.
     * Requests include:
     * 	R - Server Ready (client is connected to the server)
     * 	Q - Quit (Game over, shutdown communication streams)
     * 	I - Input Request (Read input from user)
     * 	P - Print Request (Write to the client output stream)
     */
    public void communicate () {
        String line = "";
        String response = "";
        boolean running = true;
        try {
            while(running) {
                line = socketIn.readLine();
                if (line != null){
                	switch (line.substring(0, 1)) {
                    	case "R": 
                    		System.out.println("Server on localhost:9090 ready and connected...");
                    		System.out.println("WELCOME TO THE GAME");
                    		break;
                    	case "I": 
                    		System.out.println(line.substring(5, line.length()));
                    		while(response == "") {
                    			response = stdIn.readLine();
                    		}
                    		socketOutput.flush();
                    		socketOutput.println(response);
                    		response = "";
                    		break;
                    	case "Q":
                    		running = false;
                    		break;
                    	case "P":
                    		System.out.println(line.substring(5, line.length()));
                    		break;
                	}
                } else {
                	running = false;
                }
            }
            socketIn.close();
            socketOutput.close();
            stdIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Entry point for the client class
     * @param argv command line arguments
     */
    public static void main (String[] argv) {
        Client aClient = new Client("localhost", 9090);
        aClient.communicate();
    }
}