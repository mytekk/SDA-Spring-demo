package com.example.demo.controller;

import com.example.demo.service.CalcService;
import com.example.demo.service.HomeService;
import com.example.demo.service.RandomGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by RENT on 2017-08-09.
 */

@Controller
public class HomeController {

    //sami nigdzie nie tworzymy obiektu klasy HomeController - robi to za nas spring

    //potrzebuję obiekt takiej klasy, spring go dostarczy ze swojego rejestru (kontekstu) (
    // wezmie go po nazwie typu (poszuka klasy o tej samej nazwie), po nazwie zmiennej
    // (poszuka klasy o tej samej nazwie) albo w innny sposob)
    @Autowired
    private HomeService homeService;

    @Autowired
    private CalcService calcService;

    @Autowired
    private RandomGeneratorService randomGeneratorService;

    @RequestMapping(path = "/home")
    public ModelAndView home() { //tych metod tez nigdzie nie wywolujemy, robi to za nas spring
        ModelAndView modelAndView = new ModelAndView("home");

        HomeService homeService = new HomeService("wiadomosc!");
        homeService.doSomething(); //zmienna utworzona tutaj, lokalnie

        this.homeService.doSomething(); //pole z klasy, pochodzi z rejestru-kontekstu

        return modelAndView;
    }

    @RequestMapping(path = "/add")
    public ModelAndView add () {
        ModelAndView modelAndView = new ModelAndView("calcAdd");

        int a = randomGeneratorService.generateRandom(); //spring dostarcza ten obiekt ze swojego rejestru
        int b = randomGeneratorService.generateRandom();

        int result = calcService.add(a, b);//spring dostarcza ten obiekt calcService ze swojego rejestru

        System.out.println(result);

        //przekazanie zmiennej do html
        modelAndView.addObject("result", Integer.valueOf(result));

        return modelAndView;
    }

}
