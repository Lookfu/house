package com.example.house;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
class HouseApplicationTests {
    @Test
    void contextLoads() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMdd");
        System.out.println(format.format(date));
    }
}
