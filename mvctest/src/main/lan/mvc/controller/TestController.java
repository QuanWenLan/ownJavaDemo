package lan.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Vin lan
 * @className TestController
 * @description
 * @createTime 2022-04-13  22:05
 **/
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/users")
    public String users() {
        System.out.println("come in....");
        System.out.println("come out....");
        return "users";
    }
}
