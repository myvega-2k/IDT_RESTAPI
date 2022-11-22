package com.asianaidt.myrestapi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class LambdaTest {

    @Test
    public void consumer() {
        //Immutable List 
        List<String> strList = List.of("java","boot","cloud");
        strList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s);
            }
        });
        //Lambda Expression
        strList.forEach(val -> System.out.println(val));
        //Method Reference
        strList.forEach(System.out::println);

    }
    
    
    @Test @Disabled
    public void runnable() {
        //Runnable 인터페이스
        //1. Anonymous Inner class
        //Thread(new MyRunnable())
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Inner class");
            }
        });
        t1.start();

        //2. Lambda Expression
        Thread t2 = new Thread(() -> System.out.println("Lambda Expression"));
        t2.start();
    }
}
/*
  class MyRunnable implements Runnable {
     run()
  }
 */