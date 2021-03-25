package test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class App4 {
	private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(2);

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
		
			try {
				System.out.println("开始读取");
				String s;
//				s= blockingQueue.take();
//				s=blockingQueue.poll();
				s=blockingQueue.peek();
				
				System.out.println("读取结果:"+s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				while(true) {
					System.out.println("-------------");
					blockingQueue.put("345");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		Thread t3 = new Thread(() -> {
			try {
				while(true) {
					System.out.println("==========");
					blockingQueue.put("345");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	t2.start();
	t3.start();
	}
}
