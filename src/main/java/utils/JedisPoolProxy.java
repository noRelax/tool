package utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JedisPoolProxy implements InvocationHandler {


    private JedisPool jedisPool;

    public JedisPoolProxy(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try (Jedis jedis = jedisPool.getResource()) {
            long t1 = System.currentTimeMillis();
            Object result = method.invoke(jedis, args);
            long t2 = System.currentTimeMillis();
            //LOG.info("Redis Method: {}, Time Costs: {}ms,", method.getName(), t2 - t1);
            return result;
        }
    }
}
