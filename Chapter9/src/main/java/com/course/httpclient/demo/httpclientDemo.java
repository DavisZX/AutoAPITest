package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class httpclientDemo {


    @Test
    public void testHttpclientMethod() throws IOException

    {
        //store returned result
        String result ;
        //set url
        HttpGet clientGet = new HttpGet("http://www.baidu.com");

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(clientGet);
        result = EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);

    }
}