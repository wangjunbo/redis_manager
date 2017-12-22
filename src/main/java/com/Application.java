package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maple.init.RedisConfiguration;
import com.maple.util.redis.Redis;
import com.maple.util.redis.RedisCluster;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("started");
        init();
    }
    
    public static void init(){
    	
    	if(RedisConfiguration.hostAndPort != null){
    		String[] arr = RedisConfiguration.hostAndPort.split(":");
    		try {
    			System.out.println("init redis single node");
    			
//    		    if(RedisConfiguration.password != null && RedisConfiguration.password.trim().length() > 0){
//    		    	Redis.init( arr[0], RedisConfiguration.password.trim() , Integer.parseInt(arr[1]), 2, 3, 2, 10000);
//    		    }else{
    		    	Redis.init( arr[0], null , Integer.parseInt(arr[1]), 2, 3, 2, 10000);
//    		    }
    			
    		} catch (Exception e1) {
    			e1.printStackTrace();
    		} 
    	}
		
		if(RedisConfiguration.redisCluster != null){
			System.out.println("init redis cluster");
			try {
				RedisCluster.init(RedisConfiguration.redisCluster);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}
