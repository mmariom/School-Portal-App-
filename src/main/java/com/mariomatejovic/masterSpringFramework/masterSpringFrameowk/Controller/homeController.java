package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.Controller;

import org.apache.el.parser.Node;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

    @RequestMapping(value = {"","/","/home"})
    public String getHomePage(){

        return "home.html";
    }








}
