package com.course.server;


import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@Api(value = "/",description = "Collected Post Method")
@RequestMapping("/v1")
public class postMethod {

    //define var to store cookies

    private static Cookie cookie;

    //User login to get cookies, use cookie to visit other method

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "login to get cookie",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "passWord",required = true) String password
                        ){
        if(userName.equals("zhangmangmang")&&password.equals("123456")){
            cookie = new Cookie("login","true");
            response.addCookie(cookie);
            return "Successfully login";
        }else{
            return "Wrong userName or password, login failed";
        }
    }

    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    @ApiOperation(value = "get User list",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User user){

        User userTemp;
        //get cookies
        Cookie[] cookies = request.getCookies();
        for (Cookie requestCookie:cookies){
            if(Objects.isNull(cookies)){
                return "cookies not sent, pls contact your dev team!";
            }else if(requestCookie.getName().equals("login")
                    &&requestCookie.getValue().equals("true")
                    && user.getUserName().equals("zhangmangmang")
                    && user.getPassword().equals("123456")
            ){
                userTemp = new User();
                userTemp.setName("zhengmangmang");
                userTemp.setAge("16");
                userTemp.setGander("Female");
                return userTemp.toString();
            }
        }
        return "invalid input param" ;
    }

}
