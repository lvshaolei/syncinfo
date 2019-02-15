package syncinfo.jdbc;

import redis.clients.jedis.Jedis;

public class RedisConnection {
	private static final String host = "39.36.8.43";
	public static Jedis jedis = new Jedis(host);
}
