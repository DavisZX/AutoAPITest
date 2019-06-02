package com.course.model;


import lombok.Data;

@Data
public class User {

    private int id;
    private String userName;
    private String password;
    private int age;
    private String gander;
    private String permission;
    private String isDelete;


    @Override
    public String toString(){
        return("{id:"+id+","+
                "userName:"+userName+","+
                "password:"+password+","+
                "age:"+age+","+
                "gander:"+gander+","+
                "permission:"+permission+","+
                "isDelete:"+isDelete+"}"
                );
    }
}
