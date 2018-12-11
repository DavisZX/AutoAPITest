package com.course.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;


public class GroupsOnMethod {
    @BeforeGroups("server")
    public void beforeGroupsServer(){
        System.out.println("before server group");
    }
    @AfterGroups("server")
    public void afterGroupsServer(){
        System.out.println("after server group");
    }

    @BeforeGroups("client")
    public void beforeGroupsClient(){
        System.out.println("before client group");
    }
    @AfterGroups("client")
    public void afterGroupsClient(){
        System.out.println("after client group");
    }


    @Test(groups="server")
    public void serverGroupAlpha(){
        System.out.println("server group alpha");
    }
    @Test(groups="server")
    public void serverGroupBeta(){
        System.out.println("server group beta");
    }

    @Test(groups="client")
    public void clientGroupAlpha(){
        System.out.println("client group alpha");
    }
    @Test(groups="client")
    public void clientGroupBeta(){
        System.out.println("client group beta");
    }



}
