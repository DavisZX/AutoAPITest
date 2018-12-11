package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {
    @BeforeMethod
    public void beforeTest(){
        System.out.println("prepare the test");
    }

    @Test
    public void testcase1(){
        System.out.println("hello quality world");
    }

    @Test
    public void testcase2(){
        System.out.println("try again, world");
    }
    @AfterMethod
    public void afterTest(){
        System.out.println("mession completed");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("before class");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("after class");
    }
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before suite before class");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("after suite after class");
    }
}
