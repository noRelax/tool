package test;

import java.util.concurrent.locks.ReentrantLock;

public class App1 {
	private final static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) throws Exception {
		lock.lock();
		
		new Thread(()->{
			System.out.println("begin");
			lock.lock();
			System.out.println("end");
		}).start();
		
		Thread.sleep(10000L);
		lock.unlock();
	}

}
