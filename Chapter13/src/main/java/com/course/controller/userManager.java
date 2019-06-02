package com.course.controller;


import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@Api(value = "v1",description = "user Management System")
@RequestMapping("v1")
public class userManager {


    @Autowired
    private SqlSessionTemplate temp;
    Boolean cookieValidation;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "Login API",httpMethod = "POST")
    public Boolean login(HttpServletResponse response, @RequestBody User user){
        int i = temp.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        log.info("query login result:"+i);
        if(i==1){
            log.info("current username:"+user.getUserName());
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "addUser API",httpMethod = "POST")
    public Boolean addUser(HttpServletRequest request, @RequestBody User user){
        cookieValidation = verfiyCookie(request);
        int result = 0;
        if(cookieValidation==true){
            result = temp.insert("addUser",user);


            if(result >0){
                //mandatorily COMMIT the insert result
                //temp.commit();
                log.info("addUser successed,"+result);
                return true;
            }

        }
        return false;
    }


    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "getUserInfo API",httpMethod = "POST")
    public List<User> getUserList(HttpServletRequest request,@RequestBody User user){
        cookieValidation = verfiyCookie(request);
        int result = 0;
        if(cookieValidation==true){
            List<User> users = temp.selectList("getUserInfo",user);
            log.info("getUserInfo's number"+users.size());
            return users;

        }else{
            return null;
        }
    }


    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value = "update/delete user logically",httpMethod = "POST")
    public Boolean updateUser(HttpServletRequest request,@RequestBody User user){
        cookieValidation = verfiyCookie(request);
        int result;
        if(cookieValidation==true){
            result = temp.update("updateUserInfo",user);
            if(result > 0){
                //mandatorily COMMIT the insert result
                //temp.commit();
                log.info("updateUser successfully,update count is"+result);
                return true;
            }
        }

        return false;
    }

    private Boolean verfiyCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("empty cookies, validation failed");
            return false;
        }
        for(Cookie cookie:cookies){
           if(cookie.getName().equals("login")&&cookie.getValue().equals("true")) {
               log.info("cookies validation passed");
               return true;
           }
        }
        return false;
    }
}
