package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.multibindings.Multibinder;

public class M1 extends AbstractModule {

    @Override
    public void configure() {

        binder().bind(S1.class).toProvider(S1Provider.class);

        Multibinder.newSetBinder(binder(), C1.class).addBinding().to(C1Impl.class);
    }

    public static class C1Impl implements C1 {

        @Inject
        private S1 s1; // intentional dependency cycle: C1Impl -> S1 -> C1
    }
}
