package com.example.demo.myController;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/register")
    public String register(){
        return "regist";
    }

    @RequestMapping("doRegister")
    public String doRegister(User user, Map<String,Object> map){
        User user1 = userService.findByUsername(user.getUsername());
        if (user1 == null) {
            map.put("msg", "用户名已存在");
            return "regist";
        }
        User user2 = userService.findByEmail(user.getEmail());
        if (user1 == null) {
            map.put("msg", "邮箱已被注册");
            return "regist";
        }
        userService.insertUser(user);
        map.put("msg","注册成功！");
        return "login";
    }


    // 登陆
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("doLogin")
    public String doLogin(User user,Map<String,Object> map){
        User user1 = userService.getUser(user.getEmail(), user.getPassword());
        if (user1 == null) {
            map.put("msg","邮箱或密码错误");
            return "login";
        }
        else {
            map.put("msg","登陆成功");
            return "index";
        }
    }
}
