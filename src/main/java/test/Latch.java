package test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Latch {
	static CountDownLatch countDownLatch;

	public static void main(String[] args) throws InterruptedException {
		countDownLatch = new CountDownLatch(3);
		new Thread(new MyWorder()).start();
		new Thread(new MyWorder()).start();
		new Thread(new MyWorder()).start();
		countDownLatch.await();
		System.out.println("主线程完成");
	}

	public static class MyWorder implements Runnable {
		@Override
		public void run() {
			int i = new Random().nextInt(10);
			try {
				Thread.sleep(i * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "完成");
			countDownLatch.countDown();
		}
	}
}
