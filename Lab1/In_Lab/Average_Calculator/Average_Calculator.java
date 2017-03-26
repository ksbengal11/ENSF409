public class Average_Calculator {
  /*
  * @ Author: Karan Bengali
  *           Naveed Kawsar
  */
  public static void main(String [] args){
    double sum = 0.0;
    int i = 0;
    System.out.print("The " + args.length + " numbers are : ");
    for (i = 0; i < args.length; i++){
      sum += Double.parseDouble(args[i]); // Convert args[i] to a double
      System.out.print(args[i] + " ");
    }
    System.out.println("\nAnd their average is : " + sum/(i++));
    return;
  }
}
