package test;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Barrier {
	static CyclicBarrier barrier;

	public static void main(String[] args) {
		barrier = new CyclicBarrier(3, new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("-------------");
			}

		});
		new Thread(new MyWorder()).start();
		new Thread(new MyWorder()).start();
		new Thread(new MyWorder()).start();
	}

	public static class MyWorder implements Runnable {

		@Override
		public void run() {
			while (true) {

				int i = new Random().nextInt(5000000);
				while (i-- > 0) {

				}
				if (Objects.equals("Thread-2", Thread.currentThread().getName())) {
					try {
						Thread.sleep(3000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				try {
					System.out.println(Thread.currentThread().getName() + ":处理完成");
					barrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
