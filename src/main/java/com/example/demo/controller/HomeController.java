package com.example.demo.controller;

import com.example.demo.service.CalcService;
import com.example.demo.service.HomeService;
import com.example.demo.service.RandomGeneratorService;
import com.example.demo.service.StringGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private StringGeneratorService generalStringGeneratorService;  //poszuka beana o takiej nazwie

    @Autowired
    private StringGeneratorService lettersStringGeneratorService;

    @Autowired
    private StringGeneratorService numbersStringGeneratorService;

    @Autowired
    private List<StringGeneratorService> list; //ta lista bedzie zawierala wszystkie mozliwe beany typu StringGeneratorService, a wiec 3 elementy

    @RequestMapping(path = "/home")
    public ModelAndView home() { //tych metod tez nigdzie nie wywolujemy, robi to za nas spring
        ModelAndView modelAndView = new ModelAndView("home");

        HomeService homeService = new HomeService("wiadomosc!");
        homeService.doSomething(); //zmienna utworzona tutaj, lokalnie

        this.homeService.doSomething(); //pole z klasy, pochodzi z rejestru-kontekstu

        return modelAndView;
    }

    @RequestMapping(path = "/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("calcAdd");

        int a = randomGeneratorService.generateRandom(); //spring dostarcza ten obiekt ze swojego rejestru
        int b = randomGeneratorService.generateRandom();

        int result = calcService.add(a, b);//spring dostarcza ten obiekt calcService ze swojego rejestru

        System.out.println(result);

        //przekazanie zmiennej do html
        modelAndView.addObject("result", Integer.valueOf(result));

        return modelAndView;
    }

    @RequestMapping(path = "/three-random-string")
    public ModelAndView randomString() {
        ModelAndView modelAndView = new ModelAndView("threeRandomString");

        int size = randomGeneratorService.generateRandom(); //spring dostarcza ten obiekt ze swojego rejestru

        //pierwszy sposob na wygenerowanie trzech stringow: recznietworze trzy zmienne
        /*
        String s1 = generalStringGeneratorService.generateString(size);
        String s2 = lettersStringGeneratorService.generateString(size);
        String s3 = numbersStringGeneratorService.generateString(size);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        //przekazanie zmiennej do html
        modelAndView.addObject("string1", s1);
        modelAndView.addObject("string2", s2);
        modelAndView.addObject("string3", s3);
        */

//        for (StringGeneratorService item : list) {
//            System.out.println(item.generateString(size));
//        }
//
//        list.forEach(e -> System.out.println(e.generateString(size)));

        //druga metoda: tworze liste wynikowa. Jej wartoscia jest wynik strumieniapo liscie beanow onazwie "list"
        //w strumieniu przechodze po tej liscie, generuje stringa i zapisuje go do listy
        List<String> outList = list.stream()
                .map(e -> e.generateString(size))
                .collect(Collectors.toList());

        //dla kontroli wyswietlam liste w konsoli
        outList.forEach(e -> System.out.println(e));

        //przekazuje liste do html
        modelAndView.addObject("outList", outList);

        return modelAndView;
    }

    @RequestMapping(path = "/one-random-letter-string")
    public ModelAndView randomLetters() {
        ModelAndView modelAndView = new ModelAndView("oneRandomLetterString");

        int size = randomGeneratorService.generateRandom(); //spring dostarcza ten obiekt ze swojego rejestru

        String s1 = lettersStringGeneratorService.generateString(size);

        System.out.println(s1);

        //przekazanie zmiennej do html
        modelAndView.addObject("string1", s1);

        return modelAndView;
    }

}
