package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new M1());
        injector.getInstance(S1.class);
    }
}
