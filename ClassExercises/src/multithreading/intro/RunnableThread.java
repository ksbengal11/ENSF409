package multithreading.intro;

public class RunnableThread implements Runnable {
	private long sleepTime;
	private String name;
	
	public RunnableThread(String s, long sleepTime){
		this.sleepTime = sleepTime;
		this.name = s;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 5; i++){
			try{
				Thread.sleep(this.sleepTime);
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
			System.out.println(name + " started");
		}
	}

	public static void main(String[] args) {
		RunnableThread a = new RunnableThread("A", 1000);
		RunnableThread b = new RunnableThread("B", 1);
		RunnableThread c = new RunnableThread("C", 1);

		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		Thread t3 = new Thread(c);
		
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println("Threads A, B, and C have started");
	}

}
