package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "nameage")
    public void dataProviderTest(String name, int age){
        System.out.println("name: = " + name + " , age:= " + age);
    }

    @DataProvider(name = "nameage")
    public Object[][] provideData(){
        Object[][] info= new Object[][]{
                {"zhangsan",15},
                {"lumin",12},
                {"wangzi",13}
        };

        return info;
    }

    @Test(dataProvider = "dataMethod")
    public void dataProviderTestAlp(String name, int age){
        System.out.println("Test apl name: = " + name + " , age:= " + age);
    }
    @Test(dataProvider = "dataMethod")
    public void dataProviderTestBeta(String name, int age){
        System.out.println("Test Beta name: = " + name + " , age:= " + age);
    }



    @DataProvider(name = "dataMethod")
    public Object[][] methodDataTest(Method method){
        Object[][] result = null;

        if(method.getName().equals("dataProviderTestAlp")){
            result = new Object[][]{
                    {"zhangyin",32},
                    {"zhaoyi",22}
            };
        }else if(method.getName().equals("dataProviderTestBeta")){
            result = new Object[][]{
                    {"qianxin",11},
                    {"haozi",10}
            };
        }
        return result;



    }

}
