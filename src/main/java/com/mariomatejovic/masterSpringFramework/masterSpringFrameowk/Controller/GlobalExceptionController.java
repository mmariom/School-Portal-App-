package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandelr(Exception exception){
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error");
        errorPage.addObject("errormsg",exception.getMessage());
        return errorPage;
    }
}
