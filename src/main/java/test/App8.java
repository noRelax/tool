package test;

import java.util.concurrent.atomic.AtomicInteger;

public class App8 {
	static int i = 0;
	static AtomicInteger value = new AtomicInteger(0);

	public static void main(String[] args) {
		for (int k = 0; k < 10; k++) {
			new Thread(new MyThread()).start();
		}
		System.out.println(i);
		System.out.println(value);
	}

	static class MyThread implements Runnable {

		@Override
		public void run() {
			for (int j = 0; j < 1000; j++) {
				i++;
				value.getAndIncrement();
			}

		}

	}

}
