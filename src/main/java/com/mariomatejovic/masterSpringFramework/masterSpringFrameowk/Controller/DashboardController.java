package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.Controller;

import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Person;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashboardController {


    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/dashboard")
    // Ked sa user pirhlasi tak jeho udaje budu uchovane v Authentication ,  cez
    //authentication.getName ziskaem meno atd...
    public String displayDashboard(Model model, Authentication authentication, HttpSession httpSession) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        httpSession.setAttribute("loggedInPerson",person);

       return "dashboard.html";
    }

}