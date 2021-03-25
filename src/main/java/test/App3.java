package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import zjrd.TimeUtils;

public class App3 {
	static ReentrantLock lock = new ReentrantLock();
	static Condition condition = lock.newCondition();
	static List<String> list = new ArrayList<>();

	public static void main(String[] args) throws InterruptedException {
		Thread put = new Thread(new Put());
		Thread get = new Thread(new Get());
		put.start();
		get.start();
	}

	public static class Put implements Runnable {
		@Override
		public void run() {
			while (true) {

				try {
					Thread.sleep(1000L);
					System.out.println("开始生产");
					lock.lock();
					while (Objects.equals(list.size(), 1)) {
						condition.await();
					}
					list.add(0, "goods");
					System.out.println("生产完成"+list);
					condition.signal();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}

	public static class Get implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000L);
					System.out.println("开始消费");
					lock.lock();
					while (Objects.equals(list.size(), 0)) {
						condition.await();
					}
					list.clear();
					System.out.println("消费完成");
					condition.signal();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}

			}
		}
	}

	public static class Item implements Delayed {
		private long value;
		private String time;

		public Item(String time) {
			this.value = TimeUtils.getTimestamp(TimeUtils.parse(time));
			this.time = time;
		}

		public long getValue() {
			return value;
		}

		public void setValue(long value) {
			this.value = value;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		@Override
		public int compareTo(Delayed o) {
			Item other = (Item) o;
			long tmp = this.value - other.getValue();
			if (Objects.equals(tmp, 0)) {
				return 0;
			} else if (tmp > 0) {
				return 1;
			} else {
				return -1;
			}
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return this.value - System.currentTimeMillis();
		}

		@Override
		public String toString() {
			return "Item [time=" + time + "]";
		}

	}
}
