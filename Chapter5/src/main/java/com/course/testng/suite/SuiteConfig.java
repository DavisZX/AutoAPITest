package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("start suite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("end suite");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("start test");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("end test");
    }
}
