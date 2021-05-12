package main.java.hutool.aop;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import cn.hutool.core.lang.Console;

/**
 * @author wusong
 * @create 2021-03-18 14:15
 **/
public class Cat implements Animal {
    @Override
    public void eat() {
        Console.log("猫吃鱼");
    }

    public static void main(String[] args) {
        Cat cat = ProxyUtil.proxy(new Cat(), TimeIntervalAspect.class);
        cat.eat();
    }
}
