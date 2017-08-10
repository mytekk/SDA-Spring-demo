package com.example.demo.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by RENT on 2017-08-10.
 */

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SomeRequestService {

    private String message;

    public SomeRequestService() {
        this.message = RandomStringUtils.random(10, true, false);
        System.out.println("Generated value in contructor: " + message);
    }

    public void doSomething() {
        System.out.println("My value: " + message);
    }

}
