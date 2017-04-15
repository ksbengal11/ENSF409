package multithreading.join;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.format("\n%s: %s%n",Thread.currentThread().getName(), " Started ... ");
		long startTime = System.currentTimeMillis();
		
		Thread A = new Thread (new ItemList());
		Thread B = new Thread (new ItemList());
		
		A.start();
		B.start();
		
		while(A.isAlive()){
			System.out.format("\n%s: %s%n", Thread.currentThread().getName(), " is waiting ... ");
			A.join(1000);
			if(((System.currentTimeMillis() - startTime) > 2000) && A.isAlive()){
				System.out.format("%s: %s%n", Thread.currentThread().getName(), " interrupting" + A.getName());
				A.interrupt();
				break;
			}
		}
		
		Thread.sleep(100);
		System.out.format("\n%s: %s%n", Thread.currentThread().getName(), " Ended ... ");
		System.out.println("The number of active thread are: " + Thread.activeCount());

	}

}
