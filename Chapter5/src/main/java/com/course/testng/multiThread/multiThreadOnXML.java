package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class multiThreadOnXML {

    @Test
    public void threadonXML1(){
        System.out.println("thread test method1");
        System.out.println("thread id is:" + Thread.currentThread().getId());
    }

    @Test
    public void threadonXML2(){
        System.out.println("thread test method2");
        System.out.println("thread id is:" + Thread.currentThread().getId());
    }

    @Test
    public void threadonXML3(){
        System.out.println("thread test method3");
        System.out.println("thread id is:" + Thread.currentThread().getId());
    }
}
