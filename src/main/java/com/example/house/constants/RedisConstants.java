package com.example.house.constants;

public class RedisConstants {

    public static final String HOT_HOUSE="hotHouse";
    //存储热点房屋的数量，当数据量超过HOT_HOUSE_LENGTH加上HOT_HOUSE_DELETE时就清除数据使
    //热点数据重新变为HOT_HOUSE_LENGTH个
    public static final Integer HOT_HOUSE_LENGTH=20;
    //热第数据缓冲值
    public static final Integer HOT_HOUSE_BUFFER=5;
    //展示热点数据的数量
    public static final Integer HOT_HOUSE_SHOW=5;
}
