package com.example.house.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class CommonUtil {
    private static AtomicInteger adder=new AtomicInteger(100000000);

    /**
     *
     * @return
     */
    public static String getOderId(){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMdd");
        String res=format.format(date)+""+adder.getAndIncrement();
        if(adder.get()==900000000){
            adder.set(100000000);
        }
        return res;
    }
}
