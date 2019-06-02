package com.course.utils;

import com.course.model.interfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class configFile {

    //create obj to load application properties
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(interfaceName name){
        String baseAddr = bundle.getString("test.url");
        String uri = "";

        String testUrl;

        if(name.equals(interfaceName.LOGIN)){
            uri = bundle.getString("login.uri");
        }

        if(name.equals(interfaceName.ADDUSERINFO)){
            uri = bundle.getString("addUser.uri");
        }

        if(name.equals(interfaceName.GETUSERINFO)){
            uri = bundle.getString("getUserInfo.uri");
        }

        if(name.equals(interfaceName.GETUSERLIST)){
            uri = bundle.getString("getUserList.uri");
        }

        if(name.equals(interfaceName.UPDATEUSERINFO)){
            uri = bundle.getString("updateUserInfo.uri");
        }

        testUrl = baseAddr + uri;

        return testUrl;
    };
}
