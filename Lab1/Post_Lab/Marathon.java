import java.util.ArrayList;
import java.util.Scanner;

class Marathon {
  private int Fastest_time(ArrayList<Integer> times){
    int index_fastest = 0;
    for (int i = 0; i < times.size(); i++){
      if(times.get(index_fastest) > times.get(i))
        index_fastest = i;
    }
    return index_fastest;
  }

  public static void main(String [] args){
    // Define two array lists here to store the names and the running times
    ArrayList<Integer> times = new ArrayList<Integer>();
    ArrayList<String> names = new ArrayList<String>();

    // Read user input
    String sin;
    Scanner scan = new Scanner(System.in);
    while(true){
      System.out.println("Please enter the name of the participant");
      sin = scan.nextLine();
      if(sin.toUpperCase().equals("QUIT"))
        break;

      // Add name to the array list
      names.add(new String(sin));
      System.out.println("Please enter the running time of the participant");
      sin = scan.nextLine();

      // Add the running time to the array lists
      times.add(new Integer(sin));
    }
    // Call function findFastRunner and pass the running times array lists
    // Print the name of the fastest runner to the console
    Marathon runner = new Marathon();
    System.out.println("The fastest runner is : " +
    names.get(runner.Fastest_time(times)) + " with a time of " +
    times.get(runner.Fastest_time(times)) + "s");
  }
}
