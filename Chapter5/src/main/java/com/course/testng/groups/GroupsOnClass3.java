package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "teach")
public class GroupsOnClass3 {
    public void teach(){
        System.out.println("groups on class 31 of teach");
    }

    public void stu2(){
        System.out.println("groups on class 32 of teach");
    }



}
