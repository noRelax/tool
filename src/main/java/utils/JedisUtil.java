package utils;

import java.lang.reflect.Proxy;

import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	private static JedisCommands jedisCommands;
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		JedisPoolProxy proxy = new JedisPoolProxy(jedisPool);
		jedisCommands = (JedisCommands) Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
				new Class[] { JedisCommands.class }, proxy);
	}

	public static  void set(String key, String value) {
		jedisCommands.set(key, value);
	}
}
