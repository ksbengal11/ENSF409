
import java.io.*;

public class Sums {
    public static void sum(BufferedReader in) throws IOException{
      int s, nextInt;
      s = 0;
      System.out.println("Please input the sequence of integers to sum, terminated by a 0");
      nextInt = 0;

      do{
        try {
          nextInt = Integer.parseInt(in.readLine());
          s = s + nextInt;
        }catch (NumberFormatException e){
          System.out.println("Invalid number. Please re-enter");
        }catch(IOException e){
          System.out.println(e.getMessage());
        }
      }while(nextInt!=0);

      System.out.println("The sum is " + s);
    }
    public static void main(String[] arg) throws IOException{
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      //"in" will receive data from the standard input stream
      String c = "";
      System.out.println("Do you wish to calculate a sum? (y/n)");

      try{
        c = in.readLine();
      }catch (IOException e){
        System.out.println(e.getMessage());
      }

      //Read next datum in input. A string "y" or "n" is expected
      while (!c.equals("y") && !c.equals("n")) {
        System.out.println("Please answer y or n");
        try {
          c = in.readLine();
        }catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
      while (c.equals("y")) {
        sum(in);
        System.out.println("Do you wish to calculate another sum? (y/n)");
        try{
          c = in.readLine();
        }catch(IOException e){
          System.out.println(e.getMessage());
        }
        while (!c.equals("y") && !c.equals("n")) {
          System.out.println("Please answer y or n");
          try{
            c = in.readLine();
          }catch (IOException e){
            System.out.println(e.getMessage());
          }
        }
      }
      System.out.println("Goodbye");
    }
  }
