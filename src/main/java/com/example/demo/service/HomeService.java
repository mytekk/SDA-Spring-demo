package com.example.demo.service;

import org.springframework.stereotype.Component;

/**
 * Created by RENT on 2017-08-09.
 */

//@Component //rejestrujemy naszą klasę: spring utworzy obiekt tej klasy i wrzuci go sobie do swojego rejestru (kontekstu)
    //adnotacja komponent tworzy beana
public class HomeService {

    private String message;

    public HomeService(String message) {
        this.message = message;
    }

    public void doSomething() {
        System.out.println(message);
    }

}
