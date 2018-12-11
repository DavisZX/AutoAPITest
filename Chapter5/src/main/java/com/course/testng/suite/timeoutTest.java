package com.course.testng.suite;

import org.testng.annotations.Test;

public class timeoutTest {
    @Test(timeOut = 4000)
    public void timeoutSuccess() throws InterruptedException{
        Thread.sleep(2000);
        System.out.println("timeout completed");
    }

    @Test(timeOut = 1000)
    public void timeoutFailed() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("timeout completed");
    }
}
