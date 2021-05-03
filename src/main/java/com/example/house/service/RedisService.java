package com.example.house.service;

import io.lettuce.core.RedisException;

import java.util.Map;
import java.util.Set;

public interface RedisService {

    void setString(String key, String value, long time) throws RedisException;
    void setString(String key, String value) throws RedisException;
    String getString(String key) throws RedisException;
    void deleteValue(String key) throws RedisException;

    void setZset(String key,Integer value,double score);
    void setAndIncreaseZset(String key,Integer value,int increaseNum);
    //把limit后面的数据删除掉（降序）
    void setAndIncreaseZset(String key,Integer value,int increaseNum,int limit);

    /**
     * 从Zset中获取数据，倒序
     * @param key zset的键
     * @param start 开始位置
     * @param end 结束位置
     * @return k是值，v是分数
     */
    Map<Integer,Integer> getZsetMap(String key, long start, long end);
}
