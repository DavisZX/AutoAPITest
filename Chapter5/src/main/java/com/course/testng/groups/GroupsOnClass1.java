package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("groups on class 11 of stu");
    }

    public void stu2(){
        System.out.println("groups on class 12 of stu");
    }
}
