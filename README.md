Guice does not detect dependency cycles that involve multibinders.

E.g. for the following chain of dependencies Guice does not detect the dependency cycle:
`S1 -> Multibinder<C1> -> C1Impl -> S1`. This leads to a `ProvisionException` encapsulating `java.lang.NullPointerException`.

```java
public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new M1());
        injector.getInstance(S1.class);
    }
}

class S1 {}
interface C1 {}

class M1 extends AbstractModule {
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

class S1Provider implements Provider<S1> {
    @Inject
    private Set<C1> c1s;

    @Override
    public S1 get() {
        c1s.size(); // <--- triggers java.lang.NullPointerException
        return new S1();
    }
}
```
