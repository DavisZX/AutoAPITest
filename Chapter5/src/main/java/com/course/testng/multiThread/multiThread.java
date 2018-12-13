package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class multiThread {

    @Test(invocationCount = 10,threadPoolSize = 5)

    public void multiThreadTest(){
        System.out.println("multithread test 2");
        System.out.println("threadID is "+Thread.currentThread().getId());
    }

}
