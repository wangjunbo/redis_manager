package com.maple.init;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.maple.util.redis.Redis;
import com.maple.util.redis.RedisCluster;

@ConfigurationProperties(prefix="redis")
@Component
public class RedisConfiguration {
	
	public static int cluster = 1;
	public static String hostAndPort;
	public static String password;
	public static String redisCluster;
	public static boolean isCluster = false;
	
	
	public int getCluster() {
		System.out.println("getCluster");
		return cluster;
	}

	public void setCluster(int cluster) {
		System.out.println("setCluster");
		RedisConfiguration.cluster = cluster;
		if(cluster == 1){
			isCluster = true;
		}else{
			isCluster = false;
		}
	}

	public String getHostAndPort() {
		System.out.println("getHostAndPort");
		return hostAndPort;
	}
	

	public String getRedisCluster() {
		System.out.println("getRedisCluster");
		return redisCluster;
	}

	public void setRedisCluster(String redisCluster) {
		System.out.println("setRedisCluster");
		RedisConfiguration.redisCluster = redisCluster;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		RedisConfiguration.password = password;
	}

	public void setHostAndPort(String hostAndPort)  {
		System.out.println("setHostAndPort");
		RedisConfiguration.hostAndPort = hostAndPort;
	}
	
	
}
