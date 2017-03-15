/** 
 * Started by M. Moussavi
 * March 2015
 * Completed by: STUDENT(S) NAME
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {
    
    private ObjectInputStream input;
    
    /**
     *  opens an ObjectInputStream using a FileInputStream
     */
    
    private void readObjectsFromFile(String name)
    {
        MusicRecord record =null;
        
        try
        {
            input = new ObjectInputStream(new FileInputStream( name ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file." );
        }
        
        /* The following loop is supposed to use readObject method of of
         * ObjectInputSteam to read a MusicRecord object from a binary file that
         * contains several reords.
         * Loop should terminate when an EOFException is thrown.
         */
        
//        try
//        {
            while ( true )
            {
               try{ 
                // TO BE COMPLETED BY THE STUDENTS
            	record = (MusicRecord) input.readObject();
            	System.out.printf("%-10d%-12s%-12s%10.2f\n", record.getYear(), record.getSingerName(), record.getSongName(), record.getPurchasePrice());
               }catch(EOFException e){
            	   System.err.println("Error ....");
            	   break;
               } 
               catch ( IOException ioException )
               {
               	System.err.println( "Error opening file." );
               	ioException.printStackTrace();
               	break;
               }
               catch (ClassNotFoundException e) {
				e.printStackTrace();
               }
           
            }   // END OF WHILE
     //   }
        // ADD NECESSARY catch CLAUSES HERE


    }           // END OF METHOD 
    
    
    public static void main(String [] args)
    {
        ReadRecord d = new ReadRecord();
        d.readObjectsFromFile("mySongs.ser");
    }
}