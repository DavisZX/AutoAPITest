package com.tester.extend.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class testMethod {
    @Test
    public void testFault(){
        Assert.assertEquals(110,120);
    }

    @Test
    public void testTrue(){
        Assert.assertEquals("string","string");
        Reporter.log("hey, we passed this case");
    }

    @Test
    public void testFault1(){
        Assert.assertEquals("stg","prod");
    }


    @Test
    public void logEvent(){
        Reporter.log("here's our first log");
        throw  new RuntimeException("type the new runtime exception");
    }
}
