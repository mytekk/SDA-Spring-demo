package com.example.demo.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by RENT on 2017-08-10.
 */

@Component
@Scope("prototype")
//@Scope("singleton")
public class SampleService {

    private String message;

    public SampleService() {
        this.message = RandomStringUtils.random(10, true, false);
        System.out.println("Generated value: " + message);
    }

    public void action() {
        System.out.println(message);
    }
}
