package com.blax.dist.datastore.redis;

import lombok.AllArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@ConditionalOnProperty(prefix = "com.blax.redis", name = "enable", havingValue = "true")
@DependsOn("redisTemplate")
@RequestMapping(value = "redis")
public class RedisController {

    private RedissonClient redisClient;

    @RequestMapping(value = "map/{key}", method = RequestMethod.GET)
    Map getMap(@PathVariable String key) {
        return redisClient.getMap(key);
    }

    @RequestMapping(value = "map/{key}", method = RequestMethod.POST)
    Map setMap(@PathVariable String key, @RequestBody Map values) {
        RMap<Object, Object> map = redisClient.getMap(key);
        map.putAll(values);
        return map;
    }
}
