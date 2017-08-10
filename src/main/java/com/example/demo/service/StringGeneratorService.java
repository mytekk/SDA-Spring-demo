package com.example.demo.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by RENT on 2017-08-10.
 */
public class StringGeneratorService {

    private boolean letters;

    private boolean numbers;

    public void setLetters(boolean letters) {
        this.letters = letters;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public String generateString(int size) {
        return RandomStringUtils.random(size, letters, numbers).toString();
    }

}
