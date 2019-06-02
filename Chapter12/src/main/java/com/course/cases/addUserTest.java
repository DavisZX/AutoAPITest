package com.course.cases;

//import com.alibaba.fastjson.JSONObject;
import com.course.config.testConfig;
import com.course.model.User;
import com.course.model.addUser;
import com.course.utils.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.testng.collections.Maps.newLinkedHashMap;

public class addUserTest {


    @Test(dependsOnGroups = "loginTrue",description = "add user operations need user login")
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void addUserTest() throws IOException, InterruptedException {
        SqlSession sqlSession = databaseUtil.getSqlSession();
        addUser adduser = sqlSession.selectOne("addUserCase",23);
        System.out.println(adduser.toString());
        System.out.println(testConfig.addUserUrl);

        //send request
        String result = getResult(adduser);

        Thread.sleep(3000);

        //verify user added or not
        //User user = sqlSession.selectOne(adduser.getExpected(),adduser);
        //param = "addUser"

        //may cause internal bug as userList returned as null, sql cant query out newly added
        //records as per expected!
        List<User> userList = sqlSession.selectList("addUser",adduser);
        //System.out.println(userList.toString());

        for(User user:userList){
            System.out.println("queried user is: "+user.toString());

        }


        //assert
        Assert.assertEquals(adduser.getExpected(),result);

    }

    //send request method
    private String getResult(addUser addUser) throws IOException {
        HttpPost post = new HttpPost(testConfig.addUserUrl);
        JSONObject jsonParam = new JSONObject(true);
        jsonParam.put("id",addUser.getId());
        jsonParam.put("userName", addUser.getUserName());
        jsonParam.put("password", addUser.getPassword());
        jsonParam.put("age", addUser.getAge());
        jsonParam.put("gander", addUser.getGander());
        jsonParam.put("permission", addUser.getPermission());
        jsonParam.put("isDelete", addUser.getIsDelete());

        //set header
        post.setHeader("content-type","application/json");

        //set param to entity
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
        post.setEntity(entity);

        //set cookies
        testConfig.httpClient.setCookieStore(testConfig.cookieStore);

        //define result
        String result;

        //execute the script
        HttpResponse response = testConfig.httpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(result);
        return result;
    }

}
