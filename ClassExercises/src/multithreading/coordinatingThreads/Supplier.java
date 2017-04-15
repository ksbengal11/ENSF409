package multithreading.coordinatingThreads;

import java.util.Random;

public class Supplier implements Runnable {
	private Shipping shipping;
	
	public Supplier(Shipping shipping){
		this.shipping = shipping;
	}
	
	@Override
	public void run() {
		String itemList [] = {"IBM Laptop", "Samsung Galaxy S5", "Power Adapter", "Music CD"};
		for(int i = 0; i < itemList.length; i++){
			try{
				shipping.set(itemList[i]);	
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.format("Item recieved from supplier %s%n", itemList[i]);
			try{
				Thread.sleep(new Random().nextInt(7000));
			}catch(InterruptedException e1){
				e1.printStackTrace();
			}
		}
		try{
			shipping.set("None");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
