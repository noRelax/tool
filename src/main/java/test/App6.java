package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class App6 {
	static int size = 1;
	static int i = 0;
	static CyclicBarrier barrier = new CyclicBarrier(size);
	static List<Integer> tmpList = new ArrayList<>(1);

	public static void main(String[] args) throws InterruptedException {
		List<Thread> list = new ArrayList<>();
		for (int j = 0; j < size; j++) {
			Thread thread = new Thread(new MyThread());
			list.add(thread);
		}
		list.parallelStream().forEach(x -> {
			try {
				x.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		TimeUnit.SECONDS.sleep(5);
		System.out.println(tmpList.get(0));
	}

	static class MyThread implements Runnable {

		@Override
		public void run() {
			try {
				barrier.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "开始执行" + new Date());
			try {
				Integer tmp = tmpList.get(0);
				if (tmp == null) {
					tmp = 0;
				}
				tmp += 1;
				tmpList.set(0, tmp);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}
}
