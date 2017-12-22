package com.maple.redis;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maple.init.RedisConfiguration;
import com.maple.util.redis.Redis;
import com.maple.util.redis.RedisCluster;
import com.maple.util.redis.SortedSet;

import redis.clients.jedis.Tuple;

@Controller
@RequestMapping("redis")
public class RedisController {
	
    
	private static int valuesLength = 100;
	public SortedSet sortedSet = new SortedSet(500, false);

	public Set<String> keys(String pattern) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getInstance().keys(pattern);
			} else {
				return Redis.getInstance().keys(pattern);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long count(String key){
		try {
			String type = type(key);
			if (RedisConfiguration.isCluster) {
				if("hash".equals(type)){
					return RedisCluster.getJedisCluster().hlen(key);
				}else if("list".equals(type)){
					return RedisCluster.getJedisCluster().llen(key);
				}else if("set".equals(type)){
					return RedisCluster.getJedisCluster().scard(key);
				}else if("zset".equals(type)){
					return RedisCluster.getJedisCluster().zcard(key);
				}
			} else {
				if("hash".equals(type)){
					return Redis.getInstance().hlen(key);
				}else if("list".equals(type)){
					return Redis.getInstance().llen(key);
				}else if("set".equals(type)){
					return Redis.getInstance().scard(key);
				}else if("zset".equals(type)){
					return Redis.getInstance().zcard(key);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1L;
	}
	
	public String type(String key) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().type(key);
			} else {
				return Redis.getInstance().type(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String get(String key) {
		try {
			if (RedisConfiguration.isCluster) {
				
				return RedisCluster.getJedisCluster().get(key);
			} else {
				
				return Redis.getInstance().get(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void del(String key) {
		try {
			if (RedisConfiguration.isCluster) {
				 RedisCluster.getJedisCluster().del(key);
			} else {
				 Redis.getInstance().del(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long zcard(String key) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().zcard(key);
			} else {
				return Redis.getInstance().zcard(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Map<String, String> hgetAll(String key) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().hgetAll(key);
			} else {
				return Redis.getInstance().hgetAll(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public Long hlen(String key) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().hlen(key);
			} else {
				return Redis.getInstance().hlen(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}	
	
	public List<String> lrange(final String key, final long start, final long end) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().lrange(key, start, end);
			} else {
				return Redis.getInstance().lrange(key, start, end);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public long llen(final String key) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().llen(key);
			} else {
				return Redis.getInstance().llen(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}	
	
	public Set<Tuple> zrangeByScoreWithScores( String key, long minn, long max,int offset, final int count) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().zrangeByScoreWithScores(key, 0, Long.MAX_VALUE, 0, valuesLength);
			} else {
				return Redis.getInstance().zrangeByScoreWithScores(key, 0, Long.MAX_VALUE, 0, valuesLength);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public List<String> srandmember(final String key, final int count) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().srandmember(key, valuesLength);
			} else {
				return Redis.getInstance().srandmember(key, valuesLength);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public long scard(final String key) {
		try {
			if (RedisConfiguration.isCluster) {
				return RedisCluster.getJedisCluster().scard(key);
			} else {
				return Redis.getInstance().scard(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}	
	
	public void set(final String key, final String value) {
		try {
			if (RedisConfiguration.isCluster) {
				RedisCluster.getJedisCluster().set(key,value);
			} else {
				Redis.getInstance().set(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void hset(final String key, final String field, final String value) {
		try {
			if (RedisConfiguration.isCluster) {
				 RedisCluster.getJedisCluster().hset(key, field, value);
			} else {
				 Redis.getInstance().hset(key, field, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void lpush(final String key, final String value) {
		try {
			if (RedisConfiguration.isCluster) {
				RedisCluster.getJedisCluster().lpush(key, value);
			} else {
				Redis.getInstance().lpush(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sadd(final String key, final String value) {
		try {
			if (RedisConfiguration.isCluster) {
				RedisCluster.getJedisCluster().sadd(key, value);
			} else {
				Redis.getInstance().sadd(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void zadd(final String key,double score, final String value) {
		try {
			if (RedisConfiguration.isCluster) {
				RedisCluster.getJedisCluster().zadd(key, score , value);
			} else {
				Redis.getInstance().zadd(key, score , value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	@ResponseBody
//	@RequestMapping("bigKeys")
//	public List<Map> bigKeys(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		SortedSet set = new SortedSet(100, false);
//		
//			try {
//				System.out.println(new Date().toLocaleString());
//				try {
//					
//					for (int i = 0; i < 26; i++) {
//						String pattern = (char)('a'+i)+"*";
//						LogUtil.info(pattern);
//						Set<String> keys = keys(pattern);
//						for(String k : keys ){
//							long count = count(k);
//							LogUtil.info(k+"\t"+count);
//							set.add(k, count);
//						}
//					}
//					Thread.currentThread().sleep(5000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			sortedSet = set;
//			
//			return sortedSet.getList();
//	}
	
	
	@ResponseBody
	@RequestMapping("keys")
	public Set<String> keys(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pattern = request.getParameter("pattern");
		System.out.println("pattern "+ pattern);
		Set<String> keys = new HashSet<String>();
		if (pattern != null && pattern.trim().length() > 0) {
			keys = keys(pattern);
		}
		return keys;
	}

	@ResponseBody
	@RequestMapping("value")
	public Map value(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String key = request.getParameter("key");

		String type = type(key);
		System.out.println(type);
		Map map = new HashMap();
		map.put("type", type);
		if (type.equals("hash")) {
			Map<String, String> value = hgetAll(key);
			map.put("count", hlen(key));
			map.put("value", value);
		} else if (type.equals("string")) {
			String value = get(key);
			map.put("count", 1);
			map.put("value", value);
		} else if (type.equals("zset")) {
			Long count = zcard(key);
			map.put("count", count);
			Set<Tuple> set = zrangeByScoreWithScores(key, 0, Long.MAX_VALUE, 0, valuesLength);
			Map value = new LinkedHashMap();
			for (Tuple t : set) {
				String k = new String(t.getElement());
				double d = t.getScore();
				value.put(k, d);
			}
			map.put("value", value);
		} else if (type.equals("list")) {
			List<String> value = lrange(key, 0, valuesLength);
			map.put("count", llen(key));
			map.put("value", value);
		} else if (type.equals("set")) {
			List<String> value = srandmember(key, valuesLength);
			map.put("count", scard(key));
			map.put("value", value);
		}

		return map;
	}

	@ResponseBody
	@RequestMapping("console")
	public Object console(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String line = request.getParameter("line");
		Map map = new HashMap();

		StringTokenizer st = new StringTokenizer(line);
		if (st.hasMoreTokens()) {
			String command = st.nextToken().toLowerCase();
			System.out.println(command);
			if (command.equals("get")) {
				String key = st.nextToken();
				System.out.println(get(key));
				map.put("value", get(key));
				map.put("type", "string");
			} else if (command.startsWith("del")) {
				String pattern = st.nextToken();
				Set<String> keys = keys(pattern);
				for (String k : keys) {
					del(k);
				}
				map.put("type", "void");
			} else if (command.startsWith("h")) {
				hash(map, command, st);
			} else if (command.startsWith("z")) {
				sortedSet(map, command, st);
			}
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("del")
	public String del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String key = request.getParameter("key");
		
		System.out.println("del "+key);
	
		Set<String> keys = keys(key);
		for (String k : keys) {
			del(k);
		}
				
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("action_type");
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		String score = request.getParameter("score");
		String field = request.getParameter("field");
		
		System.out.println(type);
		
		if("string".equals(type)){
			set(key, value);
     	}else if("set".equals(type)){
     		sadd(key, value);
     	}else if("list".equals(type)){
     		lpush(key, value);
     	}else if("zset".equals(type)){
     		zadd(key, Double.valueOf(score), value);
     	}else if("hash".equals(type)){
     		 hset(key, field, value);
     	}  
		
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping("info")
	public List<String> info(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		TreeSet<String> infos = new TreeSet<String>();
		List<String> infoList = new ArrayList<String>();
			
		if (RedisConfiguration.isCluster) {
			infos = RedisCluster.getInstance().info();
			
//			for(String infomation : infos){
//				
//				String[] array = infomation.split("\\n");
//				
//				for(String line : array){
//					System.out.println(count++ + "\t"+line);
//					infoList.add(line);
//				}
//				
//			}
		} else {
			String info = Redis.getInstance().info();
			infos.add(info);
		}
		
		for(String infomation : infos){
			String[] array = infomation.split("\\n");
			
			for(String line : array){
//				System.out.println(count++ + "\t"+line);
				infoList.add(line);
			}
		}
		
		return infoList;
	}


	public void hash(Map map, String command, StringTokenizer st) throws Exception {
		if (command.equals("hgetall")) {
			String key = st.nextToken();
			Map<String, String> value = hgetAll(key);
			map.put("value", value);
			map.put("type", "hash");
		} else if (command.equals("hset")) {
			String key = st.nextToken();
			hset(key, st.nextToken(), st.nextToken());
			map.put("value", "session");
			map.put("type", "hash");
		}
	}

	public void sortedSet(Map map, String command, StringTokenizer st) throws Exception {
		if (command.equals("hgetall")) {
			String key = st.nextToken();
			Map<String, String> value = hgetAll(key);
			map.put("value", value);
			map.put("type", "hash");
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println((char)('a'+1)+"*");
	}

}
