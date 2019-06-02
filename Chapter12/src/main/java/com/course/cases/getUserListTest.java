package com.course.cases;

//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.course.config.testConfig;
import com.course.model.User;
import com.course.model.getUserList;
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
import java.util.List;

public class getUserListTest {

    @Test(dependsOnGroups = "loginTrue",description = "get user list operation need user login successfully")
    public void getUserListTest() throws IOException{
        SqlSession sqlSession = databaseUtil.getSqlSession();
        getUserList getuserlist = sqlSession.selectOne("getUserListCase",1);
        System.out.println(getuserlist.toString());
        System.out.println(testConfig.getUserInfoUrl);

        //send request, param type = jsonarray
        JSONArray resultJson = getJsonResult(getuserlist);

        //get response, ensure the sql string correctly, so far, getuserlist.getExpected should be equals to getUserList
        //so that the sql will be executed correctly;
        List<User> userList = sqlSession.selectList(getuserlist.getExpected(),getuserlist);
        //verify
        for(User user:userList){
            System.out.println("queried user is: "+user.toString());

        }
        //JSONArray userListJson = new JSONArray(userList);
        JSONArray userListJson = new JSONArray(userList);
        //userListJson.add();
        Assert.assertEquals(userListJson.length(),resultJson.length());
        //Assert.assertEquals(userListJson.size(),resultJson.size());

        for(int length = 0; length < resultJson.length();length++){
            JSONObject expect = (JSONObject) resultJson.get(length);
            JSONObject actual = (JSONObject) userListJson.get(length);

            Assert.assertEquals(expect.toString(),actual.toString());

        }

    }

    private JSONArray getJsonResult(getUserList getuserlist) throws IOException {
        //as getUserList Mothed not defined in API, reuse getUserInfo method to check case's execution result;
        HttpPost post = new HttpPost(testConfig.getUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getuserlist.getUserName());
        param.put("age",getuserlist.getAge());
        param.put("gander",getuserlist.getGander());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        testConfig.httpClient.setCookieStore(testConfig.cookieStore);

        String result;

        HttpResponse response = testConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        //JSONArray jsonResult = new JSONArray();
        //jsonResult.add(result);
        JSONArray jsonResult = new JSONArray(result);

        return jsonResult;

    }
}
