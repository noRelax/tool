package main.java.hutool.thread;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.Future;

/**
 * @author wusong
 * @create 2021-04-21 11:04
 **/
public class ThreadTest {
    public static void main(String[] args) throws Exception {
        Future<Object> future = ThreadUtil.execAsync(() -> "null");
        Console.log("1111111");
        System.out.println(future.get());
        ThreadUtil.execAsync(() -> System.out.println("runnable"));

        StackTraceElement[] stackTrace = ThreadUtil.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.getClassName());
        }

        //高并发测试
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(100, () -> {
            // 测试的逻辑内容
            long delay = RandomUtil.randomLong(100, 1000);
            ThreadUtil.sleep(delay);
            Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
        });

        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());

    }
}
