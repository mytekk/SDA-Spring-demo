package com.example.demo.controller;

import com.example.demo.service.SomeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by RENT on 2017-08-10.
 */

@Controller
public class RequestScopeController {

    @Autowired
    SomeRequestService someRequestService;


    @RequestMapping(path = "/testing-request-scope")
    public ModelAndView testRequestScope() {
        ModelAndView modelAndView = new ModelAndView("sample");

        someRequestService.doSomething();

        return modelAndView;
    }

    @RequestMapping(path = "/testing-request-scope-2")
    public ModelAndView testRequestScopeSecond() {
        ModelAndView modelAndView = new ModelAndView("sample");

        someRequestService.doSomething();
        someRequestService.doSomething();
        someRequestService.doSomething();

        return modelAndView;
    }

}
