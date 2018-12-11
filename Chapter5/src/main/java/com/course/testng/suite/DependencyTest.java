package com.course.testng.suite;

import org.testng.annotations.Test;

public class DependencyTest {

    @Test
    public void testtrigger(){
        System.out.println("start test trigger");
        throw new RuntimeException();

    }

    @Test(dependsOnMethods = "testtrigger")
    public void testbody(){
        System.out.println("test successfully");
    }
}
