package multithreading.coordinatingThreads;

public class Demo {

	public static void main(String[] args) {
		Shipping shipping = new Shipping();
		
		Supplier supplier = new Supplier(shipping);
		Thread A = new Thread(supplier);
		
		Shopper shopper = new Shopper(shipping);
		Thread B = new Thread(shopper);
		
		A.start();
		B.start();
	}
	
}
