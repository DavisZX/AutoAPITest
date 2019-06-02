package com.course.controller;


import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(value = "v1", description = "sql demo script")
@RequestMapping("v1")
public class mySqlDemo {

    @Autowired
    private SqlSessionTemplate temp;

    //mapping url
    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    //describe the method
    @ApiOperation(value = "try to get user count",httpMethod = "GET")
    public int getUserCount(){
        return temp.selectOne("getUserCount");
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "insert new user into DB",httpMethod = "POST")
    public int addUser(@RequestBody User user){
        int addresult = temp.insert("addUser", user);
        return addresult ;
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "update user with targeted value",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        int updateresult = temp.update("updateUser",user);
                return updateresult;
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    @ApiOperation(value = "delete user with targeted id",httpMethod = "GET")
    public int deleteUser(@RequestParam int id){
        int deleteResult = temp.delete("deleteUser",id);
        return deleteResult;
    }

}
