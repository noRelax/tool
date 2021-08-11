package main.java.hutool.aop;

import cn.hutool.aop.aspects.SimpleAspect;
import cn.hutool.core.lang.Console;

import java.lang.reflect.Method;

/**
 * @author wusong
 * @create 2021-05-07 17:12
 * 日志拦截器
 **/
public class LogAspect extends SimpleAspect {
    @Override
    public boolean before(Object target, Method method, Object[] args) {
        Console.log("请求方法:{}", method.getName());
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        return super.after(target, method, args, returnVal);
    }
}
