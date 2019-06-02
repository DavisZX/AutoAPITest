package hello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Controller
@EnableAutoConfiguration

//@SpringBootApplication


public class SampleController {
    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "hello World" ;
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(SampleController.class, args);
    }

}
