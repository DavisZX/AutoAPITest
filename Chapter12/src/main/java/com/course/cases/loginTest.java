package com.course.cases;


//import com.alibaba.fastjson.JSONObject;
import com.course.config.testConfig;
import com.course.model.interfaceName;
import com.course.model.login;
import com.course.utils.configFile;
import com.course.utils.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class loginTest {

    @BeforeTest(groups = "loginPreparation",description = "prepartion of login test")
    public void beforeTest(){
        testConfig.loginUrl = configFile.getUrl(interfaceName.LOGIN);
        testConfig.addUserUrl = configFile.getUrl(interfaceName.ADDUSERINFO);
        testConfig.getUserInfoUrl = configFile.getUrl(interfaceName.GETUSERINFO);
        testConfig.getUserListUrl = configFile.getUrl(interfaceName.GETUSERLIST);
        testConfig.updateUserInfoUrl = configFile.getUrl(interfaceName.UPDATEUSERINFO);

        //initialize httpclient
        testConfig.httpClient = new DefaultHttpClient();
    }

    @Test(groups = "loginTrue",description = "user login sucessfully")
    public void loginTrue() throws IOException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        login loginTrue = sqlSession.selectOne("loginCase",1);
        System.out.println(loginTrue.toString());
        System.out.println(testConfig.loginUrl);

        //send request
        String result = getResult(loginTrue);

        //verify response
        Assert.assertEquals(loginTrue.getExpected(),result);

    }

    private String getResult(login loginTrue) throws IOException {
        HttpPost post = new HttpPost(testConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("userName",loginTrue.getUserName());
        param.put("password",loginTrue.getPassword());
        param.put("permission",loginTrue.getPermission());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //execute
        String result;
        HttpResponse response = testConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        testConfig.cookieStore = testConfig.httpClient.getCookieStore();
        return result;

    }


    @Test(groups = "loginFalse",description = "user login failed")
    public void loginFalse() throws IOException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        login loginFalse = sqlSession.selectOne("loginCase",2);
        System.out.println(loginFalse.toString());
        System.out.println(testConfig.loginUrl);

    }

}
