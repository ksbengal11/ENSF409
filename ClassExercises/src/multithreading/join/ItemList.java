package multithreading.join;

public class ItemList implements Runnable {
	String [] items = {"Item 1: Milk", "Item 2: Banana", "Item 3: Apple", "Item 4: Orange"};
	
	@Override
	public void run() {
		try{
			for(int i = 0; i < items.length; i++){
				Thread.sleep(1000);
				System.out.printf("\n%s: %s%n", Thread.currentThread().getName(), items[i]);
			}
		}catch(InterruptedException e){
			System.out.println("\n" + Thread.currentThread().getName() + " Interrupted");
		}
	}
}
