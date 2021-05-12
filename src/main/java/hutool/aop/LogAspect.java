package main.java.hutool.aop;

import cn.hutool.aop.aspects.SimpleAspect;

import java.lang.reflect.Method;

/**
 * @author wusong
 * @create 2021-05-07 17:12
 * 日志拦截器
 **/
public class LogAspect extends SimpleAspect {
    @Override
    public boolean before(Object target, Method method, Object[] args) {

        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        return super.after(target, method, args, returnVal);
    }
}
