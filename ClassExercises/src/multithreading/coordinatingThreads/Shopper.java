package multithreading.coordinatingThreads;

import java.util.Random;

public class Shopper implements Runnable {
	private Shipping shipping;
	
	public Shopper(Shipping shipping){
		this.shipping = shipping;
	}
	
	@Override
	public void run() {
		while(true){
			String item = null;
			try{
				item = shipping.get();
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
			if(item == "None") break;
			System.out.format("Item picked up by shopper: %s%n", item);
			try{
				Thread.sleep(new Random().nextInt(7000));
			}catch(InterruptedException e1){
				System.out.println(e1.getMessage());
			}
		}
	}
}
