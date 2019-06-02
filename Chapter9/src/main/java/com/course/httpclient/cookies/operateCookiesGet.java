package com.course.httpclient.cookies;

import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class operateCookiesGet {

    private String url;
    private ResourceBundle bundle;
    //store cookie
    private CookieStore cookieStore;

    @BeforeTest
    public void BeforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");

    }
    @Test
    public void getCookiesInResponse() throws IOException {
        String result;
        String uri = bundle.getString("operate.get.Cookies.response.uri");
        String url = this.url+uri;

        HttpGet getUrl = new HttpGet(url);
        //HttpClient can't get the cookie so change the object to DefaultHttpCLient
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(getUrl);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //get cookies info
        cookieStore = client.getCookieStore();
        List<Cookie> cookieList = cookieStore.getCookies();
        for(Cookie cookie:cookieList){
            String cookieName = cookie.getName() ;
            String cookieValue = cookie.getValue();
            System.out.println("cookie name is:"+cookieName+"  ; and cookie value is:"+cookieValue);
        }
    }

    @Test(dependsOnMethods = {"getCookiesInResponse"})
    public void testWithCookies() throws IOException{
        String testResult;
        String testuri = bundle.getString("operage.get.Cookies.request.uri");
        String testurl = this.url+testuri;

        HttpGet getUrl = new HttpGet(testurl);
        DefaultHttpClient requestClient = new DefaultHttpClient();
        //set cookies store;
        requestClient.setCookieStore(this.cookieStore);
        HttpResponse testResponse = requestClient.execute(getUrl);
        testResult = EntityUtils.toString(testResponse.getEntity(),"utf-8");

        //verify the response status code
        int testStatusCode = testResponse.getStatusLine().getStatusCode();

        if(testStatusCode == 200){
            System.out.println("response returned as expected, its value =  :"+testStatusCode+"; and returned body is :"+testResult);
        }else{
            System.out.println("response code is: "+testStatusCode);
        }


    }

}
