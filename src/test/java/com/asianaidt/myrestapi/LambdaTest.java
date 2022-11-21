package com.asianaidt.myrestapi;

import org.junit.jupiter.api.Test;

public class LambdaTest {
    @Test
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