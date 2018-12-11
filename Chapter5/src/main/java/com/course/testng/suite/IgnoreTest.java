package com.course.testng.suite;

import org.testng.annotations.Test;

public class IgnoreTest {
    @Test(enabled = false)
    public void ignoreTestFalse(){
        System.out.println("ignore test!");
    }

    @Test(enabled = true)
    public void ignoreTestTrue(){
        System.out.println("non ignore test!");

    }
}
