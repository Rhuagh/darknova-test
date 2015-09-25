package se.darknova.template.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.netflix.archaius.guice.ArchaiusModule;
import se.darknova.template.api.Test;
import se.darknova.template.client.api.TestClientModule;

import java.util.Objects;

/**
 * @author seamonr@gmail.com
 */
public class Main {


    public static void main(String[] args) {
        Injector injector = Guice.createInjector(Stage.DEVELOPMENT,
                                                 new ArchaiusModule(),
                                                 new TestClientModule());
        for(int i = 0; i < 10; i++) {
            Test test = injector.getInstance(Test.class);
            System.out.println(System.identityHashCode(test));
            System.out.println(test.getTestMessage());
        }
    }
}
