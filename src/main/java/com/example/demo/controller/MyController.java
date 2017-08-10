package com.example.demo.controller;

import com.example.demo.service.CalcService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by RENT on 2017-08-10.
 */

@Controller
public class MyController {

    @Autowired
    private CalcService calcService;

    @RequestMapping(path = {"/one", "/two", "/three"}, method = RequestMethod.GET, params = {"test"})
    public ModelAndView modelAndView(@RequestParam("test") String testVariable) {
        System.out.println("Przekazany parametr: " + testVariable);
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/query-add", params = {"a", "b"})
    public ModelAndView add(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        ModelAndView modelAndView = new ModelAndView("calcAdd");

        int result = calcService.add(a, b);//spring dostarcza ten obiekt calcService ze swojego rejestru

        System.out.println("Wynik: " + result);

        modelAndView.addObject("result", Integer.valueOf(result));

        return modelAndView;
    }

}
