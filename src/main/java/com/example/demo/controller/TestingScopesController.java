package com.example.demo.controller;

import com.example.demo.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by RENT on 2017-08-10.
 */

@Controller
public class TestingScopesController {

    @Autowired
    private SampleService firstSampleService;

    @Autowired
    private SampleService secondSampleService;


    @RequestMapping(path = "/test-scopes")
    public ModelAndView randomLetters() {
        ModelAndView modelAndView = new ModelAndView("sample");

        firstSampleService.action();

        secondSampleService.action();

        return modelAndView;
    }

}
