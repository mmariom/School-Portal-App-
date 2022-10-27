package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.Controller;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Contact;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Slf4j
public class ContactController {



    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact")
    public String displayContactPage(Model model){
        model.addAttribute("contactt",new Contact());
        return "contact.html";
    }

    // Without pojo class requesiting params ....
//    @PostMapping(value = "/saveMsg")
//    public ModelAndView saveMessage(@RequestParam(name = "meno") String meno, @RequestParam String
//     mobileNum,@RequestParam String email,@RequestParam String subject,@RequestParam String message){
//
//        log.info("Name : " + meno);
//        log.info("Mobile num : " + mobileNum);
//        log.info("email : " + email);
//        log.info("subject : " + subject);
//        log.info("message : " + message);
//
//
//        //ModelAndView support sending data and view information ... nieco ako Mode model interface ..
//        // user dostane cisto novu contact page..
//        return new ModelAndView("redirect:/contact");
//
//    }


//    @RequestMapping(value = "/saveMsg",method = POST)
//    public ModelAndView saveMessage(Contact contact){
//        contactService.saveMessageDetails(contact);
//        return new ModelAndView("redirect:/contact");
//    }


    @PostMapping(value = "/saveMsg")
//
//    model atribute sa pouziva ako binding s Contact contact, model atribud drzi new Contact()
//     ked sa formular submitne tak sa prepisu field hodnoty   do contact, tieto hodnoty sa ziskavaju
//     tym ze html ma atriuty field inak by sa pozuvvalo id a name .
    public String saveMessage(@Valid @ModelAttribute("contactt") Contact contact, Errors errors){

        if (errors.hasErrors()){
            log.error("contact form validation failed due to " + errors.toString()) ;
            return "contact.html";
        }


        contactService.saveMessageDetails(contact);
//        contactService.setCounter(contactService.getCounter()+1);
//        log.info("Number of time the Contact form is submitted " + contactService.getCounter());
        return "redirect:/contact";

    }


    @GetMapping("displayMessages")
    public ModelAndView displayMessages(){
        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return  modelAndView;
    }



    @GetMapping(value = "/closeMsg")
    public String closeMsg(@RequestParam int id) {
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }


}









