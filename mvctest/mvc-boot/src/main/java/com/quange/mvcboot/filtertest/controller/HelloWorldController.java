package com.quange.mvcboot.filtertest.controller;

/**
 * @author Lan
 * @createTime 2023-08-16  11:34
 **/

import com.quange.mvcboot.filtertest.vo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/filterTest")
public class HelloWorldController {

    @GetMapping("/noFilter")
    public String helloWorld(@RequestParam(value = "id") Integer id,
                             @RequestParam(value = "name") String name) {
        return "Hello world, id: " + id + " name: " + name;
    }

    @GetMapping("/test1")
    public String helloWorld1(@RequestParam(value = "id") Integer id) {
        return "Hello world:" + id;
    }

    @PostMapping("/test2")
    public String helloWorld2(@RequestBody User user) {
        return "Hello world:" + user.toString();
    }
}

