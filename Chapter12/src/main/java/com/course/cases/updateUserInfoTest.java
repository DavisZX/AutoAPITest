package com.course.cases;

//import com.alibaba.fastjson.JSONObject;
import com.course.config.testConfig;
import com.course.model.User;
import com.course.model.updateUserInfo;
import com.course.utils.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class updateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue", description = "update user operation need user login")
    public void updateUserInfoTest() throws IOException, InterruptedException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        updateUserInfo updateuserinfo = sqlSession.selectOne("updateUserInfoCase",1);
        System.out.println(updateuserinfo.toString());
        System.out.println(testConfig.updateUserInfoUrl);

        String result = getResult(updateuserinfo);

        Thread.sleep(3000);
        List<User> userList = sqlSession.selectList(updateuserinfo.getExpected(),updateuserinfo);

        for(User user:userList){
            System.out.println("queried users are : "+user);
        }
        //Assert.assertNotNull(user);
        //System.out.println(user);
        Assert.assertNotNull(result);
        System.out.println(result);

    }

    private String getResult(updateUserInfo updateuserinfo) throws IOException {
        HttpPost post = new HttpPost(testConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
        param.put("id",updateuserinfo.getUserId());
        param.put("userName",updateuserinfo.getUserName());
        param.put("gender",updateuserinfo.getGander());
        param.put("age",updateuserinfo.getAge());
        param.put("permission",updateuserinfo.getPermission());
        param.put("isDelete",updateuserinfo.getIsDelete());
        //param.put("isDelete",updateuserinfo.getIsDelete());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        testConfig.httpClient.setCookieStore(testConfig.cookieStore);

        String result;

        HttpResponse response = testConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        return result;
    }


    @Test(dependsOnGroups = "loginTrue", description = "delete user operation need user login")
    public void deleteUserInfoTest() throws IOException, InterruptedException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        updateUserInfo deleteuserinfo = sqlSession.selectOne("updateUserInfoCase",2);
        System.out.println(deleteuserinfo.toString());
        System.out.println(testConfig.updateUserInfoUrl);

        String result = getResult(deleteuserinfo);

        Thread.sleep(3000);
        List<User> userList = sqlSession.selectList(deleteuserinfo.getExpected(),deleteuserinfo);

        for(User user:userList){
            System.out.println("queried users are : "+user);
        }
        //Assert.assertNotNull(user);
        //System.out.println(user);
        Assert.assertNotNull(result);
        System.out.println(result);


    }


}
