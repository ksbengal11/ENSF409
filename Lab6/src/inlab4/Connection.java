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
 * @author Karan
 *
 */
public class Connection extends Server implements Runnable {
    PrintWriter sOut;
    BufferedReader sIn;
    Socket sock;
    int player_id;
    public Connection(){
    	
    }
    Connection (Socket s, int new_player_id) throws IOException {
        sock = s;
        sIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        sOut = new PrintWriter(s.getOutputStream(), true);
        player_id = new_player_id;
    }
    public void closeConnection() throws IOException{
    	System.out.printf("Player %d disconnected\n", player_id);
    	sIn.close();
    	sOut.close();
    	if(player_id == 1) p1 = null;
    	else p2 = null;
    }
    public void run () {
        try {
            String line = "R";
            while (true){
            	sIn.readLine();
            	if(line == null) closeConnection();
            	else if(line != "R") break;
            	
            	sOut.println("R");
            	if(player_id == 1){
                    sOut.println("P Waiting for player 2...");
            	} else {
            		//Game theGame = new Game();
            		System.out.println("Starting the game");
            		/*try {
                    	//theGame.start(p1.sIn, p1.sOut, sIn, sOut);
                		System.out.println("Starting the game");
                	} catch (IOException e) {
                    	sIn.close();
                    	sOut.close();
                    	p1.sIn.close();
                    	p1.sOut.close();
                    	p1 = null;
                    	p2 = null;
                	}*/
            		break;
            	}
            }
        }catch(IOException e){
        	
        }
    }
}