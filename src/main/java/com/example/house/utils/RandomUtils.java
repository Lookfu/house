package com.example.house.utils;

import java.util.Random;

public class RandomUtils {

    public static int getFiveBitRandomNumber(){
        Random random = new Random();
        return random.nextInt(89999) + 10000;
    }


}
