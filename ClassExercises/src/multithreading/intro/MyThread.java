package multithreading.intro;

public class MyThread extends Thread {
	private long sleepTime;
	
	MyThread(String s, long time){
		super(s);
		this.sleepTime = time;
		System.out.println("Name: " + getName() + " sleep: " + this.sleepTime);
	}
	
	public void run(){
		for(int i = 0; i < 5; i++){
			try{
				sleep(this.sleepTime);
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
			System.out.println(getName() + " Started");
		}
	}
	
	public static void main(String [] args){
		MyThread A = new MyThread("A", 1000);
		MyThread B = new MyThread("B", 1);
		MyThread C = new MyThread("C", 1);
		
		A.start();
		B.start();
		C.start();
	}

}
