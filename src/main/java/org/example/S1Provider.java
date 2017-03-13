package org.example;

import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.Set;

public class S1Provider implements Provider<S1> {

    @Inject
    private Set<C1> c1s;

    @Override
    public S1 get() {
        c1s.size();
        return new S1();
    }
}
