package multithreading.synchronization;

public class SynchronizedThread extends Thread {
	String name;
	Account account;
	
	public SynchronizedThread(String s, Account acc){
		this.name = s;
		this.account = acc;
	}
	
	public void run(){
		for (int i = 0; i < 5; i++){
			if(this.account.currentBalance() >= 100){
				this.account.withdraw(100);
			}
			System.out.println(getName() + " Started. Balance: "+ this.account.currentBalance()); 
		}
	}
	
	public static void main(String[] args) {
		Account acc = new Account(200);
		Thread A = new SynchronizedThread("A",acc);
		Thread B = new SynchronizedThread("B",acc);
		Thread C = new SynchronizedThread("C",acc);
		
		A.start();
		B.start();
		C.start();
		
		System.out.println("Threads A, B, and C have started without synchronize");

	}

}
