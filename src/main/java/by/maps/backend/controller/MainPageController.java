package by.maps.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
    @RequestMapping("/home/guest")
    public String home() {
        return "home";
    }

    @RequestMapping("/home/auth")
    public String authenticatedHome(){
       return "auth";
    }
    @RequestMapping("/home")
    public String mainPage() {
        return "home";
    }
    @RequestMapping("/account/detail")
    public String accountPage() {
        return "account";
    }
    @RequestMapping("/shop")
    public String shopPage() {
        return "shop";
    }
}
