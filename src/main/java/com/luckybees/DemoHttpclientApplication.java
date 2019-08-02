package com.luckybees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import java.util.function.Supplier;

@SpringBootApplication
public class DemoHttpclientApplication {


    public static void main(String[] args) {

        SpringApplication.run(DemoHttpclientApplication.class, args);



    }


    public static void t(String x) throws Exception{

        Assert.notNull(x, "x 为空" );
        System.out.println("继续");

    }

}
