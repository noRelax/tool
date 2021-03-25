package job;

import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;

/**
 * @author wusong
 * @create 2021-03-25 11:42
 **/
public class TestJob {
    public void run() {
        Console.log("TestJob执行定时任务方法run()");
    }

    public void run2() {
        Console.log("TestJob执行定时任务方法run2()");
    }

    public static void main(String[] args) {
        CronUtil.start();
    }
}
