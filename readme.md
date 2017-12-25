`redis_manager` can be used as single node pattern or as cluster pattern. It's very convenient to use.

`redis_manager` gives your application a redis web administration interface that allows you to easily manipulate the most commonly used data types for redis (strings, hashes, lists, sets, sorted sets).

`redis_manager` allows you to easily monitor redis system info, including memory usage, cpu usage, and so on.

configuration file is `application.properties`

## download.   
   You can [download](http://www.hohode.com/resource/redis/redis_manager.zip) it and run it immediately.  
   
   
## screenshot
<p align="center"><img src="http://www.hohode.com/resource/redis/rm1.png" alt="redis-manager"></p>

## cluster pattern
```
server.port=8080
redis.cluster=1
redis.redisCluster=10.95.10.29:16380,10.95.10.29:16382,10.95.10.29:16384
```

## single pattern
```
server.port=8080
redis.cluster=0
redis.hostAndPort=10.95.10.35:6379
```

## compile 
```
mvn package -Dmaven.test.skip=true
```
## run
```
java -jar redis_manager-1.jar  --spring.config.location=classpath:/path/to/application.properties
```

## browse
http://127.0.0.1:8080/


## License
`redis_manager` is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT)
