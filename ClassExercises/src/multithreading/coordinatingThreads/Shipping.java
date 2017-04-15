package multithreading.coordinatingThreads;

public class Shipping {
	private String item;
	private boolean empty = true;
	
	public synchronized String get() throws InterruptedException {
		while(empty){ // Shopper waits till empty
			wait();
		}
		empty = true;
		notifyAll();
		return item;
	}
	
	public synchronized void set(String item) throws InterruptedException {
		while(!empty){ // Shopper waits if full
			wait();
		}
		empty = false;
		this.item = item;
		notifyAll();
	}
}
