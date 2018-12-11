package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {


    public void stu1(){
        System.out.println("groups on class 21 of stu");
    }

    public void stu2(){
        System.out.println("groups on class 22 of stu");
    }
}
