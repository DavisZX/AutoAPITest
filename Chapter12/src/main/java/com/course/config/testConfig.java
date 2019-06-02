package com.course.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;



public class testConfig {

    public static String loginUrl;
    public static String addUserUrl;
    public static String getUserInfoUrl;
    public static String getUserListUrl;
    public static String updateUserInfoUrl;

    public static DefaultHttpClient httpClient ;

    public static CookieStore cookieStore ;
}
