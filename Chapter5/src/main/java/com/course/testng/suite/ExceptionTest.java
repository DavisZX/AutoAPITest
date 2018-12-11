package com.course.testng.suite;

import org.testng.annotations.Test;

public class ExceptionTest {

    @Test(expectedExceptions = RuntimeException.class)
    public void exceptionTest(){
        System.out.println("print the exception");
        throw new RuntimeException();
    }
}
