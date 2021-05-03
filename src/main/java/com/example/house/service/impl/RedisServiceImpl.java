package com.example.house.service.impl;

import com.example.house.constants.RedisConstants;
import com.example.house.service.RedisService;
import io.lettuce.core.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setString(String key, String value, long time) throws RedisException {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
    }

    @Override
    public void setString(String key, String value) throws RedisException {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getString(String key) throws RedisException {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteValue(String key) throws RedisException {
        redisTemplate.delete(key);
    }

    @Override
    public void setZset(String key, Integer value, double score) {
        redisTemplate.opsForZSet().add(key,value,score);
    }

    @Override
    public void setAndIncreaseZset(String key, Integer value, int increaseNum) {
        redisTemplate.opsForZSet().incrementScore(key,value,increaseNum);
    }

    @Override
    public void setAndIncreaseZset(String key, Integer value, int increaseNum, int limit) {
        redisTemplate.opsForZSet().incrementScore(key,value,increaseNum);
        long len=redisTemplate.opsForZSet().size(key);
        if(len>limit+RedisConstants.HOT_HOUSE_BUFFER){
            long deleteNum=len-limit;
            //删除的值包括deleteNum-1
            redisTemplate.opsForZSet().removeRange(key,0,deleteNum-1);
        }
    }

    @Override
    public Map<Integer,Integer> getZsetMap(String key, long start, long end) {
        Set<Integer> set=redisTemplate.opsForZSet().reverseRange(key,start,end);
        Map<Integer,Integer> map=new HashMap<>();
        for(Integer integer:set){
            map.put(integer,redisTemplate.opsForZSet().score(key,integer).intValue());
            // System.out.println(redisTemplate.opsForZSet().score(key,integer)+" "+integer);
        }
        return map;
    }
}
