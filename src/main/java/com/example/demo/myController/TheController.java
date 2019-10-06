package com.example.demo.myController;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class TheController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("username","未登录");
        return "index";
    }

    // 注册
    @GetMapping("/register")
    public String register(){
        return "regist";
    }

    @RequestMapping("doRegister")
    public String doRegister(User user, Model model){
        User user1 = userService.findByUsername(user.getUsername());
        if (user1 != null) {
            model.addAttribute("registerState","用户名已存在");
            return "regist";
        }
        User user2 = userService.findByEmail(user.getEmail());
        if (user2 != null) {
            model.addAttribute("registerState", "邮箱已被注册");
            return "regist";
        }
        userService.insertUser(user);
        return "login";
    }


    // 登陆
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("state","");
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(User user,Model model){
        User user1 = userService.getUser(user.getEmail(), user.getPassword());
        System.out.println(user1);
        if (user1 != null) {
            model.addAttribute("state","用户名或密码错误");
            return "login";
        }
        else {
            model.addAttribute("username",user.getUsername());
            return "index";
        }
    }
}
