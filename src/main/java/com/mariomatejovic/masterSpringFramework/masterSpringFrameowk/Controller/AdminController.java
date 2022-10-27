package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.Controller;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Person;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.SmartClass;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.AdminRepository;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("admin")
@Slf4j
public class AdminController {


    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        ModelAndView modelAndView = new ModelAndView("classes.html");

        // toto sa pouziva ked sa odosielaju nove data na server v POST metode
        // je to vlastne model ako maju data vyzerat
        modelAndView.addObject("smartClass", new SmartClass());


        modelAndView.addObject("smartClasses", adminRepository.findAll());

        return modelAndView;
    }



    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("smartClass") SmartClass smartClass){
        adminRepository.save(smartClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;

    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id){
        Optional<SmartClass> smartClass = adminRepository.findById(id);


        //Loop sa nastavuje  preto aby to vymazalo vsetkych studentov(Persons)
        // ktory maju aktualnu smartClassu v class_id (fk) pripisanu  a nastavi to null
        //  *** Odmaze vsetkych ziakov z danej classy ***
        for (Person person :
                smartClass.get().getPersons()) {
                person.setSmartClass(null);
                personRepository.save(person);
        }
        adminRepository.deleteById(id);
        return new ModelAndView("redirect:/admin/displayClasses");

    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession httpSession,
                                        @RequestParam(value = "error", required = false) String error) {


        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<SmartClass> smartClass = adminRepository.findById(classId);

        modelAndView.addObject("smartClass", smartClass.get());

        // toto sa pouziva ked sa odosielaju nove data na server v POST metode
        // je to vlastne model ako maju data vyzerat
        modelAndView.addObject("person", new Person());
        httpSession.setAttribute("smartClass" , smartClass.get());

        if(error != null) {

            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }


        return modelAndView;

    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        SmartClass smartClass = (SmartClass) session.getAttribute("smartClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());

        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+smartClass.getClassId()
                    +"&error=true");
            return modelAndView;
        }
        // Pridavame  smartclassu do Personu
        personEntity.setSmartClass(smartClass);
        personRepository.save(personEntity);
        // Pridavame entity do smartclass listu
        smartClass.getPersons().add(personEntity);
        adminRepository.save(smartClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+smartClass.getClassId());
        return modelAndView;
    }






    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        SmartClass smartClass = (SmartClass) session.getAttribute("smartClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setSmartClass(null);
        smartClass.getPersons().remove(person.get());
        SmartClass smartClassSaved = adminRepository.save(smartClass);
        session.setAttribute("smartClass",smartClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+smartClass.getClassId());
        return modelAndView;
    }








}
