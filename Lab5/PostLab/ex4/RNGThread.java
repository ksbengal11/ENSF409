package ex4;
import java.util.Random;
/**
 * Public class RNGThread will generate a random number between 0 and 100
 * @author Karan Bengali
 * @version 1.0
 * @since February 17, 2017
 */
public class RNGThread implements Runnable{
	/**
	 * Private variable r is an object of type Random
	 */
	private Random r = new Random();
	/**
	 * Variable sum stores the total sum of the random numbers
	 */
	volatile public static int sum = 0;
	/**
	 * Variable random_number stores a random number
	 */
	private int random_number = 0;
	/**
	 * This method will run the GNR method
	 */
	public void run(){
		try {
			GNR();
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method will add a random value to the total sum
	 */
	synchronized private void GNR(){
		random_number = r.nextInt(100)+1; 
		sum += random_number;
		// FOR DEBUGGING PURPOSES ONLY
		System.out.printf(random_number + " ");
	}
	/**
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Runnable[] r = new Runnable[5];
		for (int i = 0; i < 5; i++){
			r[i] = new RNGThread();
		}
		
		Thread [] t = new Thread [5];
		for (int i = 0; i < 5; i++){
			t[i] = new Thread(r[i]);
			t[i].start();
		}
		for (int i = 0; i < 5; i++){
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("\nThe total sum of our random numbers is: " + sum);
	}

}
