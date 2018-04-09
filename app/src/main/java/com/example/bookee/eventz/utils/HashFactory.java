package com.example.bookee.eventz.utils;

import java.util.HashMap;

public class HashFactory {

    public static HashMap<String,String> create() {

        return new HashMap<>(50);
    }
}
