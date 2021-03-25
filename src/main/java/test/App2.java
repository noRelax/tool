package test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class App2 {
	static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	public static void main(String[] args) {
		new Thread(()->{rwl.readLock().lock();}) .start();
		
		rwl.readLock().lock();
		rwl.readLock().lock();
		rwl.readLock().lock();
		System.out.println(rwl.getReadHoldCount());
		System.out.println(rwl.getReadLockCount());

	}
}
