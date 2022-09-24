package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    // Ked sa user pirhlasi tak jeho udaje budu uchovane v Authentication ,  cez
    //authentication.getName ziskaem meno atd...
    public String displayDashboard(Model model,Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());

       return "dashboard.html";
    }

}