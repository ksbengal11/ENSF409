package ex4;
import java.util.Random;
/**
 * Public class RNGThread will generate a random number between 0 and 100
 * @author Karan Bengali
 * @version 2.0
 * @since February 28, 2017
 */
public class RNGThread implements Runnable{
	public static int rn1;
	public static int rn2;
	public static int rn3;
	public static int rn4;
	public static int rn5;
	/**
	 * Array for storing random numbers
	 */
	public static int[] rn_array = new int[5];
	/**
	 * Private variable r is an object of type Random
	 */
	private Random r = new Random();
	/**
	 * Default constructor, stores a random number in specified index
	 * of the random number array
	 * @param i index of the random number array
	 */
	RNGThread(int i){
		switch(i){
		case 0:
			rn1 = r.nextInt(100)+1;
			break;
		case 1:
			rn2 = r.nextInt(100)+1;
			break;
		case 2:
			rn3 = r.nextInt(100)+1;
			break;
		case 3:
			rn4 = r.nextInt(100)+1;
			break;
		case 4:
			rn5 = r.nextInt(100)+1;
			break;
		}
		//rn_array[i] = r.nextInt(100)+1;
	}
	/**
	 * This method will run the GNR method
	 */
	public void run(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws InterruptedException {
		Runnable[] r = new Runnable[5];
		for (int i = 0; i < 5; i++){
			r[i] = new RNGThread(i);
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
		System.out.println(rn1 + " " + rn2 + " " + rn3 + " " + rn4 + " " + rn5 + " " );
		int total = rn1 + rn2 + rn3 + rn4 + rn5;
		/*for (int i = 0; i < 5; i++){
			System.out.print(rn_array[i] + " ");
			total += rn_array[i];
		}*/
		//System.out.println("\nThe sum of our array is : " + total);	
		System.out.println("The sum of our array is : " + total);	
	}
}
