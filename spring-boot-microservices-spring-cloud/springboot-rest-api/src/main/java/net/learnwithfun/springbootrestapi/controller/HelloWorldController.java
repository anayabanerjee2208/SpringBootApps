package net.learnwithfun.springbootrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//@ResponseBody
//@Restcontroller is a combination of @Controller and @ResponseBody annotation
@RestController
@RequestMapping("/api")
public class HelloWorldController {
@GetMapping("hello-world")
    public String HelloWorld(){
    return "hello-world";
    }
}
