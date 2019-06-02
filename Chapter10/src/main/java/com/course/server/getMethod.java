package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "Collected Get Method")
public class getMethod {

    @RequestMapping(value = "/getdemowithcookieresponse",method = RequestMethod.GET)
    @ApiOperation(value = "/get/cookies",httpMethod = "GET")

    public String getCookies(HttpServletResponse response){

        //HttpServerletRequest  -- set the request info
        //HttpServerletResponse -- set the response info
        Cookie cookie= new Cookie("login","ture");
        response.addCookie(cookie);
        return "Congrulations! you get the cookies returned" ;

    };

    @RequestMapping(value = "/getwithcookieinrequest",method = RequestMethod.GET)
    @ApiOperation(value = "/get/cookies/request",httpMethod = "GET")
    public String getCookiesRequest(HttpServletRequest request){
        //HttpServerletRequest  -- set the request info
        //HttpServerletResponse -- set the response info
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "cookies not found, check if cookies sent in request!" ;
        }else{
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                    return "Congratulations! Response returned with cookies sent";
                }
            }
        }
        return "cookies not found, check if cookies sent in request!" ;
    };

    /** create a GET method with param sent
     *  first solution: url: key=value & key=value
     *  simulate the case: getting product list
     */
    @RequestMapping(value = "/getlist/param",method = RequestMethod.GET)
    @ApiOperation(value = "/get/param/requestParam", httpMethod = "GET")
    public Map<String, Integer> getListWithParam(@RequestParam Integer start, @RequestParam Integer end) {
        Map<String, Integer> productList = new HashMap<>();
        productList.put("平多多",20);
        productList.put("零食多",15);
        productList.put("可爱多",50);
        return productList;

    };

    /**create a GET method with param sent
     *  second solution: url:ip:port/value/value
     *  simulate the case: getting product list
     *
     */
    @RequestMapping(value = "/getlist/param/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "/get/param/pathvariable", httpMethod = "GET")

    public Map<String, Integer> getListWithParamD(@PathVariable Integer start, @PathVariable Integer end) {
        Map<String, Integer> productList = new HashMap<>();
        productList.put("衣服多",900);
        productList.put("零食多",15);
        productList.put("可爱多",50);
        return productList;

    }

}
