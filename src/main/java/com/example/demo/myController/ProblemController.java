package com.example.demo.myController;


import com.example.demo.entities.User;
import com.example.demo.service.TeamService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProblemController {
    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @RequestMapping("/problem")
    public String showProblem(){
        return "problem";
    }
}
