package com.course.httpclient.cookies;

import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class operateCookiePost {


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
    public void testPostMethod() throws IOException{

        //identify the url
        String testUri = bundle.getString("operate.post.Cookies.request.uri");
        String testUrl = this.url + testUri;

        //identify the client object to execute
        DefaultHttpClient postClient = new DefaultHttpClient();
        //identify post method
        HttpPost post = new HttpPost(testUrl);
        //add param
        JSONObject param = new JSONObject();
        param.put("name","zhangmangmang");
        param.put("gander","male");

        //set header info
        post.setHeader("content-type","application-json");

        //add param into post method
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //identify object to store the result
        String testResult;

        //set cookies store
        postClient.setCookieStore(this.cookieStore);

        //execute post method
        HttpResponse postResponse = postClient.execute(post);
        //get post response
        testResult = EntityUtils.toString(postResponse.getEntity());
        System.out.println(testResult);
        //convert to json object
        JSONObject jsonResult = new JSONObject(testResult);
        String success = (String) jsonResult.get("zhangmangmang");
        String status = (String) jsonResult.get("status");

        //assert result
        Assert.assertEquals("success",success);
        Assert.assertEquals("1",status);


    }


}
