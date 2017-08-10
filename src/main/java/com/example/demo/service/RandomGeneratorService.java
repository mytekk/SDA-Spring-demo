package com.example.demo.service;

import java.util.Random;

/**
 * Created by RENT on 2017-08-09.
 */

//brak adnotacji, bo obiekt tej klasy zarejestruję beanem a nie adnotacja komponent
public class RandomGeneratorService {

    private Random random;
    private int bound;
    private int shift;

    public RandomGeneratorService(Random random) {
        this.random = random;
    }

    public int generateRandom() {
        return random.nextInt(bound) + shift;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
}
