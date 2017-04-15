package multithreading.synchronization;

public class Account {
	private double balance;
	
	public Account(double initialAmount){
		this.balance =initialAmount;
	}
	
	public void deposit(double amt){
		this.balance += amt;
	}
	
	// remove synchronized to observe the effect on the balance 
	synchronized public void withdraw(double amt){
		if(this.balance >= amt){
			try{
				Thread.sleep(4000);
			}catch(InterruptedException e){
				System.out.println("Interrupted" + e.getMessage());
			}
			this.balance -= amt;
		}
	}
	
	public double currentBalance(){
		return this.balance;
	}

}
