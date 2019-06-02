package com.course.cases;

//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.course.config.testConfig;
import com.course.model.User;
import com.course.model.getUserInfo;
import com.course.utils.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class getUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "get user info operation needs user login successfully")
    public void getUserInfoTest() throws IOException{
        SqlSession sqlSession = databaseUtil.getSqlSession();
        getUserInfo getuserinfo = sqlSession.selectOne("getUserInfoCase",1);
        System.out.println(getuserinfo.toString());
        System.out.println(testConfig.getUserInfoUrl);


        JSONArray resultJson = getJsonResult(getuserinfo);

        User user = sqlSession.selectOne(getuserinfo.getExpected(),getuserinfo);

        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);

        //assert
        Assert.assertEquals(jsonArray.toString(),resultJson.toString());
    }

    private JSONArray getJsonResult(getUserInfo getuserinfo) throws IOException {
        HttpPost post = new HttpPost(testConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",getuserinfo.getId());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        testConfig.httpClient.setCookieStore(testConfig.cookieStore);

        String result;

        HttpResponse response = testConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        //List resultList = Arrays.asList(result);
        //JSONArray resultArray = new JSONArray(resultList);
        JSONArray resultArray = new JSONArray(result);

        return resultArray;
    }
}
