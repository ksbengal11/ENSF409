/**
 * 
 */
package inlab4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private PrintWriter socketOutput;
    private Socket sock;
    private BufferedReader socketIn;
    private BufferedReader stdIn;
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
    public void communicate () {
        String line = "";
        String response = "";
        try {
            while(line != "QUIT") {
                line = socketIn.readLine();
                if (line != null){
                	switch (line.substring(0, 1)) {
                    	case "R": 
                    		System.out.println("Server on localhost:9090 ready and connected...");
                    		System.out.println("WELCOME TO THE GAME");
                    		break;
                    	case "I": 
                    		System.out.println(line.substring(2, line.length()));
                    		while(response == "") {
                    			response = stdIn.readLine();
                    		}
                    		socketOutput.flush();
                    		socketOutput.println(response);
                    		//if(response != "") System.out.println("Name entered successfully");
                    		//else System.out.println("Unsuccessful");
                    		response = "";
                    		break;
                    	case "Q":
                    		line = "QUIT";
                    		break;
                    	case "P":
                    		System.out.println(line.substring(2, line.length()));
                    		break;
                	}
                } else {
                    line = "QUIT";
                }
            }
            socketIn.close();
            socketOutput.close();
            stdIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main (String[] argv) {
        Client aClient = new Client("localhost", 9090);
        aClient.communicate();
    }
}