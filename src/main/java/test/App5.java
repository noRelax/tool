package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class App5 {
	ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		App5 app = new App5();

		Thread t1 = new Thread(() -> {
			app.lockTest();
		});
		Thread t2 = new Thread(() -> {
			app.lockTest();
		});
		t1.setName("第一个线程");
		t2.setName("第二个线程");

		t1.start();
		pause(1);

		t2.start();
	}

	public synchronized void test() {
		System.out.println(Thread.currentThread().getName() + "进入线程");
		for (long i = 0; i < 500000000; i++) {
			for (long j = 0; j < 50; j++) {

			}
		}
		System.out.println(Thread.currentThread().getName() + "离开线程");
	}

	public void test2() {
		try {
			lock.lock();
			lock.lock();
		} finally {
			lock.unlock();
			lock.unlock();
		}
		
	}
	
	public void lockTest() {
		try {
			lock.lock();
//			lock.tryLock(45, TimeUnit.SECONDS);
			System.out.println(Thread.currentThread().getName() + "得到锁");
			for (long i = 0; i < 500000; i++) {
				for (long j = 0; j < 65; j++) {

				}
			}
			System.out.println(Thread.currentThread().getName() + "离开线程");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void pause(int seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
